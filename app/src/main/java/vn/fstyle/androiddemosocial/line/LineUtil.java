package vn.fstyle.androiddemosocial.line;

import jp.line.android.sdk.LineSdkContextManager;
import jp.line.android.sdk.login.LineAuthManager;

/**
 * LineUtil.
 *
 * @author DaoLQ
 */
public class LineUtil {
    public static boolean isLoggedLine() {
        LineAuthManager authManager = LineSdkContextManager.getSdkContext().getAuthManager();
        return authManager != null && authManager.getAccessToken() != null;
    }
}
