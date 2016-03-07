package ws.temp.jadestern.model;

import android.content.Context;
import android.support.annotation.StringRes;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import lombok.Getter;
import lombok.Setter;

public class AnalLogger {
    @Getter
    @Setter
    private static Context context;

    @Getter
    @Setter
    private static Tracker tracker;

    public AnalLogger() {
    }

    public AnalLogger(Context context, Tracker tracker) {
        AnalLogger.context = context;
        AnalLogger.tracker = tracker;
    }

    public static void trackScreenView(@StringRes int resStr) {
        trackScreenView(context.getString(resStr));
    }

    public static void trackScreenView(String name) {
        tracker.setScreenName(name);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static class Account {
        public static final String CATEGORY = "account";

        public static final String ACTION_ADD_ACCOUNT = "add_account";

        public static final String ACTION_DEL_ACCOUNT = "del_account";

        public static void addAccount(long userId) {
            AnalLogger
                .getTracker()
                .send(
                    new HitBuilders.EventBuilder()
                        .setCategory(CATEGORY)
                        .setAction(ACTION_ADD_ACCOUNT)
                        .setValue(userId)
                        .build()
                );
        }
    }
}
