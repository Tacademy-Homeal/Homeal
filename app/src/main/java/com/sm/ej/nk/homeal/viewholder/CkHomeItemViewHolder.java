package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeItemViewHolder extends RecyclerView.ViewHolder {
    View view;
    public ImageView foodImage, userimage;
    public TextView userName, userAdd, foodPrice, foodName, foodInfo;

    public CkHomeItemViewHolder(View view){
        super(view);
        this.view = view;
        foodImage = (ImageView)view.findViewById(R.id.image_ck_home_item_food);
        userimage = (ImageView)view.findViewById(R.id.image_ck_home_item_user);
        userName = (TextView)view.findViewById(R.id.text_ck_home_item_username);
        userAdd = (TextView)view.findViewById(R.id.text_ck_home_item_useradd);
        foodPrice = (TextView)view.findViewById(R.id.text_ck_home_item_foodprice);
        foodName = (TextView)view.findViewById(R.id.text_ck_home_item_foodname);
        foodInfo = (TextView)view.findViewById(R.id.text_ck_home_item_foodinfo);

    }
}
