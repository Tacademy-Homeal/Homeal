package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.FontData;
import com.sm.ej.nk.homeal.manager.FontManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TOSFragment extends Fragment {


    @BindView(R.id.text_tos_main)
    TextView text_tos;

    public TOSFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tos, container, false);
        ButterKnife.bind(this, view);

        Typeface typeface = FontManager.getInstance().getTypeface(getActivity(), FontData.NOTO_M);
        text_tos.setTypeface(typeface);

        return view;
    }

    @OnClick(R.id.btn_tos_ok)
    public void onTosOk(){
        ((LoginActivity)getActivity()).changeSingUp();
    }

    @OnClick(R.id.btn_tos_backey)
    public void onTosCancle(){
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(""+getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.tos_dialog));
        builder.show();
    }
}
