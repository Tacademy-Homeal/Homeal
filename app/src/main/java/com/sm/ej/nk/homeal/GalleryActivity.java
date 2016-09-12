package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnPhotoClickListener {

    @BindView(R.id.rv_gallery)
    RecyclerView rv;

    @BindView(R.id.floating_gallery)
    FloatingActionButton fab;

    @BindView(R.id.toobar_gallery)
    Toolbar toolbar;

    GalleryAdapter mAadapter;

    String imagePath;


    public static final String INTENT_MODE = "mode";

    public static final String IMAGE_PATH = "imageoath";

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("갤러리");

        Intent intent = getIntent();
        mode = intent.getIntExtra(INTENT_MODE, -1);

        if (mode == ThumbnailEditActivity.MODE_THUMBNAIL) {
            mAadapter = new GalleryAdapter(GalleryActivity.this, GalleryManager.getInstance(GalleryActivity.this).getAllPhotoPathList(), GalleryAdapter.CHOICE_MODE_MULTIPLE);
        } else {
            mAadapter = new GalleryAdapter(GalleryActivity.this, GalleryManager.getInstance(GalleryActivity.this).getAllPhotoPathList(), GalleryAdapter.CHOICE_MODE_SINGLE);
        }
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
                switch (mode) {
                    case ThumbnailEditActivity.MODE_THUMBNAIL: {
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
                                Log.e("ssong", errorCode + "");
                            }
                        });
                        break;
                    }
                    case MenuAddActivity.MODE_MENU: {
                        Intent intent = new Intent();
                        intent.putExtra(IMAGE_PATH, imagePath);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    case CkPersonalDataActivity.MODE_GET_IMAGE: {
                        Intent intent = new Intent();
                        intent.putExtra(IMAGE_PATH, imagePath);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    case EtPersonalDataActivity.MODE_GET_IMAGE: {
                        Intent intent = new Intent();
                        intent.putExtra(IMAGE_PATH, imagePath);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void onPhotoClick(View view, GalleryItemData data, int mode) {
        if (mode == GalleryAdapter.CHOICE_MODE_SINGLE) {
            imagePath = data.getImagePath();
        } else {
            if (data.isSelected()) {
                data.setSelected(false);
            } else {
                data.setSelected(true);
            }
        }
    }
}
