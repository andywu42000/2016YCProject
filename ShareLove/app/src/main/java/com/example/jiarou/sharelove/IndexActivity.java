package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.focus.*;
import com.example.peter.focus.VendedInfoFragment;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class IndexActivity extends FragmentActivity implements OnMapReadyCallback,VenderListFragment.OnFragmentInteractionListener {

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
    /**
    Intent intent = getIntent();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {//resultCode是剛剛妳A切換到B時設的resultCode
            case 2://當B傳回來的Intent的requestCode 等於當初A傳出去的話

                zip_areas =  data.getExtras().getString("name");

                Bundle bundle=new Bundle();
                bundle.putString(zip_areas, "From Activity");
                //set Fragmentclass Arguments
                VenderListFragment fragobj=new  VenderListFragment();
                fragobj.setArguments(bundle);


                Toast.makeText(IndexActivity.this, zip_areas, Toast.LENGTH_LONG).show();
                /**
                 textview2 = (TextView) this.findViewById(R.id.textView2);
                 textview2.setText(zip_areas);

                 zip_number =zip_areas.substring(1,4);
                 Geocoder geoCoder = new Geocoder(MapsActivity.this, Locale.getDefault());

                 List<Address> addressLocation = null;
                 try {
                 addressLocation = geoCoder.getFromLocationName(zip_areas, 1);
                 } catch (IOException e) {
                 e.printStackTrace();
                 }
                 double latitude = addressLocation.get(0).getLatitude();
                 double longitude = addressLocation.get(0).getLongitude();

                 LatLng area_type = new LatLng(latitude, longitude);
                 mMap.moveCamera(CameraUpdateFactory.newLatLng(area_type));
                 mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

                 ListView list = (ListView) findViewById(R.id.listView);
                 final ArrayAdapter<String> adapter =
                 new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1,
                 android.R.id.text1);
                 list.setAdapter(adapter);

                 final Firebase myFirebaseRef = new Firebase("https://vendor-5acbc.firebaseio.com/Vendors");


                 ChildEventListener childEventListener = myFirebaseRef.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.child("Location/ZIP").getValue().toString().equals(zip_number)) {
                adapter.add((String) dataSnapshot.child("Information/Name").getValue());
                int store_number;
                store_number = ((String) dataSnapshot.child("Information/Name").getValue()).length();
                if (store_number > 0) {
                Toast.makeText(MapsActivity.this, "目前有" + store_number + "筆", Toast.LENGTH_LONG).show();
                } else {
                Toast.makeText(MapsActivity.this, "您沒有選擇任何項目" + store_number, Toast.LENGTH_LONG).show();
                }
                }

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
                });





                break;

        }
    } **/

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
