package ws.temp.jadestern.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import lombok.Setter;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import ws.temp.jadestern.activity.AddAccountActivity;

public class Account {
    private static final String TAG = Account.class.getSimpleName();

    public Twitter twitter;
    public RequestToken requestToken;
    public AccessToken accessToken;

    public String consumerKey;
    public String consumerSecret;

    @Setter
    public OnSuccessAuthNewAccountListener onSuccessAuthNewAccountListener;

    public Account(String consumer_key, String consumer_secret) {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(consumer_key, consumer_secret);
        this.consumerKey = consumer_key;
        this.consumerSecret = consumer_secret;
    }

    public static void startAddAccountActivity(Context context) {
        Intent intent = new Intent(context, AddAccountActivity.class);
        intent.putExtra("OpenType", AddAccountActivity.OpenType.ADD_TWITTER);
        context.startActivity(intent);
    }

    public void getAuthorizationURL(final Activity context) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    requestToken = twitter.getOAuthRequestToken();
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                if (requestToken != null) {
                    return requestToken.getAuthorizationURL();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String url) {
                super.onPostExecute(url);
                if (url != null) {
                    openCustumtab(url, context);
                } else
                    Log.e(TAG, "error");
            }
        }.execute();
    }

    public void getAccessToken(final String token) {
        new AsyncTask<Void, Void, AccessToken>() {

            @Override
            protected AccessToken doInBackground(Void... params) {
                try {
                    accessToken = twitter.getOAuthAccessToken(requestToken, token);
                    return accessToken;
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(AccessToken accessToken) {
                super.onPostExecute(accessToken);
                if (onSuccessAuthNewAccountListener != null)
                    onSuccessAuthNewAccountListener.onSuccessAuthNewAccount(consumerKey, consumerSecret, accessToken);
            }
        }.execute();
    }

    public void openCustumtab(String url, Activity context) {
        Util.startChromeInApp(url, context);
    }

    public interface OnSuccessAuthNewAccountListener {
        void onSuccessAuthNewAccount(String consumerKey, String consumerSecret, AccessToken accessToken);
    }

}
