package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChattingSendViewHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.text_message)
    TextView messageView;



    public ChattingSendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void setMessage(String message) {
        messageView.setText(message);
    }
}
