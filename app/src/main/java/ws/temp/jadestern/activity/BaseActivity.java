package ws.temp.jadestern.activity;

import android.support.v7.app.AppCompatActivity;
import ws.temp.jadestern.MainApplication;

public abstract class BaseActivity extends AppCompatActivity {
    public MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }
}
