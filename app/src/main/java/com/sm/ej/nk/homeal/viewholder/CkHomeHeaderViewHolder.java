package com.sm.ej.nk.homeal.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkHomeData;
import com.sm.ej.nk.homeal.manager.CalendarManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public View view;
    public ImageView userImage, nextCalendar, backCalendar, userMap;
    public TextView userName, userAddress, jjimCount, reviewCount, textDate;
    public ProgressBar progressTotal, progressTaste, progressKind, progressClean, progressPrice;
    public RecyclerView rvCalendar;
    Context context;
    CalendarAdapter mAdapter;

    public CkHomeHeaderViewHolder(Context context, View view){
        super(view);
        this.context = context;
        userImage = (ImageView)view.findViewById(R.id.image_ck_home_user);
        userName = (TextView)view.findViewById(R.id.text_ck_home_name);
        userAddress = (TextView)view.findViewById(R.id.text_ck_home_add);
        jjimCount = (TextView)view.findViewById(R.id.text_ck_home_jjimcount);
        reviewCount = (TextView)view.findViewById(R.id.text_ck_home_reviewcount);
        progressTotal = (ProgressBar)view.findViewById(R.id.progress_ck_home_total);
        progressTaste = (ProgressBar)view.findViewById(R.id.progress_ck_home_taste);
        progressKind = (ProgressBar)view.findViewById(R.id.progress_ck_home_kind);
        progressClean = (ProgressBar)view.findViewById(R.id.progress_ck_home_clean);
        progressPrice = (ProgressBar)view.findViewById(R.id.progress_ck_home_price);
        nextCalendar = (ImageView)view.findViewById(R.id.image_ck_home_next);
        backCalendar = (ImageView)view.findViewById(R.id.image_ck_home_back);
        userMap = (ImageView)view.findViewById(R.id.image_ck_home_map);
        textDate = (TextView)view.findViewById(R.id.text_ck_home_calendar);
        rvCalendar = (RecyclerView)view.findViewById(R.id.rv_ck_home_calendar);
    }

    public void setData(CkHomeData data){
        Glide.with(HomealApplication.getContext()).load(data.user.userImage).into(userImage);
        userName.setText(data.user.userName);
        userAddress.setText(data.user.userAddress);
        jjimCount.setText(data.jjimCount);
        reviewCount.setText(data.reviewCount);
        progressKind.setProgress(data.kindScore);
        progressClean.setProgress(data.cleanScore);
        progressTaste.setProgress(data.tasteScore);

        GridLayoutManager manager = new GridLayoutManager(context, 7);
        rvCalendar.setLayoutManager(manager);

        List<CalendarItem> list = new ArrayList<>();
        CalendarData calendarData = CalendarManager.getInstance().getSelectCalendarData(list);
        mAdapter = new CalendarAdapter(context, calendarData, true);
        rvCalendar.setAdapter(mAdapter);
        nextCalendar.setOnClickListener(this);
        backCalendar.setOnClickListener(this);
        Glide.with(HomealApplication.getContext()).load("http://fimg2.pann.com/new/download.jsp?FileID=28115067").into(userMap);
        textDate.setText(calendarData.year+"년 "+(calendarData.month+1)+"월");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_ck_home_back:{
                CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
                textDate.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                break;
            }
            case R.id.image_ck_home_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                textDate.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                break;
            }
        }
    }
}
