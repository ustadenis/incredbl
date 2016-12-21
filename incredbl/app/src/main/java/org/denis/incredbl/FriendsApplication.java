package org.denis.incredbl;

import android.app.Application;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * Created by ustad on 20.12.2016.
 */
public class FriendsApplication extends Application {

    private static final String TAG = FriendsApplication.class.getName();

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
                Log.e(TAG, "VKAccessToken is invalid");
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize vk sdk
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
        Log.d(TAG, "VKSdk is initialized.");
    }
}
