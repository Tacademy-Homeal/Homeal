package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.ZzimAdapter;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkJjimAddRequest;
import com.sm.ej.nk.homeal.request.CkJjimDeleteRequest;
import com.sm.ej.nk.homeal.request.ZzimListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtZzimActivity extends AppCompatActivity implements ZzimAdapter.OnJjimitemClickListener, ZzimAdapter.OnViewClickListener, ZzimAdapter.OnReviewitemClickListener {

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_et_zzim)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    //  EtHomeAdapter mAdapter;
    ZzimAdapter mAdapter;
    List<EtHomeData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_zzim);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.EtZzimActivity_appbar));

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mAdapter = new ZzimAdapter();
        mAdapter.setOnViewClickListener(this);
        mAdapter.setOnReciewClickListener(this);
        mAdapter.setOnJjimitemClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        ZzimListRequest request = new ZzimListRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<EtHomeData>>>() {

            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<EtHomeData>>> request, NetworkResult<List<EtHomeData>> result) {
                datas = result.getResult();
                mAdapter.clear();
                mAdapter.addList(datas);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<EtHomeData>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(EtZzimActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final int INTENT_SCROLL = 1;
    public static final String INTENT_CK_ID = "asd";

    @Override
    public void onReviewitemClick(View view, int position, EtHomeData data) {
        Intent intent = new Intent(EtZzimActivity.this, ReviewInfoActivity.class);
        intent.putExtra(ReviewInfoActivity.INTENT_COOKERNAME, data.getId());

        startActivity(intent);
    }

    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(this, InfoCkDetailActivity.class);
        intent.putExtra(INTENT_CK_ID, datas.get(position));
        startActivity(intent);
    }

    @Override
    public void onJjimitemClick(View view, int position, final EtHomeData data) {
        if (data.getIsBookmark() == 0) {
            CkJjimAddRequest request = new CkJjimAddRequest(this, data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(EtZzimActivity.this, "찜 추가 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(1);
                    data.setBookmarkCnt(data.getBookmarkCnt() + 1);
                    mAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(EtZzimActivity.this, "찜 추가 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode + "");
                }
            });
        } else {
            CkJjimDeleteRequest request = new CkJjimDeleteRequest(this, data.getId());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                    Toast.makeText(EtZzimActivity.this, "찜 삭제 완료", Toast.LENGTH_SHORT).show();
                    data.setIsBookmark(0);
                    data.setBookmarkCnt(data.getBookmarkCnt() - 1);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(EtZzimActivity.this, "찜 삭제 실패", Toast.LENGTH_SHORT).show();
                    Log.e("ssong", errorMessage);
                    Log.e("ssong", errorCode + "");
                }
            });
        }

    }
}
