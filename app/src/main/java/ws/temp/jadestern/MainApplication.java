package ws.temp.jadestern;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import io.fabric.sdk.android.Fabric;
import ws.temp.jadestern.model.AnalLogger;
import ws.temp.jadestern.model.Model;
import ws.temp.jadestern.model.Util;

public class MainApplication extends Application {
    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        new Util(this);
        new Model(this);
        new AnalLogger(this, getDefaultTracker());
    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

            if (BuildConfig.DEBUG) {
                analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            }

            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

}
