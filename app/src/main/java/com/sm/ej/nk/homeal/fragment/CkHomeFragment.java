package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.MapActivity;
import com.sm.ej.nk.homeal.MenuAddActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkHomeFragment extends Fragment {

    Button btnMap;

    Button btnMenu;

    public CkHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_et_home, container, false);
        ButterKnife.bind(this, v);
        Log.e("ssong","ddd");

        btnMap = (Button)v.findViewById(R.id.btn_ck_home_map);
        btnMenu = (Button)v.findViewById(R.id.btn_ck_home_menu);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(), MenuAddActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public static CkHomeFragment createInstance(){
        final CkHomeFragment pageFragment = new CkHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

}
