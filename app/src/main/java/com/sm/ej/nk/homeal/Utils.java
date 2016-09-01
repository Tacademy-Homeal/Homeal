package com.sm.ej.nk.homeal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat();
    public static String convertTimeToString(Date date) {
        return sdf.format(date);
    }

    public static Date convertStringToTime(String text) throws ParseException {
        return sdf.parse(text);
    }
}

