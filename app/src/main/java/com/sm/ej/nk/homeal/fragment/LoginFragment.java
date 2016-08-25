package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


            return view;
    }


    @OnClick(R.id.btn_login_ft_ok)
    public void onLoginOkBtn(){
        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.radiobtn_login_ft_ck:
                HomealApplication.changeCooker();
                break;
            case R.id.radiobtn_login_ft_eater:
                HomealApplication.changeEater();
                break;
        }
        ((LoginActivity)getActivity()).changeTos();
    }

}
