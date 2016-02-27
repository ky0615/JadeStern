package ws.temp.jadestern.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ws.temp.jadestern.R;
import ws.temp.jadestern.model.Account;

public class PrefaceActivity extends AppCompatActivity {
    private static final String TAG = PrefaceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 16/02/27 add splash
        setContentView(R.layout.activity_preface);

        if (accountLength() < 1) {
            // start add user activity
            Account.startAddAccountActivity(this);
        }

        finish();
    }

    public int accountLength() {
        return 0;
    }
}
