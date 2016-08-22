package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peter.focus.*;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class VenderListFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final static ArrayList<String> vendorTitleList = new ArrayList<>();
     ListView list;
    private MapView mapView;
    private GoogleMap map;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    final static String DB_URL = "https://vendor-5acbc.firebaseio.com/Vendors";
    private OnFragmentInteractionListener mListener;




    public static  VenderListFragment newInstance(){
        Bundle args = new Bundle();

        VenderListFragment fragment = new VenderListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public VenderListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener)context;
        }else{
            throw new ClassCastException(context.toString() + "must implement  OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_vender_list, container, false);
        //fragment加入地圖頁面
        mapView = (MapView) view.findViewById(R.id.map1);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();// needed to get the map to display immediately

        MapsInitializer.initialize(getActivity());

        map = mapView.getMap();





        list = (ListView) view.findViewById(R.id.venderlist_view);
        connectToFirebase();

        return view;

    }


    public void connectToFirebase(){

        final CustomAdapter adapter = new CustomAdapter(this.getActivity(), vendorTitleList);

        Firebase.setAndroidContext(this.getActivity());

        final Firebase vendor = new Firebase(DB_URL);


        Query focusVendor = vendor.orderByChild("Focus").equalTo(true);

        focusVendor.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String title = (String)dataSnapshot.child("Information").child("Name").getValue();


                vendorTitleList.add(title);

               list.setAdapter(adapter);
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

        vendorTitleList.clear();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }




    public class CustomAdapter extends BaseAdapter{

        Context c;

        ArrayList<String> vendorTitle;



        public CustomAdapter(Context context, ArrayList<String> vendorTitle){
            c = context;
            this.vendorTitle = vendorTitle;

        }

        @Override
        public int getCount() {
            return vendorTitle.size();
        }

        @Override
        public Object getItem(int position) {
            return vendorTitle.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View list;
            LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            list = inflater.inflate(R.layout.map_listview, null);

            TextView listTextView = (TextView)list.findViewById(R.id.MaplistTextView);


            final String title = vendorTitle.get(position);
            listTextView.setText(title);



            list.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    mListener.onFocusSelected(title);
                }
            });


            return list;
        }
    }




        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);


        void onFocusSelected(String vendorTitle);
    }
}
