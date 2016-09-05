package com.sm.ej.nk.homeal.data;

import java.util.List;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class CkInfoResult {
    private CkDetailData cooker_info;
    private CkDetailMenuData cooker_menu;
    private List<CkScheduleData> cooker_schedule;

    public CkDetailData getCooker_info() {
        return cooker_info;
    }

    public void setCooker_info(CkDetailData cooker_info) {
        this.cooker_info = cooker_info;
    }

    public CkDetailMenuData getCooker_menu() {
        return cooker_menu;
    }

    public void setCooker_menu(CkDetailMenuData cooker_menu) {
        this.cooker_menu = cooker_menu;
    }

    public List<CkScheduleData> getCooker_schedule() {
        return cooker_schedule;
    }

    public void setCooker_schedule(List<CkScheduleData> cooker_schedule) {
        this.cooker_schedule = cooker_schedule;
    }
}
