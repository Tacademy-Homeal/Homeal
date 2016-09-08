package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.ThumbnailAdapter;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkThumbnailDeleteRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThumbnailEditActivity extends AppCompatActivity {

    @BindView(R.id.rv_thumbnail_edit)
    RecyclerView rv;

    ThumbnailAdapter mAdapter;
    List<ThumbnailsData> thumbnailsDatas;

    public static final int INTENT_GALLERY = 10;

    private int MODE = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        thumbnailsDatas =(List<ThumbnailsData>)intent.getSerializableExtra(CkMainActivity.INTENT_THUMBNAIL_DATA);

        thumbnailsDatas.add(getLastThumbnail());
        mAdapter = new ThumbnailAdapter(ThumbnailEditActivity.this, thumbnailsDatas);
        mAdapter.setOnDelteCLickListener(new ThumbnailAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, ThumbnailsData data, int position) {
                CkThumbnailDeleteRequest request = new CkThumbnailDeleteRequest(ThumbnailEditActivity.this, data.getId());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(ThumbnailEditActivity.this, "사진 삭제 완료", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(ThumbnailEditActivity.this, "사진 삭제 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mAdapter.setOnImageCLickListener(new ThumbnailAdapter.OnImageCLickListener() {
            @Override
            public void onImageCLick(View view, ThumbnailsData data, int position) {
                Intent intent = new Intent(ThumbnailEditActivity.this, GalleryActivity.class);
                startActivityForResult(intent, INTENT_GALLERY);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(ThumbnailEditActivity.this));
        rv.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ThumbnailsData getLastThumbnail(){
        ThumbnailsData lastData = new ThumbnailsData();
        lastData.setImage("https://pixabay.com/static/uploads/photo/2016/03/21/05/05/plus-1270001_960_720.png");
        lastData.setId("-1");
        lastData.setLastItem(true);
        return lastData;
    }
}
