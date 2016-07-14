package vn.fstyle.androiddemosocial.facebook;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;

import vn.fstyle.androiddemosocial.facebook.model.UserProfile;

/**
 * SimpleFacebookCallback.
 *
 * @author DaoLQ
 */
public abstract class CustomFacebookCallback implements FacebookCallback<LoginResult> {

    private UserProfile mUserProfile;
    private ProfileTracker mProfileTracker;

    public abstract void onSuccess(UserProfile userProfile);

    @Override
    public void onSuccess(LoginResult loginResult) {
        Profile profile = Profile.getCurrentProfile();
        if (profile == null) {
            mProfileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                    UserProfile userProfile = UserProfile.builder()
                            .id(profile2.getId())
                            .name(profile2.getName())
                            .uriProfile(profile2.getProfilePictureUri(200, 200))
                            .build();
                    onSuccess(userProfile);
                    mProfileTracker.stopTracking();
                }
            };
            return;
        }
        UserProfile userProfile = UserProfile.builder()
                .id(profile.getId())
                .name(profile.getName())
                .uriProfile(profile.getProfilePictureUri(200, 200))
                .build();
        onSuccess(userProfile);
    }

    @Override
    public void onCancel() {
        // No-op
    }

    @Override
    public void onError(FacebookException error) {
        // No-op
    }
}
