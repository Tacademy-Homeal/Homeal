package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkHomeFragment extends Fragment {

    public CkHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    public static CkHomeFragment createInstance(){
        final CkHomeFragment pageFragment = new CkHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

}
