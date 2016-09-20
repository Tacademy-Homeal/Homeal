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
public class WriteReservationRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;
    public WriteReservationRequest(Context context, String id,String aftercontext,
                                   int taste,int price,int cleanliness,int kindness){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("reviews")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("cooker", id)
                .add("content", aftercontext)
                .add("taste", ""+taste)
                .add("price",""+price)
                .add("cleanliness",""+cleanliness)
                .add("kindness", ""+kindness)
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
