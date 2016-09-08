package com.sm.ej.nk.homeal.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.sm.ej.nk.homeal.data.GalleryItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class GalleryManager {
    private Context context;
    private static GalleryManager instance;
    String sort = MediaStore.Images.Media.DATE_ADDED +"DESC";

    public static GalleryManager getInstance(Context context){
        if(instance == null){
            instance = new GalleryManager(context);
        }
        return instance;
    }
    private GalleryManager(Context context){
        this.context = context;
    }

    public List<GalleryItemData> getAllPhotoPathList(){
        ArrayList<GalleryItemData> photoList = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.DISPLAY_NAME};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, sort);
        int columIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()){
            GalleryItemData itemData = new GalleryItemData(cursor.getString(columIndexData), false);
            photoList.add(itemData);
        }
        cursor.close();

        return photoList;
    }

}
