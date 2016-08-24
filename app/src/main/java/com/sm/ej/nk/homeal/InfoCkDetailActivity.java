package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoCkDetailActivity extends AppCompatActivity {

    @OnClick(R.id.btn_info_ck_detail_chat)
    public void onSendChat(){
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_info_ck_detail_menu)
    public void onSendMenuDetail(){
        Intent intent = new Intent(this, MenuDetailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_info_ck_detail_reserve)
    public void onSendReserve(){
        Intent intent = new Intent(this, ReserveRequestActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

    }
}
