package com.example.jiarou.sharelove;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VenderListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VenderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VenderListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final static ArrayList<String> vendorTitleList = new ArrayList<>();
     ListView list;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    final static String DB_URL = "https://vendor-5acbc.firebaseio.com/Vendors";
    private OnFragmentInteractionListener mListener;

    public VenderListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VenderListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VenderListFragment newInstance(String param1, String param2) {
        VenderListFragment fragment = new VenderListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_vender_list, container, false);

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
                    mListener.onFocusSelected(title/*, vendorURL, phone, remark, mon, tue, wed, thur,
                            fri, sat, sun, address, story*/);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
