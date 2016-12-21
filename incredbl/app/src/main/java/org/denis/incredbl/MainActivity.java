package org.denis.incredbl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import org.denis.incredbl.fragments.FriendInfoFragment;
import org.denis.incredbl.fragments.FriendsFragment;
import org.denis.incredbl.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getName();

    private FragmentManager mFragmentManager;

    private LoginFragment mLoginFragment = new LoginFragment();
    private FriendsFragment mFriendsFragment = new FriendsFragment();
    private FriendInfoFragment mFriendInfoFragment = new FriendInfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize fields
        mFragmentManager = getSupportFragmentManager();

        // If already logged in - go to friends list, else login fragment
        if(VKSdk.isLoggedIn()) {
            Log.d(TAG, "Already logged in.");
            mFragmentManager.beginTransaction().replace(R.id.fragment, mFriendsFragment).commit();
        } else {
            Log.d(TAG, "Need to login.");
            mFragmentManager.beginTransaction().replace(R.id.fragment, mLoginFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Login result
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Login successfully
                Log.d(TAG, "Logged in.");
                mFragmentManager.beginTransaction().replace(R.id.fragment, mFriendsFragment).commit();
            }

            @Override
            public void onError(VKError error) {
                // Login failed
                Log.d(TAG, "Login failed.");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startFriendFragment(Friend friend) {
        mFriendInfoFragment.setFriend(friend);
        mFragmentManager.beginTransaction().replace(R.id.fragment, mFriendInfoFragment)
                .addToBackStack(getResources().getString(R.string.backstack))
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
