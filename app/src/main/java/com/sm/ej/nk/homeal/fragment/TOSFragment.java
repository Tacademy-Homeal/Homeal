package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sm.ej.nk.homeal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TOSFragment extends Fragment {

    Button ts_ok;
    Button ts_cancle;

    public TOSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tos, container, false);

        ts_ok = (Button)view.findViewById(R.id.tf_ok);
        ts_cancle = (Button)view.findViewById(R.id.tf_cancle);

        ts_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ts_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
