package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sm.ej.nk.homeal.adapter.CkDetailAdapter;
import com.sm.ej.nk.homeal.data.CalendarItem;
import com.sm.ej.nk.homeal.data.CkDetailData;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoCkDetailActivity extends AppCompatActivity implements CkDetailAdapter.OnDetailAdapterClickListener{

    @BindView(R.id.toobar_ck_detail)
    Toolbar toolbar;

    @BindView(R.id.rv_ck_detail)
    RecyclerView rv;

    @BindView(R.id.floating_ck_detail)
    FloatingActionButton floatingActionButton;

    EtHomeData etHomeData;

    CkDetailAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        etHomeData = (EtHomeData)intent.getSerializableExtra(EtHomeFragment.INTENT_CK_ID);

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        mAdapter = new CkDetailAdapter(init());
        mAdapter.setOnDetailAdapterClickListener(this);
        rv.setAdapter(mAdapter);
        initMenu();
    }
    //ddddddd
    @Override
    public void onDetailAdapterClick(View view, CalendarItem data, int position) {

    }

    private CkDetailData init(){
        CkDetailData data = new CkDetailData();
        data.foodIntroduce = "음식 메늉ㄴㅇㅁㄴㅇ만마ㅣㄴ미";
        data.foodName = "음식이름";
        data.foodPrice = "음식 가격";
        data.mapImage = "https://pixabay.com/static/uploads/photo/2012/03/02/14/45/flowers-21322_960_720.jpg";
        data.userAddress = "주소오오옹오";
        data.userImage = "http://img.lifestyler.co.kr/uploads/program/cheditor/2015/03/E3XVIPNDDBU1K9CPHLIA.jpg";
        data.userName = "이름응ㅁ음으";
        data.totalScore=4;
        data.tasteScore=4;
        data.priceScore=4;
        data.kindScore=4;
        data.cleanScore=4;
        ArrayList<String> dummylist = new ArrayList<>();
        for(int i=0 ; i<5 ; i++){
            dummylist.add("http://myunchaeban.co.kr/wp/wp-content/uploads/2014/02/%EA%B0%88%EB%B9%84%ED%83%95%EC%9D%B4%EB%AF%B8%EC%A7%802-470x313.jpg");
        }
        data.pagerImageList = dummylist;

        return data;
    }

    public void initMenu(){
        ArrayList<CkDetailMenuData> dummy = new ArrayList<>();
        for(int i=0 ; i<5 ; i++){
            CkDetailMenuData menuData = new CkDetailMenuData();
            menuData.foodAddress = "주소오오"+i;
            menuData.foodImage = "http://cfile28.uf.tistory.com/image/1366E3464E05C7021C1EB6";
            menuData.foodName = "음식 이름"+i;
            menuData.foodTime = "시간"+i;
            dummy.add(menuData);
        }
        mAdapter.addMenuList(dummy);
    }
}
