package com.sm.ej.nk.homeal.viewHolder;

/**
 * Created by Tacademy on 2016-08-25.
 */

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;


public class EtHomeFragmentViewHolder extends RecyclerView.ViewHolder {

    ImageView foodView;
    TextView startView;
    TextView hartView;
    TextView commentView;


    public EtHomeFragmentViewHolder(View itemView) {
        super(itemView);

        foodView = (ImageView)itemView.findViewById(R.id.et_home_ft_foodview);
        startView = (TextView)itemView.findViewById(R.id.text_et_ft_start);
        hartView = (TextView)itemView.findViewById(R.id.text_et_ft_hart);
        commentView = (TextView)itemView.findViewById(R.id.text_et_ft_commnet);
    }

    public TextView getCommentView() {
        return commentView;
    }

    public void setCommentView(TextView commentView) {
        this.commentView = commentView;
    }

    public TextView getHartView() {
        return hartView;
    }

    public void setHartView(TextView hartView) {
        this.hartView = hartView;
    }

    public TextView getStartView() {
        return startView;
    }

    public void setStartView(TextView startView) {
        this.startView = startView;
    }


    public void setFoodView(Drawable testView) {
        foodView.setImageDrawable(testView);
    }

    public ImageView getFoodView(){
        return foodView;
    }





}
