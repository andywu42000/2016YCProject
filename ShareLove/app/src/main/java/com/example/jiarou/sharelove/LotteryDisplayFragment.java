package com.example.jiarou.sharelove;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by chiayi on 16/8/14.
 */
public class LotteryDisplayFragment extends Fragment {


    final static String URL = "";

    Button toSearchPage_btn;

    public static LotteryDisplayFragment newInstance(){
        return new LotteryDisplayFragment();
    }

    public LotteryDisplayFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //將畫面設為coupon_types_fragment.xml
        final View view = inflater.inflate(R.layout.lottery_display_fragment,container,false);
        toSearchPage_btn=(Button)view.findViewById(R.id.toSearchPage_btn);
        toSearchPage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }




}
