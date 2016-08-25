package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoCkDetailActivity extends AppCompatActivity {

    @BindView(R.id.toobar_ck_detail)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

        //set Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);

    }

    @OnClick(R.id.btn_info_ck_detail_photo)
    public void onClickPhoto(){
        Toast.makeText(this,"Photo Click",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_info_ck_detail_chatting)
    public void onClickChatting(){
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_info_ck_detai_menu)
    public void onClickMenu(){
        Intent intent = new Intent(this, MenuDetailActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_info_ck_detail_map)
    public void onClickMap(){
        Toast.makeText(this,"MAP Click",Toast.LENGTH_LONG).show();

    }
    @OnClick(R.id.btn_info_ck_detail_inquire)
    public void onClickInquire(){
        Intent intent = new Intent(this, ReserveRequestActivity.class);
        startActivity(intent);
    }



}
