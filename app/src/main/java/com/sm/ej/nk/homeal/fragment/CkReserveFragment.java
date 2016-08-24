package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.CkWriteReViewActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkReserveFragment extends Fragment {

    public static CkReserveFragment createInstance(){
        final CkReserveFragment pageFragment = new CkReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkReserveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ck_reserve, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
    @OnClick(R.id.btn_ck_reserve_review)
    public void onSettingadvice(){
        Intent intent = new Intent(getActivity(), CkWriteReViewActivity.class);
        startActivity(intent);
    }

}
