package ws.temp.jadestern;

import android.app.Application;

import ws.temp.jadestern.model.Model;
import ws.temp.jadestern.model.Util;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Util(this);
        new Model(this);
    }
}
