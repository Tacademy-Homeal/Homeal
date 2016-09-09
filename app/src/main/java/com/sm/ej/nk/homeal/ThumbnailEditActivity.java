package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.ThumbnailAdapter;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkThumbnailDeleteRequest;
import com.sm.ej.nk.homeal.request.ThumbnailListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThumbnailEditActivity extends AppCompatActivity {

    @BindView(R.id.rv_thumbnail_edit)
    RecyclerView rv;

    ThumbnailAdapter mAdapter;
    List<ThumbnailsData> thumbnailsDatas;
    List<String> imagePathList;
    String cookerId;

    public static final int INTENT_GALLERY = 10;

    private int MODE = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        cookerId = intent.getStringExtra(CkMainActivity.INTENT_COOKER_ID);
        rv.setLayoutManager(new LinearLayoutManager(ThumbnailEditActivity.this));

        getThumbnailList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTENT_GALLERY){
            if(resultCode == Activity.RESULT_OK){
                getThumbnailList();
            }
        }
    }

    private void getThumbnailList(){
        ThumbnailListRequest listRequest = new ThumbnailListRequest(ThumbnailEditActivity.this, cookerId);
        NetworkManager.getInstance().getNetworkData(listRequest, new NetworkManager.OnResultListener<NetworkResult<List<ThumbnailsData>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<ThumbnailsData>>> request, NetworkResult<List<ThumbnailsData>> result) {
                thumbnailsDatas.clear();
                thumbnailsDatas = result.getResult();
                thumbnailsDatas.add(getLastThumbnail());
                mAdapter = new ThumbnailAdapter(ThumbnailEditActivity.this, thumbnailsDatas);
                setAdapter();
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<ThumbnailsData>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    private void setAdapter(){
        mAdapter.setOnDelteCLickListener(new ThumbnailAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, final ThumbnailsData data, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThumbnailEditActivity.this);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        CkThumbnailDeleteRequest request = new CkThumbnailDeleteRequest(ThumbnailEditActivity.this, data.getId());
                        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                            @Override
                            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                                Toast.makeText(ThumbnailEditActivity.this, "사진 삭제 완료", Toast.LENGTH_SHORT).show();
                                mAdapter.deleteData(data);
                            }

                            @Override
                            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                                Toast.makeText(ThumbnailEditActivity.this, "사진 삭제 실패", Toast.LENGTH_SHORT).show();
                                Log.e("ssong", "error", e);
                                Log.e("ssong", errorMessage);
                                Log.e("ssong", errorCode+"");
                            }
                        });
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setMessage(ThumbnailEditActivity.this.getResources().getString(R.string.thumbnail_delete));
                builder.show();

            }
        });
        mAdapter.setOnImageCLickListener(new ThumbnailAdapter.OnImageCLickListener() {
            @Override
            public void onImageCLick(View view, ThumbnailsData data, int position) {
                Intent intent = new Intent(ThumbnailEditActivity.this, GalleryActivity.class);
                startActivityForResult(intent, INTENT_GALLERY);
            }
        });
    }

    private ThumbnailsData getLastThumbnail(){
        ThumbnailsData lastData = new ThumbnailsData();
        lastData.setImage("https://pixabay.com/static/uploads/photo/2016/03/21/05/05/plus-1270001_960_720.png");
        lastData.setId("-1");
        lastData.setLastItem(true);
        return lastData;
    }
}
