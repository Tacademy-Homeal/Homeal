package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CkReseveData;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class CkReserveListRequest extends AbstractRequest<NetworkResult<List<CkReseveData>>> {

    Context context;
    Request request;

    public CkReserveListRequest(Context context){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("reservations")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<CkReseveData>>>(){}.getType();
    }
    @Override
    public Request getRequest() {
        return request;
    }
}

