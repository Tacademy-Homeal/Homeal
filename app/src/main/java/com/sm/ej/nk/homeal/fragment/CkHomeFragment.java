package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.CkMainActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.CkHomeAdapter;
import com.sm.ej.nk.homeal.data.CkHomeData;
import com.sm.ej.nk.homeal.data.CkHomeItemData;
import com.sm.ej.nk.homeal.data.PersonData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkHomeFragment extends Fragment {
    @BindView(R.id.rv_ck_home)
    RecyclerView rv;

    CkHomeAdapter mAdapter;

    public CkHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, view);
        rv = (RecyclerView)view.findViewById(R.id.rv_ck_home);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mAdapter = new CkHomeAdapter();
        mAdapter.setHeader(init());
        rv.setAdapter(mAdapter);
        mAdapter.additem(initItem());

        final CkMainActivity parentActivity = (CkMainActivity)getActivity();
        parentActivity.setOnFabClickListener(new CkMainActivity.OnFabClickListener() {
            @Override
            public void onFabClick(View view, int mode) {
                if(mode == parentActivity.MODE_EDIT){
                    mAdapter.setvisivle();
                }else if(mode == parentActivity.MODE_OK){
                    mAdapter.setinvisible();
                }
            }
        });

        return view;
    }

    public static CkHomeFragment createInstance(){
        final CkHomeFragment pageFragment = new CkHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    private CkHomeData init(){
        CkHomeData data = new CkHomeData();
        data.jjimCount = "15";
        data.cleanScore=4;
        data.kindScore=4;
        data.priceScore=4;
        data.reviewCount="14";
        data.tasteScore=5;
        data.totalScore=5;
        PersonData user = new PersonData();
        user.userImage = "https://pbs.twimg.com/profile_images/740493838318329859/4zzw7Ywa_400x400.jpg";
        user.userAddress="우리집";
        user.userName="이름213";
        data.user =user;
        return data;
    }
    private List<CkHomeItemData> initItem(){
        List<CkHomeItemData> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            CkHomeItemData data = new CkHomeItemData();
            data.foodimage = "http://img1.ruliweb.com/img/cmu_yu02/689/688024_1.jpg";
            data.foodInfo="dkmgmsdfsdfksdofksodpkfposdkfopskdofksdopfksopdkfposkdfopsdkfopskdopfksdpofksdopfksopdfkopsdkfpsdkfposdkfopsdkfopsfk";
            data.foodName="음식이름"+i;
            data.foodPrice="15";
            PersonData user = new PersonData();
            user.userImage = "https://pbs.twimg.com/profile_images/740493838318329859/4zzw7Ywa_400x400.jpg";
            user.userAddress="우리집";
            user.userName="이름213";
            data.user = user;
            list.add(data);
        }
        return list;
    }
}
