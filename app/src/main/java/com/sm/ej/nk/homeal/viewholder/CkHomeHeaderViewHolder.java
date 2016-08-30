package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeHeaderViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public ImageView userImage;
    public TextView userName, userAddress, jjimCount, reviewCount;
    public ProgressBar progressTotal, progressTaste, progressKind, progressClean;

    public CkHomeHeaderViewHolder(View view){
        super(view);
        userImage = (ImageView)view.findViewById(R.id.image_ck_home_user);
        userName = (TextView)view.findViewById(R.id.text_ck_home_name);
        userAddress = (TextView)view.findViewById(R.id.text_ck_home_add);
        jjimCount = (TextView)view.findViewById(R.id.text_ck_home_jjimcount);
        reviewCount = (TextView)view.findViewById(R.id.text_ck_home_jjimcount);
        progressTotal = (ProgressBar)view.findViewById(R.id.progress_ck_home_total);
        progressTaste = (ProgressBar)view.findViewById(R.id.progress_ck_home_taste);
        progressKind = (ProgressBar)view.findViewById(R.id.progress_ck_home_kind);
        progressClean = (ProgressBar)view.findViewById(R.id.progress_ck_home_clean);
    }

}
