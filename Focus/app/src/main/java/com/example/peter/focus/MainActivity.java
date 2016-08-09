package com.example.peter.focus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FocusFragment.OnFocusSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.basic_layout, FocusFragment.newInstance(), "Focus")
                    .commit();
        }
    }

    @Override
    public void onFocusSelected(String vendorTitle, String vendorURL, String vendorPhone, String timeRemark,
                                String monTime, String tueTime, String wedTime, String thuTime, String friTime,
                                String satTime, String sunTime, String vendorAddress, String vendorStory) {

        final VenderInfoFragment infoFragment =
                VenderInfoFragment.newInstance(vendorTitle, vendorURL, vendorPhone, timeRemark, monTime,
                        tueTime, wedTime, thuTime, friTime, satTime, sunTime, vendorAddress, vendorStory);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.basic_layout, infoFragment, "VendorInfo")
                .addToBackStack(null)
                .commit();

    }
}
