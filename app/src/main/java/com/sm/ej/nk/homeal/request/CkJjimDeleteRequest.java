package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class CkJjimDeleteRequest extends AbstractRequest<NetworkResultTemp> {
    Request request;

    public CkJjimDeleteRequest(Context context,String ckId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("bookmarks")
                .addPathSegment(ckId)
                .build();

        request = new Request.Builder()
                .url(url)
                .delete()
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
