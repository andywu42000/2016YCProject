package com.example.jiarou.sharelove;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chiayi on 16/8/16.
 */
public class OwnedCouponFragment extends Fragment{



    ListView owned_coupon_list;
    Long facebookID = 111111111111111l; //到時候應該是可以透過什麼管道取得的
    String imgurURL = "http://i.imgur.com/";
    final static String MEMBER_DB_URL = "https://member-139bd.firebaseio.com/";
    final static String COUPON_DB_URL = "https://coupon-da649.firebaseio.com/";
    final static ArrayList<String> couponIDList = new ArrayList<>();
    final static ArrayList<String> couponDueDateList = new ArrayList<>();
    final static ArrayList<String> couponInfoList = new ArrayList<>();


    public static OwnedCouponFragment newInstance(){
        return new OwnedCouponFragment();
    }

    public OwnedCouponFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.owned_coupon_fragment,container,false);
        owned_coupon_list = (ListView) view.findViewById(R.id.listView);
        connectToMemberFirebase();
        return view;
    }


    public void connectToMemberFirebase(){
        final CustomAdapter adapter = new CustomAdapter(this.getActivity(), couponIDList, couponDueDateList,couponInfoList);
        Firebase.setAndroidContext(this.getActivity());
        final Firebase member_db = new Firebase(MEMBER_DB_URL);

        Query query = member_db.orderByChild("Facebook_ID").equalTo(facebookID);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                //    Long id= (Long) dataSnapshot.child("Owned_Points").getValue();
                HashMap<String,Map<String, String>> id = (HashMap<String,Map<String, String>>) dataSnapshot.child("Owned_Coupons").getValue();

                for (Map.Entry<String,Map<String, String>> entry : id.entrySet()) {
                    String key = entry.getKey();

                    String coupon_id = id.get(key).get("Coupon_ID");
                    String due_date  = id.get(key).get("Due_Date");
                    String coupon_info = id.get(key).get("Information");

                    //String pic = imgurURL + picId + ".jpg";
                    //vendorPicList.add(pic);


                    couponIDList.add(coupon_id);
                    couponInfoList.add(coupon_info);
                    couponDueDateList.add(due_date);
                    owned_coupon_list.setAdapter(adapter);
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

        couponDueDateList.clear();
        couponInfoList.clear();
        couponIDList.clear();

    }




    public class CustomAdapter extends BaseAdapter {

        Context c;

        ArrayList<String> couponID;

        ArrayList<String> couponDueDate;

        ArrayList<String> couponInfo;


        public CustomAdapter (Context context, ArrayList<String> couponIDList,ArrayList<String> couponDueDateList,ArrayList<String> couponInfoList ){

            c = context;
            this.couponID = couponIDList;
            this.couponDueDate = couponDueDateList;
            this.couponInfo = couponInfoList;



        }


        @Override
        public int getCount() {
            return couponID.size();
        }

        @Override
        public Object getItem(int position) {
            return couponID.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View list;
            LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = inflater.inflate(R.layout.custom_owned_coupon_listview, null);

            ImageView listImageView = (ImageView)list.findViewById(R.id.listImageView);
            TextView  coupon_name = (TextView)list.findViewById(R.id.coupon_name);
            TextView  due_date = (TextView) list.findViewById(R.id.due_date);
            Button    use_btn = (Button)list.findViewById(R.id.use_btn);


            final String id = couponID.get(position);
//            connectToCouponFirebase(id);
            coupon_name.setText(id);

            final String date = couponDueDate.get(position);
            due_date.setText(date);


//            final String vendorURL = vendorPic.get(position);
//            //Do bitmapTask
//            DownloadImageTask downloadImage = new DownloadImageTask(listImageView);
//            downloadImage.execute(vendorURL);




            return list;
        }
    }

}


// OnFocusListener等要點listview進去時再用


