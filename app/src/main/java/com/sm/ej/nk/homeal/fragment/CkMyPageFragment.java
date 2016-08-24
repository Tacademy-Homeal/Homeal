package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.CkPersonalDataActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkMyPageFragment extends Fragment {

    public static CkMyPageFragment createInstance() {
        final CkMyPageFragment pageFragment = new CkMyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkMyPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ck_my_page, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
    @OnClick(R.id.btn_ck_mypage_personal)
    public void onCkMypagePersonal(){
        Intent intent = new Intent(getActivity(), CkPersonalDataActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_ck_mypage_setting)
    public void onCkMypageSetting(){
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

}
