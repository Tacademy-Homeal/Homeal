package com.sm.ej.nk.homeal.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sm.ej.nk.homeal.ChattingActivity;
import com.sm.ej.nk.homeal.CkMainActivity;
import com.sm.ej.nk.homeal.EtMainActivity;
import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.SplashActivity;
import com.sm.ej.nk.homeal.Utils;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.ChatMessage;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.manager.ChattingDBManager;
import com.sm.ej.nk.homeal.manager.NetworkManager;
import com.sm.ej.nk.homeal.request.MessageListRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


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
    public static final int NETWORK_NOMAL = 1;


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

        switch (message_type){
            case MESSAGE_CHATTING:
                chatting();
                break;
            case MESSAGE_ALARM:
                break;
            case MESSAGE_WRITE:
                break;
        }
        sendNotification(message);
    }

    private void chatting() {
        long lastTime = ChattingDBManager.getInstance().getLastReceiveDate();

        MessageListRequest request = new MessageListRequest(this);
        try {
            NetworkResult<List<ChatMessage>> result = NetworkManager.getInstance().getNetworkDataSync(request);
            List<ChatMessage> list = result.getResult();
            for (ChatMessage m : list) {
                try {

                    //   addMessage(String name, String image, Long senderid, String message, Date date,int type)
                    ChattingDBManager.getInstance().addMessage(m.getSender(), ChatContract.ChatMessage.TYPE_RECEIVE, m.getMessage(),
                            Utils.convertStringToTime(m.getDate()));
                    Intent i = new Intent(ACTION_CHAT);
                    i.putExtra(EXTRA_CHAT_USER, m.getSender());
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
        intent.putExtra(ChattingActivity.EXTRA_USER, m.getSender());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Chat Message")
                .setContentText(m.getMessage())
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    private void sendNotification(String message) {

        //CKmain and ETmain
        if(HomealApplication.isCooker() == true){
            intent  = new Intent(this, CkMainActivity.class);
        }else{
            intent = new Intent(this, EtMainActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
