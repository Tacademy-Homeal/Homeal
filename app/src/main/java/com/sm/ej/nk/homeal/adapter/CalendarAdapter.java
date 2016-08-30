package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.view.CalendarView;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarView> {
    Context context;
    CalendarData data;

    SparseBooleanArray itemSelected = new SparseBooleanArray();
    int checkedPosition = INVALID_POSITION;

    public static final int INVALID_POSITION = -1;
    public static final int CHOICE_MODE_SINGLE = 0;
    public static final int CHOICE_MODE_MULTIPLE = 1;

    private int mode = CHOICE_MODE_SINGLE;

    public CalendarAdapter(Context context, CalendarData data){
        this.context = context;
        this.data = data;
    }

    @Override
    public CalendarView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ck_detail_calender, parent, false);
        return new CalendarView(view);
    }

    @Override
    public void onBindViewHolder(CalendarView holder, int position) {
        holder.setData(data.days.get(position));
        final int finalposition = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == CHOICE_MODE_SINGLE){
                    setItemChecked(finalposition, true);
                }else{
                    boolean checked = itemSelected.get(finalposition);
                    setItemChecked(finalposition, !checked);
                }

                if(listener != null){
                    listener.onCalendarAdapterClick(view,  finalposition , data.days.get(finalposition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int size=0;
        if(data!=null){
            size = data.days.size();
        }
        return size;
    }

    public interface OnCalendarAdapterClickListener{
        public void onCalendarAdapterClick(View view,int position, CalendarItem data);
    }
    OnCalendarAdapterClickListener listener;
    public void setOnCalendarAdpaterClickListener(OnCalendarAdapterClickListener listener){
        this.listener = listener;
    }

    public void setItemChecked(int position, boolean isChecked){
        if(mode == CHOICE_MODE_SINGLE){
            if(checkedPosition != position){
                if(isChecked){
                    checkedPosition = position;
                    notifyDataSetChanged();
                }
            }else{
                if(!isChecked){
                    checkedPosition = INVALID_POSITION;
                    notifyDataSetChanged();
                }
            }
        }else{
            boolean cheked = itemSelected.get(position);
            if(cheked != isChecked){
                itemSelected.put(position, isChecked);
                notifyDataSetChanged();
            }
        }
    }
}
