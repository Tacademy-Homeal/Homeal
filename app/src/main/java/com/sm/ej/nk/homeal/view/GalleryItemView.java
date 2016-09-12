package com.sm.ej.nk.homeal.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class GalleryItemView extends RecyclerView.ViewHolder {

    public ImageView imagePhoto;
    public RelativeLayout layoutSelect;

    public GalleryItemView(View view){
        super(view);

        imagePhoto = (ImageView)view.findViewById(R.id.image_gallery);
        layoutSelect = (RelativeLayout)view.findViewById(R.id.relativeSelect);
    }
    boolean isChecked;
    public void setChecked(boolean checked){
        if(isChecked != checked){
            isChecked = checked;
            drawCheck();
        }
    }

    private void drawCheck(){
        if(isChecked){
            layoutSelect.setVisibility(View.VISIBLE);
        }else{
            layoutSelect.setVisibility(View.INVISIBLE);
        }
    }
}
