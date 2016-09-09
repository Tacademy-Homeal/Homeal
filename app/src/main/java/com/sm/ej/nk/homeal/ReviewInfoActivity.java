package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sm.ej.nk.homeal.adapter.ReviewAdapter;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ReviewData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkReviewListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewInfoActivity extends AppCompatActivity {

    @BindView(R.id.rv_review)
    RecyclerView rv;

    ReviewAdapter mAdapter;

    public static final String INTENT_COOKERNAME = "hhh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_info);
        ButterKnife.bind(this);

        rv.setLayoutManager(new LinearLayoutManager(ReviewInfoActivity.this));
        Intent intent = getIntent();
        String cookerId = intent.getStringExtra(INTENT_COOKERNAME);
        CkReviewListRequest request = new CkReviewListRequest(ReviewInfoActivity.this, cookerId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<ReviewData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ReviewData>>> request, NetworkResult<List<ReviewData>> result) {
                mAdapter = new ReviewAdapter(ReviewInfoActivity.this, result.getResult());
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ReviewData>>> request, int errorCode, String errorMessage, Throwable e) {
                Log.e("ssong", errorMessage);
                Log.e("ssong", errorCode+"");
            }
        });
    }
}
