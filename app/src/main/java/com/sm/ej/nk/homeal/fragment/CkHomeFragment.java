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
public class CkHomeFragment extends Fragment implements CkMainActivity.OnFabClickListener{
    @BindView(R.id.rv_ck_home)
    RecyclerView rv;

    CkHomeAdapter mAdapter;
    CkMainActivity parentActivity;

    public CkHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentasasfasfaf
        View view=  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, view);

        rv = (RecyclerView)view.findViewById(R.id.rv_ck_home);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mAdapter = new CkHomeAdapter(getContext());
        mAdapter.setHeader(init());
        rv.setAdapter(mAdapter);
        mAdapter.additem(initItem());
        mAdapter.setOnHomeAdapterClickListner(new CkHomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(View view, int position, CkHomeItemData data) {
                if(listener !=null){
                    listener.onHomeFragmentClick(view, position, data);
                }
            }
        });

        mAdapter.setOnHomeViewClick(new CkHomeAdapter.OnHomeViewClickListener() {
            @Override
            public void onHomeViewClick(View view, int position, CkHomeItemData data) {

            }
        });
        parentActivity = (CkMainActivity)getActivity();
        parentActivity.setOnFabClickListener(this);

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
        user.userImage = "http://cfile223.uf.daum.net/image/112889584D19C77A0BA345";
        user.userAddress="우리집";
        user.userName="이름213";
        data.user =user;
        return data;
    }
    private List<CkHomeItemData> initItem(){
        List<CkHomeItemData> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            CkHomeItemData data = new CkHomeItemData();
            data.foodimage = "https://i.ytimg.com/vi/4U0xCOuwK_Y/maxresdefault.jpg";
            data.foodInfo="dkmgmsdfsdfksdofksodpkfposdkfopskdofksdopfksopdkfposkdfopsdkfopskdopfksdpofksdopfksopdfkopsdkfpsdkfposdkfopsdkfopsfk";
            data.foodName="음식이름"+i;
            data.foodPrice="15";
            PersonData user = new PersonData();
            user.userImage = "http://cfile223.uf.daum.net/image/112889584D19C77A0BA345";
            user.userAddress="우리집";
            user.userName="이름213";
            data.user = user;
            list.add(data);
        }
        return list;
    }

    @Override
    public void onFabClick(View view, int mode) {
        if(mode == parentActivity.MODE_EDIT){
            mAdapter.setvisivle();
        }else if(mode == parentActivity.MODE_OK){
            mAdapter.setinvisible();
        }
    }

    public interface OnHomeFragmentClickListener{
        public void onHomeFragmentClick(View view, int position, CkHomeItemData data);
    }
    OnHomeFragmentClickListener listener;
    public void setOnHomeFragmentClickListner(OnHomeFragmentClickListener listener){
        this.listener = listener;
    }

    public interface  OnHomeViewClickLIstener{
        public void onHomeViewClick(View view, int position, CkHomeItemData data);
    }
    public void setOnHomeViewClickListener(OnHomeViewClickLIstener listener){

    }
}
