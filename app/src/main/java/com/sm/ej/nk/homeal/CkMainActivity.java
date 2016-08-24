package com.sm.ej.nk.homeal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sm.ej.nk.homeal.adapter.CkViewPagerAdapter;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.CkHomeFragment;
import com.sm.ej.nk.homeal.fragment.CkReserveFragment;
import com.sm.ej.nk.homeal.fragment.CkMyPageFragment;

import butterknife.BindView;

public class CkMainActivity extends AppCompatActivity {
    @BindView(R.id.tablayout_ck_main)
    TabLayout tabLayout;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager_ck_main)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_main);

        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.viewpager_ck_main);
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

    private void setupTabViewPager(ViewPager v){
        final CkViewPagerAdapter pagerAdapter = new CkViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(CkHomeFragment.createInstance(),CK_HOME);
        pagerAdapter.addFragment(ChatListFragment.createInstance(), CK_CHAT_LIST);
        pagerAdapter.addFragment(CkReserveFragment.createInstance(), CK_RESERVE);
        pagerAdapter.addFragment(CkMyPageFragment.createInstance(), CK_MYPAGE);
        v.setAdapter(pagerAdapter);
    }
}
