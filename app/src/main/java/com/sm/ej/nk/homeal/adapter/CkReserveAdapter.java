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
public class CkReserveAdapter extends RecyclerView.Adapter<CkReserveViewHolder> implements CkReserveViewHolder.OnAgreeItemClickListener, CkReserveViewHolder.OnDisagreeItemClickListener, CkReserveViewHolder.OnReviewItemClickListener {
    List<CkReserveData> items = new ArrayList<>();

    public void add(CkReserveData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public CkReserveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ck_reserve_fragment, parent, false);
        CkReserveViewHolder holder = new CkReserveViewHolder(view);
        holder.setOnAgreeItemClickListener(this);
        holder.setOnDisagreeItemClickListener(this);
        holder.setOnReviewItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CkReserveViewHolder holder, int position) {
        CkReserveViewHolder crvh = (CkReserveViewHolder) holder;
        holder.setReserveData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, CkReserveData data, int position);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onAgreeItemClick(View view, CkReserveData data, int position) {
        if (listener != null) {
            listener.onAdapterItemClick(view, data, position);
        }
    }

    //----------------------------------------------------------------------------------------------------

    public interface OndisAdapterItemClickLIstener {
        public void ondisAdapterItemClick(View view, CkReserveData data, int position);
    }

    OndisAdapterItemClickLIstener dListener;

    public void setOndisAdapterItemClickListener(OndisAdapterItemClickLIstener dListener) {
        this.dListener = dListener;
    }

    @Override
    public void onDisagreeItemClick(View view, CkReserveData data, int position) {
        if (dListener != null) {
            dListener.ondisAdapterItemClick(view, data, position);
        }
    }
//------------------------------------------------------------------------------------------------------------------
    public interface OnreviewAdapterItemClickLIstener {
        public void onreviewAdapterItemClick(View view, CkReserveData data, int position);
    }

    OnreviewAdapterItemClickLIstener rListener;

    public void setOnreviewAdapterItemClickListener(OnreviewAdapterItemClickLIstener rListener) {
        this.rListener = rListener;
    }

    @Override
    public void onReviewItemClick(View view, CkReserveData data, int position) {
        if (rListener != null) {
            rListener.onreviewAdapterItemClick(view, data, position);
        }
    }


}
