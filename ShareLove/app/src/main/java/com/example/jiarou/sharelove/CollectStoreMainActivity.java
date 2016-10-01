package com.example.jiarou.sharelove;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.peter.focus.*;

/**
 * Created by chiayi on 16/8/17.
 */
public class CollectStoreMainActivity extends AppCompatActivity  implements CollectStoreFragment.OnFocusSelected
        , com.example.peter.focus.VendedInfoFragment.OnCommentSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_store);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, CollectStoreFragment.newInstance(), "CollectStore")
                    .commit();
        }
    }

    @Override
    public void OnFocusSelected(String vendorTitle) {
        final com.example.peter.focus.VendedInfoFragment infoFragment =
                com.example.peter.focus.VendedInfoFragment.newInstance(vendorTitle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, infoFragment, "VendorInfo")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCommentSelected(String vendorTitle) {
        final CommentFragment commentFragment = CommentFragment.newInstance(vendorTitle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, commentFragment, "Comment")
                .addToBackStack(null)
                .commit();
    }
}