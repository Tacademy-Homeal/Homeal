package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
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

    @BindView(R.id.btn_et_mypager_personal)
    Button btnPersonal;

    @BindView(R.id.btn_et_setting)
    Button btnSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_et_my_page, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick(R.id.btn_et_mypager_personal)
    public void changeEtPersonalDataActivity(){

    }

    @OnClick(R.id.btn_et_setting)
    public void changeSettingActivity(){

    }


}
