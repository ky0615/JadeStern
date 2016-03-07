package ws.temp.jadestern.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import lombok.Getter;
import lombok.Setter;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import ws.temp.jadestern.BuildConfig;
import ws.temp.jadestern.activity.AddAccountActivity;
import ws.temp.jadestern.model.db.Account;

public class AccountModel extends Model {
    private static final String TAG = AccountModel.class.getSimpleName();

    public Twitter twitter;

    public RequestToken requestToken;

    public AccessToken accessToken;

    @Setter
    public OnSuccessAuthNewAccountListener onSuccessAuthNewAccountListener;

    @Getter
    private String consumerKey;

    @Getter
    private String consumerSecret;

    public AccountModel() {
        this(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET);
    }

    public AccountModel(String consumer_key, String consumer_secret) {
        super();

        twitter = new TwitterFactory().getInstance();
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
                    Util.startChromeInApp(url, context);
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

    public boolean isAddedAccount(long userId) {
        if (userId != -1L)
            throw new IllegalArgumentException("User id is not defined.");

        Account account = realm.where(Account.class)
            .equalTo("userId", userId)
            .findFirst();
        return account != null;
    }

    public void addAccount(long userId, String screenName, String token, String tokenSecret) {
        Account account = new Account();
        account.setUserId(userId);
        account.setScreenName(screenName);
        account.setConsumerKey(consumerKey);
        account.setConsumerSecret(consumerSecret);
        account.setOauthToken(token);
        account.setOauthTokenSecret(tokenSecret);

        addAccount(account);
    }

    public void addAccount(AccessToken accessToken) {
        addAccount(accessToken.getUserId(), accessToken.getScreenName(), accessToken.getToken(), accessToken.getTokenSecret());
    }

    public void addAccount(Account account) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(account);
        realm.commitTransaction();
    }

    public int getAccountLength() {
        return realm.where(Account.class)
            .findAll().size();
    }

    public interface OnSuccessAuthNewAccountListener {
        void onSuccessAuthNewAccount(String consumerKey, String consumerSecret, AccessToken accessToken);
    }

}
