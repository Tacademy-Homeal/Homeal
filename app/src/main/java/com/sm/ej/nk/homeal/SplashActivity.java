package com.sm.ej.nk.homeal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.gcm.RegistrationIntentService;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.manager.PropertyManager;
import com.sm.ej.nk.homeal.request.FacebookLoginRequest;
import com.sm.ej.nk.homeal.request.ProfileRequest;

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
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                doRealStart();
            }
        };
         setUpIfNeeded();
    }
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    private void setUpIfNeeded() {
        String regId = PropertyManager.getInstance().getRegistartionId();
        if (!regId.equals("")) {
            doRealStart();
        } else {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }
    private void doRealStart(){
        ProfileRequest request = new ProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                if(result.getResult().equals("cooker")){
                    HomealApplication.changeCooker();
                }else if(result.getResult().equals("eater")){
                    HomealApplication.changeEater();
                }
                moveMainActivity();
            }
            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e)  {
                if (errorCode == -1) {
                    if (errorMessage.equals("not login")) {
                        loginSharedPreference();
                        return;
                    }
                }moveLoginActivity();
            }
        });
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
    private void moveLoginActivity(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 1000);
    }

    public static final String TYPE = "cooker";

    private void moveMainActivity(){
       Long userid = (Long)getIntent().getLongExtra(ChattingActivity.EXTRA_USER,-100);
        //User user = new User();
        if(userid == -100){
                if(HomealApplication.isCooker() == true){
                    Intent intent = new Intent(this,LoginActivity.class);
                    long cooker = 1;
                    intent.putExtra(TYPE,cooker);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this,LoginActivity.class);
                    long eater = 2;
                    intent.putExtra(TYPE,eater);
                    startActivity(intent);
                }
        }else{
            Intent mainIntent;
            if(HomealApplication.isCooker() == true){
                mainIntent = new Intent(this,CkMainActivity.class);
                mainIntent.putExtra(CkMainActivity.EXTRA_TAB_INDEX,CkMainActivity.CK_CHAT_LIST);
            }else{
                mainIntent = new Intent(this,EtMainActivity.class);
                mainIntent.putExtra(EtMainActivity.EXTRA_TAB_INDEX,EtMainActivity.ET_CHAT);
            }

            Intent chatIntent = new Intent(this,ChattingActivity.class);

            chatIntent.putExtra(ChattingActivity.EXTRA_USER,userid);
            Intent[] intents = {mainIntent, chatIntent};
            startActivities(intents);
        }
        finish();
    }


    private void loginSharedPreference() {
        if (isFacebookLogin()) {
            processFacebookLogin();}
//        } else if (isAutoLogin()) {
//            processAutoLogin();}
        else {
            moveLoginActivity();
        }
    }

    private boolean isAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        return !TextUtils.isEmpty(email);
    }


    private void processAutoLogin() {
//        String email = PropertyManager.getInstance().getEmail();
//        if (!TextUtils.isEmpty(email)) {
//            String password = PropertyManager.getInstance().getPassword();
//            String regid = PropertyManager.getInstance().getRegistartionId();
//            FacebookLoginRequest request = new FacebookLoginRequest(this, token, regid);
//            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
//                @Override
//                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
//                    moveMainActivity();
//                }
//
//                @Override
//                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
//                    moveLoginActivity();
//                }
//            });
//        }
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
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                    if (result.getCode() == 1) {
                        moveMainActivity();
                    } else {
                        resetFacebookAndMoveLoginActivity();
                    }
                }
                @Override
                public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
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

                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                        if (result.getCode() == 1) {
                            moveMainActivity();
                        } else {
                            resetFacebookAndMoveLoginActivity();
                        }
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
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
