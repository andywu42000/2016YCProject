package com.example.jiarou.sharelove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FocusFragment.OnFocusSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.peter.focus.R.layout.focus_main);
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(com.example.peter.focus.R.id.focus_root, FocusFragment.newInstance(), "Focus")
                    .commit();
        }
    }

    @Override
    public void onFocusSelected(String vendorTitle/*, String vendorURL, String vendorPhone, String timeRemark,
                                String monTime, String tueTime, String wedTime, String thuTime, String friTime,
                                String satTime, String sunTime, String vendorAddress, String vendorStory*/) {

        final VendedInfoFragment infoFragment =
                VendedInfoFragment.newInstance(vendorTitle/*, vendorURL, vendorPhone, timeRemark, monTime,
                        tueTime, wedTime, thuTime, friTime, satTime, sunTime, vendorAddress, vendorStory*/);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(com.example.peter.focus.R.id.focus_root, infoFragment, "VendorInfo")
                .addToBackStack(null)
                .commit();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(com.example.peter.focus.R.id.focus_root);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
