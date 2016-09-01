package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CalendarItemData;
import com.sm.ej.nk.homeal.manager.CalendarManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleEditActivity extends AppCompatActivity {

    @BindView(R.id.rv_schedule_edit_calendar)
    RecyclerView rv;

    @BindView(R.id.text_schedule_edit_calendar)
    TextView textCalendar;

    @BindView(R.id.text_schedule_edit_mornig)
    TextView textMornig;

    @BindView(R.id.text_schedule_edit_launch)
    TextView textLaunch;

    @BindView(R.id.text_schedule_edit_dinner)
    TextView textDinner;

    @BindView(R.id.edit_schedule_edit_reservecount)
    EditText editReserve;

    @BindView(R.id.toolbar_schedule_edit)
    Toolbar toolbar;

    CalendarAdapter mAdapter;
    ArrayList<CalendarItemData> mItemdata = new ArrayList<>();

    private static int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);
        ButterKnife.bind(this);
        try{
            CalendarManager.getInstance().setDataObject(mItemdata);
        }catch(Exception e){
            e.printStackTrace();
        }
        CalendarData calendarData = CalendarManager.getInstance().getCalendarData();
        mAdapter = new CalendarAdapter(this, calendarData);
        mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
            @Override
            public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                textCalendar.setText(""+data.month+"월" +data.dayOfMonth+"일");
            }
        });

        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        if(MODE  == CkMainActivity.MODE_SCHEDULR_EDIT){

        }else if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
            rv.setLayoutManager(new GridLayoutManager(this, 7));
            rv.setAdapter(mAdapter);
        }
    }
}
