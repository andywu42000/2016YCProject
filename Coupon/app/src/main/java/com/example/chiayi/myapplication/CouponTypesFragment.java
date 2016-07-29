package com.example.chiayi.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chiayi on 16/7/27.
 */
public class CouponTypesFragment extends Fragment{



    TextView textView2;
    Button coupon_type1;
    Button coupon_type2;
    Button coupon_type3;
    GridView gridView;
    final  static ArrayList<Long> couponPriceList= new ArrayList<>();
    final  static ArrayList<Long> couponAmountList= new ArrayList<>();
    final static ArrayList<String> couponInfoList= new ArrayList<>();
    final static ArrayList<String> couponNameList= new ArrayList<>();
    final static ArrayList<String> couponImagesList=new ArrayList<>();
    final static String DB_URL="https://coupon-da649.firebaseio.com/";
    String imgurURL = "http://i.imgur.com/";
    private OnCouponSelected mListener;



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
        if (context instanceof OnCouponSelected) {
            mListener = (OnCouponSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnCouponSelected.");
        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //將畫面設為coupon_types_fragment.xml
        final View view = inflater.inflate(R.layout.coupon_types_fragment,container,false);

        //Coupon types page button (change page)
        coupon_type1=(Button)view.findViewById(R.id.button);
        coupon_type2=(Button)view.findViewById(R.id.button2);
        coupon_type3=(Button)view.findViewById(R.id.button3);

        coupon_type1.setOnClickListener(ChangePage);
        coupon_type2.setOnClickListener(ChangePage);
        coupon_type3.setOnClickListener(ChangePage);

        //宣告 GridView
        gridView=(GridView)view.findViewById(R.id.gridView);
        connectToFirebase("平價");

        return view;
    }


    //Change Pages Button
    private View.OnClickListener ChangePage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){

                case R.id.button:
                    connectToFirebase("平價");
                    return;
                case R.id.button2:
                    connectToFirebase("中等");
                    return;
                case R.id.button3:
                    connectToFirebase("豪華");
                    return;
                default:
            }


        }
    };


    //連接Firebase取出名字、價錢、照片，置入ArrayList中
    public void connectToFirebase(String couponType){
        //Adapter宣告
        final CustomAdapter adapter= new CustomAdapter(this.getActivity(), couponNameList, couponPriceList, couponImagesList, couponAmountList, couponInfoList);
        //Connection Firebase
        Firebase.setAndroidContext(this.getActivity());
        final Firebase ref = new Firebase(DB_URL);
        //Query
        Query budgetRef = ref.orderByChild("Type").equalTo(couponType);
        budgetRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Long price = (Long) dataSnapshot.child("Price").getValue();
                String name = (String) dataSnapshot.child("Name").getValue();
                String photoId = (String) dataSnapshot.child("Photos").child("Photo_ID").getValue();
                String information = (String) dataSnapshot.child("Information").getValue();
                Long remainingAmount= (Long)dataSnapshot.child("Remaining_Amount").getValue();

                //CouponURL是完整rl
                String couponURL = imgurURL + photoId + ".jpg";
                couponImagesList.add(couponURL);
                System.out.println(couponURL);
                couponURL = imgurURL;



                couponNameList.add(name);
                couponPriceList.add(price);
                couponInfoList.add(information);
                couponAmountList.add(remainingAmount);


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

        couponImagesList.clear();
        couponNameList.clear();
        couponPriceList.clear();
        couponInfoList.clear();
        couponAmountList.clear();
    }
    public interface OnCouponSelected{
        void onCouponSelected(String CouponName, String Price, String couponURL, String url, String info);
    }



    public class CustomAdapter extends BaseAdapter {

        final ArrayList<String> couponName;
        final ArrayList<Long> couponPrice;
        Context context;
        ArrayList<String> couponImage;
        ArrayList<String> couponInfo;
        ArrayList<Long> couponAmount;

        public CustomAdapter(Context c, final ArrayList<String> couponName, final ArrayList<Long> couponPrice,  ArrayList<String> couponImage, ArrayList<Long> couponAmount, ArrayList<String> couponInfo){

            context = c;
            this.couponImage=couponImage;
            this.couponPrice=couponPrice;
            this.couponName=couponName;
            this.couponAmount=couponAmount;
            this.couponInfo=couponInfo;


        }


        @Override
        public int getCount() {
            return couponName.size();
        }

        @Override
        public Object getItem(int position) {
            return couponName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View grid;
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.custom_gridview, null);
            TextView textView = (TextView) grid.findViewById(R.id.textView1);
            TextView textView2 = (TextView) grid.findViewById(R.id.textView2);
            ImageView imageView = (ImageView)grid.findViewById(R.id.imageView1);


            final String name = couponName.get(position);
            textView.setText(name);


            final String price = String.valueOf(couponPrice.get(position));
            textView2.setText(price);


            final String couponURL = couponImage.get(position);
            DownloadImageTask downloadImage = new DownloadImageTask(imageView);
            downloadImage.execute(couponURL);


            final String info = couponInfo.get(position);


            final String amount = String.valueOf(couponAmount.get(position));


            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "You Clicked " + couponName.get(position)+ couponPrice.get(position)+ couponImage.get(position), Toast.LENGTH_LONG).show();
                    mListener.onCouponSelected(name,price,couponURL,info,amount);

                }
            });
            return grid;
        }



    }







}


