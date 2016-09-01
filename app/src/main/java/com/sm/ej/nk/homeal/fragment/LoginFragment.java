package com.sm.ej.nk.homeal.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.FontData;
import com.sm.ej.nk.homeal.manager.FontManager;

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


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        callbackManager = CallbackManager.Factory.create();
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
/*
    @OnClick(R.id.btn_login_ft_ok)
    public void onLoginOkBtn() {

    }*/

    @OnClick(R.id.btn_login_facebook)
    public void onClickFacebook() {

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radiobtn_login_ft_ck:
                HomealApplication.changeCooker();
                break;
            case R.id.radiobtn_login_ft_eater:
                HomealApplication.changeEater();
                break;
        }
        ((LoginActivity) getActivity()).changeTos();;
    }
/*
    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "login manager...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }*/

}
