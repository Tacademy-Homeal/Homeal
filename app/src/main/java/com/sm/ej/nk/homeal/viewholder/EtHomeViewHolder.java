package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout jjimLinear, reviewLinear;

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
        starCount.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return true;
            }
        });
        starCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        starCount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        jjimLinear = (LinearLayout)view.findViewById(R.id.linear_et_home_jjim);
        jjimLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jjimClickListener!=null){
                    jjimClickListener.onJjimClick(view, getAdapterPosition());
                }
            }
        });
        reviewLinear = (LinearLayout)view.findViewById(R.id.linear_et_home_review);
        reviewLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reviewClickListener!=null){
                    reviewClickListener.onReviewClick(view, getAdapterPosition());
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onViewClick(view, getAdapterPosition());
                }
            }
        });
    }
    public interface OnViewClickListener{
        public void onViewClick(View view,int position);
    }
    OnViewClickListener listener;
    public void setOnViewClickListner(OnViewClickListener listener){
        this.listener = listener;
    }

    public interface OnJjimClickListener{
        public void onJjimClick(View view, int position);
    }

    OnJjimClickListener jjimClickListener;

    public void setOnJjimClickListener(OnJjimClickListener listener){
        this.jjimClickListener = listener;
    }

    public interface OnReviewClickListener{
        public void onReviewClick(View view, int position);
    }

    OnReviewClickListener reviewClickListener;

    public void setOnReviewClickListener(OnReviewClickListener listener){
        this.reviewClickListener = listener;
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

        starCount.setRating(data.getGrade());
    }
}
