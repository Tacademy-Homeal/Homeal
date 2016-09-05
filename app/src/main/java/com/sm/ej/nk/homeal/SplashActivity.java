package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.sm.ej.nk.homeal.manager.CalendarManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//
//        TestRequest request = new TestRequest(this);
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
//            @Override
//            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
//                Log.e("ssong", result.getMessage());
//                moveLoginActivity();
//            }
//
//            @Override
//            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
//
//            }
//        });
        CalendarManager.clearInstance();
        moveLoginActivity();
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
    private void moveLoginActivity(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }


}
