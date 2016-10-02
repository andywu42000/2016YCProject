package com.example.jiarou.sharelove;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.peter.focus.CommentFragment;
import com.example.peter.focus.VendedInfoFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class IndexActivity extends FragmentActivity implements OnMapReadyCallback
        , VenderListFragment.OnFragmentInteractionListener, VendedInfoFragment.OnCommentSelected, VendedInfoFragment.OnNavigationSelected {

    String zip_areas;
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
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onCommentSelected(String vendorTitle) {
        final CommentFragment commentFragment = CommentFragment.newInstance(vendorTitle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.venderlist, commentFragment, "Comment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNavigationSelected(String vendorTitle) {
        final VenderListFragment venderListFragment = VenderListFragment.newInstance();

        venderListFragment.getNavigation(vendorTitle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.venderlist, venderListFragment, "Venderlist2")
                .addToBackStack(null)
                .commit();
    }
}
