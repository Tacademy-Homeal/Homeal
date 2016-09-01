package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailItemViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public TextView daynum, day, foodname, foodadd, time;
    public ImageView image, imageButton;
    public CkDetailMenuData datas;

    public CkDetailItemViewHolder(View view){
        super(view);
        daynum = (TextView)view.findViewById(R.id.text_item_ck_detail_numday);
        day = (TextView)view.findViewById(R.id.text_item_ck_detail_day);
        foodname = (TextView)view.findViewById(R.id.text_item_ck_detail_food);
        foodadd = (TextView)view.findViewById(R.id.text_item_ck_detail_add);
        time = (TextView)view.findViewById(R.id.text_item_ck_detail_time);
        image = (ImageView)view.findViewById(R.id.image_item_ck_detail);
        imageButton = (ImageView)view.findViewById(R.id.image_item_ck_detail_button);

    }

    public void setData(CkDetailMenuData datas){
        this.datas = datas;
        foodname.setText(datas.getFoodName());
        foodadd.setText(datas.getFoodAddress());
        time.setText(datas.getFoodTime());
        Glide.with(HomealApplication.getContext()).load(datas.image).into(image);

    }
}
