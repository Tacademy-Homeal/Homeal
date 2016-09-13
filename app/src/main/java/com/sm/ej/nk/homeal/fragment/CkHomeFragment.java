package com.sm.ej.nk.homeal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sm.ej.nk.homeal.CkMainActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.ReviewInfoActivity;
import com.sm.ej.nk.homeal.adapter.CkHomeAdapter;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkMenuDeleteRequest;
import com.sm.ej.nk.homeal.request.CkPageCheckRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CkHomeFragment extends Fragment implements CkMainActivity.OnFabClickListener, CkHomeAdapter.OnReviewCLickLIstener{
    @BindView(R.id.rv_ck_home)
    RecyclerView rv;

    CkHomeAdapter mAdapter;
    CkMainActivity parentActivity;
    List<CkScheduleData> list;
    List<ThumbnailsData> thumbnailsDatas;
    String cookerId;

    public List<CkScheduleData> getCkSchedule(){
        return this.list;
}
    public List<ThumbnailsData> getThumbnailsDatas() {
        return this.thumbnailsDatas;
    }
    public CkHomeFragment() {
    }

    public String getCookerId(){
        return cookerId;
    }

    public void changeMenu(List<CkDetailMenuData> data){
        mAdapter.changeMenu(data);
    }

    public void changeSchedule(List<CkScheduleData> data){
        mAdapter.changeSchedule(data);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_ck_home, container, false);
        ButterKnife.bind(this, view);

        rv = (RecyclerView)view.findViewById(R.id.rv_ck_home);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mAdapter = new CkHomeAdapter(getContext());
        mAdapter.setOnReviewClickListener(this);

        CkPageCheckRequest request = new CkPageCheckRequest(getContext(),"35");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<CkInfoResult>() {
            @Override
            public void onSuccess(NetworkRequest<CkInfoResult> request, CkInfoResult result) {
                CalendarManager.clearInstance();
                list = result.getCooker_schedule();
                thumbnailsDatas = result.getCooker_thumbnail();
                Log.e("ssong", result.getCooker_info().uid);
                cookerId = result.getCooker_info().uid;
                mAdapter.setResult(result);
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<CkInfoResult> request, int errorCode, String errorMessage, Throwable e) {
                Log.e("ssong", "error",e);
                Log.e("ssong", errorMessage);
                Log.e("ssong", errorCode+"");
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

        mAdapter.setOnMenuDeleteClickListener(new CkHomeAdapter.OnMenuDeleteClickLIstener() {
            @Override
            public void onMenuDeleteClick(View view,final CkDetailMenuData data) {
                CkMenuDeleteRequest deleteRequest = new CkMenuDeleteRequest(getContext(), data.id);
                NetworkManager.getInstance().getNetworkData(deleteRequest, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(getContext(), "메뉴 삭제 완료", Toast.LENGTH_SHORT).show();
                        mAdapter.deleteMenu(data);
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });
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

    @Override
    public void onReviewCLick(View view, CkDetailData data) {
        Intent intent = new Intent(getContext(), ReviewInfoActivity.class);
        intent.putExtra(ReviewInfoActivity.INTENT_COOKERNAME, data.uid);
        startActivity(intent);
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
