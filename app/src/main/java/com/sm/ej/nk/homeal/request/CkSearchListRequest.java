package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.EtHomeData;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-12.
 */
public class CkSearchListRequest extends AbstractRequest<NetworkResult<List<EtHomeData>>> {

    Request request;
    public CkSearchListRequest(Context context, String keyword, String pageNo, String rowCount){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("cookers")
                .addQueryParameter("keyword",keyword)
                .addQueryParameter("pageNo", pageNo)
                .addQueryParameter("rowCount", rowCount)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<EtHomeData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
