package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkReseveData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkReserveViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_ck_reserve_picture)
    ImageView pictureView;

    @BindView(R.id.text_ck_reserve_date)
    TextView dateView;

    @BindView(R.id.text_ck_reserve_person)
    TextView reservePersonView;

    @BindView(R.id.text_ck_reserve_state)
    TextView reserveStateView;

    @BindView(R.id.text_ck_reserve_foodname)
    TextView foodNameView;

    @BindView(R.id.btn_ck_reserve_state_disagree)
    Button btn_reserve_disagree;

    @BindView(R.id.btn_ck_reserve_state_agree)
    Button btn_reserve_agree;

    CkReseveData reserveData;

    private static final int TYPE_REQUEST = 1;
    private static final int TYPE_REQUEST_COMPLETE = 2;
    private static final int TYPE_REQUEST_REJECT = 3;
    private static final int TYPE_COOKER_CANCLE = 4;
    private static final int TYPE_EATER_CANCLE = 5;
    private static final int TYPE_EAT_COMPLETE = 6;
    private static final int TYPE_END = 7;

    public CkReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        buttonSst();
    }

    //Button set
    public void buttonSst(){


        btn_reserve_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aListener != null) {
                    aListener.onAgreeButtonClick(v, reserveData, getAdapterPosition());
                }
            }
        });

        btn_reserve_disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dListener != null) {
                    dListener.onDisagreeButtonClick(v, reserveData, getAdapterPosition());
                }
            }
        });
    }

    //set Data
    public void setReserveData(CkReseveData reserveData) {
        this.reserveData = reserveData;

        Glide.with(pictureView.getContext()).load(reserveData.getImage()).into(pictureView);
        foodNameView.setText(reserveData.getMname());
        dateView.setText(reserveData.getDate());
        reservePersonView.setText(reserveData.getUname());
        btn_reserve_agree.setVisibility(View.VISIBLE);
        btn_reserve_disagree.setVisibility(View.VISIBLE);

        //Button setting && status setting
        switch (reserveData.getStatus()){
            case TYPE_REQUEST :
                reserveStateView.setText(R.string.text_ck_reserve_request);
                break;

            case TYPE_REQUEST_COMPLETE :
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setText("취소");
                reserveStateView.setText(R.string.text_ck_reserve_request_complete);
                break;

            case TYPE_REQUEST_REJECT:
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setVisibility(View.GONE);
                reserveStateView.setText("거절승인");
                break;
            case TYPE_COOKER_CANCLE:
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setVisibility(View.GONE);
                reserveStateView.setText("거절승인");
                break;
            case TYPE_EATER_CANCLE:
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setVisibility(View.GONE);
                reserveStateView.setText("이터거절");
                break;
            case TYPE_EAT_COMPLETE :
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setVisibility(View.GONE);
                reserveStateView.setText(R.string.text_ck_reserve_eat_end);
                break;
            case TYPE_END :
                btn_reserve_agree.setVisibility(View.GONE);
                btn_reserve_disagree.setVisibility(View.GONE);
                reserveStateView.setText("식사종료");
                break;
        }
    }

    //Agree Button
    public interface OnAgreeButtonClickListener {

        public void onAgreeButtonClick(View view, CkReseveData reserveData, int position);
    }

    OnAgreeButtonClickListener aListener;

    public void setOnAgreeButtonClickListener(OnAgreeButtonClickListener aListener) {
        this.aListener = aListener;
    }

    //DisAgreen Button
    public interface OnDisagreeButtonClickListener {
        public void onDisagreeButtonClick(View view, CkReseveData reserveData, int position);
    }

    OnDisagreeButtonClickListener dListener;

    public void setOnDisagreeButtonClickListener(OnDisagreeButtonClickListener dListener) {
        this.dListener = dListener;
    }

}