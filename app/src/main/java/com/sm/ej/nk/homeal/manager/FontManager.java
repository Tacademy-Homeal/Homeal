package com.sm.ej.nk.homeal.manager;

import android.content.Context;
import android.graphics.Typeface;

import com.sm.ej.nk.homeal.data.FontData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class FontManager {

    private static FontManager instance;

    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

    private FontManager() {

    }

    Map<String, Typeface> fontMap = new HashMap<>();

    public Typeface getTypeface(Context context, String fontName) {
        Typeface typeface = fontMap.get(fontName);
        if (typeface == null) {
            FontData fd = FontData.searchFont(fontName);
            if (fd == null) return null;
            typeface = Typeface.createFromAsset(context.getAssets(), fd.fileName);
            fontMap.put(fontName, typeface);
        }
        return typeface;
    }
}
