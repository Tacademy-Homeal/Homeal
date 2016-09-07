package com.sm.ej.nk.homeal.manager;

import android.os.AsyncTask;
import android.widget.EditText;

import com.sm.ej.nk.homeal.CkPersonalDataActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class TranslateManager {

    public static final int KOREA_TO_ENGLISH = 0;
    public static final int ENGLISH_TO_KOREA = 1;
    public static final String TARGET_URL = "https://openapi.naver.com/v1/language/translate";
    String[] params;
    EditText transView;

    private static TranslateManager instance;

    public static TranslateManager getInstance() {
        if (instance == null) {
            instance = new TranslateManager();
        }
        return instance;
    }
    public TranslateManager() {
    }

    public void translateKoreantoEng(String ko) {
        params = new String[3];
        params[0] = ko;
        params[1] = "ko";
        params[2] = "en";
        new AsyncTrans().execute(params);
    }

    public void translateEngtoKorean(String eng, String ko){
        params[0] = transView.getText().toString();
        params[1] = "en";
        params[2] = "ko";
        new AsyncTrans().execute(params);
    }

    public class AsyncTrans extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result ="";
            HttpURLConnection conn = null;
            BufferedReader fromServer = null;
            StringBuilder queryBuf = new StringBuilder();
            try{
                queryBuf.append("text="+strings[0])
                        .append("&source="+strings[1])
                        .append("&target="+strings[2]);

                URL target = new URL(TARGET_URL);
                conn = (HttpURLConnection)target.openConnection();
                conn.setConnectTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("X-Naver-Client-Id", " EoqHhA2zE3io6u505lUo");
                conn.setRequestProperty("X-Naver-Client-Secret", "4FcfMQQfRv");
                OutputStream toServer = conn.getOutputStream();
                toServer.write(new String(queryBuf.toString()).getBytes("UTF-8"));
                toServer.close();

                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    fromServer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String onLine="";

                    StringBuilder jsonBuf = new StringBuilder();
                    while((onLine = fromServer.readLine())!=null){
                        jsonBuf.append(onLine);
                    }
                    result =TranslateManager.getJSon(jsonBuf);

                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
//            transView.setText(s);
            ((CkPersonalDataActivity)CkPersonalDataActivity.mContext).setTranslate(s);
        }
    }

    public static String getJSon(StringBuilder buf){
        JSONObject jo = null;
        JSONObject jo2 = null;
        JSONObject jo3 = null;
        JSONArray jsonArray = null;
        String result = "";
        try{
            jo = new JSONObject(buf.toString());
            jo2 = jo.getJSONObject("message");
            jo3 = jo2.getJSONObject("result");
            result = jo3.getString("translatedText");
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
