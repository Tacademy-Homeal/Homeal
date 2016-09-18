package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ReserveData;
import com.sm.ej.nk.homeal.viewholder.EtReserveViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class EtReserveAdapter extends RecyclerView.Adapter<EtReserveViewHolder>
        implements EtReserveViewHolder.OnReserveButtonClick{
    List<ReserveData> items = new ArrayList<>();

    public void add(ReserveData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ReserveData> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public EtReserveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_et_reserve_fragment, parent, false);
        EtReserveViewHolder holder = new EtReserveViewHolder(view);
        holder.setReserveButtonClick(this);
//        holder.setCancelButtonClick(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(EtReserveViewHolder holder, int position) {
        EtReserveViewHolder ervh = (EtReserveViewHolder) holder;
        holder.setReserveData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //Review Button click evnet & cancle button

    public interface OnReserveAdapterClick {
        public void onReserveAdapterClick(View view, ReserveData etReserveData, int position);
    }

    OnReserveAdapterClick rListener;

    public void setReserveItemClickListener(OnReserveAdapterClick rListener) {
        this.rListener = rListener;
    }

    @Override
    public void onReserveButtonClick(View view, ReserveData etReserveData, int position) {
        if(rListener != null){
            rListener.onReserveAdapterClick(view,etReserveData,position);
        }
    }


//    //cancel button setting
//
//    public interface OnCancelAdapterClick{
//        public void onCancelAdapterClick(View view, ReserveData data, int position);
//    }
//
//    OnCancelAdapterClick cListener;
//
//    public void setCancelItemClickListener(OnCancelAdapterClick cListener){
//        this.cListener = cListener;
//    }
//
//    @Override
//    public void onCancelButtonClick(View view, ReserveData data, int position) {
//        if(cListener != null){
//            cListener.onCancelAdapterClick(view, data, position);
//        }
//
//    }
}