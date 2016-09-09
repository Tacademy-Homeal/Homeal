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

import com.sm.ej.nk.homeal.InfoCkDetailActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.ReviewInfoActivity;
import com.sm.ej.nk.homeal.adapter.EtHomeAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkJjimAddRequest;
import com.sm.ej.nk.homeal.request.CkJjimDeleteRequest;
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

    LinearLayoutManager manager;
    boolean isLastItem;
    private static int PAGENO;
    private static String ROWCOUNT="7";

    EtHomeAdapter mAdapter;
    List<EtHomeData> datas;

    public static EtHomeFragment createInstance(){
        final EtHomeFragment pageFragment = new EtHomeFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    public EtHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_et_home, container, false);
        ButterKnife.bind(this, v);
        PAGENO = 1;
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new EtHomeAdapter();
        mAdapter.setOnJjimitemClickListener(this);
        mAdapter.setOnReciewClickListener(this);
        mAdapter.setOnViewClickListener(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        addItem(""+PAGENO, ROWCOUNT);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(isLastItem && newState == RecyclerView.SCROLL_STATE_IDLE){
                    addItem(""+PAGENO, ROWCOUNT);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = manager.getItemCount();
                int lastVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
                if(totalItemCount >0 && lastVisibleItemPosition != RecyclerView.NO_POSITION && (totalItemCount -1 <= lastVisibleItemPosition)){
                    isLastItem = true;
                }else{
                    isLastItem = false;
                }
            }
        });
        return v;
    }

    private void addItem(final String pageNo, String rowCount){
        CkPageListRequest request = new CkPageListRequest(getContext() ,pageNo, rowCount);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<EtHomeData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<EtHomeData>>> request, NetworkResult<List<EtHomeData>> result) {
                datas = result.getResult();
                mAdapter.addList(datas);
                PAGENO+=1;
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<EtHomeData>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    public static final String INTENT_CK_ID = "asd";
    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_CK_ID, );
        intent.putExtra(INTENT_CK_ID, mAdapter.getDataList().get(position));
        Log.e("ssong", mAdapter.getDataList().get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onJjimitemClick(View view, int position, final EtHomeData data) {
        if(data.getIsBookmark() == 0){
            CkJjimAddRequest request = new CkJjimAddRequest(getContext(), data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(getContext(), "찜 추가 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(1);
                    data.setBookmarkCnt(data.getBookmarkCnt()+1);
                    mAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(getContext(), "찜 추가 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode+"");
                }
            });
        }else{
            CkJjimDeleteRequest request = new CkJjimDeleteRequest(getContext(), data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(getContext(), "찜 삭제 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(0);
                    data.setBookmarkCnt(data.getBookmarkCnt()-1);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(getContext(), "찜 삭제 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode+"");
                }
            });
        }

    }

    @Override
    public void onReviewitemClick(View view, int position, EtHomeData data) {
        Intent intent = new Intent(getActivity(), ReviewInfoActivity.class);
        intent.putExtra(ReviewInfoActivity.INTENT_COOKERNAME, data.getId());

        startActivity(intent);
    }
}
