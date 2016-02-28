package ws.temp.jadestern.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import ws.temp.jadestern.R;

public class Util {
    public static Context context;

    public static Handler handler;

    public Util() {

    }

    public Util(Context context) {
        Util.context = context;
        Util.handler = new Handler();
    }

    /**
     * 経過時間を単位を揃えて表示
     *
     * @param time         経過時間(ミリ秒)
     * @param allowFeature 未来の時間でも計算をするかどうか
     * @return 経過時間
     */
    public static String getTimeDiffText(long time, boolean allowFeature) {
        long diffTime = (System.currentTimeMillis() - time) / 1000L;
        String unit;
        String direction = "前";
        long divide;

        if (!allowFeature && diffTime < 0)
            return "最新";
        else if (diffTime < 0) {
            direction = "先";
            diffTime *= -1L;
        }

        if (diffTime < 60) {
            unit = "秒";
            divide = 1;
        } else if (diffTime < 60 * 60) {
            unit = "分";
            divide = 60;
        } else if (diffTime < 60 * 60 * 24) {
            unit = "時間";
            divide = 60 * 60;
        } else if (diffTime < 60 * 60 * 24 * 365) {
            unit = "日";
            divide = 60 * 60 * 24;
        } else {
            unit = "年";
            divide = 60 * 60 * 24 * 365;
        }

        return new StringBuilder()
                .append(diffTime / divide)
                .append(unit)
                .append(direction)
                .toString();
    }

    public static String addText(String base, String text) {
        if (text.length() == 0)
            return base;
        StringBuilder sb = new StringBuilder(base);
        if (base.length() > 0)
            sb.append("\n");
        sb.append(text);

        return sb.toString();
    }

    public static void setTimeout(final long ms, final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(ms);
                    handler.post(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void startChromeInApp(String url, Activity context) {

        final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context, R.color.primary_dark))
                .setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setCloseButtonIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.searchbar_back))
                .build();

        customTabsIntent.launchUrl(context, Uri.parse(url));
    }


}
