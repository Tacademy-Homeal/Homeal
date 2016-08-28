package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.viewholder.EtHomeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtHomeAdapter extends RecyclerView.Adapter<EtHomeViewHolder> implements EtHomeViewHolder.OnReviewClickListener, EtHomeViewHolder.OnJjimClickListener, EtHomeViewHolder.OnViewClickListener{
    List<EtHomeData> list = new ArrayList<>();

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addList(List<EtHomeData> list){
      this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public EtHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_et_home, parent, false);
        return new EtHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EtHomeViewHolder holder, int position) {
        holder.setData(list.get(position));
        holder.setOnJjimClickListener(this);
        holder.setOnReviewClickListener(this);
        holder.setOnViewClickListner(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onViewClick(View view) {
        if(listener!=null){
            listener.onViewClick(view);
        }
    }
    public interface OnViewClickListener{
        public void onViewClick(View view);
    }
    OnViewClickListener listener;
    public void setOnViewClickListener(OnViewClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onJjimClick(View view) {
        if(jjimitemClickListener!=null){
            jjimitemClickListener.onJjimitemClick(view);
        }
    }
    public interface OnJjimitemClickListener{
        public void onJjimitemClick(View view);
    }

    OnJjimitemClickListener jjimitemClickListener;
    public void setOnJjimitemClickListener(OnJjimitemClickListener listener){
        this.jjimitemClickListener = listener;
    }


    @Override
    public void onReviewClick(View view) {
        if(reviewitemClickListener!=null){
            reviewitemClickListener.onReviewitemClick(view);
        }
    }

    public interface OnReviewitemClickListener{
        public void onReviewitemClick(View view);
    }

    OnReviewitemClickListener reviewitemClickListener;
    public void setOnReciewClickListener(OnReviewitemClickListener listener){
        this.reviewitemClickListener = listener;
    }

}
