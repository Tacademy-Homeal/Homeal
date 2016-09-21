package com.sm.ej.nk.homeal.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sm.ej.nk.homeal.ChattingActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SplashActivity;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.ChatMessage;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.User;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.request.ReceiveMessageRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.sm.ej.nk.homeal.Utils.convertStringToTimea_df;


/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChattingGcmListenerService extends FirebaseMessagingService {

    private static final String TAG = "ChatGcmListenerService";
    public static final String ACTION_CHAT = "com.sm.ej.nk.homeal.data.chatmessage";
    public static final String EXTRA_CHAT_USER = "chatuser";
    public static final String EXTRA_RESULT = "result";
    public static final int MESSAGE_CHATTING = 1;
    public static final int MESSAGE_ALARM = 2;
    public static final int MESSAGE_WRITE = 3;

    LocalBroadcastManager mLBM;
    Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        mLBM = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String message = remoteMessage.getFrom();
        Log.d(TAG, "Message: " + message);
        Map<String,String> data = remoteMessage.getData();
        int message_type = Integer.parseInt(data.get("key").toString());
        int code = Integer.parseInt(data.get("code").toString());

        switch (message_type){
            case MESSAGE_CHATTING:
                chatting();
                break;
            case MESSAGE_ALARM:
                sendAlarmNotification(code);
                break;
            case MESSAGE_WRITE:
                sendWriteNotification();
                break;
        }
    }

    private void chatting() {
        ReceiveMessageRequest request = new ReceiveMessageRequest(this);
        try {
            NetworkResult<List<ChatMessage>> result = NetworkManager.getInstance().getNetworkDataSync(request);
            List<ChatMessage> list = result.getResult();
            for (ChatMessage m : list) {
                try {
                    User user = new User();
                    user.setId(m.getSender());//id  이다.
                    user.setName(m.getName());
                    //   addMessage(User user, int type, String message, Date date,String image)
                    ChattingDBManager.getInstance().addMessage(user, ChatContract.ChatMessage.TYPE_RECEIVE, m.getMessage(),
                            convertStringToTimea_df(m.getDate()),m.getImage());
                    Intent i = new Intent(ACTION_CHAT);
                    i.putExtra(EXTRA_CHAT_USER, user.getId());
                    mLBM.sendBroadcastSync(i);
                    boolean processed = i.getBooleanExtra(EXTRA_RESULT, false);
                    if (!processed) {
                        sendNotification(m);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(ChatMessage m) {
        Intent intent = new Intent(this, SplashActivity.class);

        long id = m.getSender();
        Long userid = id;
        intent.putExtra(ChattingActivity.EXTRA_USER, userid);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Chat Message")
                .setContentText(m.getMessage())
                .setContentText(""+userid)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendAlarmNotification(int code){
        String message = "";
        switch (code){
            case 1:
                message = "예약 요청 완료";
                break;
            case 2:
                message = "예약 거절 당함";
                break;
            case 3:
                message = "3";
                break;
            case 4:
                message = "4";
                break;
            case 5:
                message = "5";
                break;
            case 6:
                message = "6";
                break;
            case 7:
                message = "7";
                break;
        }

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.homeal_icon)
                .setTicker("New alarm")
                .setContentTitle("Homeal")
                .setContentText(message)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


    private void sendWriteNotification(){

        String message = "후기가 작성되었습니다.";
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.homeal_icon)
                .setTicker("New alarm")
                .setContentTitle("Homeal")
                .setContentText(message)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
