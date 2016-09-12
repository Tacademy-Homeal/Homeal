package com.sm.ej.nk.homeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.CalendarAdapter;
import com.sm.ej.nk.homeal.data.CalendarData;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CalendarItemData;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkScheduleEditRequest;
import com.sm.ej.nk.homeal.request.CkScheduleInsertRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleEditActivity extends AppCompatActivity implements View.OnClickListener{

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

    @BindView(R.id.image_schedule_edit_back)
    ImageView backImage;

    @BindView(R.id.image_schedule_edit_next)
    ImageView nextImage;

    @BindView(R.id.image_schedule_edit_share)
    ImageView shareImage;

    @BindView(R.id.fab_schedule_edit)
    FloatingActionButton fab;

    List<CkScheduleData> scheduleList;
    List<CalendarItem> calendarItems;
    CalendarAdapter mAdapter;
    ArrayList<CalendarItemData> mItemdata = new ArrayList<>();
    CalendarItem calendarItem;

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
        CalendarManager.clearInstance();
        calendarItem = null;

        backImage.setOnClickListener(this);
        nextImage.setOnClickListener(this);
        textMornig.setOnClickListener(this);
        textLaunch.setOnClickListener(this);
        textDinner.setOnClickListener(this);
        shareImage.setOnClickListener(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        if(MODE  == CkMainActivity.MODE_SCHEDULR_EDIT){
            scheduleList = (List<CkScheduleData>)intent.getSerializableExtra(CkMainActivity.INTENT_SCHEDULE_DATA);
            changeCalendarScheduleData(scheduleList);
            CalendarData calendarData = CalendarManager.getInstance().getSelectCalendarData(calendarItems);

            mAdapter = new CalendarAdapter(this, calendarData, true);
            mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
                @Override
                public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                    if(data.isSelect){
                        textCalendar.setText(""+(data.month+1)+"월" +data.dayOfMonth+"일");
                        calendarItem = data;
                    }
                }
            });
        }else if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
            calendarItem = null;
            CalendarData calendarData = CalendarManager.getInstance().getCalendarData();
            textCalendar.setText(calendarData.year+"년 "+(calendarData.month+1)+"월");
            mAdapter = new CalendarAdapter(this, calendarData, false);
            mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
                @Override
                public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                    textCalendar.setText(""+(data.month+1)+"월" +data.dayOfMonth+"일");
                    calendarItem = data;
                }
            });
        }
        rv.setLayoutManager(new GridLayoutManager(this, 7));
        rv.setAdapter(mAdapter);
        fab.setOnClickListener(this);
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
        switch(view.getId()){
            case R.id.image_schedule_edit_back:{
                CalendarData data = CalendarManager.getInstance().getLastMonthCalendarData();
                textCalendar.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                break;
            }
            case R.id.image_schedule_edit_next:{
                CalendarData data = CalendarManager.getInstance().getNextMonthCalendarData();
                textCalendar.setText(data.year+"년 "+(data.month+1)+"월");
                mAdapter.setCalendarData(data);
                mAdapter.cleanChecked();
                break;
            }
            case R.id.text_schedule_edit_mornig:{
                break;
            }
            case R.id.text_schedule_edit_launch:{
                break;
            }
            case R.id.text_schedule_edit_dinner:{
                break;
            }
            case R.id.image_schedule_edit_share:{
                break;
            }
            case R.id.fab_schedule_edit:{
                if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
                    if(valueCheck()){
                        CkScheduleInsertRequest request = new CkScheduleInsertRequest(ScheduleEditActivity.this, calendarItem.getDate(2).toString(), editReserve.getText().toString(), "1");
                        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                            @Override
                            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 생성 완료", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 생성 실패", Toast.LENGTH_SHORT).show();
                                Log.e("ssong", "error",e);
                                Log.e("ssong", errorMessage);
                                Log.e("ssong", errorCode+"");
                            }
                        });
                    }
                }else if(MODE == CkMainActivity.MODE_SCHEDULR_EDIT){
                    if(valueCheck()){
                        Log.e("ssong", ""+calendarItem.dayOfMonth);
                        Log.e("ssong", ""+calendarItem.year);
                        Log.e("ssong", ""+calendarItem.month);
                        Log.e("ssong", ""+calendarItem.id);
                        CkScheduleEditRequest request = new CkScheduleEditRequest(ScheduleEditActivity.this, calendarItem.id);
                        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                            @Override
                            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 삭제 완료", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 생성 실패", Toast.LENGTH_SHORT).show();
                                Log.e("ssong", "error",e);
                            }
                        });
                    }
                }
            }
        }
    }
    private boolean valueCheck(){
        if(calendarItem==null){
            Toast.makeText(ScheduleEditActivity.this, "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(editReserve.getText().toString())){
            Toast.makeText(ScheduleEditActivity.this, "입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
