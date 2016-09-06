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
import com.sm.ej.nk.homeal.data.ZzimData;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class ZzimViewHolder extends RecyclerView.ViewHolder {
    private View zzimView;
    private ImageView zzimuserImage, zzimfoodImage, zzimjjimImage;
    private TextView zzimName, zzimAddress, zzimMenu, zzimjjimCount, zzimreviewCount, zzimPrice;
    private RatingView zzimstarCount;
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

        zzimstarCount = (RatingView) itemView.findViewById(R.id.rating_et_zzim);

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
                if (zzimClickListener != null) {
                    zzimClickListener.onZzimClick(view, getAdapterPosition());
                }
            }
        });
        zzimreviewLinear = (LinearLayout) itemView.findViewById(R.id.linear_et_zzim_review);
        zzimreviewLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zzimreviewClickListener != null) {
                    zzimreviewClickListener.onReviewClick(view, getAdapterPosition());
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

    public interface OnZzimClickListener{
        public void onZzimClick(View view, int position);
    }

    OnZzimClickListener zzimClickListener;

    public void setOnZzimClickListener(OnZzimClickListener listener){
        this.zzimClickListener = listener;
    }

    public interface OnReviewClickListener{
        public void onReviewClick(View view, int position);
    }

    OnReviewClickListener zzimreviewClickListener;

    public void setOnReviewClickListener(OnReviewClickListener listener){
        this.zzimreviewClickListener = listener;
    }

    public void setData(ZzimData data){
        Glide.with(zzimuserImage.getContext()).load(data.getImage()).into(zzimuserImage);
        Glide.with(zzimfoodImage.getContext()).load(data.getThumbnail()).into(zzimfoodImage);

        zzimName.setText(data.getName());
        zzimAddress.setText(data.getAddress());
        zzimMenu.setText(data.getFoodName());
        zzimjjimCount.setText(data.getBookmarkCnt());
        zzimreviewCount.setText(data.getReviewCnt());
        zzimPrice.setText(data.getFoodPrice());

        zzimstarCount.setRating(data.getGrade());
    }
}