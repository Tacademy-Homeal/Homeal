package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class FontData {
    public String fontName;
    public String fileName;

    public FontData(String fontName, String fileName) {
        this.fontName = fontName;
        this.fileName = fileName;
    }

    public static final String NOTO_M = "noto";
    public static final String NOTO_D = "roboto";

    public static final String NOTO_MEDIUM = "NotoSansKR-Medium-Hestia.otf";
    public static final String NOTO_DEMI = "NotoSansKR-DemiLight-Hestia.otf";


    static FontData[] fontDatas = {new FontData(NOTO_M, NOTO_MEDIUM)
            , new FontData(NOTO_D, NOTO_DEMI)
    };
    public static FontData searchFont(String name) {
        for (FontData fd : fontDatas) {
            if (fd.fontName.equals(name)) {
                return fd;
            }
        }
        return null;
    }
}