package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class CkScheduleInsertRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;

    public CkScheduleInsertRequest(Context context,String date, String pax, String sharing){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("schedules")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("date", date)
                .add("pax", pax)
                .add("sharing", sharing)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResultTemp>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
