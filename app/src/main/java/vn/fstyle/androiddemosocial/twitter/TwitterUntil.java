package vn.fstyle.androiddemosocial.twitter;

import com.twitter.sdk.android.core.TwitterCore;

/**
 * TwitterUntil.
 *
 * @author DaoLQ
 */
public final class TwitterUntil {
    public static boolean isLoggedTwitter() {
        try {
            return TwitterCore.getInstance().getApiClient() != null;
        } catch (IllegalStateException e) {
            return false;
        }
    }
}
