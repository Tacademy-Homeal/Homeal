package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ZzimData;
import com.sm.ej.nk.homeal.viewholder.ZzimViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class ZzimAdapter extends RecyclerView.Adapter<ZzimViewHolder> implements ZzimViewHolder.OnReviewClickListener, ZzimViewHolder.OnZzimClickListener, ZzimViewHolder.OnViewClickListener {
    List<ZzimData> list = new ArrayList<>();

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void add(ZzimData data){
        this.list.add(data);
        notifyDataSetChanged();
    }

    public void addList(List<ZzimData> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public ZzimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_et_zzim, parent, false);
        return new ZzimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ZzimViewHolder holder, int position) {
        holder.setData(list.get(position));
        holder.setOnViewClickListner(this);
        holder.setOnReviewClickListener(this);
        holder.setOnZzimClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onReviewClick(View view, int position) {
        if(reviewitemClickListener!=null){
            reviewitemClickListener.onReviewitemClick(view,position);
        }
    }

    public interface OnReviewitemClickListener{
        public void onReviewitemClick(View view, int position);
    }

    OnReviewitemClickListener reviewitemClickListener;
    public void setOnReviewClickListener(OnReviewitemClickListener listener){
        this.reviewitemClickListener = listener;
    }

    @Override
    public void onViewClick(View view, int position) {
        if (listener != null) {
            listener.onViewClick(view, position);
        }
    }

    public interface OnViewClickListener {
        public void onViewClick(View view, int position);
    }

    OnViewClickListener listener;

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onZzimClick(View view, int position) {
        if(zzimitemClickListener!=null){
            zzimitemClickListener.onZzimitemClick(view,position);
        }
    }
    public interface OnZzimitemClickListener{
        public void onZzimitemClick(View view, int position);
    }

    OnZzimitemClickListener zzimitemClickListener;
    public void setOnZzimitemClickListener(OnZzimitemClickListener listener){
        this.zzimitemClickListener = listener;
    }
}
