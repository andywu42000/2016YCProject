package com.example.chiayi.myapplication;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by chiayi on 16/7/27.
 */
public class CouponTypesFragment extends Fragment{


    TextView textView2;
    Button button;
    Button button2;
    Button button3;

    GridView gridView;
    final  static ArrayList<Long> couponPriceList= new ArrayList<>();
    final static ArrayList<String> couponNameList= new ArrayList<>();
    public static int [] couponImages={R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    final static String DB_URL="https://coupon-da649.firebaseio.com/";


    public static CouponTypesFragment newInstance() {

        Bundle args = new Bundle();

        CouponTypesFragment fragment = new CouponTypesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CouponTypesFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        // 這裡作 如果選了button1則取得...頁面




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.coupon_types_fragment,container,false);
        //GridView
        gridView=(GridView)view.findViewById(R.id.gridView);
        final CustomAdapter adapter= new CustomAdapter(this.getActivity(), couponNameList, couponPriceList, couponImages);


        Firebase.setAndroidContext(this.getActivity());
        final Firebase ref = new Firebase(DB_URL);
        //Query
        Query budgetRef = ref.orderByChild("Type").equalTo("平價");
        budgetRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Long price = (Long) dataSnapshot.child("Price").getValue();
                String name = (String) dataSnapshot.child("Name").getValue();
                couponNameList.add(name);
                couponPriceList.add(price);
                System.out.println(couponNameList);
                System.out.println(couponPriceList);
                System.out.println(couponImages);
                gridView.setAdapter(adapter);

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
}
