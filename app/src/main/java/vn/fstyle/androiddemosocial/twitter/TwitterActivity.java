package vn.fstyle.androiddemosocial.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import vn.fstyle.androiddemosocial.R;

public class TwitterActivity extends AppCompatActivity {

    private TwitterAuthClient mTwitterAuthClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        mTwitterAuthClient = new TwitterAuthClient();
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btnLoginTwitter:
                logInTwitter();
                break;
            case R.id.btnGetUserProfileTwitter:
                if (!TwitterUntil.isLoggedTwitter()) {
                    return;
                }
                getTwitterUserProfile();
                break;
            case R.id.btnLogoutTwitter:
                logOutTwitter();
                break;
        }
    }

    private void logInTwitter() {
        mTwitterAuthClient.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                long userId = result.data.getUserId();
                String screenName = result.data.getUserName();
                Log.d("", "DebugLog success ");
            }

            @Override
            public void failure(TwitterException e) {
                Log.d("", "DebugLog failure : " + e.getMessage());
            }
        });
    }

    private void getTwitterUserProfile() {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        twitterApiClient.getAccountService().verifyCredentials(false, false, new Callback<com.twitter.sdk.android.core.models.User>() {
            @Override
            public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                Log.d("", "DebugLog success: Name: " + result.data.name);
                Log.d("", "DebugLog success: Image Url: " + result.data.profileImageUrl);
            }

            @Override
            public void failure(TwitterException e) {
                Log.d("", "DebugLog failure : " + e.getMessage());
            }
        });
    }

    private void logOutTwitter() {
        // Clear cookies
        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookie();
        // Clear session
        TwitterCore.getInstance().getAppSessionManager().clearActiveSession();
        // Logout
        TwitterCore.getInstance().logOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }
}
