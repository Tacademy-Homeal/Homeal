package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class EtInfoupdateRequest extends AbstractRequest<NetworkResultTemp> {
    MediaType jpeg = MediaType.parse("image/jpeg");
    Context context;
    Request request;

    public EtInfoupdateRequest(Context context, String name, String birth, String phone, String introduce, String gender, File image, String country) {
        HttpUrl url = getBaseHttpsUrlBuilder()
                .addPathSegment("eaters")
                .addPathSegment("me")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .addFormDataPart("name", name)
                .addFormDataPart("birth", birth)
                .addFormDataPart("phone", phone)
                .addFormDataPart("introduce", introduce)
                .addFormDataPart("gender", gender)
                .addFormDataPart("country", country);

        if (image != null) {
            builder.addFormDataPart("image", image.getName(), RequestBody.create(jpeg, image));
        }


        RequestBody body = builder.build();
        request = new Request.Builder()
                .url(url)
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResultTemp>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
