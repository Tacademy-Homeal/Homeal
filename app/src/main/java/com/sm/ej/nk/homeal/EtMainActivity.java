package com.sm.ej.nk.homeal;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sm.ej.nk.homeal.adapter.ViewPagerFragmentAdapter;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.EtHomeFragment;
import com.sm.ej.nk.homeal.fragment.EtMyPageFragment;
import com.sm.ej.nk.homeal.fragment.EtReserveFragment;
import com.sm.ej.nk.homeal.view.AlarmPopupWindow;
import com.sm.ej.nk.homeal.view.SearchPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EtMainActivity extends AppCompatActivity {
    SearchPopupWindow searchpopupWindow;
    AlarmPopupWindow alarmPopupWindow;

    @BindView(R.id.toolbar_et_toolbar)
    Toolbar toolbar;

    @BindView(R.id.tablayout_et_main)
    TabLayout tabLayout;

    @BindView(R.id.layout_alert)
    RelativeLayout alertLayout;

    @BindView(R.id.layout_blur)
    RelativeLayout blurLayout;

    @BindView(R.id.viewpager_et_main)
    ViewPager viewPager;

    @BindView(R.id.btn_alert_erase)
    Button btnErase;

    private static int[] icon = {R.drawable.ic_home_white_48dp, R.drawable.ic_chat_white_48dp,
            R.drawable.ic_access_time_white_48dp, R.drawable.ic_person_white_48dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (viewPager != null) {
            setupTabViewPager(viewPager);
        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        //image set
        for (int i = 0; i < icon.length; i++) {
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }

        alertLayout.setVisibility(View.INVISIBLE);
        blurLayout.setVisibility(View.INVISIBLE);

    }

    public static final String EXTRA_TAB_INDEX = "tabindex";
    public static final String ET_HOME = "이터홈";
    public static final String ET_CHAT = "채팅 리스트";
    public static final String ET_RESERVE = "예약";
    public static final String ET_MYPAGE = "내정보";

    private void setupTabViewPager(ViewPager v) {
        final ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(EtHomeFragment.createInstance(), ET_HOME);
        pagerAdapter.addFragment(ChatListFragment.createInstance(), ET_CHAT);
        pagerAdapter.addFragment(EtReserveFragment.createInstance(), ET_RESERVE);
        pagerAdapter.addFragment(EtMyPageFragment.createInstance(), ET_MYPAGE);
        v.setAdapter(pagerAdapter);
    }

    public void moveChattigActivity() {
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_et_main_alarm: {
//                alarmpopupWindow = AlarmPopupWindow.getinstance(this);
//                alarmpopupWindow.showAsDropDown(toolbar);
                if (alertLayout.getVisibility() == View.INVISIBLE) {
                    alertLayout.setVisibility(View.VISIBLE);
                    blurLayout.setVisibility(View.VISIBLE);
                    alertLayout.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                } else {
                    alertLayout.setVisibility(View.INVISIBLE);
                    blurLayout.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case R.id.btn_et_main_search: {
//                searchpopupWindow = SearchPopupWindow.getInstance(this);
//                searchpopupWindow.setOnSearchPopupClickListener(new SearchPopupWindow.OnSearchPopupClickListener() {
//                    @Override
//                    public void onSearchPopupClick(View view) {
//                        viewPager.setCurrentItem(0);
//                        tabLayout.setupWithViewPager(viewPager);
//                        for(int i = 0; i < icon.length; i++){
//                            tabLayout.getTabAt(i).setIcon(icon[i]);
//                        }
//                    }
//                });
//
//                searchpopupWindow.showAsDropDown(toolbar);
                Intent intent = new Intent(EtMainActivity.this, SearchActivity.class);
                startActivity(intent);

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

    @OnClick(R.id.btn_alert_erase)
    public void onErase(){

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setMessage(getResources().getString(R.string.main_finish));
        builder.show();
    }
}
