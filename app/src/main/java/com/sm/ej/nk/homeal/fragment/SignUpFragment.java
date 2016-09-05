package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sm.ej.nk.homeal.LoginActivity;
import com.sm.ej.nk.homeal.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    @BindView(R.id.sf_ok)
    Button sf_ok;

    @BindView(R.id.edit_frist_name)
    EditText edit_frist_name;

    @BindView(R.id.edit_second_name)
    EditText edit_second_name;

    @BindView(R.id.singUp_spinner_ck_year)
    Spinner spinner_year;

    @BindView(R.id.singUp_spinner_ck_month)
    Spinner spinner_month;

    @BindView(R.id.singUp_spinner_ck_day)
    Spinner spinner_day;



    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);



        ArrayList<String> monthList = new ArrayList<>();
        for (int month = 1; month < 13; month++) {
            monthList.add(String.valueOf(month));
        }
        ArrayAdapter<String> monthAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, monthList);
        spinner_month.setAdapter(monthAdatper);

        ArrayList<String> dayList = new ArrayList<>();
        for (int day = 1; day < 32; day++) {
            dayList.add(String.valueOf(day));
        }
        ArrayAdapter<String> dayAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dayList);
        spinner_day.setAdapter(dayAdatper);

        ArrayList<String> yearList = new ArrayList<>();
        for (int year = 1930; year < 2031; year++) {
            yearList.add(String.valueOf(year));
        }
        ArrayAdapter<String> yearAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, yearList);
        spinner_year.setAdapter(yearAdatper);

        return view;
    }

    @OnClick(R.id.sf_ok)
    public void onSingUpOk(){
        ((LoginActivity)getActivity()).moveMainActivity();

//        if(!TextUtils.isEmpty(editText.getText().toString())){
//        }else{
//            showDialog();
//        }
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

    private static final int GET_IMAGE = 1;



}
