package com.sm.ej.nk.homeal.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.EtWriteReviewActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtReserveAdapter;
import com.sm.ej.nk.homeal.data.EtReserveData;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
//
public class EtReserveFragment extends Fragment {
    @BindView(R.id.rv_et_reserve)
    RecyclerView EtReserveView;
    EtReserveAdapter mAdapter;

    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;



    public static EtReserveFragment createInstance() {
        final EtReserveFragment pageFragment = new EtReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtReserveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_et_reserve, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        EtReserveView.setAdapter(mAdapter);
        EtReserveView.setLayoutManager(manager);

        initData();

       mAdapter.setOnReviewItemClickListener(new EtReserveAdapter.OnReserveAdapterClick() {

           @Override
           public void onReserveAdapterClick(View view, EtReserveData etReserveData, int position) {
               switch (etReserveData.getReserverState()){
                   case TYPE_REQUEST :
                       showDialog();
                       break;
                   case TYPE_REQUEST_COMPLETE :
                       showDialog();
                       break;
                   case TYPE_EAT_COMPLETE :
                       Intent intent = new Intent(getActivity(),EtWriteReviewActivity.class);
                       startActivity(intent);
                       break;
                   case TYPE_END :
                       break;
               }
           }
        });
        return view;
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("취소",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.et_reservation_cancle));
        builder.show();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new EtReserveAdapter();
    }


    private void initData() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            EtReserveData data = new EtReserveData();
            data.setFoodName("kimch");
            data.setReserveDate("date" + i);
            data.setReservePerson(" Lee sung");
            data.setCkName("Kim nam gil");

            if(i > 0 && i < 12) {
                data.setReserverState(TYPE_REQUEST);
            }else if(i < 15){
                data.setReserverState(TYPE_REQUEST_COMPLETE);
            }else{
                data.setReserverState(TYPE_EAT_COMPLETE);
            }
            data.setCkPictureUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTACDX3BGsb6q0XNgMjYnyIEHxalWJ2JAC2xwEev-gsonJGBi5GassamzA");
            mAdapter.add(data);
        }
    }

}
