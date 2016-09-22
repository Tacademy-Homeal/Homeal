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
 * Created by kimnamgil on 2016. 9. 15..
 */
public class ReservationsChangeRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;

    public ReservationsChangeRequest(Context context,int id, int status,String uld) {

        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("reservations")
                .addPathSegment(""+id)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("status", "" + status)
                .add("receiver",uld)
                .build();

        request = new Request.Builder()
                .url(url)
                .put(body)
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
    }{
    }
}