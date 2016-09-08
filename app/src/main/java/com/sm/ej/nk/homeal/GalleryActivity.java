package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sm.ej.nk.homeal.adapter.GalleryAdapter;
import com.sm.ej.nk.homeal.data.GalleryItemData;
import com.sm.ej.nk.homeal.decoration.GridDividerDecoration;
import com.sm.ej.nk.homeal.manager.GalleryManager;
import com.sm.ej.nk.homeal.view.GalleryItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnPhotoClickListener{

    @BindView(R.id.rv_gallery)
    RecyclerView rv;

    List<String> imagePathList;
    GalleryAdapter mAadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        imagePathList = new ArrayList<>();
        mAadapter = new GalleryAdapter(GalleryActivity.this, GalleryManager.getInstance(GalleryActivity.this).getAllPhotoPathList());
        mAadapter.setOnPhotoClickListener(this);
        rv.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 4));
        rv.setAdapter(mAadapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new GridDividerDecoration(getResources(), R.drawable.divider_recycler_gallery));

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
