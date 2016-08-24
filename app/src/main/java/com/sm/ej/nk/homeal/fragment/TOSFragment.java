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
public class TOSFragment extends Fragment {

    @BindView(R.id.btn_tos_ok)
    Button btn_tos_ok;

    @BindView(R.id.btn_tos_backey)
    Button btn_tos_backey;


    public TOSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tos, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.btn_tos_ok)
    public void onTosOk(){
        ((LoginActivity)getActivity()).changeSingUp();

    }

    @OnClick(R.id.btn_tos_backey)
    public void onTosCancle(){

    }
}
