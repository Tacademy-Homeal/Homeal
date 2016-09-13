package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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
    SparseBooleanArray itemSelected = new SparseBooleanArray();

    int checkPosition = INVALID_POSITION;

    public static final int INVALID_POSITION = -1;
    public static final int CHOICE_MODE_SINGLE = 1;
    public static final int CHOICE_MODE_MULTIPLE = 2;

    private int mode;

    public List<GalleryItemData> getPhotoList() {
        return data;
    }



    public GalleryAdapter(Context context, List<GalleryItemData> data, int ChoiceMode){
        this.context = context;
        this.data = data;
        mode = ChoiceMode;
    }

    @Override
    public GalleryItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new GalleryItemView(view);
    }

    @Override
    public void onBindViewHolder(final GalleryItemView holder,final int position) {
        final GalleryItemData item = data.get(position);
        Glide.with(context).load(item.getImagePath()).centerCrop().crossFade().into(holder.imagePhoto);
        if(mode == CHOICE_MODE_SINGLE){
            holder.setChecked(checkPosition == position);
        }else{
            holder.setChecked(itemSelected.get(position));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener !=null){
                    listener.onPhotoClick(view, item, mode);
                }
                if(mode ==CHOICE_MODE_SINGLE){
                    setItemChecked(position, true);
                }else{
                    boolean checked = itemSelected.get(position);
                    setItemChecked(position, !checked);
                }
            }
        });
    }
    public List<GalleryItemData> getCheckedItemPositions(){
        List<GalleryItemData> selectPhotoList = new ArrayList<>();
        for(int i=0; i<data.size(); i++){
            if(itemSelected.get(i)){
                selectPhotoList.add(data.get(i));
            }
        }
        return selectPhotoList;
    }

    private void setItemChecked(int position, boolean isChecked){
        if(mode == CHOICE_MODE_SINGLE){
            if(checkPosition != position){
                if(isChecked){
                    checkPosition = position;
                    notifyDataSetChanged();
                }
            }else{
                if(!isChecked){
                    checkPosition = INVALID_POSITION;
                    notifyDataSetChanged();
                }
            }
        }else{
            boolean checked = itemSelected.get(position);
            if(checked != isChecked){
                itemSelected.put(position, isChecked);
                notifyDataSetChanged();
            }
        }
    }

    public void clearCheked(){
        itemSelected.clear();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnPhotoClickListener{
        public void onPhotoClick(View view, GalleryItemData data, int mode);
    }
    OnPhotoClickListener listener;
    public void setOnPhotoClickListener(OnPhotoClickListener listener){
        this.listener = listener;
    }
}
