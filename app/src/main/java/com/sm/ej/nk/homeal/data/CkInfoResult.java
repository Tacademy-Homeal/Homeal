package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class CkInfoResult {
    private String code;
    private InfoResult cooker_info;
    private CkMenuResult cooker_menu;
    private SchedulesResult cooker_schedule;
    private CkthumbnailResult cooker_thumbnail;

    public String getCode() {
        return code;
    }

    public InfoResult getCooker_info() {
        return cooker_info;
    }

    public CkMenuResult getCooker_menu() {
        return cooker_menu;
    }

    public SchedulesResult getCooker_schedule() {
        return cooker_schedule;
    }

    public CkthumbnailResult getCooker_thumbnail() {
        return cooker_thumbnail;
    }
}
