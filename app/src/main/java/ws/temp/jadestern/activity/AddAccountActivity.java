package ws.temp.jadestern.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ws.temp.jadestern.BuildConfig;
import ws.temp.jadestern.R;
import ws.temp.jadestern.model.AccountModel;
import ws.temp.jadestern.model.AnalLogger;

public class AddAccountActivity extends BaseActivity {
    private static final String TAG = AddAccountActivity.class.getSimpleName();

    private static final String OPEN_TYPE = "OpenType";

    AccountModel accountModel;

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.input_pin_base)
    View input_pin_base;

    @Bind(R.id.input_pin)
    EditText input_pin;

    private OpenType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        type = (OpenType) intent.getSerializableExtra(OPEN_TYPE);

        accountModel = new AccountModel(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET);

        switch (type) {
            case ADD_TWITTER:
                input_pin_base.setVisibility(View.GONE);
                accountModel.getAuthorizationURL(this);
                break;
            case RETURN_TWITTER:
                input_pin_base.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick(R.id.submit_pin)
    public void onClickPinButton() {
        String pin = input_pin.getText().toString();
        accountModel.setOnSuccessAuthNewAccountListener((consumerKey, consumerSecret, accessToken) -> {
            if (accessToken == null) {
                Snackbar.make(coordinatorLayout, "fail the auth", Snackbar.LENGTH_LONG).show();
                // TODO: 16/02/28 open dialog: question to reacquire the token.
                return;
            }
            Toast.makeText(this, "account auth was successful!: " + accessToken.getScreenName(), Toast.LENGTH_LONG).show();
            accountModel.addAccount(accessToken);
            AnalLogger.Account.addAccount(accessToken.getUserId());
            returnToMainActivity();
        });
        accountModel.getAccessToken(pin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnalLogger.trackScreenView(R.string.activity_add_account);
    }

    @Override
    protected void onPause() {
        super.onPause();
        input_pin_base.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(OPEN_TYPE, type);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        type = (OpenType) savedInstanceState.getSerializable(OPEN_TYPE);
    }

    public void returnToMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public enum OpenType {
        ADD_TWITTER(1),
        RETURN_TWITTER(2);

        private final int id;

        OpenType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
