package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.InfoResult;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class CkInfoRequest extends AbstractRequest<NetworkResult<InfoResult>>  {

    Request request;

    public CkInfoRequest(Context context) {
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment("me")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<InfoResult>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
