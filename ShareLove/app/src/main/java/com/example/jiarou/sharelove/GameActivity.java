package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GameActivity extends AppCompatActivity  implements GameFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction fragmentTrans = fragmentMgr.beginTransaction();
        GameFragment gameFrangment = new GameFragment();
        fragmentTrans.add(R.id.game_root, gameFrangment, "game");
        fragmentTrans.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
