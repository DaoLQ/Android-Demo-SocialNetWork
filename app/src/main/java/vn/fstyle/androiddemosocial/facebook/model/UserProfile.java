package vn.fstyle.androiddemosocial.facebook.model;

import android.net.Uri;

import lombok.Getter;
import lombok.experimental.Builder;

/**
 * Profile.
 *
 * @author DaoLQ
 */
@Builder
@Getter
public class UserProfile {
    private String id;
    private String name;
    private Uri uriProfile;
}
