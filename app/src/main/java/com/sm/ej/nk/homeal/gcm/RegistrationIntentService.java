package com.sm.ej.nk.homeal.gcm;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.sm.ej.nk.homeal.manager.PropertyManager;

import java.io.IOException;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class RegistrationIntentService  extends FirebaseInstanceIdService {


    private static final String[] TOPICS = {"global"};

    public static final String REGISTRATION_COMPLETE = "registrationComplete";

//    public RegistrationIntentService() {
//        super(REGISTRATION_COMPLETE);
//    }

    @Override
    public void onTokenRefresh() {
        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            PropertyManager.getInstance().setRegistrationId(token);
            subscribeTopics(token);
        } catch (Exception e) {
            Log.d(REGISTRATION_COMPLETE, "Failed to complete token refresh", e);
        }
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]


}
