package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CookerData;
import com.sm.ej.nk.homeal.data.CookerDataResult;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class CkInfoRequest extends AbstractRequest<NetworkResult<CookerDataResult>>  {

    Request request;

    public CkInfoRequest(Context context) {
        HttpUrl url = getBaseHttpsUrlBuilder()
//                .host("ec2-52-78-131-245.ap-northeast-2.compute.amazonaws.com:443")
                .addPathSegment("cookers/me")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<CookerData>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
