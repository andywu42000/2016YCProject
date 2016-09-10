package com.example.jiarou.sharelove;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link Start_GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Start_GameFragment extends Fragment implements LocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Start_game mListener;
    private GoogleMap mMap;
    private MapView mapView;
    static final int MIN_TINE = 5000;
    static final float MIN_DIST = 5;
    public  static  final int LOCATION_REQUEST_CODE=1;
    LocationManager mgr;
    TextView txv;
    Button get;


    public Start_GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Start_GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Start_GameFragment newInstance() {
        Start_GameFragment fragment = new Start_GameFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_start_game, container, false);


        //fragment加入地圖頁面
        mapView = (MapView) view.findViewById(R.id.game_map);
        mapView.onCreate(savedInstanceState);
        setUpMapIfNeed();
        mapView.onResume();// needed to get the map to display immediately



        mMap = mapView.getMap();
        LatLng nccu = new LatLng(24.9849998, 121.5761281);
        mMap.addMarker(new MarkerOptions().position(nccu).title("Marker in NCCU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nccu));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        txv = (TextView) view.findViewById(R.id.txv);

        mgr = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        get = (Button) view.findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent((Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                startActivity(it);

            }
        });




        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                     // If request is cancelled, the result arrays are empty.
                                 if (grantResults.length > 0
                                   && grantResults[0] == PackageManager.PERMISSION_GRANTED
                                   && (ActivityCompat.checkSelfPermission(getActivity(),
                                         android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                        || ActivityCompat.checkSelfPermission(getActivity(),
                                         android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                             mgr.requestLocationUpdates(mgr.GPS_PROVIDER
                      ,1000*60,2, (LocationListener) this);    android.location.Location location = mgr.getLastKnownLocation(mgr.GPS_PROVIDER);
                           }
                           }
                       }
    }



    @Override
    public void onResume() {
        super.onResume();


        setUpMapIfNeed();

        String best = mgr.getBestProvider(new Criteria(), true);
        if (best != null) {
            txv.setText("取得定位資訊中...");
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
            } else {           mgr.requestLocationUpdates(mgr.GPS_PROVIDER
                    ,1000*60,2, (LocationListener) this);
                android.location.Location location = mgr.getLastKnownLocation(mgr.GPS_PROVIDER);
            }
            mgr.requestLocationUpdates(best, MIN_TINE, MIN_DIST, (LocationListener) getActivity());
        } else {
            txv.setText("請確認已開啟定位功能！！");
        }


    }

    private void setUpMapIfNeed() {
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
            } else {           mgr.requestLocationUpdates(mgr.GPS_PROVIDER
                    ,1000*60,2, (LocationListener) this);
                android.location.Location location = mgr.getLastKnownLocation(mgr.GPS_PROVIDER);
            }
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.moveCamera(CameraUpdateFactory.zoomTo(18));

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {           mgr.requestLocationUpdates(mgr.GPS_PROVIDER
                ,1000*60,2, (LocationListener) this);
            android.location.Location location = mgr.getLastKnownLocation(mgr.GPS_PROVIDER);
        }
        mgr.removeUpdates((LocationListener) getActivity());


    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener. Start_game();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  Start_game) {
            mListener = ( Start_game) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement  Start_game");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        String str="定位提供者："+ location.getProvider();
        str+= String.format("\n緯度:%.5f\n經度:%.5f",
                location.getLatitude(),
                location.getLongitude());
        txv.setText(str);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Start_game {
        // TODO: Update argument type and name
        void  Start_game();
    }
}
