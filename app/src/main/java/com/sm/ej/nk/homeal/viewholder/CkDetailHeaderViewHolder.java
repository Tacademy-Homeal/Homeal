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
    ImageView userImage, mapImage, backImage, nextImage, launch, dinner, sharingImage;
    TextView userName, userAddress, calendarDate,pax, totalScore, tasteScore, kindScore, cleanScore, priceScore, sharingOk, sharingNo;
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
    CalendarData calendarData;


    public CkDetailHeaderViewHolder(Context context,View view){
        super(view);
        this.context = context;
        this.view = view;
        viewPager = (ViewPager)view.findViewById(R.id.viewpager_ck_detail);
        userImage = (ImageView)view.findViewById(R.id.image_ck_detail_user);
        mapImage = (ImageView)view.findViewById(R.id.image_ck_detail_map);
        userName = (TextView)view.findViewById(R.id.text_ck_detail_name);
        userAddress = (TextView)view.findViewById(R.id.text_ck_detail_address);
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
        totalScore = (TextView)view.findViewById(R.id.text_ck_detail_total_score);
        priceScore = (TextView)view.findViewById(R.id.text_ck_detail_price_score);
        tasteScore = (TextView)view.findViewById(R.id.text_ck_detail_taste_score);
        kindScore = (TextView)view.findViewById(R.id.text_ck_detail_kind_score);
        cleanScore = (TextView)view.findViewById(R.id.text_ck_detail_clean_score);
        sharingImage = (ImageView)view.findViewById(R.id.image_ck_detail_sharing);

        launch = (ImageView)view.findViewById(R.id.image_ck_detail_launch);
        dinner = (ImageView)view.findViewById(R.id.image_ck_detail_dinner);
        pax = (TextView)view.findViewById(R.id.text_ck_detail_reservecount);

        sharingOk = (TextView)view.findViewById(R.id.text_ck_detail_sharing_ok);
        sharingNo = (TextView)view.findViewById(R.id.text_ck_detail_sharing_no);
    }

    public void showSchedule(CalendarItem item){
        if(item.sharing==1){
            sharingOk.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            sharingNo.setTextColor(Color.BLACK);
        }else{
            sharingNo.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            sharingOk.setTextColor(Color.BLACK);
        }
        sharingImage.setImageResource(R.drawable.homeal_sharing);

        if(item.isLaunch){
            launch.setImageResource(R.drawable.homeal_launch_fill);
        }else{
            launch.setImageResource(R.drawable.homeal_launch_empty);
        }

        if(item.isDinner){
            dinner.setImageResource(R.drawable.homeal_dinner_fill);
        }else{
            dinner.setImageResource(R.drawable.homeal_dinner_empty);
        }
        pax.setText(item.pax);
    }

    public void setThumbnails(List<ThumbnailsData> data){
        pagerAdapter = new ViewPagerAdapter(context, data);
        viewPager.setAdapter(pagerAdapter);
    }
    public void setData(CkDetailData data){
        this.data = data;

        circleIndicator.setViewPager(viewPager);
        Glide.with(context).load(data.image).into(userImage);
        Glide.with(context).load(data.map).into(mapImage);
        mapImage.setOnClickListener(this);
        userName.setText(data.name);
        userAddress.setText(data.address);
        backImage.setOnClickListener(this);
        nextImage.setOnClickListener(this);

        totalScore.setText(""+data.grade);
        tasteScore.setText(""+data.taste);
        priceScore.setText(""+data.price);
        cleanScore.setText(""+data.cleanliness);
        kindScore.setText(""+data.kindness);

        progressTotal.setProgress((int)data.grade);
        progressTaste.setProgress((int)data.taste);
        progressPrice.setProgress((int)data.price);
        progressClean.setProgress((int)data.cleanliness);
        progresskind.setProgress((int)data.kindness);
    }

    public void setSchedule(List<CkScheduleData> list){
        this.list = list;
        changeCalendarScheduleData(list);
        cm = CalendarManager.getInstance();
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        calendar.setLayoutManager(manager);
        calendarData = cm.getSelectCalendarData(calendarItems);

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

    public CalendarData getCalendarData(){
        return calendarData;
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

    public interface OnMapClickListener{
        public void onMapClick(View view, CkDetailData data);
    }
    OnMapClickListener mapListener;
    public void setOnMapClickListener(OnMapClickListener listener){
        this.mapListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_ck_detail_back:{
                CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
                calendarDate.setText(data.year+"년 "+(data.month+1)+"월");
                calendarAdapter.setCalendarData(data);
                calendarAdapter.cleanChecked();
                launch.setImageResource(R.drawable.homeal_launch_empty);
                dinner.setImageResource(R.drawable.homeal_dinner_empty);
                sharingImage.setImageResource(R.drawable.homeal_sharing_empty);
                pax.setText("0");
                sharingNo.setTextColor(Color.BLACK);
                sharingOk.setTextColor(Color.BLACK);
                break;
            }
            case R.id.image_ck_detail_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                calendarDate.setText(data.year+"년 "+(data.month+1)+"월");
                calendarAdapter.setCalendarData(data);
                calendarAdapter.cleanChecked();
                launch.setImageResource(R.drawable.homeal_launch_empty);
                dinner.setImageResource(R.drawable.homeal_dinner_empty);
                sharingImage.setImageResource(R.drawable.homeal_sharing_empty);
                pax.setText("0");
                sharingNo.setTextColor(Color.BLACK);
                sharingOk.setTextColor(Color.BLACK);
                break;
            }
            case R.id.image_ck_detail_map:{
                if(mapListener!=null){
                    mapListener.onMapClick(view, data);
                }
                break;
            }
        }
    }

}
