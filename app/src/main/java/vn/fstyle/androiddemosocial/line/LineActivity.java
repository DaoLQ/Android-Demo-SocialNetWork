package vn.fstyle.androiddemosocial.line;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import jp.line.android.sdk.LineSdkContextManager;
import jp.line.android.sdk.api.ApiClient;
import jp.line.android.sdk.api.ApiRequestFuture;
import jp.line.android.sdk.api.ApiRequestFutureListener;
import jp.line.android.sdk.login.LineAuthManager;
import jp.line.android.sdk.login.LineLoginFuture;
import jp.line.android.sdk.login.LineLoginFutureListener;
import jp.line.android.sdk.model.Profile;
import vn.fstyle.androiddemosocial.R;

public class LineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btnLineLogin:
                loginByLine();
                break;
            case R.id.btnLineGetUserProfile:
                if (!LineUtil.isLoggedLine()) {
                    return;
                }
                getUserProfileLine();
                break;
            case R.id.btnLineLogout:
                if (!LineUtil.isLoggedLine()) {
                    return;
                }
                logoutByLine();
                break;
        }
    }

    private void loginByLine() {
        LineAuthManager authManager = LineSdkContextManager.getSdkContext().getAuthManager();
        authManager.login(this).addFutureListener(new LineLoginFutureListener() {
            @Override
            public void loginComplete(LineLoginFuture lineLoginFuture) {
                switch (lineLoginFuture.getProgress()) {
                    case SUCCESS:
                        Log.d("", "DebugLog loginComplete SUCCESS");
                        break;
                    case CANCELED:
                        Log.d("", "DebugLog loginComplete CANCELED");
                        break;
                }
            }
        });
    }

    private void getUserProfileLine() {
        ApiClient apiClient = LineSdkContextManager.getSdkContext().getApiClient();
        apiClient.getMyProfile(new ApiRequestFutureListener<Profile>() {
            @Override
            public void requestComplete(ApiRequestFuture<Profile> apiRequestFuture) {
                Profile profile = apiRequestFuture.getResponseObject();
                Log.d("", "DebugLog requestComplete ");
            }
        });
    }

    private void logoutByLine() {
        LineSdkContextManager.getSdkContext().getAuthManager().logout();
    }
}
