package vn.fstyle.androiddemosocial.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.fstyle.androiddemosocial.R;
import vn.fstyle.androiddemosocial.facebook.model.Friend;
import vn.fstyle.androiddemosocial.facebook.model.FriendListResponse;
import vn.fstyle.androiddemosocial.facebook.model.UserProfile;

/**
 * MinSDK 15
 * Config Gradle
 */
public class FacebookActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;


    private List<Friend> mFriends = new ArrayList<>();
    private String paramAfter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        mCallbackManager = CallbackManager.Factory.create();
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginFacebook();
                break;
            case R.id.btnGetFriendList:
                if (!FacebookUtil.isLoggedIn()) {
                    return;
                }
                getFriendList();
                break;
        }
    }

    private void getFriendList() {
        Bundle parameters = new Bundle();
        parameters.putString("after", paramAfter);
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/taggable_friends", parameters, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                FriendListResponse friendListResponse = new Gson().fromJson(response.getRawResponse(), FriendListResponse.class);
                mFriends.addAll(friendListResponse.getFriends());
                paramAfter = friendListResponse.getPaging().getCursors().getAfter();
                Log.d("", "DebugLog onCompleted ");
            }
        }).executeAsync();
    }

    private void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_friends", "user_photos", "read_custom_friendlists"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new CustomFacebookCallback() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.d("", "DebugLog onSuccess ");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
