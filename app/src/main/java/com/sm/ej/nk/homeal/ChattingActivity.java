package com.sm.ej.nk.homeal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sm.ej.nk.homeal.adapter.ChattingAdapter;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.gcm.ChattingGcmListenerService;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.manager.NetworkRequest;
import com.sm.ej.nk.homeal.request.MessageSendRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChattingActivity extends AppCompatActivity {

    @BindView(R.id.rv_chattingactivity)
    RecyclerView rv_chatting;

    ChattingAdapter mAdapter;

    @BindView(R.id.input_edit)
    EditText inputView;

    @BindView(R.id.toobar_chatting)
    Toolbar toolbar;

    public static final String EXTRA_USER = "userinfo";
    LocalBroadcastManager mLBM;
    Long id;


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
        id = (long) getIntent().getLongExtra(EXTRA_USER,-100);
        mAdapter = new ChattingAdapter();
        rv_chatting.setAdapter(mAdapter);
        rv_chatting.setLayoutManager(new LinearLayoutManager(this));
        mLBM = LocalBroadcastManager.getInstance(this);
    }


    @OnClick(R.id.btn_send)
    public void onSend(View view){
        final String message = inputView.getText().toString();
        MessageSendRequest request = new MessageSendRequest(this, id, message);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                User user = new User();
                user.setId(id);
                ChattingDBManager.getInstance().addMessage(user, ChatContract.ChatMessage.TYPE_SEND, message,user.getImage());
                updateMessage();
                inputView.setText(null);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Log.d("Chatting fail","fail");
            }
        });
    }

    private void updateMessage() {
        Cursor c = ChattingDBManager.getInstance().getChatMessage(id);
        mAdapter.changeCursor(c);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMessage();
        mLBM.registerReceiver(mReceiver, new IntentFilter(ChattingGcmListenerService.ACTION_CHAT));
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            id = (long) intent.getLongExtra(ChattingGcmListenerService.EXTRA_CHAT_USER,-100);
            if (id != 100) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateMessage();
                    }
                });
                intent.putExtra(ChattingGcmListenerService.EXTRA_RESULT, true);
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.changeCursor(null);
        mLBM.unregisterReceiver(mReceiver);
    }


}
