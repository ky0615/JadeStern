package ws.temp.jadestern;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import ws.temp.jadestern.model.Model;
import ws.temp.jadestern.model.Util;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        new Util(this);
        new Model(this);
    }
}
