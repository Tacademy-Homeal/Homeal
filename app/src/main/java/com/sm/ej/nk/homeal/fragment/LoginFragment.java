package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.btn_login_ft_ok)
    Button btn_login_ft_ok;

    @BindView(R.id.btn_login_ft_back)
    Button btn_login_ft_back;

    @BindView(R.id.radiobtn_login_ft_ck)
    RadioButton radiobtn_cooker;

    @BindView(R.id.radiobtn_login_ft_eater)
    RadioButton radiobtn_eater;

    @BindView(R.id.radio_group_login)
    RadioGroup radioGroup;


    public LoginFragment() {
        // Required empty public constructor
    }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view =  inflater.inflate(R.layout.fragment_login, container, false);
            ButterKnife.bind(this, view);


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedid) {
                    if (checkedid == R.id.radiobtn_login_ft_ck) {
                        HomealApplication.changeCooker();
                    }else if(checkedid == R.id.radiobtn_login_ft_eater){
                        HomealApplication.changeEater();
                    }
                }
            });

            return view;
    }


    @OnClick(R.id.btn_login_ft_ok)
    public void onLoginOkBtn(){

        ((LoginActivity)getActivity()).changeTos();

    }

    @OnClick(R.id.btn_login_ft_back)
    public void onLoginBackkey(){

    }
}
