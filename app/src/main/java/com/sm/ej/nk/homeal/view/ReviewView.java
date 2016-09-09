package com.sm.ej.nk.homeal.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ReviewData;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class ReviewView extends RecyclerView.ViewHolder {
    public View view;
    public ImageView image;
    public TextView textName, textMain, textDate;

    public ReviewView(View view){
        super(view);
        image = (ImageView)view.findViewById(R.id.image_review);
        textName = (TextView)view.findViewById(R.id.text_review_name);
        textMain = (TextView)view.findViewById(R.id.text_review_main);
        textDate = (TextView)view.findViewById(R.id.text_review_date);
    }

    public void setData(ReviewData data){
        Glide.with(HomealApplication.getContext()).load(data.getImage()).into(image);
        textName.setText(data.getName());
        textMain.setText(data.getReview());
        textDate.setText(data.getDate());
    }
}
