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
import android.widget.Toast;

import com.sm.ej.nk.homeal.CkWriteReViewActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.CkReserveAdapter;
import com.sm.ej.nk.homeal.data.CkReserveData;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkReserveFragment extends Fragment {
    @BindView(R.id.rv_ck_reserve)
    RecyclerView CkReserveView;
    CkReserveAdapter mAdapter;

    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;

    public static CkReserveFragment createInstance() {
        final CkReserveFragment pageFragment = new CkReserveFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public CkReserveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ck_reserve, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        CkReserveView.setAdapter(mAdapter);
        CkReserveView.setLayoutManager(manager);
        AlertDialog dialog;
        setCookerButton();
        initData();
        return view;
    }
    private void setCookerButton(){
        mAdapter.setOnAgreeButtonClickListener(new CkReserveAdapter.OnAagreeButtonClickLIstener() {
            @Override
            public void onAagreeButtonClick(View view, CkReserveData data, int position) {
                Toast.makeText(getContext(),"승인되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnDisagreeButtonClickLIstener(new CkReserveAdapter.OnDisagreeButtonClickLIstener() {
            @Override
            public void onDisagreeButtonClick(View view, CkReserveData data, int position) {
                Toast.makeText(getContext(),"거절 되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnreviewAdapterItemClickListener(new CkReserveAdapter.OnreviewButtonClickLIstener() {
            @Override
            public void onreviewAdapterItemClick(View view, CkReserveData data, int position) {
               switch (data.getReserveState()){
                   case TYPE_REQUEST_COMPLETE:
                       showDialog();
                       break;
                   case TYPE_EAT_COMPLETE:
                       Intent intent = new Intent(getActivity(), CkWriteReViewActivity.class);
                       startActivity(intent);
                       break;
               }
            }
        });
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


    private void initData() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            CkReserveData data = new CkReserveData();
            data.setFoodName("Noodle");
            data.setReservePerson("Namgil");
            data.setEtName("Lee");
            data.setReserveDate("2010.10.5");

            if(i < 5) {
                data.setReserveState(TYPE_REQUEST);
            }else if(i < 10 && i > 5){
                data.setReserveState(TYPE_REQUEST_COMPLETE);
            }else if(i< 15 && i > 10){
                data.setReserveState(TYPE_EAT_COMPLETE);
            }else{
                data.setReserveState(TYPE_END);
            }

            data.setEtPictureUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDLcDeVw9X8oxxP6EpKmgcBi5iG9SWnxThw0WHhg2DevUyEOk9");
            mAdapter.add(data);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new CkReserveAdapter();
    }

}
