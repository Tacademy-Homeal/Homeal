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
import com.sm.ej.nk.homeal.data.CalendarItemData;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.manager.CalendarManager;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CkDetailHeaderViewHolder extends RecyclerView.ViewHolder {
    View view;
    ViewPager viewPager;
    ImageView userImage, mapImage;
    TextView userName, userAddress, foodPrice, foodName, foodIntroduce, calendarMonth, calendarYear;
    RecyclerView calendar;
    CkDetailData data;
    ViewPagerAdapter pagerAdapter;
    CalendarAdapter calendarAdapter;
    Context context;
    ArrayList<CalendarItemData> mItemData = new ArrayList<>();
    ProgressBar progressPrice, progressTaste, progresskind, progressClean;
    ProgressBar progressTotal;

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
        foodIntroduce = (TextView)view.findViewById(R.id.text_ck_detail_introduce);
        calendar = (RecyclerView)view.findViewById(R.id.rv_ck_detail_calendar);
        calendarMonth = (TextView)view.findViewById(R.id.text_ck_detail_month);
        calendarYear = (TextView)view.findViewById(R.id.text_ck_detail_year);
        progressTotal = (ProgressBar) view.findViewById(R.id.progress_total);
        progressPrice = (ProgressBar)view.findViewById(R.id.progress_price);
        progressTaste = (ProgressBar)view.findViewById(R.id.progress_taste);
        progresskind = (ProgressBar)view.findViewById(R.id.progress_kind);
        progressClean = (ProgressBar)view.findViewById(R.id.progress_clean);
    }

    public void setData(CkDetailData data){
        this.data = data;
        pagerAdapter = new ViewPagerAdapter(context, data.pagerImageList);
        viewPager.setAdapter(pagerAdapter);
        Glide.with(context).load(data.userImage).into(userImage);
        Glide.with(context).load(data.mapImage).into(mapImage);
        userName.setText(data.userName);
        userAddress.setText(data.userAddress);
        foodPrice.setText(data.foodPrice);
        foodName.setText(data.foodName);
        foodIntroduce.setText(data.foodIntroduce);

        try {
            CalendarManager.getInstance().setDataObject(mItemData);
        } catch (CalendarManager.NoComparableObjectException e) {
            e.printStackTrace();
        }
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        calendar.setLayoutManager(manager);
        CalendarData calendarData = CalendarManager.getInstance().getCalendarData();
        calendarAdapter = new CalendarAdapter(context, calendarData);
        calendar.setAdapter(calendarAdapter);

        progressTotal.setProgress(data.totalScore);
        progressTaste.setProgress(data.tasteScore);
        progressPrice.setProgress(data.priceScore);
        progressClean.setProgress(data.cleanScore);
        progresskind.setProgress(data.kindScore);
    }
}
