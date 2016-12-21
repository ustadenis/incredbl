package org.denis.incredbl;

import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.net.URL;

/**
 * Created by ustad on 20.12.2016.
 */

public class Friend {

    private static final String TAG = Friend.class.getName();

    private String mFriendID;

    private VKApiUserFull mUser;
    private String mName;
    private String mBirthDate;
    private String mLocation;

    public Friend(String friendID, VKApiUserFull user, String name, String birthdate, String location) {
        mFriendID = friendID;
        mUser = user;
        mName = name;
        mBirthDate = birthdate;
        mLocation = location;
    }

    public static Friend friendFromVKApiUserFull(VKApiUserFull vkFriend) {

        String userID = vkFriend.getId() + "";
        String name = "";
        String birthdate = "";
        String location = "";

        if (vkFriend.first_name != null && vkFriend.last_name != null) {
            name = vkFriend.first_name + " " + vkFriend.last_name;
        }
        if (vkFriend.bdate != null) {
            birthdate = vkFriend.bdate;
        }
        if (vkFriend.city != null) {
            location = vkFriend.city.title;
        }

        Friend friend = new Friend(userID, vkFriend, name, birthdate, location);

        return friend;
    }

    public String getName() { return mName; }

    public String getBirthDate() { return mBirthDate; }

    public String getLocation() { return mLocation; }

    public VKApiUserFull getUser() { return mUser; }
}
