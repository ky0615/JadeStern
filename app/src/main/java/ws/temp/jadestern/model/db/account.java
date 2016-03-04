package ws.temp.jadestern.model.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Account extends RealmObject {

    @PrimaryKey
    private long userId;

    @Required
    private String screenName;

    @Required
    private String consumerKey;

    @Required
    private String consumerSecret;

    @Required
    private String oauthToken;

    @Required
    private String oauthTokenSecret;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }

//    public AccessToken getAccessToken() {
//        return new AccessToken(oauthToken, oauthTokenSecret, userId);
//    }
//
//    public void setAccessToken(AccessToken accessToken) {
//        this.oauthToken = accessToken.getToken();
//        this.oauthTokenSecret = accessToken.getTokenSecret();
//
//        if (accessToken.getUserId() != -1L)
//            this.userId = accessToken.getUserId();
//    }
}
