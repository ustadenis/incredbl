package org.denis.incredbl;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ustad on 20.12.2016.
 */
public class FriendsAdapter extends ArrayAdapter {

    public FriendsAdapter(Context context, ArrayList<Friend> friends) {
        super(context, R.layout.friend_item, friends);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friend friend = (Friend) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.friend_item, null);
        }

        ((TextView) convertView.findViewById(R.id.friend_name_text))
                .setText(friend.getName());
        ImageView iv = (ImageView) convertView.findViewById(R.id.friend_avatar_image);

        Picasso.with(getContext())
                .load(friend.getUser().photo_200)
                .into(iv);

        return convertView;
    }
}
