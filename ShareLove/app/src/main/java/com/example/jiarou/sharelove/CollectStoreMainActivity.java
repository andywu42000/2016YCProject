package com.example.jiarou.sharelove;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chiayi on 16/8/17.
 */
public class CollectStoreMainActivity extends AppCompatActivity {

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
}