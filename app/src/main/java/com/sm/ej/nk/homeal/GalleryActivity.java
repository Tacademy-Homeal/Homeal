package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.sm.ej.nk.homeal.adapter.GalleryAdapter;
import com.sm.ej.nk.homeal.data.GalleryItemData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.decoration.GridDividerDecoration;
import com.sm.ej.nk.homeal.manager.GalleryManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkThumbnailInsertRequest;
import com.sm.ej.nk.homeal.view.GalleryItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnPhotoClickListener{

    @BindView(R.id.rv_gallery)
    RecyclerView rv;

    @BindView(R.id.floating_gallery)
    FloatingActionButton fab;

    @BindView(R.id.toobar_gallery)
    Toolbar toolbar;

    List<String> imagePathList;
    GalleryAdapter mAadapter;

    public static final String TO_THUMBNAIL="qqqq";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("겔러리");

        imagePathList = new ArrayList<>();
        mAadapter = new GalleryAdapter(GalleryActivity.this, GalleryManager.getInstance(GalleryActivity.this).getAllPhotoPathList());
        mAadapter.setOnPhotoClickListener(this);
        rv.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 4));
        rv.setAdapter(mAadapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new GridDividerDecoration(getResources(), R.drawable.divider_recycler_gallery));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog;
                progressDialog = ProgressDialog.show(GalleryActivity.this, "전송중", "잠시만 기달려주세요", true);
                progressDialog.setCancelable(false);
                progressDialog.show();
                CkThumbnailInsertRequest request = new CkThumbnailInsertRequest(GalleryActivity.this, mAadapter.getSlectedPhotoList());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        progressDialog.dismiss();
                        Toast.makeText(GalleryActivity.this, "추가 완료", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(GalleryActivity.this, "추가 실패", Toast.LENGTH_SHORT).show();
                        Log.e("sssong", errorMessage);
                        Log.e("ssong", errorCode+"");
                    }
                });
                setResult(Activity.RESULT_OK);
            }
        });


    }

    @Override
    public void onPhotoClick(View view, GalleryItemView holder, int position) {
        GalleryItemData itemData = mAadapter.getPhotoList().get(position);

        if(itemData.isSelected()){
            itemData.setSelected(false);
            imagePathList.remove(itemData.getImagePath());
        }else{
            itemData.setSelected(true);
            imagePathList.add(itemData.getImagePath());
        }
        mAadapter.getPhotoList().set(position, itemData);
        mAadapter.notifyDataSetChanged();

    }


}
