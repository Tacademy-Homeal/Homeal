package com.sm.ej.nk.homeal;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class HomealApplication extends Application {
    private static Context context;
    private static boolean isCooker =false;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static boolean isCooker(){
        return isCooker;
    }

    public static Context getContext() {
        return context;
    }
}
