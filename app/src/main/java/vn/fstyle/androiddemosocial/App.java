package vn.fstyle.androiddemosocial;

import android.app.Application;

import jp.line.android.sdk.LineSdkContextManager;

/**
 * App.
 *
 * @author DaoLQ
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LineSdkContextManager.initialize(this);
    }
}
