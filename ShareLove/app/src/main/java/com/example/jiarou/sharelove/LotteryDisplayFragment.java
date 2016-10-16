package com.example.jiarou.sharelove;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chiayi on 16/8/14.
 */
public class LotteryDisplayFragment extends Fragment{


    //  還沒做restart fragment 的部分

    final static String LOTTO_DB_URL = "https://lottery-72c58.firebaseio.com/"; //用在開獎
    final static String MEMBER_DB_URL = "https://member-139bd.firebaseio.com/";
    Long facebookID = (Long) 111111111111111l; //到時候應該是可以透過什麼管道取得的
    ArrayList<Long> lottoWinNum = new ArrayList<>();
    ArrayList<Long> lottoNum = new ArrayList<>();
    Object n1,n2,n3,n4,n5;
    Object w_n1,w_n2,w_n3,w_n4,w_n5;
    String owned_period;
    String win_period;
    String memberKey;
    Long owned_points;
    String message;


    TextView cnum1,cnum2,cnum3,cnum4,cnum5,winning_num,check_y_n;
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
        getMemberLottoNum();


        gameRule_btn = (Button)view.findViewById(R.id.gameRule_btn);
        check_y_n = (TextView) view.findViewById(R.id.check_y_n);
        check_btn = (Button)view.findViewById(R.id.check_btn);
        winning_num = (TextView) view.findViewById(R.id.winning_num);
       // toSearchPage_btn=(Button)view.findViewById(R.id.toSearchPage_btn);
//        toSearchPage_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent();
//                intent.setClass(getActivity(), SearchActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });

