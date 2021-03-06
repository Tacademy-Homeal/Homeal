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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    @BindView(R.id.edit_schedule_edit_reservecount)
    EditText editReserve;

    @BindView(R.id.toolbar_schedule_edit)
    Toolbar toolbar;

    @BindView(R.id.image_schedule_edit_back)
    ImageView backImage;

    @BindView(R.id.image_schedule_edit_next)
    ImageView nextImage;

    @BindView(R.id.fab_schedule_edit)
    FloatingActionButton fab;

    @BindView(R.id.radiogroup_schedule)
    RadioGroup radioGroup;

    @BindView(R.id.radio_schedule_launch)
    RadioButton launchRadio;

    @BindView(R.id.radio_schedule_dinner)
    RadioButton dinnerRadio;

    @BindView(R.id.radiogroup_schedule_sharing)
    RadioGroup groupSharing;

    @BindView(R.id.radio_schedule_sharing_ok)
    RadioButton radioOk;

    @BindView(R.id.radio_schedule_sharing_no)
    RadioButton radioNo;

    List<CkScheduleData> scheduleList;
    List<CalendarItem> calendarItems;
    CalendarAdapter mAdapter;
    ArrayList<CalendarItemData> mItemdata = new ArrayList<>();
    CalendarItem calendarItem;
    int time= -1;
    String sharing= null;
    String deleteTime;
    CalendarData calendarData;

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

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        calendarData = CalendarManager.getInstance().getCalendarData();

        Intent intent = getIntent();
        MODE = intent.getIntExtra(CkMainActivity.INTENT_MODE, -1);
        if(MODE  == CkMainActivity.MODE_SCHEDULR_EDIT){
            scheduleList = (List<CkScheduleData>)intent.getSerializableExtra(CkMainActivity.INTENT_SCHEDULE_DATA);
            changeCalendarScheduleData(scheduleList);
            CalendarData calendarData = CalendarManager.getInstance().getSelectCalendarData(calendarItems);
            editReserve.setEnabled(false);

            mAdapter = new CalendarAdapter(this, calendarData, true);
            mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
                @Override
                public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                    if(data.isSelect){
                        launchRadio.setChecked(false);
                        dinnerRadio.setChecked(false);
                        textCalendar.setText(""+(data.month+1)+"월" +data.dayOfMonth+"일");
                        calendarItem = data;
                        editReserve.setText(""+data.pax);
                        if(data.sharing==1){
                            radioOk.setChecked(true);
                            radioOk.setEnabled(false);
                            radioNo.setEnabled(false);
                        }else{
                            radioNo.setChecked(true);
                            radioOk.setEnabled(false);
                            radioNo.setEnabled(false);
                        }
                        editReserve.setEnabled(false);

                        if(data.isLaunch){
                            launchRadio.setEnabled(true);
                        }else{
                            launchRadio.setEnabled(false);
                        }
                        if(data.isDinner){
                            dinnerRadio.setEnabled(true);
                        }else{
                            dinnerRadio.setEnabled(false);
                        }

                    }
                }
            });
        }else if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
            calendarItem = null;
            editReserve.setEnabled(true);

            mAdapter = new CalendarAdapter(this, calendarData, false);
            mAdapter.setOnCalendarAdpaterClickListener(new CalendarAdapter.OnCalendarAdapterClickListener() {
                @Override
                public void onCalendarAdapterClick(View view, int position, CalendarItem data) {
                    textCalendar.setText(""+(data.month+1)+"월" +data.dayOfMonth+"일");
                    calendarItem = data;
                }
            });
        }
        textCalendar.setText(calendarData.year+"년 "+(calendarData.month+1)+"월");
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
            case R.id.fab_schedule_edit:{
                if(MODE == CkMainActivity.MODE_SCHEDULE_INSERT){
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.radio_schedule_launch:
                            time = 2;
                            break;
                        case R.id.radio_schedule_dinner:
                            time = 3;
                            break;
                    }
                    switch (groupSharing.getCheckedRadioButtonId()){
                        case R.id.radio_schedule_sharing_ok:
                            sharing="1";
                            break;
                        case R.id.radio_schedule_sharing_no:
                            sharing="0";
                            break;
                    }
                    if(valueCheck()){
                        CkScheduleInsertRequest request = new CkScheduleInsertRequest(ScheduleEditActivity.this, calendarItem.getDate(time).toString(), editReserve.getText().toString(), sharing);
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
                    if(deletevalueCheck()){
                        Log.e("ssong", ""+calendarItem.dayOfMonth);
                        Log.e("ssong", ""+calendarItem.year);
                        Log.e("ssong", ""+calendarItem.month);
                        Log.e("ssong", ""+calendarItem.id);
                        switch (radioGroup.getCheckedRadioButtonId()){
                            case R.id.radio_schedule_launch:
                                deleteTime = "Launch";
                                break;
                            case R.id.radio_schedule_dinner:
                                deleteTime = "Dinner";
                                break;
                        }
                        CkScheduleEditRequest request = new CkScheduleEditRequest(ScheduleEditActivity.this, calendarItem.idMap.get(deleteTime));
                        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                            @Override
                            public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 삭제 완료", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {
                                Toast.makeText(ScheduleEditActivity.this, "일정 삭제 실패", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ScheduleEditActivity.this, "예약 인원을 입역해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(sharing==null){
            Toast.makeText(ScheduleEditActivity.this, "합석 여부를 선택해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(time==-1){
            Toast.makeText(ScheduleEditActivity.this, "시간을 선택해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean deletevalueCheck(){
        if(calendarItem==null){
            Toast.makeText(ScheduleEditActivity.this, "날짜를 선택해 주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
