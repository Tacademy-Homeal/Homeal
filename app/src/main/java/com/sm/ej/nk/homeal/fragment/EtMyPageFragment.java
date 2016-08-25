package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.EtPersonalDataActivity;
import com.sm.ej.nk.homeal.EtZzimActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtMyPageFragment extends Fragment {

    public static EtMyPageFragment createInstance(){
        final EtMyPageFragment pageFragment = new EtMyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtMyPageFragment() {
        // Required empty public constructor
    }

//    @BindView(R.id.btn_et_mypager_personal)
//    Button btnPersonal;
//
//    @BindView(R.id.btn_et_setting)
//    Button btnSetting;
//
//    @BindView(R.id.btn_et_zzim)
//    Button btnzzim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_et_my_page, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick(R.id.text_et_mypage_personal)
    public void changeEtPersonalDataActivity(){
        Intent intent = new Intent(getActivity(), EtPersonalDataActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.text_et_mypage_setting)
    public void changeSettingActivity(){
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.text_et_mypage_zzim)
    public void changeZzimActivity(){
        Intent intent = new Intent(getActivity(), EtZzimActivity.class);
        startActivity(intent);
    }

}
