package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChattingReceiveViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_message)
    TextView messageView;

    @BindView(R.id.image_recive)
    ImageView pictureView;

    public ChattingReceiveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setImage(String image){
        Glide.with(pictureView.getContext()).load(image).into(pictureView);
    }



    public void setMessage(String message) {
        messageView.setText(message);
    }

}