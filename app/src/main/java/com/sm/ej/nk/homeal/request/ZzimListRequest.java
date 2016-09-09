package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class ZzimListRequest extends AbstractRequest<NetworkResult<List<EtHomeData>>> {

    Context context;
    Request request;

    public ZzimListRequest(Context context) {
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("bookmarks")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<EtHomeData>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
