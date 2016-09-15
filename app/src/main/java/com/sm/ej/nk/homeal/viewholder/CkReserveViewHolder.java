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
    TextView etNameView;

    @BindView(R.id.rating_ck_reserve_et)
    RatingBar etRatingView;

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

    @BindView(R.id.text_ck_reserve_number)
    TextView reserve_numberView;

    ReserveData reserveData;

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
        //Unealbe rating state
        etRatingView.setEnabled(false);

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
        btn_reserve_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cListener != null) {
                    cListener.onCancelButtonClick(v, reserveData, getAdapterPosition());
                }
            }
        });
    }


    //set Data
    public void setReserveData(ReserveData reserveData) {
        this.reserveData = reserveData;

        Glide.with(pictureView.getContext()).load(reserveData.getImage()).into(pictureView);
        foodNameView.setText(reserveData.getMname());
        dateView.setText(reserveData.getDate());
        reservePersonView.setText(""+reserveData.getPax());
        etNameView.setText(reserveData.getUname());

        //btn
        btn_init();
        switch (reserveData.getStatus()){
            case TYPE_REQUEST :
                btn_reserve_agree.setVisibility(View.VISIBLE);
                btn_reserve_disagree.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_request);
                break;
            case TYPE_REQUEST_COMPLETE :
                btn_reserve_write.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_request_complete);
                break;
            case TYPE_EAT_COMPLETE :
                btn_reserve_write.setVisibility(View.VISIBLE);
                reserveStateView.setText(R.string.text_ck_reserve_eat_end);
                break;
            case TYPE_END :
                reserveStateView.setText("");
                break;
        }
    }

    public void btn_init(){
        btn_reserve_agree.setVisibility(View.INVISIBLE);
        btn_reserve_disagree.setVisibility(View.INVISIBLE);
        btn_reserve_write.setVisibility(View.INVISIBLE);

    }

    //Agree Button
    public interface OnAgreeButtonClickListener {

        public void onAgreeButtonClick(View view, ReserveData reserveData, int position);
    }

    OnAgreeButtonClickListener aListener;

    public void setOnAgreeButtonClickListener(OnAgreeButtonClickListener aListener) {
        this.aListener = aListener;
    }

    //DisAgreen Button
    public interface OnDisagreeButtonClickListener {
        public void onDisagreeButtonClick(View view, ReserveData reserveData, int position);
    }

    OnDisagreeButtonClickListener dListener;

    public void setOnDisagreeButtonClickListener(OnDisagreeButtonClickListener dListener) {
        this.dListener = dListener;
    }


    //Review Button
    public interface OnCancelClickListener {

        public void onCancelButtonClick(View view, ReserveData reserveData, int position);
    }

    OnCancelClickListener cListener;

    public void setCancelButtonClickListener(OnCancelClickListener cListener) {
        this.cListener = cListener;
    }
}