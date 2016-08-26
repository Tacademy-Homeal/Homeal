package com.sm.ej.nk.homeal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtHomeAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtHomeFragment extends Fragment {

    @BindView(R.id.rv_et_home_ft)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    EtHomeAdapter mAdapter;

    public static EtHomeFragment createInstance(){
        final EtHomeFragment pageFragment = new EtHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_et_home, container, false);
        ButterKnife.bind(this, v);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mAdapter = new EtHomeAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        initData();
        return v;
    }


    private void initData() {
        List<EtHomeData> datas = new ArrayList<>();
        for(int i=0; i<10; i++){
            EtHomeData data = new EtHomeData();
            data.setAddress("주소"+i);
            data.setFoodImageUrl("http://blog.jinbo.net/attach/615/200937431.jpg");
            data.setUserImageUrl("https://pixabay.com/static/uploads/photo/2014/12/17/14/20/summer-anemone-571531_960_720.jpg");
            data.setFoodName("음식이름"+i);
            data.setJjimCount(""+i);
            data.setRatingCount(i%5+1);
            data.setReviewCount(""+i);
            data.setName("이름"+i);
            datas.add(data);
        }
        mAdapter.addList(datas);
    }
}
