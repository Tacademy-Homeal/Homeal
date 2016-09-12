package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ReserveData;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-12.
 */
public class ReservationListRequest extends AbstractRequest<NetworkResult<List<ReserveData>>> {

    Context context;
    Request request;

    public ReservationListRequest(Context context){
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
        return new TypeToken<NetworkResult<List<ReserveData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
