package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.PersonalDataActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SettingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    public static MyPageFragment createInstance(){
        final MyPageFragment pageFragment = new MyPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_page, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_mypage_personal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalDataActivity.class);
                startActivity(intent);
            }
        });

        btn = (Button)view.findViewById(R.id.btn_mypage_setting);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
