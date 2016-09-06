package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.InfoCkDetailActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.EtHomeAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.StoreListResult;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkPageListRequest;

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
        String pageno = "1";
        String rowCount = "10";
        CkPageListRequest request = new CkPageListRequest(getContext() ,pageno, rowCount);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<StoreListResult>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<StoreListResult>> request, NetworkResult<StoreListResult> result) {
                        datas = result.getResults().getStoreList();
                        mAdapter.clear();
                        mAdapter.addList(datas);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<StoreListResult>> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });


        return v;
    }


    public static final String INTENT_CK_ID = "asd";
    public static final String INTENT_ZZIM_ID = "asdf";
    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_CK_ID, );
        intent.putExtra(INTENT_CK_ID, datas.get(position));
        Log.e("ssong", datas.get(position).getId());
        intent.putExtra(INTENT_ZZIM_ID, datas.get(position));
        Log.e("eun", datas.get(position).getId());
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
