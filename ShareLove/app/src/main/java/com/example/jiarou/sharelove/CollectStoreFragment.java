package com.example.jiarou.sharelove;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by chiayi on 16/8/17.
 */
public class CollectStoreFragment extends Fragment {



    public static CollectStoreFragment newInstance(){
        return new CollectStoreFragment();
    }

    public CollectStoreFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.collect_store_fragment,container,false);

        return view;
    }








}
