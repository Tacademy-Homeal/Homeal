package com.sm.ej.nk.homeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoCkDetailActivity extends AppCompatActivity {

    @BindView(R.id.toobar_et_detail)
    Toolbar toolbar;

    @BindView(R.id.image_info_et_detail_photo)
    ImageView mainFoodView;

    @BindView(R.id.image_info_et_detail_map)
    ImageView mapView;

    @BindView(R.id.image_info_et_detai_menu)
    ImageView detailFoodView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ck_detail);
        ButterKnife.bind(this);

        //set Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mainFoodView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.food2));
        mapView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),R.drawable.googlemap));
        detailFoodView.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.food));
    }



    @OnClick(R.id.image_info_et_detail_photo)
    public void onClickPhoto(){
        Toast.makeText(this,"Photo Click",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_info_et_detail_chatting)
    public void onClickChatting(){
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.image_info_et_detai_menu)
    public void onClickMenu(){
        Intent intent = new Intent(this, MenuDetailActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.image_info_et_detail_map)
    public void onClickMap(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_info_et_detail_inquire)
    public void onClickInquire(){
        Intent intent = new Intent(this, ReserveRequestActivity.class);
        startActivity(intent);
    }




}
