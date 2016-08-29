package com.sm.ej.nk.homeal;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class HomealApplication extends Application {

    private static Context context;
    private static Boolean isCooker =false;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());

    }

    public static boolean isCooker(){
        return isCooker;
    }

    public static void changeCooker(){
        isCooker = true;
    }

    public static void changeEater(){
        isCooker  = false;
    }

    public static Context getContext() {
        return context;
    }
}
