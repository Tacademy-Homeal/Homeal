package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.ZzimAdapter;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ZzimData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.ZzimListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtZzimActivity extends AppCompatActivity implements ZzimAdapter.OnZzimitemClickListener, ZzimAdapter.OnViewClickListener, ZzimAdapter.OnReviewitemClickListener {

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_et_zzim)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

  //  EtHomeAdapter mAdapter;
    ZzimAdapter mAdapter;
    List<ZzimData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_zzim);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("찜 목 록");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mAdapter = new ZzimAdapter();
        mAdapter.setOnReviewClickListener(this);
        mAdapter.setOnViewClickListener(this);
//        mAdapter.setOnViewClickListener(this);
//        mAdapter.setOnReciewClickListener(this);
//        mAdapter.setOnJjimitemClickListener(this);
        mAdapter.setOnZzimitemClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
//        initData();

        ZzimListRequest request = new ZzimListRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<ZzimData>>>() {

            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ZzimData>>> request, NetworkResult<List<ZzimData>> result) {
//                Toast.makeText(EtZzimActivity.this, "hi", Toast.LENGTH_SHORT).show();
                datas = result.getResult();
                mAdapter.clear();
                mAdapter.addList(datas);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ZzimData>>> request, int errorCode, String errorMessage, Throwable e) {
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

//    private void initData() {
//        datas = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ZzimData data = new ZzimData();
//            data.setAddress("주소" + i);
//            data.setThumbnail("http://blog.jinbo.net/attach/615/200937431.jpg");
//            data.setImage("https://pixabay.com/static/uploads/photo/2014/12/17/14/20/summer-anemone-571531_960_720.jpg");
//            data.setFoodName("음식이름" + i);
//            data.setBookmarkCnt("" + i);
//            data.setGrade(i % 5 + 1);
//            data.setReviewCnt("" + i);
//            data.setName("이름" + i);
//            datas.add(data);
//        }
//        mAdapter.addList(datas);
//    }


    public static final int INTENT_SCROLL = 1;
    public static final String INTENT_CK_ID = "asd";


//    @Override
//    public void onJjimitemClick(View view, int position) {
//        Snackbar.make(view, "이미지, 텍스트 변경", Snackbar.LENGTH_SHORT).show();
//    }

    @Override
    public void onReviewitemClick(View view, int position) {
        Intent intent = new Intent(this, InfoCkDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewClick(View view, int position) {
        Intent intent = new Intent(this, InfoCkDetailActivity.class);
        intent.putExtra(INTENT_CK_ID, datas.get(position));
        startActivity(intent);
    }

    @Override
    public void onZzimitemClick(View view, int position) {
        Snackbar.make(view, "이미지, 텍스트 변경", Snackbar.LENGTH_SHORT).show();
    }

//    @Override
//    public void onReviewitemClick(View view, int position) {
//        Intent intent = new Intent(this, InfoCkDetailActivity.class);
//        startActivity(intent);
//    }
//
////    public static final String INTENT_CK_ID = "asd";
//    public static final String INTENT_ZZIM_ID="asdf";
//
//    @Override
//    public void onViewClick(View view, int position) {
//        Intent intent = new Intent(this, InfoCkDetailActivity.class);
//        intent.putExtra(INTENT_ZZIM_ID, datas.get(position));
//        startActivity(intent);
//    }
//
//    @Override
//    public void onZzimitemClick(View view, int position) {
//        Snackbar.make(view, "이미지, 텍스트 변경", Snackbar.LENGTH_SHORT).show();
//    }
}
