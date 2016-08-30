package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sm.ej.nk.homeal.adapter.ViewPagerFragmentAdapter;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.CkHomeFragment;
import com.sm.ej.nk.homeal.fragment.CkMyPageFragment;
import com.sm.ej.nk.homeal.fragment.CkReserveFragment;
import com.sm.ej.nk.homeal.view.AlarmPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CkMainActivity extends AppCompatActivity {
    @BindView(R.id.tablayout_ck_main)
    TabLayout tabLayout;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager_ck_main)
    ViewPager viewPager;

    AlarmPopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if(viewPager!=null){
            setupTabViewPager(viewPager);
        }

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
    }
    private static final String CK_HOME = "쿠커홈";
    private static final String CK_CHAT_LIST = "채팅리스트";
    private static final String CK_RESERVE = "예약";
    private static final String CK_MYPAGE = "내정보";
    private static final String CK_NUM = "쿠커";

    private void setupTabViewPager(ViewPager v){

        final ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("cooker",CK_NUM);
        pagerAdapter.addFragment(CkHomeFragment.createInstance(),CK_HOME);
        pagerAdapter.addFragment(ChatListFragment.createInstance(), CK_CHAT_LIST);
        pagerAdapter.addFragment(CkReserveFragment.createInstance(), CK_RESERVE);
        pagerAdapter.addFragment(CkMyPageFragment.createInstance(), CK_MYPAGE);
        v.setAdapter(pagerAdapter);

    }

    public void moveChattigActivity() {

        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ck_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_et_main_alarm:
//                popupWindow = AlarmPopupWindow.getinstance(this);
//                popupWindow.showAsDropDown(toolbar);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
