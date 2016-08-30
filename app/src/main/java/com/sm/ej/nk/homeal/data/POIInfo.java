package com.sm.ej.nk.homeal.data;

public class POIInfo {
    private String count;
    private String page;
    private String totalCount;
    private POIS pois;

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public POIS getPois() {
        return this.pois;
    }

    public void setPois(POIS pois) {
        this.pois = pois;
    }
}
