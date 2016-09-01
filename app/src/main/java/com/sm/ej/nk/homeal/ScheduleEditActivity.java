package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.manager.CalendarManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleEditActivity extends AppCompatActivity {

    @BindView(R.id.rv_schedule_edit_calendar)
    RecyclerView rv;

    CalendarAdapter mAdapter;

    private static int MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);
        ButterKnife.bind(this);

        CalendarData calendarData = CalendarManager.getInstance().getCalendarData();

        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        if(MODE  == CkMainActivity.MODE_SCHEDULR_EDIT){

        }else if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
            mAdapter = new CalendarAdapter(this, calendarData);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(mAdapter);
        }
    }
}
