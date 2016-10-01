package com.example.jiarou.sharelove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.peter.focus.GlobalVariable;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peter on 2016/8/18.
 */
public class Login extends AppCompatActivity{
    final static String DB_URL = "https://member-139bd.firebaseio.com/";

    CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //初始化臉書
        FacebookSdk.sdkInitialize(getApplicationContext());

        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //建立callbackManager處理login回呼
        callbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = (LoginButton)findViewById(R.id.loginButton);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                final Firebase member = new Firebase(DB_URL);

                Query member2 = member.orderByChild("Facebook_ID").equalTo(loginResult.getAccessToken().getUserId());

                member2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                            globalVariable.setUserId(loginResult.getAccessToken().getUserId());

                            //成功登入，跳轉至地圖頁面
                            Intent intent;
                            intent = new Intent();
                            intent.setClass(Login.this, MapsActivity.class);
                            startActivity(intent);
                        }else{
                            Map<String, Object> newMember = new HashMap<String, Object>();
                            Map<String, String> favVendor = new HashMap<String, String>();
                            favVendor.put("Vendor_ID", "");

                            newMember.put("Birthday", "");
                            newMember.put("Default_Zone", null);
                            newMember.put("Facebook_ID", loginResult.getAccessToken().getUserId());
                            newMember.put("Favorite_Vendors", favVendor);
                            newMember.put("Lottery_Numbers", null);
                            newMember.put("Nickname", "");
                            newMember.put("Owned_Coupons", null);
                            newMember.put("Owned_Points", 0);
                            newMember.put("Photos", null);
                            newMember.put("Share_Times", 0);
                            newMember.put("Suspended", false);

                            member.push().setValue(newMember);

                            GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                            globalVariable.setUserId(loginResult.getAccessToken().getUserId());

                            //成功登入，跳轉至地圖頁面
                            Intent intent;
                            intent = new Intent();
                            intent.setClass(Login.this, MapsActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                Map<String, Object> newMember = new HashMap<String, Object>();
                newMember.put("Birthday", "");
                newMember.put("Default_Zone", null);
                newMember.put("Facebook_ID", loginResult.getAccessToken().getUserId());
                newMember.put("Favorite_Vendors", null);
                newMember.put("Lottery_Numbers", null);
                newMember.put("Nickname", "");
                newMember.put("Owned_Coupons", null);
                newMember.put("Owned_Points", 0);
                newMember.put("Photos", null);
                newMember.put("Share_Times", 0);
                newMember.put("Suspended", false);

                member.push().setValue(newMember);

                //成功登入，跳轉至地圖頁面
                Intent intent;
                intent = new Intent();
                intent.setClass(Login.this, MapsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    //接收callbackManager回傳資料
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
