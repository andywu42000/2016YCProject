package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.peter.focus.*;
import com.example.peter.focus.VendedInfoFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class IndexActivity extends FragmentActivity implements OnMapReadyCallback,VenderListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.venderlist, VenderListFragment.newInstance(), "venderlist")
                    .commit();
        }


    }

    @Override
    public void onFocusSelected(String vendorTitle) {

        final VendedInfoFragment infoFragment =
                VendedInfoFragment.newInstance(vendorTitle);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.venderlist, infoFragment, "VendorInfo")
                .addToBackStack(null)
                .commit();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.venderlist);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
