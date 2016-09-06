package com.sm.ej.nk.homeal.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.adapter.ViewPagerAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View view;
    ViewPager viewPager;
    ImageView userImage, mapImage, backImage, nextImage, sharingImage;
    TextView userName, userAddress, foodPrice, foodName, calendarDate, morning, launch, dinner, pax;
    RecyclerView calendar;
    CkDetailData data;
    ViewPagerAdapter pagerAdapter;
    CalendarAdapter calendarAdapter;
    Context context;
    ProgressBar progressPrice, progressTaste, progresskind, progressClean;
    ProgressBar progressTotal;
    CalendarManager cm;
    CirclePageIndicator circleIndicator;
    List<CkScheduleData> list;
    List<CalendarItem> calendarItems;
//hhhhhhhh
    public CkDetailHeaderViewHolder(Context context,View view){
        super(view);
        this.context = context;
        this.view = view;
        viewPager = (ViewPager)view.findViewById(R.id.viewpager_ck_detail);
        userImage = (ImageView)view.findViewById(R.id.image_ck_detail_user);
        mapImage = (ImageView)view.findViewById(R.id.image_ck_detail_map);
        userName = (TextView)view.findViewById(R.id.text_ck_detail_name);
        userAddress = (TextView)view.findViewById(R.id.text_ck_detail_address);
        foodPrice = (TextView)view.findViewById(R.id.text_ck_detail_price);
        foodName = (TextView)view.findViewById(R.id.text_ck_detail_foodname);
        calendar = (RecyclerView)view.findViewById(R.id.rv_ck_detail_calendar);
        calendarDate = (TextView)view.findViewById(R.id.text_ck_detail_date);
        progressTotal = (ProgressBar) view.findViewById(R.id.progress_total);
        progressPrice = (ProgressBar)view.findViewById(R.id.progress_price);
        progressTaste = (ProgressBar)view.findViewById(R.id.progress_taste);
        progresskind = (ProgressBar)view.findViewById(R.id.progress_kind);
        progressClean = (ProgressBar)view.findViewById(R.id.progress_clean);
        backImage = (ImageView)view.findViewById(R.id.image_ck_detail_back);
        nextImage = (ImageView)view.findViewById(R.id.image_ck_detail_next);
        circleIndicator = (CirclePageIndicator)view.findViewById(R.id.indicator_ck_detail);

        sharingImage = (ImageView)view.findViewById(R.id.image_ck_detail_share);
        morning = (TextView)view.findViewById(R.id.text_ck_detail_morning);
        launch = (TextView)view.findViewById(R.id.text_ck_detail_launch);
        dinner = (TextView)view.findViewById(R.id.text_ck_detail_dinner);
        pax = (TextView)view.findViewById(R.id.text_ck_detail_reservecount);
    }

    public void showSchedule(CalendarItem item){
        if(item.sharing==1){
            sharingImage.setBackgroundColor(Color.BLUE);
        }else{
            sharingImage.setBackgroundColor(Color.RED);
        }

        if(item.isMorning){
            morning.setBackgroundColor(Color.RED);
        }else{
            morning.setBackgroundColor(Color.WHITE);
        }

        if(item.isLaunch){
            launch.setBackgroundColor(Color.RED);
        }else{
            launch.setBackgroundColor(Color.WHITE);
        }

        if(item.isDinner){
            dinner.setBackgroundColor(Color.RED);
        }else{
            dinner.setBackgroundColor(Color.WHITE);
        }
        pax.setText(item.pax);
    }

    public void setThumbnails(List<ThumbnailsData> data){
        pagerAdapter = new ViewPagerAdapter(context, data);
        viewPager.setAdapter(pagerAdapter);
    }
    public void setData(CkDetailData data){
        this.data = data;

//        circleIndicator.setViewPager(viewPager);
        Glide.with(context).load(data.image).into(userImage);
        Glide.with(context).load(data.mapImage).into(mapImage);
        userName.setText(data.name);
        userAddress.setText(data.address);
        backImage.setOnClickListener(this);
        nextImage.setOnClickListener(this);


        progressTotal.setProgress(data.grade);
//        progressTaste.setProgress(Integer.parseInt(data.taste));
        progressPrice.setProgress(data.price);
        progressClean.setProgress(data.cleanliness);
        progresskind.setProgress(data.kindness);
    }

    public void setSchedule(List<CkScheduleData> list){
        this.list = list;
        changeCalendarScheduleData(list);
        cm = CalendarManager.getInstance();
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        calendar.setLayoutManager(manager);
        final CalendarData calendarData = cm.getSelectCalendarData(calendarItems);

        calendarAdapter = new CalendarAdapter(context, calendarData, true);
        calendarAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
            @Override
            public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                if(data.isSelect){
                    calendarDate.setText((data.month+1)+"월"+data.dayOfMonth+"일");
                }
                if(listener!=null){
                    listener.onCalendarHeaderViewClickListener(view, data, position);
                }
            }
        });
        calendar.setAdapter(calendarAdapter);
        calendarDate.setText(calendarData.year+"년 "+(calendarData.month+1)+"월");
    }

    public void changeCalendarScheduleData(List<CkScheduleData> list){
        calendarItems = new ArrayList<>();
        if(!list.isEmpty()){
            for(int i=0; i<list.size(); i++){
                calendarItems.add(list.get(i).getCalendar());
            }
        }
    }

    public interface OnCalendarHeaderViewClickListener{
        public void onCalendarHeaderViewClickListener(View view, CalendarItem data, int position);
    }

    OnCalendarHeaderViewClickListener listener;
    public void setOnCalendarHeaderViewClickListener(OnCalendarHeaderViewClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_ck_detail_back:{
                CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
                calendarDate.setText(data.year+"년 "+(data.month+1)+"월");
                calendarAdapter.setCalendarData(data);
                calendarAdapter.cleanChecked();
                morning.setBackgroundColor(Color.WHITE);
                launch.setBackgroundColor(Color.WHITE);
                dinner.setBackgroundColor(Color.WHITE);
                sharingImage.setBackgroundColor(Color.WHITE);
                pax.setText("");
                break;
            }
            case R.id.image_ck_detail_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                calendarDate.setText(data.year+"년 "+(data.month+1)+"월");
                calendarAdapter.setCalendarData(data);
                calendarAdapter.cleanChecked();
                morning.setBackgroundColor(Color.WHITE);
                launch.setBackgroundColor(Color.WHITE);
                dinner.setBackgroundColor(Color.WHITE);
                sharingImage.setBackgroundColor(Color.WHITE);
                pax.setText("");
                break;
            }
        }
    }

}
