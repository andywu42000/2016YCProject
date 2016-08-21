package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.peter.focus.VendedInfoFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class IndexActivity extends AppCompatActivity implements OnMapReadyCallback,VenderListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction fragmentTrans = fragmentMgr.beginTransaction();
        VenderListFragment venderListFragment = new VenderListFragment();
        fragmentTrans.add(R.id.venderlist,venderListFragment, "venderlist");
        fragmentTrans.commit();













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
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
