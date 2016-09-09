package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.viewholder.ThumbnailViewHolder;

import java.util.List;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
    private List<ThumbnailsData> datas;
    private Context context;

    public ThumbnailAdapter(Context context,List<ThumbnailsData> datas){
        this.datas = datas;
    }

    @Override
    public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_thumbnail, parent, false);
        return new ThumbnailViewHolder(view);
    }
//
    @Override
    public void onBindViewHolder(ThumbnailViewHolder holder,final int position) {

        Glide.with(HomealApplication.getContext())
                .load(datas.get(position).getImage())
                .centerCrop()
                .into(holder.imageView);
        if(datas.get(position).isLastItem()){
            holder.deleteImage.setVisibility(View.INVISIBLE);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageLIstener!=null){
                        imageLIstener.onImageCLick(view, datas.get(position), position);
                    }
                }
            });
        }else{
            holder.deleteImage.setVisibility(View.VISIBLE);
            holder.deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if(deleteListener != null){
                        deleteListener.onDeleteClick(view, datas.get(position), position);
                    }
                }

            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnImageCLickListener{
        public void onImageCLick(View view, ThumbnailsData data, int position);
    }
    OnImageCLickListener imageLIstener;
    public void setOnImageCLickListener(OnImageCLickListener listener){
        this.imageLIstener = listener;
    }

    public interface OnDeleteClickListener{
        public void onDeleteClick(View view, ThumbnailsData data, int position);
    }
    OnDeleteClickListener deleteListener;
    public void setOnDelteCLickListener(OnDeleteClickListener listener){
        this.deleteListener = listener;
    }

}
