package com.sm.ej.nk.homeal.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.manager.CalendarManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CkHomeHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public View view;
    public ImageView userImage, nextCalendar, backCalendar, userMap, imageShare;
    public TextView userName, userAddress, jjimCount, reviewCount, textDate, textMorning, textLaunch, textDinner, textPax, totalScore, tasteScore, kindScore, cleanScore, priceScore;
    public ProgressBar progressTotal, progressTaste, progressKind, progressClean, progressPrice;
    public RecyclerView rvCalendar;
    Context context;
    CalendarAdapter mAdapter;
    List<CalendarItem> calendarItems;
    public LinearLayout reviewLinear;

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
        imageShare = (ImageView)view.findViewById(R.id.image_ck_home_share);
        textMorning = (TextView)view.findViewById(R.id.text_ck_home_mornig);
        textLaunch = (TextView)view.findViewById(R.id.text_ck_home_launch);
        textDinner = (TextView)view.findViewById(R.id.text_ck_home_dinner);
        textPax = (TextView)view.findViewById(R.id.text_ck_home_reservecount);
        reviewLinear = (LinearLayout)view.findViewById(R.id.linear_review_ck_home);
        reviewLinear.setOnClickListener(this);

        totalScore = (TextView)view.findViewById(R.id.text_ck_home_total);
        tasteScore = (TextView)view.findViewById(R.id.text_ck_home_taste);
        kindScore = (TextView)view.findViewById(R.id.text_ck_home_kind);
        cleanScore = (TextView)view.findViewById(R.id.text_ck_home_clean);
        priceScore = (TextView)view.findViewById(R.id.text_ck_home_price);
    }

    public void setData(CkDetailData data){
        Glide.with(HomealApplication.getContext()).load(data.image).into(userImage);
        userName.setText(data.name);
        userAddress.setText(data.address);
        jjimCount.setText(data.bookmarkCnt);
        reviewCount.setText(data.reviewCnt);
        progressKind.setProgress(data.kindness);
        progressClean.setProgress(data.cleanliness);
        progressTaste.setProgress(data.taste);
        progressPrice.setProgress(data.price);
        progressTotal.setProgress((int)data.grade);

        totalScore.setText(""+data.grade);
        kindScore.setText(""+data.kindness);
        cleanScore.setText(""+data.cleanliness);
        tasteScore.setText(""+data.taste);
        priceScore.setText(""+data.price);

        nextCalendar.setOnClickListener(this);
        backCalendar.setOnClickListener(this);
        Glide.with(HomealApplication.getContext()).load(data.map).into(userMap);
    }

    public void setCalendar(List<CkScheduleData> items){
        changeCalendarScheduleData(items);
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        rvCalendar.setLayoutManager(manager);

        CalendarData calendarData = CalendarManager.getInstance().getSelectCalendarData(calendarItems);
        mAdapter = new CalendarAdapter(context, calendarData, true);
        mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
            @Override
            public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                if(data.isSelect){
                    textDate.setText((data.month+1)+"월"+data.dayOfMonth+"일");
                }
                if(listener!=null){
                    listener.onCalendarClick(view, data, position);
                }
            }
        });
        rvCalendar.setAdapter(mAdapter);
        textDate.setText(calendarData.year+"년 "+(calendarData.month+1)+"월");
    }

    public void changeCalendarScheduleData(List<CkScheduleData> list){
        calendarItems = new ArrayList<>();
        if(!list.isEmpty()){
            for(int i=0; i<list.size(); i++){
                calendarItems.add(list.get(i).getCalendar());
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_ck_home_back:{
                CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
                textDate.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                textMorning.setBackgroundColor(Color.WHITE);
                textLaunch.setBackgroundColor(Color.WHITE);
                textDinner.setBackgroundColor(Color.WHITE);
                imageShare.setBackgroundColor(Color.WHITE);
                textPax.setText("");
                break;
            }
            case R.id.image_ck_home_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                textDate.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                textMorning.setBackgroundColor(Color.WHITE);
                textLaunch.setBackgroundColor(Color.WHITE);
                textDinner.setBackgroundColor(Color.WHITE);
                imageShare.setBackgroundColor(Color.WHITE);
                textPax.setText("");
                break;
            }
            case R.id.linear_review_ck_home:{
                if(reviewListener!=null){
                    reviewListener.onReviewClick(view);
                }
                break;
            }
        }
    }

    public void showSchedule(CalendarItem item){
        if(item.sharing==1){
            imageShare.setBackgroundColor(Color.BLUE);
        }else{
            imageShare.setBackgroundColor(Color.RED);
        }

        if(item.isMorning){
            textMorning.setBackgroundColor(Color.RED);
        }else{
            textMorning.setBackgroundColor(Color.WHITE);
        }

        if(item.isLaunch){
            textLaunch.setBackgroundColor(Color.RED);
        }else{
            textLaunch.setBackgroundColor(Color.WHITE);
        }

        if(item.isDinner){
            textDinner.setBackgroundColor(Color.RED);
        }else{
            textDinner.setBackgroundColor(Color.WHITE);
        }
        textPax.setText(item.pax);
    }

    public interface OnCalendarClickLIsener{
        public void onCalendarClick(View view, CalendarItem data, int position);
    }
    OnCalendarClickLIsener listener;
    public void setOnCalendarCickListener(OnCalendarClickLIsener listener){
        this.listener  = listener;
    }

    public interface OnReviewClickListener{
        public void onReviewClick(View view);
    }
    OnReviewClickListener reviewListener;
    public void setOnReviewClickListener(OnReviewClickListener listener){
        reviewListener = listener;
    }
}
