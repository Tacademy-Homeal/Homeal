package com.sm.ej.nk.homeal;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.gcm.RegistrationIntentService;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.manager.PropertyManager;
import com.sm.ej.nk.homeal.request.FacebookLoginRequest;

public class SplashActivity extends AppCompatActivity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 100;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    LoginManager loginManager;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CalendarManager.clearInstance();
//
//        loginManager = LoginManager.getInstance();
//        callbackManager = CallbackManager.Factory.create();

//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                doRealStart();
//            }
//        };
//
//        setUpIfNeeded();

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


    private void setUpIfNeeded(){
        if(checkPlayServices()){
            String regId = PropertyManager.getInstance().getRegistartionId();
            if(!regId.equals("")){
                doRealStart();
            }else{
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }
    private void doRealStart(){
        //what is doRealStart()????
//        ProfileRequest request = new ProfileRequest(this);
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
//
//            @Override
//            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
//                moveMainActivity();
//            }
//
//            @Override
//            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
//                if (errorCode == -1) {
//                    if (errorMessage.equals("not login")) {
//                        loginSharedPreference();
//                        return;
//                    }
//                }
//                moveLoginActivity();
//            }
//        });
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Log.e("GCM_HOMEAL","SUCCESS");
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    private void moveMainActivity(){
        User user = (User)getIntent().getSerializableExtra(ChattingActivity.EXTRA_USER);
        if(user == null){
            startActivity(new Intent(this,EtMainActivity.class));
        }else{
            Intent mainIntent = new Intent(this,EtMainActivity.class);
            mainIntent.putExtra(EtMainActivity.EXTRA_TAB_INDEX,EtMainActivity.ET_CHAT);
            //            mainIntent.putExtra(CkMainAcivity.EXTRA_TAB_INDEX,EtMainActivity.ET_CHAT);
            Intent chatIntent = new Intent(this,ChattingActivity.class);
            chatIntent.putExtra(ChattingActivity.EXTRA_USER,user);
            Intent[] intents = {mainIntent, chatIntent};
            startActivities(intents);
        }
        finish();
    }


    private void loginSharedPreference() {
        if (isFacebookLogin()) {
            processFacebookLogin();
        }  else {
            moveLoginActivity();
        }
    }

    private boolean isFacebookLogin() {
        if (!TextUtils.isEmpty(PropertyManager.getInstance().getFacebookId())) {
            return true;
        }
        return false;
    }

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
            resetFacebookAndMoveLoginActivity();
            return;
        }
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regId = PropertyManager.getInstance().getRegistartionId();
            FacebookLoginRequest request = new FacebookLoginRequest(this, token, regId );
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
                    if (result.getCode() == 1) {
                        moveMainActivity();
                    } else {
                        resetFacebookAndMoveLoginActivity();
                    }
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
                    loginManager.logOut();
                    facebookLogin();
                }
            });
        } else {
            facebookLogin();
        }
    }

    private void resetFacebookAndMoveLoginActivity() {
        loginManager.logOut();
        PropertyManager.getInstance().setFacebookId("");
        moveLoginActivity();
    }


    //some modeify facebook
    private void facebookLogin() {
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
                    resetFacebookAndMoveLoginActivity();
                    return;
                }
                FacebookLoginRequest request = new FacebookLoginRequest(SplashActivity.this, accessToken.getToken(),
                        PropertyManager.getInstance().getRegistartionId());

                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
                        if (result.getCode() == 1) {
                            moveMainActivity();
                        } else {
                            resetFacebookAndMoveLoginActivity();
                        }
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
                        resetFacebookAndMoveLoginActivity();
                    }
                });

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        loginManager.logInWithReadPermissions(this, null);
    }





}
