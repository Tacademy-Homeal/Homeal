package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class ThumbnailViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView, deleteImage;
    public View view;

    public ThumbnailViewHolder(View view){
        super(view);
        this.view = view;
        imageView = (ImageView)view.findViewById(R.id.image_thumbnail);
        deleteImage = (ImageView)view.findViewById(R.id.image_thumbnail_delete);
    }
}
