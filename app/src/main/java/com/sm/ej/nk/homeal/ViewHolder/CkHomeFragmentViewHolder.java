package com.sm.ej.nk.homeal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkHomeFragmentViewHolder extends RecyclerView.ViewHolder{


    TextView titleView;

    public CkHomeFragmentViewHolder(View itemView){
        super(itemView);
    }


    public void setTitle(String title) {
        titleView.setText(title);
    }
}
