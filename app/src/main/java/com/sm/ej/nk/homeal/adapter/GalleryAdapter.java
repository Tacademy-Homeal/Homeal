package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.GalleryItemData;
import com.sm.ej.nk.homeal.view.GalleryItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryItemView>{
    Context context;
    List<GalleryItemData> data;

    public List<GalleryItemData> getPhotoList() {
        return data;
    }

    public List<GalleryItemData> getSlectedPhotoList(){
        List<GalleryItemData> selectPhotoList = new ArrayList<>();
        for(int i=0; i< data.size(); i++){
            GalleryItemData item = data.get(i);
            if(item.isSelected()){
                selectPhotoList.add(item);
            }
        }
        return selectPhotoList;
    }

    public GalleryAdapter(Context context, List<GalleryItemData> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public GalleryItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new GalleryItemView(view);
    }

    @Override
    public void onBindViewHolder(final GalleryItemView holder,final int position) {
        GalleryItemData item = data.get(position);
        Glide.with(context).load(item.getImagePath()).centerCrop().crossFade().into(holder.imagePhoto);
        if(item.isSelected()){
            holder.layoutSelect.setVisibility(View.VISIBLE);
        }else{
            holder.layoutSelect.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.onPhotoClick(view, holder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnPhotoClickListener{
        public void onPhotoClick(View view, GalleryItemView holder, int position);
    }
    OnPhotoClickListener listener;
    public void setOnPhotoClickListener(OnPhotoClickListener listener){
        this.listener = listener;
    }
}
