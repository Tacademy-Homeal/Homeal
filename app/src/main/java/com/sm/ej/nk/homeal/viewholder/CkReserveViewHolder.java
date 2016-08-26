package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkReserveData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkReserveViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_ck_reserve_picture)
    ImageView pictureView;
//test
    @BindView(R.id.text_ck_reserve_date)
    TextView dateView;

    @BindView(R.id.text_ck_reserve_person)
    TextView reservepersonView;

    @BindView(R.id.text_ck_reserve_etname)
    TextView cknameView;

    @BindView(R.id.rating_ck_reserve_et)
    RatingBar ckratingView;

    @BindView(R.id.text_ck_reserve_state)
    TextView reservestateView;

    @BindView(R.id.text_ck_reserve_foodname)
    TextView foodnameView;

    @BindView(R.id.btn_ck_reserve_state_disagree)
    Button disagreeView;

    @BindView(R.id.btn_ck_reserve_state_agree)
    Button agreeView;

    @BindView(R.id.btn_ck_review_write)
    Button reviewwriteView;

    @OnClick(R.id.btn_ck_reserve_state_disagree)
    public void onDisagree() {
        showdialog();
    }

    private void showdialog() {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
//        builder.setTitle("Alert Dialog");
//        builder.setMessage("Dialog Message");
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Yes click", Toast.LENGTH_SHORT).show();
//            }
//        });
////        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                Toast.makeText(MainActivity.this, "Cancel click", Toast.LENGTH_SHORT).show();
////            }
////        });
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "No click", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public CkReserveViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        agreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aListener != null) {
                    aListener.onAgreeItemClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });

        disagreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dListener != null) {
                    dListener.onDisagreeItemClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });
        reviewwriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rListener != null) {
                    rListener.onReviewItemClick(v, ckReserveData, getAdapterPosition());
                }
            }
        });
    }

    public interface OnAgreeItemClickListener {

        public void onAgreeItemClick(View view, CkReserveData ckReserveData, int position);
    }

    OnAgreeItemClickListener aListener;

    public void setOnAgreeItemClickListener(OnAgreeItemClickListener aListener) {
        this.aListener = aListener;
    }

    public interface OnDisagreeItemClickListener {
        public void onDisagreeItemClick(View view, CkReserveData ckReserveData, int position);
    }

    OnDisagreeItemClickListener dListener;

    public void setOnDisagreeItemClickListener(OnDisagreeItemClickListener dListener) {
        this.dListener = dListener;
    }

    public interface OnReviewItemClickListener {

        public void onReviewItemClick(View view, CkReserveData ckReserveData, int position);
    }

    OnReviewItemClickListener rListener;

    public void setOnReviewItemClickListener(OnReviewItemClickListener rListener) {
        this.rListener = rListener;
    }

    CkReserveData ckReserveData;

    public void setReserveData(CkReserveData ckReserveData) {
        this.ckReserveData = ckReserveData;
        pictureView.setImageDrawable(ckReserveData.getEtpicture());
        dateView.setText(ckReserveData.getReservedate());
        reservepersonView.setText(ckReserveData.getReserveperson());
        cknameView.setText(ckReserveData.getEtname());
        foodnameView.setText(ckReserveData.getFoodname());
        reservestateView.setText(ckReserveData.getReservestate());
        disagreeView.setText(ckReserveData.getBtndisagree());
        agreeView.setText(ckReserveData.getBtnagree());
        reviewwriteView.setText(ckReserveData.getBtnreviewwrite());
    }
}