package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CkInfoResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-20.
 */
public class CkHomeRequest  extends AbstractRequest<CkInfoResult>{
    Context context;
    Request request;

    public CkHomeRequest(Context context){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment("store")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<CkInfoResult>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
