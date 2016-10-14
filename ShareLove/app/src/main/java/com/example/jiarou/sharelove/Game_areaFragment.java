package com.example.jiarou.sharelove;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link Game_areaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Game_areaFragment extends Fragment {


    final static ArrayList<String> address = new ArrayList<>();

    int num;
    Spinner city,area;
    Set set =new  HashSet();
    ListView list;
    final static String DB_URL = "https://vendor-5acbc.firebaseio.com/Vendors";
    String get_city;
    String get_area;
    String get_address;
    ArrayAdapter<String>  area_adapter,adapter;
    String get_location;
    Button start;




    private Choose_area mListener;

    public Game_areaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Game_areaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Game_areaFragment newInstance() {
        Game_areaFragment fragment = new Game_areaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Start_GameFragment start_gameFragment =
                Start_GameFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("location", get_location);
        start_gameFragment.setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.fragment_game_area, container, false);





        //選擇城市的spinner
        city=(Spinner) view.findViewById(R.id.city);
        final  ArrayAdapter<String> city_adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(city_adapter);

        city.setOnItemSelectedListener(selectListener);

        area_adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);

        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area = (Spinner) view.findViewById(R.id.area);
        area.setAdapter(area_adapter);

        area.setOnItemSelectedListener(zipListener);

        list = (ListView) view.findViewById(R.id.check);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, android.R.id.text1);
        list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                get_location = (String) list.getItemAtPosition(position);


                Log.d("location", "location" + get_location);

            }
        });


        start =(Button) view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           if(get_location==null) {
               Toast.makeText(getActivity(), "尚未選取地址", Toast.LENGTH_LONG).show();

           }else {

               AlertDialog.Builder bdr = new AlertDialog.Builder(getActivity());
               bdr.setMessage("確認後就無法返回重新選擇囉！");
               bdr.setTitle("提醒");
               bdr.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       mListener.Choose_area(get_location);


                   }
               });
               bdr.setNegativeButton("返回", null);
               bdr.show();







           }
           }
        });








        Log.d("set", "set" + set);


        Firebase.setAndroidContext(this.getActivity());
        final Firebase vendor = new Firebase(DB_URL);
        Query gameQuery = vendor.orderByChild("Game").equalTo(true);
        gameQuery.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String title = (String) dataSnapshot.child("Location/Address").getValue();
                String city = title.substring(0, 3);


                Log.d("test", "test" + address);

                set.add(city);

                num=set.size();
                Iterator it;
                it=set.iterator();
                while (it.hasNext()){
                    city_adapter.add((String) it.next());

                }




                Log.d("set", "set" + set);

                //Toast.makeText(getActivity(),num , Toast.LENGTH_LONG).show();




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














        return view;
    }


    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){
            //讀取第一個下拉選單是選擇第幾個
           get_city  = city.getSelectedItem().toString();

            area_adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, android.R.id.text1);
            //載入第二個下拉選單Spinner
            area.setAdapter( area_adapter);



            final Firebase vendor = new Firebase(DB_URL);

            vendor.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String areas = (String) dataSnapshot.child("Location/Address").getValue();


                    //找到台北市取出區域
                    if (areas.contains(get_city)) {
                        get_area = areas.substring(3, 6);;
                        area_adapter.add(get_area);

                    }else {

                        Log.d("area", "area" + "失敗");
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




        }

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };









    private AdapterView.OnItemSelectedListener zipListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View v, int position,long id){


           final String select_address  = area.getSelectedItem().toString();


            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, android.R.id.text1);
            list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            list.setAdapter(adapter);



            final Firebase myFirebaseRef = new Firebase("https://vendor-5acbc.firebaseio.com/Vendors");
            ChildEventListener childEventListener = myFirebaseRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String address = (String) dataSnapshot.child("Location/Address").getValue();
                    String  city_area=get_city+select_address;


                    if (address.contains(city_area)) {
                        get_address = address;;
                        Log.d("area", "area" +  get_address);
                        adapter.add(get_address);

                    }else {

                        Log.d("area", "area" + "失敗");
                    }
                    //有問題的code


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

        public void onNothingSelected(AdapterView<?> arg0){

        }

    };

/**
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.Choose_area();
        }
    }**/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Choose_area) {
            mListener = (Choose_area) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Choose_area");
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
    public interface Choose_area {
        // TODO: Update argument type and name
        void Choose_area(String data);


    }
}
