package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResult;
import com.sm.ej.nk.homeal.data.ReviewData;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class CkReviewListRequest extends AbstractRequest<NetworkResult<List<ReviewData>>> {
    Request request;

    public CkReviewListRequest(Context context, String cookerId){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("cookers")
                .addPathSegment(cookerId)
                .addPathSegment("reviews")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ReviewData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
