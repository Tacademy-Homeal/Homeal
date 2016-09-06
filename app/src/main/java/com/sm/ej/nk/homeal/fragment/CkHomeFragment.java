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
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkPageCheckRequest;

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
    List<CkScheduleData> list;

    public List<CkScheduleData> getCkSchedule(){
        return this.list;
}
    public CkHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();

        rv = (RecyclerView)view.findViewById(R.id.rv_ck_home);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mAdapter = new CkHomeAdapter(getContext());

        CkPageCheckRequest request = new CkPageCheckRequest(getContext(), "2");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<CkInfoResult>() {
            @Override
            public void onSuccess(NetworkRequest<CkInfoResult> request, CkInfoResult result) {
                mAdapter.setResult(result);
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<CkInfoResult> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
        mAdapter.setOnHomeAdapterClickListner(new CkHomeAdapter.OnHomeAdapterClickListener() {
            @Override
            public void onHomeAdapterClick(View view, int position, CkDetailMenuData data) {
                if(listener !=null){
                    listener.onHomeFragmentClick(view, position, data);
                }
            }
        });

        mAdapter.setOnHomeViewClick(new CkHomeAdapter.OnHomeViewClickListener() {
            @Override
            public void onHomeViewClick(View view, int position, CkDetailMenuData data) {
                if(viewListener!=null){
                    viewListener.onHomeViewClick(view, position, data);
                }
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

    @Override
    public void onFabClick(View view, int mode) {
        if(mode == parentActivity.MODE_EDIT){
            mAdapter.setvisivle();
        }else if(mode == parentActivity.MODE_OK){
            mAdapter.setinvisible();
        }
    }

    public interface OnHomeFragmentClickListener{
        public void onHomeFragmentClick(View view, int position, CkDetailMenuData data);
    }
    OnHomeFragmentClickListener listener;
    public void setOnHomeFragmentClickListner(OnHomeFragmentClickListener listener){
        this.listener = listener;
    }

    public interface  OnHomeViewClickLIstener{
        public void onHomeViewClick(View view, int position, CkDetailMenuData data);
    }
    OnHomeViewClickLIstener viewListener;
    public void setOnHomeViewClickListener(OnHomeViewClickLIstener listener){
        viewListener = listener;
    }
}
