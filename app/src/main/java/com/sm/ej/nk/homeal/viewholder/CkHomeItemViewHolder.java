package com.sm.ej.nk.homeal.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    public View view;
    Context context;
    public ImageView foodImage, userImage, editImage, deleteImage;
    public TextView userName, userAdd, foodPrice, foodName, foodInfo;
    private CkDetailMenuData data;

    public CkHomeItemViewHolder(View view, Context context){
        super(view);
        this.view = view;
        this.context = context;
        foodImage = (ImageView)view.findViewById(R.id.image_ck_home_item_food);
        foodPrice = (TextView)view.findViewById(R.id.text_ck_home_item_foodprice);
        foodName = (TextView)view.findViewById(R.id.text_ck_home_item_foodname);
        foodInfo = (TextView)view.findViewById(R.id.text_ck_home_item_foodinfo);
        editImage = (ImageView)view.findViewById(R.id.image_ck_home_edit);
        deleteImage = (ImageView)view.findViewById(R.id.image_ck_home_delete);
        editImage.setOnClickListener(this);
        deleteImage.setOnClickListener(this);
    }

    public void visibleImage(){
        editImage.setVisibility(View.VISIBLE);
        deleteImage.setVisibility(View.VISIBLE);
    }

    public void invisibleImage(){
        editImage.setVisibility(View.INVISIBLE);
        deleteImage.setVisibility(View.INVISIBLE);
    }

    public void setData(CkDetailMenuData data){
        this.data = data;
        Glide.with(HomealApplication.getContext()).load(data.getImage()).into(foodImage);
        foodPrice.setText(data.price+"원");
        foodName.setText(data.name);
        foodInfo.setText(data.introduce);
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()){
            case R.id.image_ck_home_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if(listener!=null){
                            listener.onMenuDeleteClick(view, data);
                        }
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setMessage(context.getResources().getString(R.string.ck_home_dialog));
                builder.show();
                break;
        }
    }

    public interface OnMenuDeleteClickListener{
        public void onMenuDeleteClick(View view, CkDetailMenuData menuData);
    }

    OnMenuDeleteClickListener listener;
    public void setOnMenuDeleteClickLIstener(OnMenuDeleteClickListener listener){
        this.listener = listener;
    }
}