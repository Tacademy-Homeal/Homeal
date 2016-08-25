package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

        initData();

        mAdapter.setOnAdapterItemClickListener(new CkReserveAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, CkReserveData ckReserveData, int position) {
                if (getId() % 2 == 0) {
                  //  showdialog();
                    Toast.makeText(getContext(), "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), CkWriteReViewActivity.class));
                }
            }
        });
        return view;
    }

//    private void showdialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
//        selectedPosition = 2;
//        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                selectedPosition = i;
//            }
//        });
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (selectedPosition != -1) {
//                    Toast.makeText(MainActivity.this, "selected : " + items[selectedPosition], Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        builder.create().show();
//    }

//}


private void initData(){
        Random r=new Random();
        for(int i=0;i<20;i++){
        CkReserveData data=new CkReserveData();
        data.setFoodname("foodname "+i);
        data.setReservedate("date"+i);
        data.setReserveperson("person"+i);
        data.setEtname("ckname"+i);
        data.setReservestate("state"+i);
        data.setEtpicture(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
        if(i%2==0)data.setBtnagree("승인");
        else data.setBtnagree("후기 작성");
        data.setBtndisagree("거절");
        mAdapter.add(data);
        }

        }

@Override
public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mAdapter=new CkReserveAdapter();
        }

        }
