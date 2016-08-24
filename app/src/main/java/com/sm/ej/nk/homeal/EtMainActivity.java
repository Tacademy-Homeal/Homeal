package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sm.ej.nk.homeal.adapter.ViewPagerAdapter;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;
import com.sm.ej.nk.homeal.fragment.EtMyPageFragment;
import com.sm.ej.nk.homeal.fragment.EtReserveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtMainActivity extends AppCompatActivity {

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
        v.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.et_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.btn_et_main_search:

            case R.id.btn_et_main_alarm:
                //popupWindow 334p
        }
        return super.onOptionsItemSelected(item);
    }
}
