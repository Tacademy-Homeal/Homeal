package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CkInfoResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class CkPageCheckRequest extends AbstractRequest<CkInfoResult>{
    Context context;
    Request request;

    public CkPageCheckRequest(Context context,String ckId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment(ckId)
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
