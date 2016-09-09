package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ReviewData;
import com.sm.ej.nk.homeal.view.ReviewView;

import java.util.List;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewView> {

    List<ReviewData> datas;

    public ReviewAdapter(Context context, List<ReviewData> datas){
        this.datas = datas;
    }

    @Override
    public ReviewView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review, parent, false);
        return new ReviewView(view);
    }

    @Override
    public void onBindViewHolder(ReviewView holder, int position) {
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
