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
public class SingUpFragment extends Fragment {

    Button sf_ok;
    Button sf_cancle;

    public SingUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sing_up, container, false);

        sf_ok = (Button)view.findViewById(R.id.sf_ok);

        sf_cancle = (Button)view.findViewById(R.id.tf_cancle);

        return view;
    }

}
