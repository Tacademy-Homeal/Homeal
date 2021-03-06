package com.sm.ej.nk.homeal;

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.sm.ej.nk.homeal.adapter.ViewPagerFragmentAdapter;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.CkScheduleData;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ThumbnailsData;
import com.sm.ej.nk.homeal.fragment.ChatListFragment;
import com.sm.ej.nk.homeal.fragment.CkHomeFragment;
import com.sm.ej.nk.homeal.fragment.CkMyPageFragment;
import com.sm.ej.nk.homeal.fragment.CkReserveFragment;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.CkMenuListRequest;
import com.sm.ej.nk.homeal.request.CkScheduleListRequest;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CkMainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
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

    @BindView(R.id.fab_thumbnail_insert)
    FloatingActionButton fabThumbnailInsert;

    @BindView(R.id.layout_alert)
    RelativeLayout alertLayout;

    @BindView(R.id.layout_blur)
    RelativeLayout blurLayout;

    private CkHomeFragment ckHomeFragment;

//    AlarmPopupWindow popupWindow;

    List<CkScheduleData> scheduleDatas;
    List<ThumbnailsData> thumbnailsDatas;

    public static int INTENT_MENU = 0;
    public static int INTENT_SCHEDULE = 1;
    public static int INTENT_THUMBNAIL = 2;

    private static boolean isEditMode = false;

    private int[] icon = {R.drawable.ic_home_white_48dp, R.drawable.ic_chat_white_48dp,
            R.drawable.ic_access_time_white_48dp, R.drawable.ic_person_white_48dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ckHomeFragment = CkHomeFragment.createInstance();
        if (viewPager != null) {
            setupTabViewPager(viewPager);
        }

        fab.getMenuIconView().setImageResource(R.drawable.fab_add);
        fab.setClosedOnTouchOutside(true);
        fab.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditMode) {
                    fab.getMenuIconView().setImageResource(R.drawable.fab_add);
                    if (listener != null) {
                        listener.onFabClick(view, MODE_OK);
                    }
                    isEditMode = false;
                } else {
                    if (fab.isOpened()) {
                        fab.close(true);
                    } else {
                        fab.open(true);
                    }
                }
            }
        });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
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
                startActivityForResult(intent, INTENT_SCHEDULE);
                fab.close(true);
            }
        });

        fabSchedulrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CkMainActivity.this, ScheduleEditActivity.class);
                intent.putExtra(INTENT_MODE, MODE_SCHEDULR_EDIT);
                scheduleDatas = ckHomeFragment.getCkSchedule();
                intent.putExtra(INTENT_SCHEDULE_DATA, (Serializable) scheduleDatas);
                startActivityForResult(intent, INTENT_SCHEDULE);

            }
        });

        ckHomeFragment.setOnHomeFragmentClickListner(new CkHomeFragment.OnHomeFragmentClickListener() {
            @Override
            public void onHomeFragmentClick(View view, int position, CkDetailMenuData data) {
                Intent intent = new Intent(CkMainActivity.this, MenuAddActivity.class);
                intent.putExtra(INTENT_MODE, MODE_MENU_EDIT);
                intent.putExtra(INTENT_MENU_DATA, data);
                startActivityForResult(intent, INTENT_MENU);
            }
        });

        ckHomeFragment.setOnHomeViewClickListener(new CkHomeFragment.OnHomeViewClickLIstener() {
            @Override
            public void onHomeViewClick(View view, int position, CkDetailMenuData data) {
                if (!isEditMode) {
                    Intent intent = new Intent(CkMainActivity.this, MenuAddActivity.class);
                    intent.putExtra(INTENT_MODE, MODE_MENU_SHOW);
                    intent.putExtra(INTENT_MENU_DATA, data);
                    startActivity(intent);
                }
            }
        });

        fabThumbnailInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CkMainActivity.this, ThumbnailEditActivity.class);
                intent.putExtra(INTENT_MODE, MODE_THUMBNAIL_INSERT);
                intent.putExtra(INTENT_COOKER_ID, ckHomeFragment.getCookerId());
                startActivityForResult(intent, INTENT_THUMBNAIL);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        //image set
        for (int i = 0; i < 4; i++) {
            tabLayout.getTabAt(i).setIcon(icon[i]);
        }

        alertLayout.setVisibility(View.INVISIBLE);
        blurLayout.setVisibility(View.INVISIBLE);
    }

    public static final String EXTRA_TAB_INDEX = "tabindex";
    private static final String CK_HOME = "쿠커홈";
    public static final String CK_CHAT_LIST = "채팅리스트";
    private static final String CK_RESERVE = "예약";
    private static final String CK_MYPAGE = "내정보";
    private static final String CK_NUM = "쿠커";

    private void setupTabViewPager(ViewPager v) {

        final ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putString("cooker", CK_NUM);
        pagerAdapter.addFragment(ckHomeFragment, CK_HOME);
        pagerAdapter.addFragment(ChatListFragment.createInstance(), CK_CHAT_LIST);
        pagerAdapter.addFragment(CkReserveFragment.createInstance(), CK_RESERVE);
        pagerAdapter.addFragment(CkMyPageFragment.createInstance(), CK_MYPAGE);
        v.setAdapter(pagerAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_MENU) {
            if (resultCode == Activity.RESULT_OK) {

                CkMenuListRequest request = new CkMenuListRequest(CkMainActivity.this, ckHomeFragment.getCookerId());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<CkDetailMenuData>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<CkDetailMenuData>>> request, NetworkResult<List<CkDetailMenuData>> result) {
                        ckHomeFragment.changeMenu(result.getResult());
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<CkDetailMenuData>>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(CkMainActivity.this, "매뉴 리스트 불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (requestCode == INTENT_SCHEDULE) {
            if (resultCode == Activity.RESULT_OK) {
                CkScheduleListRequest request = new CkScheduleListRequest(CkMainActivity.this, ckHomeFragment.getCookerId());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<CkScheduleData>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<CkScheduleData>>> request, NetworkResult<List<CkScheduleData>> result) {
                        ckHomeFragment.changeSchedule(result.getResult());
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<CkScheduleData>>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(CkMainActivity.this, "일정 리스트 불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ck_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_ck_main_alarm:
//                popupWindow = AlarmPopupWindow.getinstance(this);
//                popupWindow.showAsDropDown(toolbar);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                fab.getMenuIconView().setImageResource(R.drawable.fab_add);
                fab.showMenu(true);
                break;
            default:
                fab.hideMenu(true);
                isEditMode = false;
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public static final String INTENT_COOKER_ID = "etet";

    public static final String INTENT_MENU_DATA = "asdasd";
    public static final String INTENT_SCHEDULE_DATA = "qqqq";
    public static final String INTENT_THUMBNAIL_DATA = "ssong";
    public static final String INTENT_MODE = "SchedulrMode";

    public static final int MODE_SCHEDULE_INSERT = 2;
    public static final int MODE_SCHEDULR_EDIT = 3;

    public static final int MODE_EDIT = 0;
    public static final int MODE_OK = 1;

    public static final int MODE_MENU_EDIT = 9;
    public static final int MODE_MENU_INSERT = 10;
    public static final int MODE_MENU_SHOW = 11;

    public static final int MODE_THUMBNAIL_INSERT = 55;
    public static final int MODE_THUMBNAIL_EDIT = 66;

    public interface OnFabClickListener {
        public void onFabClick(View view, int mode);
    }

    public void fabshow(boolean a){
        if(tabLayout.getSelectedTabPosition()==0){
            if(a){
                fab.hideMenu(false);
            }else{
                fab.showMenu(false);
            }
        }
    }

    OnFabClickListener listener;

    public void setOnFabClickListener(OnFabClickListener listener) {
        this.listener = listener;
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
