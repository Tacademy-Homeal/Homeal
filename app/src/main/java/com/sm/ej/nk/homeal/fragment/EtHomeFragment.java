package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.InfoCkDetailActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtHomeAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkPageListRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtHomeFragment extends Fragment implements EtHomeAdapter.OnReviewitemClickListener, EtHomeAdapter.OnJjimitemClickListener, EtHomeAdapter.OnViewClickListener{

    @BindView(R.id.rv_et_home_ft)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    EtHomeAdapter mAdapter;
    List<EtHomeData> datas;

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
        mAdapter.setOnJjimitemClickListener(this);
        mAdapter.setOnReciewClickListener(this);
        mAdapter.setOnViewClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        initData();
        String pageno = "1";
        String rowCount = "10";
        CkPageListRequest request = new CkPageListRequest(getContext() ,pageno, rowCount);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<EtHomeData>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<EtHomeData>> request, NetworkResult<EtHomeData> result) {
                EtHomeData list = result.getResult();
                mAdapter.clear();
                mAdapter.add(list);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<EtHomeData>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
        return v;
    }


    private void initData() {
        datas = new ArrayList<>();
        for(int i=0; i<10; i++){
            EtHomeData data = new EtHomeData();
            data.setAddress("주소"+i);
            data.setFoodImageUrl("http://blog.jinbo.net/attach/615/200937431.jpg");
            data.setImage("https://pixabay.com/static/uploads/photo/2014/12/17/14/20/summer-anemone-571531_960_720.jpg");
            data.setFoodName("음식이름"+i);
            data.setBookmarkCnt(""+i);
            data.setGrade(""+i%5+1);
            data.setReviewCnt(""+i);
            data.setName("이름"+i);
            datas.add(data);
        }
        mAdapter.addList(datas);
    }

    public static final String INTENT_CK_ID = "asd";
    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_CK_ID, );
        intent.putExtra(INTENT_CK_ID, datas.get(position));

        startActivity(intent);
    }

    @Override
    public void onJjimitemClick(View view, int position) {
        Snackbar.make(view,"이미지, 텍스트 변경", Snackbar.LENGTH_SHORT).show();
    }

    public static final int INTENT_SCROLL = 1;
    @Override
    public void onReviewitemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_SCROLL, );

        startActivity(intent);
    }
}
