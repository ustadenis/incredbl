package org.denis.incredbl.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.denis.incredbl.Friend;
import org.denis.incredbl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendInfoFragment extends Fragment {

    private static final String TAG = FriendInfoFragment.class.getName();

    private ImageView mAvatarImageView;

    private TextView mNameTextView;

    private TextView mBirthDateTextView;

    private TextView mLocationTextView;

    private AppCompatActivity mActivity;

    private Friend mFriend;

    public FriendInfoFragment() {
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
        return inflater.inflate(R.layout.fragment_friend_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAvatarImageView = (ImageView) view.findViewById(R.id.big_avatar_image);
        mNameTextView = (TextView) view.findViewById(R.id.name_text);
        mBirthDateTextView = (TextView) view.findViewById(R.id.birthdate_text);
        mLocationTextView = (TextView) view.findViewById(R.id.location_text);

        if(mFriend != null) {
            mNameTextView.setText(mFriend.getName());
            mBirthDateTextView.setText(mFriend.getBirthDate());
            mLocationTextView.setText(mFriend.getLocation());

            Picasso.with(getContext())
                    .load(mFriend.getUser().photo_200)
                    .into(mAvatarImageView);
        }
    }

    public void setFriend(Friend friend) { mFriend = friend; }
}
