package com.sm.ej.nk.homeal.viewholder;

import android.content.Context;
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
import com.sm.ej.nk.homeal.data.CalendarItemData;
import com.sm.ej.nk.homeal.data.CkDetailData;
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
    ImageView userImage, mapImage, backImage, nextImage;
    TextView userName, userAddress, foodPrice, foodName, calendarDate;
    RecyclerView calendar;
    CkDetailData data;
    ViewPagerAdapter pagerAdapter;
    CalendarAdapter calendarAdapter;
    Context context;
    ArrayList<CalendarItemData> mItemData = new ArrayList<>();
    ProgressBar progressPrice, progressTaste, progresskind, progressClean;
    ProgressBar progressTotal;
    CalendarManager cm;
    CirclePageIndicator circleIndicator;

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
    }

    public void setData(CkDetailData data){
        this.data = data;
        pagerAdapter = new ViewPagerAdapter(context, data.pagerImageList);
        viewPager.setAdapter(pagerAdapter);
//        circleIndicator.setViewPager(viewPager);
        Glide.with(context).load(data.userImage).into(userImage);
        Glide.with(context).load(data.mapImage).into(mapImage);
        userName.setText(data.userName);
        userAddress.setText(data.address);
        foodPrice.setText(data.foodPrice);
        foodName.setText(data.foodName);
        backImage.setOnClickListener(this);
        nextImage.setOnClickListener(this);

        cm = CalendarManager.getInstance();
        try {
            cm.setDataObject(mItemData);
        } catch (CalendarManager.NoComparableObjectException e) {
            e.printStackTrace();
        }
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        calendar.setLayoutManager(manager);

        List<CalendarItem> items = new ArrayList<>();
        for(int i=4; i<10; i++){
            CalendarItem item = new CalendarItem();
            item.dayOfMonth=i;
            item.isSelect = true;
            item.year = 2016;
            item.month = 8;
            items.add(item);
        }
        final CalendarData calendarData = cm.getSelectCalendarData(items);


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
        progressTotal.setProgress(data.totalScore);
        progressTaste.setProgress(data.tasteScore);
        progressPrice.setProgress(data.priceScore);
        progressClean.setProgress(data.cleanScore);
        progresskind.setProgress(data.kindScore);
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
                break;
            }
            case R.id.image_ck_detail_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                calendarDate.setText(data.year+"년 "+(data.month+1)+"월");
                calendarAdapter.setCalendarData(data);
                calendarAdapter.cleanChecked();
                break;
            }
        }
    }
}
