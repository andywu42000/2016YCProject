package com.example.jiarou.sharelove;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by chiayi on 16/8/17.
 */
public class AddVendorFragment extends Fragment{


    public static AddVendorFragment newInstance(){
        return new AddVendorFragment();
    }

    public AddVendorFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //將畫面設為coupon_types_fragment.xml
        final View view = inflater.inflate(R.layout.add_vendor_fragment,container,false);
        return view;
    }





}
