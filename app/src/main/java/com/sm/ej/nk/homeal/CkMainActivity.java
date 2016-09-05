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
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.sm.ej.nk.homeal.adapter.ViewPagerFragmentAdapter;
import com.sm.ej.nk.homeal.data.CkHomeItemData;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.CkHomeFragment;
import com.sm.ej.nk.homeal.fragment.CkMyPageFragment;
import com.sm.ej.nk.homeal.fragment.CkReserveFragment;
import com.sm.ej.nk.homeal.view.AlarmPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CkMainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    @BindView(R.id.tablayout_ck_main)
    TabLayout tabLayout;

    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager_ck_main)
    ViewPager viewPager;

    @BindView(R.id.floating_ck_home)
    FloatingActionMenu fab;

    @BindView(R.id.fab_edit)
    FloatingActionButton fabEdit;

    @BindView(R.id.fab_insert)
    FloatingActionButton fabInsert;

    @BindView(R.id.fab_schedulinsert)
    FloatingActionButton fabScheduleInsert;

    @BindView(R.id.fab_scheduleedit)
    FloatingActionButton fabSchedulrEdit;

    private static CkHomeFragment ckHomeFragment;

    AlarmPopupWindow popupWindow;

    public static int INTENT_MENU =0;
    public static int INTENT_SCHEDULE = 1;

    private static boolean isEditMode = false;

    private static int[] icon = {R.drawable.ic_home_white_48dp, R.drawable.ic_chat_white_48dp,
                                R.drawable.ic_access_time_white_48dp, R.drawable.ic_person_white_48dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ckHomeFragment = CkHomeFragment.createInstance();

        if(viewPager!=null){
            setupTabViewPager(viewPager);
        }

        fab.getMenuIconView().setImageResource(R.drawable.fab_add);
        fab.setClosedOnTouchOutside(true);
        fab.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEditMode){
                    fab.getMenuIconView().setImageResource(R.drawable.fab_add);
                    if(listener!=null){
                        listener.onFabClick(view, MODE_OK);
                    }
                    isEditMode = false;
                }else{
                    if(fab.isOpened()){
                        fab.close(true);
                    }else{
                        fab.open(true);
                    }
                }
            }
        });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onFabClick(view, MODE_EDIT);
                }
                fab.getMenuIconView().setImageResource(R.drawable.ic_star);
                isEditMode = true;
                fab.close(true);
            }
        });

        fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CkMainActivity.this, MenuAddActivity.class);
                intent.putExtra(INTENT_MODE, MODE_MENU_INSERT);
                startActivityForResult(intent, INTENT_MENU);
                fab.close(true);
            }
        });

        fabScheduleInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CkMainActivity.this, ScheduleEditActivity.class);
                intent.putExtra(INTENT_MODE, MODE_SCHEDULE_INSERT);
//                intent.putExtra(INTENT_SCHEDULE_DATA, (Serializable)ckHomeFragment.getCkSchedule());
                startActivityForResult(intent ,INTENT_SCHEDULE);
                fab.close(true);
            }
        });

        fabSchedulrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CkMainActivity.this, ScheduleEditActivity.class);
                intent.putExtra(INTENT_MODE, MODE_SCHEDULR_EDIT);
                startActivityForResult(intent, INTENT_SCHEDULE);
            }
        });

        ckHomeFragment.setOnHomeFragmentClickListner(new CkHomeFragment.OnHomeFragmentClickListener() {
            @Override
            public void onHomeFragmentClick(View view, int position, CkHomeItemData data) {
                Intent intent = new Intent(CkMainActivity.this, MenuAddActivity.class);
                intent.putExtra(INTENT_MODE, MODE_MENU_EDIT);
                intent.putExtra(INTENT_MENU_DATA, data);
                startActivityForResult(intent, INTENT_MENU);
            }
        });

        ckHomeFragment.setOnHomeViewClickListener(new CkHomeFragment.OnHomeViewClickLIstener() {
            @Override
            public void onHomeViewClick(View view, int position, CkHomeItemData data) {
                if(!isEditMode){
                    Intent intent = new Intent(CkMainActivity.this, MenuAddActivity.class);
                    intent.putExtra(INTENT_MODE, MODE_MENU_SHOW);
                    intent.putExtra(INTENT_MENU_DATA, data);
                    startActivity(intent);
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
        tabLayout.addOnTabSelectedListener(this);

        //image set
        for(int i = 0; i < 4; i++){
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }
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
        pagerAdapter.addFragment(ckHomeFragment,CK_HOME);
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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch(tab.getPosition()){
            case 0:
                fab.showMenu(true);
                break;
            default:
                fab.hideMenu(true);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public static final String INTENT_MENU_DATA = "asdasd";
    public static final String INTENT_SCHEDULE_DATA = "qqqq";
    public static final String INTENT_MODE = "SchedulrMode";
    public static final int MODE_SCHEDULE_INSERT=2;
    public static final int MODE_SCHEDULR_EDIT = 3;

    public static final int MODE_EDIT = 0;
    public static final int MODE_OK = 1;

    public static final int MODE_MENU_EDIT = 9;
    public static final int MODE_MENU_INSERT=10;
    public static final int MODE_MENU_SHOW=11;

    public interface OnFabClickListener{
        public void onFabClick(View view, int mode);
    }
    OnFabClickListener listener;

    public void setOnFabClickListener(OnFabClickListener listener){
        this.listener = listener;
    }

    public static CkHomeFragment getHomeFragment(){
        return ckHomeFragment;
    }
}
