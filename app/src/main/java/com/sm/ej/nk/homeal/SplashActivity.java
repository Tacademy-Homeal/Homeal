package com.sm.ej.nk.homeal;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.manager.CalendarManager;
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
         setUpIfNeeded();
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


    private void setUpIfNeeded() {
        String regId = PropertyManager.getInstance().getRegistartionId();
        if (!regId.equals("")) {
            doRealStart();
        } else {
            new AsyncTask<Void,Void,Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    String token = FirebaseInstanceId.getInstance().getToken();
                    PropertyManager.getInstance().setRegistrationId(token);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    doRealStart();
                }
            }.execute();
        }
    }
    private void doRealStart(){
        moveLoginActivity();

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
//        if (accessToken != null) {
//            String token = accessToken.getToken();
//            String regId = PropertyManager.getInstance().getRegistartionId();
//            FacebookLoginRequest request = new FacebookLoginRequest(this, token, regId );
////            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
////                @Override
////                public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
////                    if (result.getCode() == 1) {
////                        moveMainActivity();
////                    } else {
////                        resetFacebookAndMoveLoginActivity();
////                    }
////                }
////
////                @Override
////                public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
////                    loginManager.logOut();
////                    facebookLogin();
////                }
////            });
////        } else {
////            facebookLogin();
////        }
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

//                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
//                    @Override
//                    public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
//                        if (result.getCode() == 1) {
//                            moveMainActivity();
//                        } else {
//                            resetFacebookAndMoveLoginActivity();
//                        }
//                    }
//
//                    @Override
//                    public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
//                        resetFacebookAndMoveLoginActivity();
//                    }
//                });

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
