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
 * Created by Tacademy on 2016-09-09.
 */
public class CkJjimAddRequest extends AbstractRequest<NetworkResultTemp> {

    Request request;

    public CkJjimAddRequest(Context context, String cookerId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("bookmarks")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("cooker", cookerId)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .post(body)
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
