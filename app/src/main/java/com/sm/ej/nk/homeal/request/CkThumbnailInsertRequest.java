package com.sm.ej.nk.homeal.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sm.ej.nk.homeal.data.GalleryItemData;
import com.sm.ej.nk.homeal.data.NetworkResultTemp;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-09.
 */
public class CkThumbnailInsertRequest extends AbstractRequest<NetworkResultTemp>{

    Request request;
    MediaType jpeg = MediaType.parse("image/jpeg");

    public CkThumbnailInsertRequest(Context context,List<GalleryItemData> imageLIst){
        HttpUrl url = getBaseHttpUrlBuilder()
                .addPathSegment("photos")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder();

        for(int i=0; i<imageLIst.size(); i++){
            if(imageLIst.get(i).getImagePath()!=null){
                File file = new File(imageLIst.get(i).getImagePath());
                builder.addFormDataPart("photos",file.getName(),  RequestBody.create(jpeg, file));
            }
        }

        RequestBody body = builder.build();
        request = new Request.Builder()
                .url(url)
                .post(body)
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
