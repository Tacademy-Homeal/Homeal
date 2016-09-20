package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class LogOutRequest  extends AbstractRequest<NetworkResultTemp> {
    Request request;

    public LogOutRequest(Context context) {

        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("auth")
                .addPathSegment("logout")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResultTemp>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}

