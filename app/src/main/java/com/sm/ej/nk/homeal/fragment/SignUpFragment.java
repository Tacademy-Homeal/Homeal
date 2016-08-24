package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    @BindView(R.id.sf_ok)
    Button sf_ok;

    @BindView(R.id.sf_cancle)
    Button sf_cancle;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.sf_ok)
    public void onSingUpOk(){
        ((LoginActivity)getActivity()).moveMainActivity();
    }

    @OnClick(R.id.sf_cancle)
    public void onSingUpCancle(){
    }
}
