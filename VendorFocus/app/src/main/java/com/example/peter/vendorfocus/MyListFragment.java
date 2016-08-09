package com.example.peter.vendorfocus;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Peter on 2016/8/3.
 */
public class MyListFragment extends ListFragment {

    private DatabaseReference vendor;

    public class Vendor {
        String Introduction;
        String Name;
        String Phone;

        public Vendor(){

        }

        public Vendor(String introduction, String name, String phone){
            this.Introduction = introduction;
            this.Name = name;
            this.Phone = phone;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public String getName(){
            return Name;
        }

        public String getPhone(){
            return Phone;
        }


    }

    Vendor[] vendors = {};





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.focus_list_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vendor = FirebaseDatabase.getInstance().getReference();

        vendor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vendorSnapshot : dataSnapshot.child("Vendors").getChildren()) {
                    Vendor detailSnapshot = vendorSnapshot.child("Information").getValue(Vendor.class);

                    vendors =
                            new Vendor[]{detailSnapshot};
                    setListAdapter(new MyCustomAdapter(
                            getActivity(),
                            R.layout.row,
                            vendors));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }

    public class MyCustomAdapter extends ArrayAdapter<Vendor>{
        public MyCustomAdapter(Context context, int textViewResourcedId, Vendor[] objects) {
            super(context, textViewResourcedId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater=getActivity().getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView text1 = (TextView)row.findViewById(R.id.textView);


            text1.setText(vendors[position].getIntroduction());


            return row;
        }

    }


}
