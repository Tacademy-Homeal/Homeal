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
 * Created by Tacademy on 2016-08-25.
 */
public class EtReserveViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_et_reserve_picture)
    ImageView pictureView;

    @BindView(R.id.text_et_reserve_date)
    TextView dateView;

    @BindView(R.id.text_et_reserve_person)
    TextView reservepersonView;

    @BindView(R.id.text_et_reserve_ckname)
    TextView cknameView;

    @BindView(R.id.rating_et_reserve_ck)
    RatingBar ckratingView;

    @BindView(R.id.text_et_reserve_state)
    TextView reservestateView;

    @BindView(R.id.text_et_reserve_foodname)
    TextView foodnameView;

    @BindView(R.id.btn_et_reserve_state)
    Button stateView;


    //    @OnClick(R.id.btn_et_reserve_state)
//    Button reservestate
    public EtReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    EtReserveData etReserveData;
    public void setReserveData(EtReserveData etReserveData){
        this.etReserveData = etReserveData;
        pictureView.setImageDrawable(etReserveData.getCkpicture());
        dateView.setText(etReserveData.getReservedate());
        reservepersonView.setText(etReserveData.getReserveperson());
        cknameView.setText(etReserveData.getCkname());
        foodnameView.setText(etReserveData.getFooname());
        reservestateView.setText(etReserveData.getReservestate());
        stateView.setText(etReserveData.getBtnname());
    }
}


