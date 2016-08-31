package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtReserveData;

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
    Button btn_reserve;


    //    @OnClick(R.id.btn_et_reserve_state)
//    Button reservestate
    public EtReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cListener != null){
                    cListener.onReserveButtonClick(view,etReserveData,getAdapterPosition());
                }
            }
        });
    }

    EtReserveData etReserveData;

    public void setReserveData(EtReserveData etReserveData) {
        this.etReserveData = etReserveData;
        pictureView.setImageDrawable(etReserveData.getCkpicture());
        dateView.setText(etReserveData.getReservedate());
        reservePersonView.setText(etReserveData.getReserveperson());
        ckNameView.setText(etReserveData.getCkname());
        foodNameView.setText(etReserveData.getFooname());
        reserveStateView.setText(etReserveData.getReservestate());
        btn_reserve.setText(etReserveData.getBtnetreviewwrite());
    }


    //Observer Button
    public interface OnReserveButtonClick {
        public void onReserveButtonClick(View view, EtReserveData etReserveData, int position);
    }

    OnReserveButtonClick cListener;

    public void setOnResrveButtonClick(OnReserveButtonClick cListener) {
        this.cListener = cListener;
    }


}


