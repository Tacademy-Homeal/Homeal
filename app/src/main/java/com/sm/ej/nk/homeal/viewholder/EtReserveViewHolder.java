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

    @BindView(R.id.text_et_reserve_ckname)
    TextView ckNameView;

    @BindView(R.id.rating_et_reserve_ck)
    RatingBar ckRatingView;

    @BindView(R.id.text_et_reserve_state)
    TextView reserveStateView;

    @BindView(R.id.text_et_reserve_foodname)
    TextView foodNameView;

    @BindView(R.id.btn_et_reserve)
    Button btn_reserve;



    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;
//    Button reservestate
    public EtReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rListener != null){
                    rListener.onReserveButtonClick(view,etReserveData,getAdapterPosition());
                }
            }
        });
    }

    ReserveData etReserveData;

    public void setReserveData(ReserveData etReserveData) {
        this.etReserveData = etReserveData;

        //Url to image
        Glide.with(pictureView.getContext()).load(etReserveData.getImage()).into(pictureView);
        ckNameView.setText("Cooker/"+etReserveData.getUname());
        foodNameView.setText("Menu/"+etReserveData.getMname());
        dateView.setText(etReserveData.getDate());
        ckRatingView.setRating(etReserveData.getGrade());

        btn_reserve.setVisibility(View.VISIBLE);

        switch (etReserveData.getStatus()){
            case TYPE_REQUEST :
                reserveStateView.setText("예약진행");
                btn_reserve.setText(R.string.et_reservation_cancle);
                break;
            case TYPE_REQUEST_COMPLETE :
                reserveStateView.setText(R.string.et_text_reservation_complete);
                btn_reserve.setText(R.string.et_reservation_cancle);
                break;
            case TYPE_REQUEST_REJECT :
                reserveStateView.setText("쿠커거절");
                btn_reserve.setVisibility(View.GONE);
                break;
            case TYPE_COOKER_CANCLE :
                reserveStateView.setText("쿠커취소");
                btn_reserve.setVisibility(View.GONE);
                break;
            case TYPE_EATER_CANCLE :
                reserveStateView.setText("예약취소");
                btn_reserve.setVisibility(View.GONE);
                break;
            case TYPE_EAT_COMPLETE:
                reserveStateView.setText("식사완료");
                btn_reserve.setVisibility(View.VISIBLE);
                btn_reserve.setText(R.string.after_write);
                break;
            case TYPE_END:
                reserveStateView.setText("식사완료");
                btn_reserve.setVisibility(View.GONE);
        }

    }

    //Reseve Button
    public interface OnReserveButtonClick {
        public void onReserveButtonClick(View view, ReserveData etReserveData, int position);
    }

    OnReserveButtonClick rListener;

    public void setReserveButtonClick(OnReserveButtonClick rListener) {
        this.rListener = rListener;
    }
}


