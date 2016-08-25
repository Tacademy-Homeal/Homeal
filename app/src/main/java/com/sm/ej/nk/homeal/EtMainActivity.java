package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sm.ej.nk.homeal.adapter.ViewPagerAdapter;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;
import com.sm.ej.nk.homeal.fragment.EtMyPageFragment;
import com.sm.ej.nk.homeal.fragment.EtReserveFragment;
import com.sm.ej.nk.homeal.view.AlarmPopupWindow;
import com.sm.ej.nk.homeal.view.SearchPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtMainActivity extends AppCompatActivity {
    SearchPopupWindow searchpopupWindow;
    AlarmPopupWindow alarmPopupWindow;

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tablayout_et_main)
    TabLayout tabLayout;

    @BindView(R.id.viewpager_et_main)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if(viewPager!=null){
            setupTabViewPager(viewPager);
        }
        tabLayout.setupWithViewPager(viewPager);
    }

    public static final String ET_HOME = "이터홈";
    public static final String ET_CHAT = "채팅 리스트";
    public static final String ET_RESERVE = "예약";
    public static final String ET_MYPAGE = "내정보";

    private void setupTabViewPager(ViewPager v){
        final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(EtHomeFragment.createInstance(), ET_HOME);
        pagerAdapter.addFragment(ChatListFragment.createInstance(), ET_CHAT);
        pagerAdapter.addFragment(EtReserveFragment.createInstance(), ET_RESERVE);
        pagerAdapter.addFragment(EtMyPageFragment.createInstance(), ET_MYPAGE);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_search_category_default);

        v.setAdapter(pagerAdapter);
    }

    public void moveChattigActivity() {

        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_et_main_alarm: {
                alarmPopupWindow = new AlarmPopupWindow(this);
                alarmPopupWindow.showAsDropDown(toolbar);
                break;
            }
            case R.id.btn_et_main_search: {
                searchpopupWindow = new SearchPopupWindow(this);
                searchpopupWindow.setOnSearchPopupClickListener(new SearchPopupWindow.OnSearchPopupClickListener() {
                    @Override
                    public void onSearchPopupClick(View view) {
                        viewPager.setCurrentItem(0);
                        tabLayout.setupWithViewPager(viewPager);
                    }
                });
                searchpopupWindow.showAsDropDown(toolbar);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.et_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
