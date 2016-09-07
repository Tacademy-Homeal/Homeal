package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailItemViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public TextView daynum, day, foodname, foodadd, time, foodintro, foodprice;
    public ImageView image, imageButton;
    public Button swipebtn;
    public CkDetailMenuData datas;
    public SwipeLayout swipeLayout;
    public boolean isSelect;

    public CkDetailItemViewHolder(View view){
        super(view);
        foodname = (TextView)view.findViewById(R.id.text_item_ck_detail_food);
        image = (ImageView)view.findViewById(R.id.image_item_ck_detail);
        foodintro = (TextView)view.findViewById(R.id.text_item_ck_detail_intro);
        foodprice = (TextView)view.findViewById(R.id.text_item_ck_detail_price);
        swipeLayout = (SwipeLayout)view.findViewById(R.id.swipe_ck_detail);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipebtn =(Button)swipeLayout.findViewById(R.id.btn_ck_detail_swipe);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.right_ck_detail));
        isSelect = false;
    }

    public void setData(final CkDetailMenuData datas){
        this.datas = datas;
        foodname.setText(datas.getFoodName());
        foodintro.setText(datas.introduce);
        foodprice.setText(datas.price);
        Glide.with(HomealApplication.getContext()).load(datas.image).into(image);
        swipebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){

                    if(isSelect){
                        swipebtn.setBackgroundResource(R.drawable.imgres2);
                        isSelect = false;

                    }else{
                        swipebtn.setBackgroundResource(R.drawable.imgres);
                        isSelect = true;
                        swipeLayout.open(false, true);
                    }
                    listener.onMenuSwipeClick(view, datas, getAdapterPosition()-1, isSelect);

                }
            }
        });
    }
    public interface OnMenuSwipeClickLIstener{
        public void onMenuSwipeClick(View view, CkDetailMenuData data, int position, boolean select);
    }

    OnMenuSwipeClickLIstener listener;

    public void setOnMEnuSwipeClickListener(OnMenuSwipeClickLIstener listener){
        this.listener = listener;
    }
}
