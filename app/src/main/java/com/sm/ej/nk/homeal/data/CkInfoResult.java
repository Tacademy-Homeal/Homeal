package com.sm.ej.nk.homeal.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class CkInfoResult implements Serializable{
    private String code;
    private CkDetailData cooker_info;
    private List<CkDetailMenuData> cooker_menu;
    private List<CkScheduleData> cooker_schedule;
    private List<ThumbnailsData> cooker_thumbnail;

    public String getCode() {
        return code;
    }

    public CkDetailData getCooker_info() {
        return cooker_info;
    }

    public List<CkDetailMenuData> getCooker_menu() {
        return cooker_menu;
    }

    public List<CkScheduleData> getCooker_schedule() {
        return cooker_schedule;
    }

    public List<ThumbnailsData> getCooker_thumbnail() {
        return cooker_thumbnail;
    }
}
