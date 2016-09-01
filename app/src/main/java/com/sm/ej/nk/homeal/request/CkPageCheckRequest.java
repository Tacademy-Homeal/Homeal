package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.Gson;
import com.sm.ej.nk.homeal.data.CkDetailPageData;
import com.sm.ej.nk.homeal.manager.NetworkRequest;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class CkPageCheckRequest extends NetworkRequest<CkDetailPageData>{
    Context context;
    Request request;

    public CkPageCheckRequest(Context context, int ckId){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com:8080")
                .addPathSegment("cookers")
                .addPathSegment(""+ckId)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected CkDetailPageData parse(ResponseBody body) throws IOException {
        Gson gson = new Gson();
        CkDetailPageData result = gson.fromJson(body.charStream(), CkDetailPageData.class);
        return result;
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
