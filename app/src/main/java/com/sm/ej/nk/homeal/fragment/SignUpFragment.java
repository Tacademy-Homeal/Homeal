package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    @BindView(R.id.sf_ok)
    Button sf_ok;

    @BindView(R.id.edit_sign_up)
    EditText editText;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.sf_ok)
    public void onSingUpOk(){
        if(!TextUtils.isEmpty(editText.getText().toString())){
            ((LoginActivity)getActivity()).moveMainActivity();
        }else{
            showDialog();
        }
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.sign_up));
        builder.show();
    }
}
