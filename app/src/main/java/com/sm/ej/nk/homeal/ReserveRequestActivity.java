package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.sm.ej.nk.homeal.adapter.ReserveRequestAdapter;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.ReserveSendRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReserveRequestActivity extends AppCompatActivity {


    @BindView(R.id.toobar_et_reservation)
    Toolbar toolbar;

    @BindView(R.id.rv_reserve_request)
    RecyclerView recyclerView;

    @BindView(R.id.fab_reserve_request)
    FloatingActionButton fab;

    CalendarItem calendarItem;
    List<CkDetailMenuData> menuDataList;
    ReserveRequestAdapter mAdapter;
    String cookerid;
    String personCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_request);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.ck_home_fabsend);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        cookerid = intent.getStringExtra(InfoCkDetailActivity.INTENT_RESERVE_CKID);

        calendarItem =(CalendarItem)intent.getSerializableExtra(InfoCkDetailActivity.INTENT_RESERVE_CALENDAR);
        menuDataList = InfoCkDetailActivity.getSelectMenu();
        mAdapter = new ReserveRequestAdapter();
        mAdapter.setCalendar(calendarItem);
        mAdapter.setMenu(menuDataList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personCount = mAdapter.getEidtText();
                ReserveSendRequest request = new ReserveSendRequest(ReserveRequestActivity.this, cookerid, calendarItem.id, menuDataList, personCount);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResultTemp>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResultTemp> request, NetworkResultTemp result) {
                        Toast.makeText(ReserveRequestActivity.this, "예약 요청 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResultTemp> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });
            }
        });
    }
}
