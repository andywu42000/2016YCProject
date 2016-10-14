package com.example.jiarou.sharelove;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.*;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GameActivity extends AppCompatActivity  implements GameFragment.OpenGame,ChanglleFragment.OnFragmentInteractionListener,Game_areaFragment.Choose_area,Start_GameFragment.Start_game,LocationListener,ChanglleFragment.delete{
    String get_number;
    String get_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.game_root, GameFragment.newInstance(), "game")
                    .commit();


        }

    }


    @Override
    public void OpenGame() {

        final ChanglleFragment changlleFragment =
                ChanglleFragment.newInstance();

                 getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_root, changlleFragment, "changenge")
                .addToBackStack(null)
                .commit();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.game_root);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFragmentInteraction(String number) {
        final Game_areaFragment game_areaFragment =
                Game_areaFragment.newInstance();
                get_number = number;


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_root,game_areaFragment, "game_areaFragment")
                .addToBackStack(null)
                .commit();



    }

    @Override
    public void Choose_area(String data) {
        final android.support.v4.app.FragmentManager fragmentManager =
              getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.game_root);
        fragmentTransaction.remove(fragment);

        fragmentTransaction.commit();


        final Start_GameFragment start_gameFragment =
                Start_GameFragment.newInstance();
        Bundle bundle =new Bundle();
        bundle.putString("address",data);
        bundle.putString("number",get_number);
        start_gameFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.game_root, start_gameFragment, " start_gameFragment")
                .addToBackStack(null)
                .commit();


    }



    @Override
    public void Start_game(String check) {
        final ChanglleFragment changlleFragment =
                ChanglleFragment.newInstance();
               get_check=check;
        Bundle bundle =new Bundle();
        bundle.putString("check",get_check);

        changlleFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_root, changlleFragment, "changenge")
                .addToBackStack(null)
                .commit();


    }
    @Override
    public void delete() {
       final GameFragment  gameFragment =GameFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_root, GameFragment.newInstance(), "game")
                .addToBackStack(null)
                .commit();




    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
