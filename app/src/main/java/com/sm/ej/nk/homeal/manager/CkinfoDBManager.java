package com.sm.ej.nk.homeal.manager;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sm.ej.nk.homeal.HomealApplication;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CkinfoDBManager extends SQLiteOpenHelper {


    private static CkinfoDBManager instance;

    public static CkinfoDBManager getInstance() {
        if (instance == null) {
            instance = new CkinfoDBManager();
        }
        return instance;
    }
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "info_db";

    public CkinfoDBManager(){
        super(HomealApplication.getContext(),DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}