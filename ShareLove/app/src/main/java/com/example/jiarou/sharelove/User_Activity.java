package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class User_Activity extends AppCompatActivity  implements UserFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_);

        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction fragmentTrans = fragmentMgr.beginTransaction();
         UserFragment userFrangment = new UserFragment();
        fragmentTrans.add(R.id.user_root, userFrangment, "user");
        fragmentTrans.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
