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
 * Created by Tacademy on 2016-08-31.
 */

public class  CkPageListRequest extends AbstractRequest<NetworkResult<List<EtHomeData>>>{
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
        return new TypeToken<NetworkResult<List<EtHomeData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
