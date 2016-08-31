package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.Gson;
import com.sm.ej.nk.homeal.data.EtHomeResult;
import com.sm.ej.nk.homeal.manager.NetworkRequest;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-31.
 */

public class CkPageListRequest extends NetworkRequest<EtHomeResult>{
    Context context;
    Request request;

    public CkPageListRequest(Context context, int pageNo, int rowCount){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com:8080")
                .addPathSegment("cookers")
                .addQueryParameter("pageNo",""+pageNo)
                .addQueryParameter("rowCount", ""+rowCount)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected EtHomeResult parse(ResponseBody body) throws IOException {
        Gson gson = new Gson();
        EtHomeResult result = gson.fromJson(body.charStream(), EtHomeResult.class);

        return result;
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
