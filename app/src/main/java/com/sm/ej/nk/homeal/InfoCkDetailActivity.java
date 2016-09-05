package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.sm.ej.nk.homeal.adapter.CkDetailAdapter;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkInfoResult;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        etHomeData = (EtHomeData)intent.getSerializableExtra(EtHomeFragment.INTENT_CK_ID);
        Log.e("ssong", etHomeData.getId());

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


        CkPageCheckRequest request = new CkPageCheckRequest(this, etHomeData.getId());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<CkInfoResult>() {
            @Override
            public void onSuccess(NetworkRequest<CkInfoResult> request, CkInfoResult result) {
                mAdapter.addHeader(result.getCooker_info());
                mAdapter.addMenu(result.getCooker_menu());
                List<CkScheduleData> list =  result.getCooker_schedule();
                mAdapter.addSchedule(list);
                mAdapter.setOnDetailAdapterClickListener(InfoCkDetailActivity.this);
                rv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<CkInfoResult> request, int errorCode, String errorMessage, Throwable e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onDetailAdapterClick(View view, CalendarItem data, int position) {

    }

//    private CkDetailData init(){
//        CkDetailData data = new CkDetailData();
//        data.mapImage = "https://pixabay.com/static/uploads/photo/2012/03/02/14/45/flowers-21322_960_720.jpg";
//        data.address = "주소오오옹오";
//        data.image = "http://img.lifestyler.co.kr/uploads/program/cheditor/2015/03/E3XVIPNDDBU1K9CPHLIA.jpg";
//        data.name = "이름응ㅁ음으";
//        data.taste="4";
//        data.grade =4;
//        data.priceScore="4";
//        data.kind="4";
//        data.clean="4";
//        ArrayList<String> dummylist = new ArrayList<>();
//        for(int i=0 ; i<5 ; i++){
//            dummylist.add("http://myunchaeban.co.kr/wp/wp-content/uploads/2014/02/%EA%B0%88%EB%B9%84%ED%83%95%EC%9D%B4%EB%AF%B8%EC%A7%802-470x313.jpg");
//        }
//        data.thumbnail = dummylist;
//
//        return data;
//    }

    public void initMenu(){
        ArrayList<CkDetailMenuData> dummy = new ArrayList<>();
        for(int i=0 ; i<5 ; i++){
            CkDetailMenuData menuData = new CkDetailMenuData();
            menuData.foodAddress = "주소오오"+i;
            menuData.image = "http://cfile28.uf.tistory.com/image/1366E3464E05C7021C1EB6";
            menuData.name = "음식 이름"+i;
            menuData.foodTime = "시간"+i;
            dummy.add(menuData);
        }
        mAdapter.addMenuList(dummy);
    }
}
