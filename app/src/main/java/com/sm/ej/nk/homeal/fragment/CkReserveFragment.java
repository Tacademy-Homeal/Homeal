package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private static final int TYPE_QUEST = 0;
    private static final int TYPE_QUEST_COMPLETE = 1;
    private static final int TYPE_EAT_END = 2;
    private static final int TYPE_END = 3;

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
        return view;
    }

    private void initData() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            CkReserveData data = new CkReserveData();
            data.setFoodName("Noodle");
            data.setReservePerson("Namgil");
            data.setEtName("Lee");
            data.setReserveDate("2010.10.5");

            if(i < 10) {
                data.setReserveState(TYPE_QUEST);
            }else if(i < 13 || i < 10){
                data.setReserveState(TYPE_QUEST_COMPLETE);
            }else if(i< 17 || i > 13){
                data.setReserveState(TYPE_EAT_END);
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
