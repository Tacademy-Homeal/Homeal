package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtHomeData;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class ZzimViewHolder extends RecyclerView.ViewHolder {
    private View zzimView;
    private ImageView zzimuserImage, zzimfoodImage, zzimjjimImage;
    private TextView zzimName, zzimAddress, zzimMenu, zzimjjimCount, zzimreviewCount, zzimPrice;
    private RatingBar zzimstarCount;
    private LinearLayout zzimLinear, zzimreviewLinear;

    public ZzimViewHolder(View itemView) {
        super(itemView);
        zzimuserImage = (ImageView) itemView.findViewById(R.id.image_et_zzim_user);
        zzimfoodImage = (ImageView) itemView.findViewById(R.id.image_et_zzim_food);
        zzimjjimImage = (ImageView) itemView.findViewById(R.id.image_et_zzim_jjim);
        zzimName = (TextView) itemView.findViewById(R.id.text_et_zzim_name);
        zzimAddress = (TextView) itemView.findViewById(R.id.text_et_zzim_address);
        zzimMenu = (TextView) itemView.findViewById(R.id.text_et_zzim_foodname);
        zzimjjimCount = (TextView) itemView.findViewById(R.id.text_et_zzim_jjimcount);
        zzimreviewCount = (TextView) itemView.findViewById(R.id.text_et_zzim_reviewcount);
        zzimPrice = (TextView) itemView.findViewById(R.id.text_et_zzim_foodprice);

        zzimstarCount = (RatingBar) itemView.findViewById(R.id.rating_et_zzim);

        zzimstarCount.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                return true;
            }
        });
        zzimstarCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        zzimstarCount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        zzimLinear = (LinearLayout) itemView.findViewById(R.id.linear_et_zzim_jjim);
        zzimLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jjimClickListener != null) {
                    jjimClickListener.onJjimClick(view, getAdapterPosition());
                }
            }
        });
        zzimreviewLinear = (LinearLayout) itemView.findViewById(R.id.linear_et_zzim_review);
        zzimreviewLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reviewClickListener != null) {
                    reviewClickListener.onReviewClick(view, getAdapterPosition());
                }
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
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
    public void setData(EtHomeData data) {
        Glide.with(zzimuserImage.getContext()).load(data.getFoodImageUrl()).into(zzimuserImage);
        Glide.with(zzimfoodImage.getContext()).load(data.getImage()).into(zzimfoodImage);

        zzimName.setText(data.getName());
        zzimAddress.setText(data.getAddress());
        zzimjjimCount.setText(""+data.getBookmarkCnt());
        zzimreviewCount.setText(""+data.getReviewCnt());
        zzimstarCount.setRating((int)data.getGrade());
//        if (data.getReviewCnt()==0){
//            zzimreviewCount.setImage
//        }
        if(data.getIsBookmark()==0){
            zzimjjimImage.setImageResource(R.drawable.homeal_heart);
        }else{
            zzimjjimImage.setImageResource(R.drawable.homeal_heart_fill);
        }
    }
}