package com.example.jiarou.sharelove;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Button mButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);
        final Firebase myFirebaseRef = new Firebase("https://vendor-5acbc.firebaseio.com/Vendors");
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);

        mapFragment.getMapAsync(this);



        mButton = (Button) findViewById(R.id.search_button);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MapsActivity.this, SearchActivity.class);
                startActivity(intent);

            }

        });

        ListView list = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1);
        list.setAdapter(adapter);
        ChildEventListener childEventListener = myFirebaseRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.child("Information/Name").getValue());

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


    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nccu = new LatLng(24.9849998, 121.5761281);
        mMap.addMarker(new MarkerOptions().position(nccu).title("Marker in NCCU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nccu));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        final Firebase myFirebaseRef = new Firebase("https://vendor-5acbc.firebaseio.com/Vendors");
        ChildEventListener childEventListener = myFirebaseRef.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    String Latitude = dataSnapshot.child("Location/Latitude").getValue().toString();
                    String Longitude = dataSnapshot.child("Location/Longitude").getValue().toString();


                    double location_left = Double.parseDouble(Latitude);
                    double location_right = Double.parseDouble( Longitude);
                    String name =(String) dataSnapshot.child("Information/Name").getValue();
                    LatLng cod = new LatLng( location_left, location_right);
                    mMap.addMarker(new MarkerOptions().position(cod).title(name));


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


            }


        }


/**
 // Obtain the SupportMapFragment and get notified when the map is ready to be used.
 SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
 .findFragmentById(R.id.fragment);

 mapFragment.getMapAsync(this);

 */


        /*

        myFirebaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Location loca = postSnapshot.getValue(Location.class);
                   myFirebaseRef.child(snapshot.getKey()).setValue(loca);
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

    }

**/
/**
 Lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
new Firebase("https://vendor-5acbc.firebaseio.com/Vendors")
.orderByChild("Location")
.equalTo((String) Lst.getItemAtPosition(position))
.addListenerForSingleValueEvent(new ValueEventListener() {
public void onDataChange(DataSnapshot dataSnapshot) {
if (dataSnapshot.hasChildren()) {
DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
firstChild.getRef().removeValue();
}
}

public void onCancelled(FirebaseError firebaseError) {
}
});
}
});
 }
 */


/**
 ListView listView =(ListView) findViewById(R.id.listView);

 final List<Location> locations=new LinkedList<>();
 final ArrayAdapter<Location> adapter=new ArrayAdapter<Location>(
 this, android.R.layout.two_line_list_item,locations
 ){
@Override public View getView(int position, View view, ViewGroup parent) {
if (view == null) {
view = getLayoutInflater().inflate(android.R.layout.two_line_list_item, parent, false);
}
Location loca = locations.get(position);
((TextView) view.findViewById(android.R.id.text1)).setText(loca.Address);
((TextView) view.findViewById(android.R.id.text2)).setText(loca.ZIP);

((TextView) view.findViewById(android.R.id.text1)).setText(String.format("%.2f", loca.getLongitude()));
((TextView) view.findViewById(android.R.id.text2)).setText(String.format("%.2f", loca.getLatitude()));

return view;
}
};

 listView.setAdapter(adapter);

 ChildEventListener childEventListener = rootRef.addChildEventListener(new ChildEventListener() {
@Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
Location loca = dataSnapshot.getValue(Location.class);
locations.add(loca);
adapter.notifyDataSetChanged();

}

@Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {

}

@Override public void onChildRemoved(DataSnapshot dataSnapshot) {

}

@Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

}

@Override public void onCancelled(FirebaseError firebaseError) {

}

});
 */


                /**
                 * Manipulates the map once available.
                 * This callback is triggered when the map is ready to be used.
                 * This is where we can add markers or lines, add listeners or move the camera. In this case,
                 * we just add a marker near Sydney, Australia.
                 * If Google Play services is not installed on the device, the user will be prompted to install
                 * it inside the SupportMapFragment. This method will only be triggered once the user has
                 * installed Google Play services and returned to the app.
                 */




