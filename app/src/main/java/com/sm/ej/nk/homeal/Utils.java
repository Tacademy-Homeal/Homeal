package com.sm.ej.nk.homeal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat();
    static SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static String convertTimeToString(Date date) {
        return sdf.format(date);
    }

    public static Date convertStringToTime(String text) throws ParseException {
        return sdf.parse(text);
    }

    public static Date  convertStringToTimea_df(String text) throws ParseException {
        return adf.parse(text);
    }
}

