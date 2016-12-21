package org.denis.incredbl.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.denis.incredbl.Friend;
import org.denis.incredbl.FriendsAdapter;
import org.denis.incredbl.MainActivity;
import org.denis.incredbl.R;
import org.denis.incredbl.VKHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getName();

    private AppCompatActivity mActivity;

    private ListView mFriendsList;

    private ArrayList<Friend> mFriendsArrayList = new ArrayList<>();

    private FriendsAdapter mFriendsAdapter;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (AppCompatActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFriendsList = (ListView) view.findViewById(R.id.friends_list);

        if(mFriendsAdapter == null) {
            mFriendsAdapter = new FriendsAdapter(getContext(), mFriendsArrayList);

            VKHelper.getInstance().getFriendsList(new VKHelper.GetFriendsCallBack() {
                @Override
                public void onGetFriendsSuccess(VKList<VKApiUserFull> friends) {

                    for (VKApiUserFull user :
                            friends) {
                        if (user != null) {
                            mFriendsAdapter.add(Friend.friendFromVKApiUserFull(user));
                        }
                    }

                    mFriendsAdapter.notifyDataSetChanged();
                }
            });
        }

        mFriendsList.setAdapter(mFriendsAdapter);

        mFriendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mActivity != null && mActivity instanceof MainActivity) {
                    MainActivity activity = (MainActivity) mActivity;

                    activity.startFriendFragment((Friend) parent.getItemAtPosition(position));
                }
            }
        });
    }
}
