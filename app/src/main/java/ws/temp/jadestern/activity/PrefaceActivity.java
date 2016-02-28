package ws.temp.jadestern.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ws.temp.jadestern.R;
import ws.temp.jadestern.model.AccountModel;

public class PrefaceActivity extends AppCompatActivity {
    private static final String TAG = PrefaceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 16/02/27 add splash
        setContentView(R.layout.activity_preface);
        AccountModel accountModel = new AccountModel();

        if (accountModel.getAccountLength() < 1) {
            // start add user activity
            AccountModel.startAddAccountActivity(this);
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
