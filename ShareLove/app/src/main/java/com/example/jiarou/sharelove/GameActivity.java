package com.example.jiarou.sharelove;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.Objects;

public class GameActivity extends AppCompatActivity  implements GameFragment.OpenGame,ChanglleFragment.OnFragmentInteractionListener,Game_areaFragment.Choose_area,Start_GameFragment.Start_game,LocationListener,ChanglleFragment.delete{
    String get_number;
    String get_check;
    String condition;
    String Address;
    private  Fragment fg;
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

                get_number = number;

        final Firebase FirebaseRef = new Firebase("https://member-activity.firebaseio.com/Activity");
        Query memberQuery = FirebaseRef.orderByChild("Facebook_ID").equalTo(111111111111111l);
        memberQuery.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String memberKey01 = dataSnapshot.getKey();
                condition = (String) dataSnapshot.child("condition").getValue();
                Address = (String) dataSnapshot.child("now").getValue();
                Log.d("d", "hhhh" + Address);
                if (Objects.equals(condition, "false")) {
                    final Game_areaFragment game_areaFragment =
                            Game_areaFragment.newInstance();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.game_root, game_areaFragment, "game_areaFragment")
                            .addToBackStack(null)
                            .commit();
                } else if (Objects.equals(condition, "true")) {

                    final Game_areaFragment game_areaFragment =
                            Game_areaFragment.newInstance();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.game_root, game_areaFragment, "game_areaFragment")
                            .addToBackStack(null)
                            .commit();

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
    public void Choose_area(String data) {




        final android.support.v4.app.FragmentManager fragmentManager =
                getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("game_areaFragment");
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.hide(fragment);
        //fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();


        final Start_GameFragment start_gameFragment =
                Start_GameFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("address", data);
        bundle.putString("number", get_number);

        bundle.putString("123", Address);
        start_gameFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_root, start_gameFragment, " start_gameFragment")
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

    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event){
        Log.d("test","event");
        AlertDialog.Builder bdr = new AlertDialog.Builder(this);
        bdr.setMessage("確定離開遊戲嘛？");
        bdr.setTitle("提醒");
        bdr.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameActivity.this.finish();

            }
        });

        bdr.setNegativeButton("繼續", null);
                 bdr.show();
                 if (fg instanceof Start_GameFragment) {
                     Start_GameFragment.onKeyDown(keyCode, event);

                 }
                 return super.onKeyDown(keyCode, event);
             }


         }
