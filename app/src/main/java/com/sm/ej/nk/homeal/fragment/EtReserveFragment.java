package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.EtWriteReviewActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtReserveFragment extends Fragment {

    public static EtReserveFragment createInstance(){
        final EtReserveFragment pageFragment = new EtReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @BindView(R.id.btn_et_reserve_write)
    Button wrtiteBtn;

    public EtReserveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_et_reserve, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_et_reserve_write)
    public void moveReserveWrite(){
        Intent intent = new Intent(getActivity(), EtWriteReviewActivity.class);
        startActivity(intent);
    }

}
