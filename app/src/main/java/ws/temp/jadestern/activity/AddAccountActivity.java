package ws.temp.jadestern.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import twitter4j.auth.AccessToken;
import ws.temp.jadestern.BuildConfig;
import ws.temp.jadestern.R;
import ws.temp.jadestern.model.Account;

public class AddAccountActivity extends AppCompatActivity {
    private static final String TAG = AddAccountActivity.class.getSimpleName();

    Account account;

    private OpenType type;

    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.input_pin_base)
    View input_pin_base;

    @Bind(R.id.input_pin)
    EditText input_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        type = (OpenType) intent.getSerializableExtra("OpenType");

        account = new Account(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET);

        switch (type) {
            case ADD_TWITTER:
                input_pin_base.setVisibility(View.GONE);
                account.getAuthorizationURL(this);
                break;
            case RETURN_TWITTER:
                input_pin_base.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick(R.id.submit_pin)
    public void onClickPinButton() {
        String pin = input_pin.getText().toString();
        account.setOnSuccessAuthNewAccountListener(new Account.OnSuccessAuthNewAccountListener() {
            @Override
            public void onSuccessAuthNewAccount(String consumerKey, String consumerSecret, AccessToken accessToken) {
                if (accessToken == null) {
                    Snackbar.make(coordinatorLayout, "fail the auth", Snackbar.LENGTH_LONG).show();
                    return;
                }

                Snackbar.make(coordinatorLayout, "account auth was successful!: " + accessToken.getScreenName(), Snackbar.LENGTH_LONG).show();
            }
        });
        account.getAccessToken(pin);
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
        savedInstanceState.putSerializable("OpenType", type);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        type = (OpenType) savedInstanceState.getSerializable("OpenType");
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
