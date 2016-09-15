package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ReserveData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtReserveViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_et_reserve_picture)
    ImageView pictureView;

    //testdsd
    @BindView(R.id.text_et_reserve_date)
    TextView dateView;

    @BindView(R.id.text_et_reserve_person)
    TextView reservePersonView;

    @BindView(R.id.text_et_reserve_ckname)
    TextView ckNameView;

    @BindView(R.id.rating_et_reserve_ck)
    RatingBar ckRatingView;

    @BindView(R.id.text_et_reserve_state)
    TextView reserveStateView;

    @BindView(R.id.text_et_reserve_foodname)
    TextView foodNameView;

    @BindView(R.id.btn_et_review_write)
    Button btn_write;

    @BindView(R.id.btn_et_cancel)
    Button btn_cancel;


    private static final int TYPE_REQUEST = 0;
    private static final int TYPE_REQUEST_COMPLETE = 1;
    private static final int TYPE_DISH_COMPLETE = 2;
    private static final int TYPE_END = 4;
    //    @OnClick(R.id.btn_et_reserve_state)
//    Button reservestate
    public EtReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wListener != null){
                    wListener.onWriteButtonClick(view,etReserveData,getAdapterPosition());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cListender != null){
                    cListender.onCancelButtonClick(view,etReserveData,getAdapterPosition());
                }
            }
        });
    }

    ReserveData etReserveData;

    public void setReserveData(ReserveData etReserveData) {
        this.etReserveData = etReserveData;

        //Url to image
        Glide.with(pictureView.getContext()).load(etReserveData.getImage()).into(pictureView);
        ckNameView.setText(etReserveData.getUname());
        foodNameView.setText(etReserveData.getMname());
        reservePersonView.setText(etReserveData.getUname());
        dateView.setText(etReserveData.getDate());

        //btn setting
        switch (etReserveData.getStatus()){
            case TYPE_REQUEST :
                reserveStateView.setText(R.string.et_text_reservation_request);
                break;
            case TYPE_REQUEST_COMPLETE :
                reserveStateView.setText(R.string.et_text_reservation_complete);
                break;
            case TYPE_DISH_COMPLETE :
                reserveStateView.setText(R.string.et_text_reservation_end);
                break;
            case TYPE_END :
                reserveStateView.setText("");
                break;
        }
    }


    //WRITE Button
    public interface OnWriteButtonClick {
        public void onWriteButtonClick(View view, ReserveData etReserveData, int position);
    }

    OnWriteButtonClick wListener;

    public void setWriteButtonClick(OnWriteButtonClick wListener) {
        this.wListener = wListener;
    }

    //Cancel button
    public interface OnCacncelButtonClick{
        public void onCancelButtonClick(View view, ReserveData data,int position);
    }

    OnCacncelButtonClick cListender;

    public void setCancelButtonClick(OnCacncelButtonClick cListender){
        this.cListender = cListender;
    }


}


