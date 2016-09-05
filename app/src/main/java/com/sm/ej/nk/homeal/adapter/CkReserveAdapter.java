package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CkReserveData;
import com.sm.ej.nk.homeal.viewholder.CkReserveViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CkReserveAdapter extends RecyclerView.Adapter<CkReserveViewHolder> implements CkReserveViewHolder.OnAgreeButtonClickListener,
        CkReserveViewHolder.OnDisagreeButtonClickListener, CkReserveViewHolder.OnReviewButtonClickListener {
    List<CkReserveData> items = new ArrayList<>();

    public void add(CkReserveData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(CkReserveData[] data){

    }

    @Override
    public CkReserveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_reserve_fragment, parent, false);
        CkReserveViewHolder holder = new CkReserveViewHolder(view);
        holder.setOnAgreeButtonClickListener(this);
        holder.setOnDisagreeButtonClickListener(this);
        holder.setOnReviewButtonClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CkReserveViewHolder holder, int position) {
        CkReserveViewHolder crvh = (CkReserveViewHolder) holder;
     //   holder.setReserveData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Observer
    @Override
    public void onAgreeButtonClick(View view, CkReserveData ckReserveData, int position) {
        if (listener != null) {
            listener.onAagreeButtonClick(view, ckReserveData, position);
        }
    }

    @Override
    public void onDisagreeButtonClick(View view, CkReserveData ckReserveData, int position) {
        if (dListener != null) {
            dListener.onDisagreeButtonClick(view, ckReserveData, position);
        }
    }

    @Override
    public void onReviewButtonClick(View view, CkReserveData ckReserveData, int position) {
        if (rListener != null) {
            rListener.onreviewAdapterItemClick(view, ckReserveData, position);
        }
    }

    //Agree button
    public interface OnAagreeButtonClickLIstener {
        public void onAagreeButtonClick(View view, CkReserveData data, int position);
    }

    OnAagreeButtonClickLIstener listener;

    public void setOnAgreeButtonClickListener(OnAagreeButtonClickLIstener listener) {
        this.listener = listener;
    }

    //DisAgree button
    public interface OnDisagreeButtonClickLIstener {
        public void onDisagreeButtonClick(View view, CkReserveData data, int position);
    }

    OnDisagreeButtonClickLIstener dListener;

    public void setOnDisagreeButtonClickLIstener(OnDisagreeButtonClickLIstener dListener) {
        this.dListener = dListener;
    }


    //Write button
    public interface OnreviewButtonClickLIstener {
        public void onreviewAdapterItemClick(View view, CkReserveData data, int position);
    }

    OnreviewButtonClickLIstener rListener;

    public void setOnreviewAdapterItemClickListener(OnreviewButtonClickLIstener rListener) {
        this.rListener = rListener;
    }




}
