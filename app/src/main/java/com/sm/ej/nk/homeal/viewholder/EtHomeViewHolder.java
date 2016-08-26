package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.francescocervone.openratingview.RatingView;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtHomeData;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtHomeViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private ImageView userimage, foodimage, jjimimage;
    private TextView name, address, menu, jjimCount, reviewCount, price;
    private RatingView starCount;


    public EtHomeViewHolder(View view){
        super(view);
        this.view = view;
        userimage = (ImageView)view.findViewById(R.id.image_et_home_user);
        foodimage = (ImageView)view.findViewById(R.id.image_et_home_food);
        jjimimage = (ImageView)view.findViewById(R.id.image_et_home_jjim);

        name = (TextView)view.findViewById(R.id.text_et_home_name);
        address = (TextView)view.findViewById(R.id.text_et_home_address);
        menu = (TextView)view.findViewById(R.id.text_et_home_foodname);
        jjimCount = (TextView)view.findViewById(R.id.text_et_home_jjimcount);
        reviewCount = (TextView)view.findViewById(R.id.text_et_home_reviewcount);
        price = (TextView)view.findViewById(R.id.text_et_home_foodprice);

        starCount = (RatingView)view.findViewById(R.id.rating_et_home);
    }
    public void setData(EtHomeData data){
        Glide.with(userimage.getContext()).load(data.getUserImageUrl()).into(userimage);
        Glide.with(foodimage.getContext()).load(data.getFoodImageUrl()).into(foodimage);

        name.setText(data.getName());
        address.setText(data.getAddress());
        menu.setText(data.getFoodName());
        jjimCount.setText(data.getJjimCount());
        reviewCount.setText(data.getReviewCount());
        price.setText(data.getFoodPrice());

        starCount.setRating(data.getRatingCount());
    }
}
