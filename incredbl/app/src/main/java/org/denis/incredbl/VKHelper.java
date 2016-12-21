package org.denis.incredbl;

import android.util.Log;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ustad on 21.12.2016.
 */
public class VKHelper {

    private static final String TAG = VKHelper.class.getName();

    private static VKHelper mInstance;

    private VKList<VKApiUserFull> mFriendsVKList;

    private VKHelper() {}

    public static VKHelper getInstance() {
        if (mInstance == null) {
            mInstance = new VKHelper();
        }
        return mInstance;
    }

    public void getFriendsList(final GetFriendsCallBack callback) {
        if(mFriendsVKList == null) {
            VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FRIENDS_ONLY));

            request.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    super.onComplete(response);
                    Log.d(TAG, "Successfully response: " + response.toString());

                    try {
                        final JSONObject jsonResponse = response.json.getJSONObject("response");
                        JSONArray items = jsonResponse.getJSONArray("items");

                        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, items,
                                VKApiConst.FIELDS, "photo_200, first_name, last_name, bdate, city"));

                        request.executeWithListener(new VKRequest.VKRequestListener() {
                            @Override
                            public void onError(VKError error) {
                                super.onError(error);
                                Log.e(TAG, error.toString());
                            }

                            @Override
                            public void onComplete(VKResponse response) {
                                super.onComplete(response);
                                Log.d(TAG, "Friend successfully executed.");

                                mFriendsVKList = (VKList<VKApiUserFull>) response.parsedModel;

                                callback.onGetFriendsSuccess(mFriendsVKList);
                            }
                        });
                    } catch (Exception e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
            });
        } else {
            callback.onGetFriendsSuccess(mFriendsVKList);
        }
    }

    public interface GetFriendsCallBack {
        void onGetFriendsSuccess(VKList<VKApiUserFull> friends);
    }
}
