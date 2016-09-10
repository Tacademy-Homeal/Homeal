package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.CkDetailMenuData;
import com.sm.ej.nk.homeal.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by 송민 on 2016-09-10.
 */
public class CkMenuListRequest extends AbstractRequest<NetworkResult<List<CkDetailMenuData>>> {

    Request request;

    public CkMenuListRequest(Context context, String cookerId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment(cookerId)
                .addPathSegment("menus")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<CkDetailMenuData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
