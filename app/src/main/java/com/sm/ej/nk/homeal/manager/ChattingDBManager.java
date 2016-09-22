package com.sm.ej.nk.homeal.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sm.ej.nk.homeal.HomealApplication;
import com.sm.ej.nk.homeal.data.ChatContract;
import com.sm.ej.nk.homeal.data.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChattingDBManager extends SQLiteOpenHelper {
    private static ChattingDBManager instance;
    public static ChattingDBManager getInstance(){
        if(instance == null){
            instance = new ChattingDBManager();
        }
        return instance;
    }
    private static final String DB_NAME = "chat_db";
    private static final int DB_VERSION = 1;

    private ChattingDBManager() {
        super(HomealApplication.getContext(), DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + ChatContract.ChatUser.TABLE + "(" +
                ChatContract.ChatUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChatContract.ChatUser.COLUMN_SERVER_ID + " INTEGER," +
                ChatContract.ChatUser.COLUMN_IMAGE + " TEXT," +
                ChatContract.ChatUser.COLUMN_NAME + " TEXT," +
                ChatContract.ChatUser.COLUMN_LAST_MESSAGE_ID + " INTEGER);";
        db.execSQL(sql);

        sql = "CREATE TABLE " + ChatContract.ChatMessage.TABLE + "(" +
                ChatContract.ChatMessage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChatContract.ChatMessage.COLUMN_USER_ID + " INTEGER," +
                ChatContract.ChatMessage.COLUM_IMAGEURL + " TEXT," +
                ChatContract.ChatMessage.COLUMN_TYPE + " INTEGER," +
                ChatContract.ChatMessage.COLUMN_MESSAGE + " TEXT," +
                ChatContract.ChatMessage.COLUMN_CREATED + " INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long getUserId(long serverId) {
        String selection = ChatContract.ChatUser.COLUMN_SERVER_ID + " = ?";
        String[] args = {""+serverId};
        String[] columns = {ChatContract.ChatUser._ID};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(ChatContract.ChatUser.TABLE, columns, selection, args, null, null, null);
        try {
            if (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(ChatContract.ChatUser._ID));
                return id;
            }
        } finally {
            c.close();
        }
        return -1;
    }

    public long getLastReceiveDate() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {ChatContract.ChatMessage.COLUMN_CREATED};
        String selection = ChatContract.ChatMessage.COLUMN_TYPE + " = ?";
        String[] args = {"" + ChatContract.ChatMessage.TYPE_RECEIVE};
        String orderBy = ChatContract.ChatMessage.COLUMN_CREATED + " DESC";
        String limit = "1";
        Cursor c = db.query(ChatContract.ChatMessage.TABLE, columns, selection, args, null, null, orderBy, limit);
        try {
            if (c.moveToNext()) {
                long time = c.getLong(c.getColumnIndex(ChatContract.ChatMessage.COLUMN_CREATED));
                return time;
            }
        } finally {
            c.close();
        }
        return 0;
    }

    ContentValues values = new ContentValues();
    public long addUser(User user) {
        if (getUserId(user.getId()) == -1) {
            SQLiteDatabase db = getWritableDatabase();
            values.clear();
            values.put(ChatContract.ChatUser.COLUMN_SERVER_ID, user.getId());
            values.put(ChatContract.ChatUser.COLUMN_NAME, user.getName());
            values.put(ChatContract.ChatUser.COLUMN_IMAGE,user.getImage());
            return db.insert(ChatContract.ChatUser.TABLE, null, values);
        }
        throw new IllegalArgumentException("aleady user added");
    }

    Map<Long, Long> resolveUserId = new HashMap<>();
    public long addMessage(User user, int type, String message,String image) {
        return addMessage(user, type, message, new Date(),image);
    }
    public long addMessage(User user, int type, String message, Date date,String image) {
        Long uid = resolveUserId.get(user.getId());
        if (uid == null) {
            long flag = getUserId(user.getId());
            if (flag == -1) {
                user.setImage(image);
                addUser(user);
            }
            resolveUserId.put(user.getId(), user.getId());
            uid = user.getId();
        }
        SQLiteDatabase db = getWritableDatabase();
        values.clear();
        values.put(ChatContract.ChatMessage.COLUMN_USER_ID, uid);
        values.put(ChatContract.ChatMessage.COLUMN_TYPE, type);
        values.put(ChatContract.ChatMessage.COLUMN_MESSAGE, message);
        values.put(ChatContract.ChatMessage.COLUM_IMAGEURL, image);

        long current = date.getTime();
        values.put(ChatContract.ChatMessage.COLUMN_CREATED, current);
        try {
            db.beginTransaction();
            long mid = db.insert(ChatContract.ChatMessage.TABLE, null, values);

            values.clear();
            values.put(ChatContract.ChatUser.COLUMN_LAST_MESSAGE_ID, mid);
            String selection = ChatContract.ChatUser._ID + " = ?";

            String[] args = {"" + uid};
            db.update(ChatContract.ChatUser.TABLE, values, selection, args);
            db.setTransactionSuccessful();
            return mid;
        } finally {
            db.endTransaction();
        }
    }

    public Cursor getChatUser() {
        String table = ChatContract.ChatUser.TABLE + " INNER JOIN " +
                ChatContract.ChatMessage.TABLE + " ON " +
                ChatContract.ChatUser.TABLE + "." + ChatContract.ChatUser._ID + " = " +
                ChatContract.ChatMessage.TABLE + "." + ChatContract.ChatMessage._ID;
        String[] columns = {ChatContract.ChatUser.TABLE + "." + ChatContract.ChatUser._ID,
                ChatContract.ChatUser.COLUMN_SERVER_ID,
                ChatContract.ChatUser.COLUMN_NAME,
                ChatContract.ChatUser.COLUMN_IMAGE,
                ChatContract.ChatMessage.COLUMN_MESSAGE};
        String sort = ChatContract.ChatUser.COLUMN_NAME  + " COLLATE LOCALIZED ASC";
        SQLiteDatabase db = getReadableDatabase();

        return db.query(table, columns, null, null, null, null, sort);
    }

    public Cursor getChatMessage(Long customld) {
        long userid = -1;
        Long uid = resolveUserId.get(customld);
        if (uid == null) {
            long id = getUserId(customld);
            if (id != -1) {
                resolveUserId.put(customld, id);
                userid = id;
            }
        } else {
            userid = uid;
        }
        String[] columns = {ChatContract.ChatMessage._ID,
                ChatContract.ChatMessage.COLUMN_TYPE,
                ChatContract.ChatMessage.COLUM_IMAGEURL,
                ChatContract.ChatMessage.COLUMN_MESSAGE,
                ChatContract.ChatMessage.COLUMN_CREATED};
        String selection = ChatContract.ChatMessage.COLUMN_USER_ID + " = ?";
        String[] args = {"" + userid};
        String sort = ChatContract.ChatMessage.COLUMN_CREATED + " ASC";
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ChatContract.ChatMessage.TABLE, columns, selection, args, null, null, sort);
    }


}
