package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.sm.ej.nk.homeal.adapter.CkDetailAdapter;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;
import com.sm.ej.nk.homeal.manager.CalendarManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkPageCheckRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoCkDetailActivity extends AppCompatActivity implements CkDetailAdapter.OnDetailAdapterClickListener{

    @BindView(R.id.toobar_ck_detail)
    Toolbar toolbar;

    @BindView(R.id.rv_ck_detail)
    RecyclerView rv;

    @BindView(R.id.floating_ck_detail)
    FloatingActionMenu fab;

    @BindView(R.id.fab_chat)
    FloatingActionButton fabChat;

    @BindView(R.id.fab_send)
    FloatingActionButton fabSend;


    EtHomeData etHomeData;

    CkDetailAdapter mAdapter;
    CkInfoResult resultDara;
    static List<CkDetailMenuData> selectMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        etHomeData = (EtHomeData)intent.getSerializableExtra(EtHomeFragment.INTENT_CK_ID);
        Log.e("ssong ID", etHomeData.getId());
        selectMenuList = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.infockdetail_name));

        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        mAdapter = new CkDetailAdapter();
        mAdapter.setOnMEnuSwipeClickListener(new CkDetailAdapter.OnMenuSwipeClickListener() {
            @Override
            public void onMenuSwipeClick(View view, CkDetailMenuData data, int position, boolean select) {
               if(select){
                   selectMenuList.add(data);
               }else{
                   selectMenuList.remove(data);
               }

            }
        });

        CkPageCheckRequest request = new CkPageCheckRequest(this, etHomeData.getId());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<CkInfoResult>() {
            @Override
            public void onSuccess(NetworkRequest<CkInfoResult> request, CkInfoResult result) {
                resultDara = result;
                CalendarManager.clearInstance();
                mAdapter.setResult(result);
                mAdapter.setOnDetailAdapterClickListener(InfoCkDetailActivity.this);
                rv.setAdapter(mAdapter);
            }
            @Override
            public void onFail(NetworkRequest<CkInfoResult> request, int errorCode, String errorMessage, Throwable e) {
                e.printStackTrace();
            }
        });

        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAdapter.getSelectCalendarItem()==null){
                    Toast.makeText(InfoCkDetailActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(InfoCkDetailActivity.this, ReserveRequestActivity.class);
                    intent.putExtra(INTENT_RESERVE_CALENDAR, mAdapter.getSelectCalendarItem());
                    intent.putExtra(INTENT_RESERVE_CKID, resultDara.getCooker_info().uid);
                    startActivity(intent);
                }
            }
        });
    }
    public static List<CkDetailMenuData> getSelectMenu(){
        return selectMenuList;
    }

    public static final String INTENT_RESERVE_MENU = "rrrr";
    public static final String INTENT_RESERVE_CALENDAR= "www";
    public static final String INTENT_RESERVE_CKID = "eeee";

    @Override
    public void onDetailAdapterClick(View view, CalendarItem data, int position) {

    }

}
