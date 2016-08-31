package com.example.jiarou.sharelove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chiayi on 16/8/14.
 */
public class LotteryDisplayFragment extends Fragment{


    final static String LOTTO_DB_URL = "https://lottery-72c58.firebaseio.com/"; //用在開獎
    final static String MEMBER_DB_URL = "https://member-139bd.firebaseio.com/";
    Long facebookID = 111111111111111l; //到時候應該是可以透過什麼管道取得的
    ArrayList<Long> lottoNum = new ArrayList<>();
    Object n1,n2,n3,n4,n5;
    Long total_num;

    TextView cnum1,cnum2,cnum3,cnum4,cnum5;
    Button toSearchPage_btn, gameRule_btn, check_btn;
    LottoRuleDialogFragment lottoRuleFragment = new LottoRuleDialogFragment();

    public static LotteryDisplayFragment newInstance(){
        return new LotteryDisplayFragment();
    }

    public LotteryDisplayFragment(){




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        //將畫面設為coupon_types_fragment.xml
        final View view = inflater.inflate(R.layout.lottery_display_fragment,container,false);

        gameRule_btn = (Button)view.findViewById(R.id.gameRule_btn);
        check_btn = (Button)view.findViewById(R.id.check_btn);
        toSearchPage_btn=(Button)view.findViewById(R.id.toSearchPage_btn);
        toSearchPage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });

        gameRule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLottoRule();


            }
        });



        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                check_lotto();


            }
        });




        getMemberLottoNum();



        return view;
    }






    public void check_lotto(){

        Firebase.setAndroidContext(this.getActivity());
        final Firebase member_db = new Firebase(MEMBER_DB_URL);
        final Firebase lotto_db = new Firebase(LOTTO_DB_URL);
        final Query query = member_db.orderByChild("Facebook_ID").equalTo(facebookID);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                final String memberID = dataSnapshot.getKey();
                String key;
                String period = "";
                final ArrayList<String> p_arrayList = new ArrayList();
                final ArrayList <String> k_arraylist = new ArrayList();
                HashMap<String, Object> id = (HashMap<String, Object>) dataSnapshot.child("Lottery_Numbers").getValue();
                for (Map.Entry<String,Object> entry: id.entrySet()){

                    key = entry.getKey();
                    period = (String) dataSnapshot.child("Lottery_Numbers").child(key).child("Period").getValue();

                    k_arraylist.add(key);
                    p_arrayList.add(key);

                }



                Query query1 = lotto_db.limitToFirst(1);
                query1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String k = dataSnapshot.getKey();
                        String lotto_period = (String)dataSnapshot.child(k).child("Period").getValue();
                        Long one_num = (Long)dataSnapshot.child(k).child("Numbers").child("First").getValue();
                        Long two_num = (Long)dataSnapshot.child(k).child("Numbers").child("Second").getValue();
                        Long three_num = (Long)dataSnapshot.child(k).child("Numbers").child("Third").getValue();
                        Long four_num = (Long)dataSnapshot.child(k).child("Numbers").child("Fourth").getValue();
                        Long five_num = (Long)dataSnapshot.child(k).child("Numbers").child("Fifth").getValue();


                        String num1 = String.valueOf(one_num);
                        String num2 = String.valueOf(two_num);
                        String num3 = String.valueOf(three_num);
                        String num4 = String.valueOf(four_num);
                        String num5 = String.valueOf(five_num);

                        total_num = Long.parseLong(num1+num2+num3+num4+num5) ;



                        for(int i=0 ; i<p_arrayList.size(); i++){

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date m_date = sdf.parse(p_arrayList.get(i));
                                Date l_date = sdf.parse(lotto_period);

                                if (m_date.compareTo(l_date)<0){


                                    member_db.child(memberID).child("Lottery_Numbers").child(k_arraylist.get(i)).removeValue();


                                }else if (m_date.compareTo(l_date) == 0){


                                    System.out.println("等待兌獎");




                                }




                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

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














    public void showLottoRule (){

        lottoRuleFragment.setTargetFragment(this,0);
        lottoRuleFragment.show(getFragmentManager(), "Lotto_Rule_dialog");

    }



    public void getMemberLottoNum (){


        Firebase.setAndroidContext(this.getActivity());
        final Firebase member_db = new Firebase(MEMBER_DB_URL);
        Query query = member_db.orderByChild("Facebook_ID").equalTo(facebookID);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                String key;
                ArrayList <String> k_arraylist = new ArrayList();
                cnum1 = (TextView) getView().findViewById(R.id.current_num1);
                cnum2 = (TextView) getView().findViewById(R.id.current_num2);
                cnum3 = (TextView) getView().findViewById(R.id.current_num3);
                cnum4 = (TextView) getView().findViewById(R.id.current_num4);
                cnum5 = (TextView) getView().findViewById(R.id.current_num5);



                HashMap<String, Object> id = (HashMap<String, Object>) dataSnapshot.child("Lottery_Numbers").getValue();
                for (Map.Entry<String,Object> entry: id.entrySet()){

                    key = entry.getKey();
                    k_arraylist.add(key);

                }


                String latest_key = k_arraylist.get(0);


                //每週六0:00時會將舊東西顯示在上期收藏
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                n1 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("First").getValue();
                n2 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Second").getValue();
                n3 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Third").getValue();
                n4 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Fourth").getValue();
                n5 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Fifth").getValue();


                cnum1.setText(String.valueOf(n1));
                cnum2.setText(String.valueOf(n2));
                cnum3.setText(String.valueOf(n3));
                cnum4.setText(String.valueOf(n4));
                cnum5.setText(String.valueOf(n5));



                // 如果最新的db和...差7則....



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
