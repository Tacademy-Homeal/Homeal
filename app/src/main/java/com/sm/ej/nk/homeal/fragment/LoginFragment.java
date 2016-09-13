package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.FontData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.manager.FontManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.manager.PropertyManager;
import com.sm.ej.nk.homeal.request.FacebookLoginRequest;
import com.sm.ej.nk.homeal.request.TestRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    @BindView(R.id.radio_group_login)
    RadioGroup radioGroup;

    @BindView(R.id.radiobtn_login_ft_eater)
    RadioButton radio_eater;

    @BindView(R.id.radiobtn_login_ft_ck)
    RadioButton radio_cooker;

    @BindView(R.id.btn_login_facebook)
    ImageButton btnFacebook;

    CallbackManager callbackManager;
    LoginManager mLoginManager;

    @BindView(R.id.textview_login_select)
    TextView loginSelectView;

    TestRequest request;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            LoginManager.getInstance().logOut();
        }
        PropertyManager.getInstance().setFacebookId("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mLoginManager = LoginManager.getInstance();
        imageSet();
        return view;
    }
    private void imageSet(){
        Typeface typeface = FontManager.getInstance().getTypeface(getActivity(), FontData.NOTO_D);
        loginSelectView.setTypeface(typeface);
        radio_eater.setTypeface(typeface);
        radio_cooker.setTypeface(typeface);
    }

    @OnClick(R.id.btn_login_facebook)
    public void onClickFacebook() {

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radiobtn_login_ft_ck:{
                HomealApplication.changeCooker();
                break;
            }
            case R.id.radiobtn_login_ft_eater:{
                HomealApplication.changeEater();
                break;
            }
        }

        loginFacebook();
    }

    private void loginFacebook() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            return;
        }
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    processAfterFacebookLogin();
                }

                @Override
                public void onCancel() {

                }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }

    private void processAfterFacebookLogin() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regid = PropertyManager.getInstance().getRegistartionId();
            FacebookLoginRequest request = new FacebookLoginRequest(getContext(), token, regid);

            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {

                @Override
                public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                    if (result.getCode() == 1) {
                        if(result.getResult().equals("empty")){
                            ((LoginActivity)getActivity()).changeTos();
                        }else{
                            String facebookId = accessToken.getUserId();
                            PropertyManager.getInstance().setFacebookId(facebookId);
                            if(result.getResult().equals("cooker") && HomealApplication.isCooker() == true){
                                ((LoginActivity) getActivity()).moveCkMainActivity();
                            }else if(result.getResult().equals("eater") && HomealApplication.isCooker() == false){
                                ((LoginActivity) getActivity()).moveEtMainAcivity();
                            }else{
                                Toast.makeText(getContext(),"해당 사항 없음",Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (result.getCode() == 2){
                        ((LoginActivity)getActivity()).changeTos();
                    }
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(getContext(), "login fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
