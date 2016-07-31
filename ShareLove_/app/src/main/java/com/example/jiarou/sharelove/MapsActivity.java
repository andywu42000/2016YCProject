package com.example.jiarou.sharelove;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String FIREBASE_URL="https://vendor-5acbc.firebaseio.com/comment" +
            "      ";
    private Firebase firebaseRef;
    private GoogleMap mMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        firebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.child("users").getChildren()) {
                    String rightLocation = child.child("location_right").getValue().toString();
                    String leftLocation = child.child("location_left").getValue().toString();

                    double location_left = Double.parseDouble(leftLocation);
                    double location_right = Double.parseDouble(rightLocation);
                    String party_title = child.child("party/party_title").getValue().toString();
                    LatLng cod = new LatLng(location_left, location_right);
                    googleMap.addMarker(new MarkerOptions().position(cod).title(party_title));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            private void positionCamera(LatLng origin, LatLng dest){
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(origin); if(dest != null) builder.include(dest);
                LatLngBounds bounds = builder.build();
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 30)); }
        });
    }
}
