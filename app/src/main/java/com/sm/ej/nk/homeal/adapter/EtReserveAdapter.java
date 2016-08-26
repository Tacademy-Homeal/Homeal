package com.sm.ej.nk.homeal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.EtReserveData;
import com.sm.ej.nk.homeal.viewholder.EtReserveViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class EtReserveAdapter extends RecyclerView.Adapter<EtReserveViewHolder> implements EtReserveViewHolder.OnCancleItemClickListener, EtReserveViewHolder.OnReviewItemClickListener {
    List<EtReserveData> items = new ArrayList<>();

    public void add(EtReserveData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public EtReserveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_et_reserve_fragment, parent, false);
        EtReserveViewHolder holder = new EtReserveViewHolder(view);
        holder.setOnCancleItemClickListener(this);
        holder.setOnReviewItemClickListener(this);
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


    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, EtReserveData etReserveData, int position);
    }

    OnAdapterItemClickLIstener cListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener cListener) {
        this.cListener = cListener;
    }
    @Override
    public void onCancleItemClick(View view, EtReserveData etReserveData, int position) {
        if (cListener != null) {
            cListener.onAdapterItemClick(view, etReserveData, position);
        }
    }
//-------------------------------------------------------------------------------------------
    public interface OnreviewAdapterItemClickLIstener {
        public void onreviewAdapterItemClick(View view, EtReserveData etReserveData, int position);
    }

    OnreviewAdapterItemClickLIstener rListener;

    public void setOnreviewAdapterItemClickListener(OnreviewAdapterItemClickLIstener rListener) {
        this.rListener = rListener;
    }
    @Override
    public void onReviewItemClick(View view, EtReserveData etReserveData, int position) {
        if (rListener != null) {
            rListener.onreviewAdapterItemClick(view, etReserveData, position);
        }

    }

}
