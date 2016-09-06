package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkReserveData;

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

    @BindView(R.id.text_ck_reserve_etname)
    TextView ckNameView;

    @BindView(R.id.rating_ck_reserve_et)
    RatingBar ckRatingView;

    @BindView(R.id.text_ck_reserve_state)
    TextView reserveStateView;

    @BindView(R.id.text_ck_reserve_foodname)
    TextView foodNameView;

    @BindView(R.id.btn_ck_reserve_state_disagree)
    Button btn_reserve_disagree;

    @BindView(R.id.btn_ck_reserve_state_agree)
    Button btn_reserve_agree;

    @BindView(R.id.btn_ck_review_write)
    Button btn_reserve_write;

    CkReserveData ckReserveData;

    private static final int TYPE_QUEST = 0;
    private static final int TYPE_QUEST_COMPLETE = 1;
    private static final int TYPE_EAT_END = 2;
    private static final int TYPE_END = 3;


    public CkReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //Unealbe rating state
        ckRatingView.setEnabled(false);

        buttonSst();
    }

    //Button set
    public void buttonSst(){
        btn_reserve_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aListener != null) {
                    aListener.onAgreeButtonClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });

        btn_reserve_disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dListener != null) {
                    dListener.onDisagreeButtonClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });
        btn_reserve_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rListener != null) {
                    rListener.onReviewButtonClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });
    }


    //set Data
    public void setReserveData(CkReserveData ckReserveData) {
        this.ckReserveData = ckReserveData;
        Glide.with(pictureView.getContext()).load(ckReserveData.getUimage()).into(pictureView);
        dateView.setText(ckReserveData.getSdate());
       // reservePersonView.setText(ckReserveData.get());
        ckNameView.setText(ckReserveData.getUname());
        foodNameView.setText(ckReserveData.getMname());

        btn_reserve_agree.setVisibility(View.INVISIBLE);
        btn_reserve_disagree.setVisibility(View.INVISIBLE);
        btn_reserve_write.setVisibility(View.INVISIBLE);


        switch (ckReserveData.getRstatus()){
            case TYPE_QUEST :
                btn_reserve_agree.setVisibility(View.VISIBLE);
                btn_reserve_disagree.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_request);
                break;
            case TYPE_QUEST_COMPLETE :
                btn_reserve_write.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_request_complete);
                break;
            case TYPE_EAT_END :
                btn_reserve_write.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_eat_end);
                break;
            case TYPE_END :
                reserveStateView.setText("");
                break;
        }
    }


    //Agree Button
    public interface OnAgreeButtonClickListener {

        public void onAgreeButtonClick(View view, CkReserveData ckReserveData, int position);
    }

    OnAgreeButtonClickListener aListener;

    public void setOnAgreeButtonClickListener(OnAgreeButtonClickListener aListener) {
        this.aListener = aListener;
    }

    //DisAgreen Button
    public interface OnDisagreeButtonClickListener {
        public void onDisagreeButtonClick(View view, CkReserveData ckReserveData, int position);
    }

    OnDisagreeButtonClickListener dListener;

    public void setOnDisagreeButtonClickListener(OnDisagreeButtonClickListener dListener) {
        this.dListener = dListener;
    }


    //Review Button
    public interface OnReviewButtonClickListener {

        public void onReviewButtonClick(View view, CkReserveData ckReserveData, int position);
    }

    OnReviewButtonClickListener rListener;

    public void setOnReviewButtonClickListener(OnReviewButtonClickListener rListener) {
        this.rListener = rListener;
    }
}