        gameRule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLottoRule();


            }
        });





        //getLastestLottoNum;
        final Firebase lotto_db = new Firebase(LOTTO_DB_URL);
        Query query = lotto_db.limitToLast(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                w_n1 = dataSnapshot.child("Numbers").child("First").getValue();
                w_n2 = dataSnapshot.child("Numbers").child("Second").getValue();
                w_n3 = dataSnapshot.child("Numbers").child("Third").getValue();
                w_n4 = dataSnapshot.child("Numbers").child("Fourth").getValue();
                w_n5 = dataSnapshot.child("Numbers").child("Fifth").getValue();
                win_period = (String) dataSnapshot.child("Period").getValue();

                String w1 = String.valueOf(w_n1);
                String w2 = String.valueOf(w_n2);
                String w3 = String.valueOf(w_n3);
                String w4 = String.valueOf(w_n4);
                String w5 = String.valueOf(w_n5);

                lottoWinNum.add((Long) w_n5);
                lottoWinNum.add((Long) w_n4);
                lottoWinNum.add((Long) w_n3);
                lottoWinNum.add((Long) w_n2);
                lottoWinNum.add((Long) w_n1);


                String winning_number = w5 + w4 + w3 + w2 + w1;
                winning_num.setText(winning_number);


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



        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    checkWinNum();
                    check_y_n.setText(message);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });


        check_y_n.setText(message);



        return view;
    }



    public void showLottoRule (){

        lottoRuleFragment.setTargetFragment(this, 0);
        lottoRuleFragment.show(getFragmentManager(), "Lotto_Rule_dialog");

    }




    public void checkWinNum() throws ParseException {



        final Firebase member_db = new Firebase(MEMBER_DB_URL);
        System.out.println("==================");
        System.out.println(lottoNum);
        System.out.println(owned_period);
        System.out.println("==================");
        System.out.println(lottoWinNum);
        System.out.println(win_period);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;

        Long total_points;


        if (owned_period == null || win_period == null){


        }else{

            date1 = format.parse(owned_period);
            date2 = format.parse(win_period);

        }


        if (win_period == null){


            Toast.makeText(getContext(),"目前尚未開出獎號，週六凌晨將準時開獎",Toast.LENGTH_LONG).show();


        }else if (owned_period == null){

            Toast.makeText(getContext(),"請先前去收集獎號",Toast.LENGTH_LONG).show();


        } else if (owned_period.equals(win_period)){


            if (lottoNum.get(3) == null || lottoNum.get(2) == null){

                Toast.makeText(getContext(),"抱歉! 您收集的樂透數不足三個，故無法兌獎。請繼續參與下週的樂透活動。",Toast.LENGTH_LONG).show();
                member_db.child(memberKey).child("Lottery_Numbers").removeValue();

            }else{


                int m=0, j, h;

                for(j=4 ; j>= 0 ; j--){

                    if (lottoNum.get(j) == lottoWinNum.get(j)){

                        m=m+1;
                    }else{

                        break;
                    }


                }


                if (m>=3){

                    h=m;

                    //分發點數給中獎者
                    switch (h){

                        case 3:

                            message = "中三碼";
                            //check_y_n.setText("中末三碼");
                            Toast.makeText(getContext(),"恭喜中獎，將贈予您愛心點數 5 點，請至個人頁面查看",Toast.LENGTH_LONG).show();

                            total_points = owned_points+5;
                            member_db.child(memberKey).child("Owned_Points").setValue(total_points);
                            member_db.child(memberKey).child("Lottery_Numbers").removeValue();

                            break;


                        case 4:

                            message = "中四碼";
                            //check_y_n.setText("中末四碼");
                            Toast.makeText(getContext(),"恭喜中獎，將贈予您愛心點數 10 點，請至個人頁面查看",Toast.LENGTH_LONG).show();

                            total_points = owned_points+10;
                            member_db.child(memberKey).child("Owned_Points").setValue(total_points);
                            member_db.child(memberKey).child("Lottery_Numbers").removeValue();
                            break;



                        case 5:

                            message = "全中";
                            //check_y_n.setText("全中");
                            Toast.makeText(getContext(),"恭喜中獎，將贈予您愛心點數 1000 點，請至個人頁面查看",Toast.LENGTH_LONG).show();
                            total_points = owned_points+1000;
                            member_db.child(memberKey).child("Owned_Points").setValue(total_points);
                            member_db.child(memberKey).child("Lottery_Numbers").removeValue();

                            break;

                    }

                }else{

                    message = "可惜沒中獎";
                    //check_y_n.setText("可惜沒中獎");
                    member_db.child(memberKey).child("Lottery_Numbers").removeValue();

                }

            }


        }else if (date1.compareTo(date2)<0){


            Toast.makeText(getContext(), "由於之前已錯過兌獎期限，請重新集樂透號碼", Toast.LENGTH_LONG).show();
            member_db.child(memberKey).child("Lottery_Numbers").removeValue();


        }else if (date1.compareTo(date2)>0){

            Toast.makeText(getContext(), "發生錯誤", Toast.LENGTH_LONG).show();


        }







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
                memberKey = dataSnapshot.getKey();


                if (dataSnapshot.hasChild("Lottery_Numbers")){
                    HashMap<String, Object> id = (HashMap<String, Object>) dataSnapshot.child("Lottery_Numbers").getValue();
                    for (Map.Entry<String,Object> entry: id.entrySet()){

                        key = entry.getKey();
                        k_arraylist.add(key);

                    }


                    String latest_key = k_arraylist.get(0);


                    owned_period = (String)dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Period").getValue();
                    owned_points = (Long)dataSnapshot.child("Owned_Points").getValue();
                    n1 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("First").getValue();
                    n2 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Second").getValue();
                    n3 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Third").getValue();
                    n4 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Fourth").getValue();
                    n5 = dataSnapshot.child("Lottery_Numbers").child(latest_key).child("Numbers").child("Fifth").getValue();


                    System.out.println("+++++++++++++++++++++++++++++++++++++++");
                    System.out.println(n1);
                    System.out.println(n2);
                    System.out.println(n3);



                    cnum1.setText(String.valueOf(n1));
                    cnum2.setText(String.valueOf(n2));
                    cnum3.setText(String.valueOf(n3));
                    cnum4.setText(String.valueOf(n4));
                    cnum5.setText(String.valueOf(n5));



                    if (n2.equals("") && n3.equals("") && n4.equals("") && n5.equals("")){

                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add((Long) n1);



                    }else if (n3.equals("") && n4.equals("") && n5.equals("")){


                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add((Long)n2);
                        lottoNum.add((Long)n1);

                    }else if (n4.equals("") && n5.equals("")){

                        lottoNum.add(null);
                        lottoNum.add(null);
                        lottoNum.add((Long)n3);
                        lottoNum.add((Long)n2);
                        lottoNum.add((Long)n1);


                    }else if (n5.equals("")){


                        lottoNum.add(null);
                        lottoNum.add((Long)n4);
                        lottoNum.add((Long)n3);
                        lottoNum.add((Long)n2);
                        lottoNum.add((Long)n1);

                    }else{

                        lottoNum.add((Long)n5);
                        lottoNum.add((Long)n4);
                        lottoNum.add((Long)n3);
                        lottoNum.add((Long) n2);
                        lottoNum.add((Long) n1);


                    }

                }else{


                    cnum1.setText("");
                    cnum2.setText("");
                    cnum3.setText("");
                    cnum4.setText("");
                    cnum5.setText("您還未收集這期樂透");

                }

                //下面是另一個 function

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
