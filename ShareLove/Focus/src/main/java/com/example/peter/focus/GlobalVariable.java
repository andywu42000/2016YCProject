package com.example.peter.focus;

import android.app.Application;

/**
 * Created by Peter on 2016/9/19.
 */
public class GlobalVariable extends Application {
    public String userId = "" ;
    public void setUserId(String userId){this.userId = userId;}
    public String getUserId(){return userId;}
}
