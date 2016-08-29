package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.sm.ej.nk.homeal.adapter.ChattingAdatper;
import com.sm.ej.nk.homeal.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChattingActivity extends AppCompatActivity {

    @BindView(R.id.rv_chattingactivity)
    RecyclerView rv_chatting;

    ChattingAdatper mAdapter;

    @BindView(R.id.input_edit)
    EditText inputView;

    @BindView(R.id.toobar_chatting)
    Toolbar toolbar;

    public static final String EXTRA_USER = "user";
    User user;

    LocalBroadcastManager mLBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        ButterKnife.bind(this);

        //set Toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        mAdapter = new ChattingAdatper();
        rv_chatting.setAdapter(mAdapter);
        rv_chatting.setLayoutManager(new LinearLayoutManager(this));
        mLBM = LocalBroadcastManager.getInstance(this);
    }

    @OnClick(R.id.btn_send)
    public void onSend(){
        final String message = inputView.getText().toString();



    }


}
