package com.example.chiayi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TextView textView;
    TextView textView2;
    //TextView textView3;
    Button button;
    Button button2;
    Button button3;
    GridView gridView;
    //Context context;

    //ArrayList couponName;
    final  static ArrayList<Long> couponPriceList= new ArrayList<>();
    final static ArrayList<String> couponNameList= new ArrayList<>();

   // public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
   // public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};




    //示範: 到時候要取Firebase內的資料
    //public static String [] couponNameList={"摩斯漢堡"};
    //public static String [] couponPriceList;
    public static int [] couponImages={R.mipmap.ic_launcher,R.mipmap.ic_launcher};



    final static String DB_URL="https://coupon-da649.firebaseio.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //GridView
        gridView=(GridView) findViewById(R.id.gridView);
        final CustomAdapter adapter= new CustomAdapter(this, couponNameList, couponPriceList, couponImages);
        //gridView.setAdapter(new CustomAdapter(this, couponNameList, couponPriceList));



        Firebase.setAndroidContext(this);
        //Connect to Firebase-Coupon
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


    }




}
