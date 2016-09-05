package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.StoreListResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-31.
 */

public class CkPageListRequest extends AbstractRequest<NetworkResult<StoreListResult>>{
    Context context;
    Request request;

    public CkPageListRequest(Context context, String pageNo, String rowCount){
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("cookers")
                .addQueryParameter("pageNo", pageNo)
                .addQueryParameter("rowCount", rowCount)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<StoreListResult>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
