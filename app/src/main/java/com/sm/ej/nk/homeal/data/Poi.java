package com.sm.ej.nk.homeal.data;

public class Poi {
    private String detailBizName;
    private String detailInfoFlag;
    private String lowerAddrName;
    private String frontLon;
    private String roadName;
    private String bizName;
    private String lowerBizName;
    private String upperAddrName;
    private String frontLat;
    private String id;
    private String radius;
    private String rpFlag;
    private String middleAddrName;
    private String detailAddrName;
    private String secondNo;
    private String telNo;
    private String secondBuildNo;
    private String noorLon;
    private String middleBizName;
    private String noorLat;
    private String firstBuildNo;
    private String firstNo;
    private String upperBizName;
    private String name;
    private String parkFlag;
    private String desc;

    private Double latitude = null;
    private Double longitude = null;

    public double getLatitude() {
        if (latitude == null) {
            latitude = (Double.parseDouble(frontLat) + Double.parseDouble(noorLat)) / 2;
        }
        return latitude;
    }

    public double getLongitude() {
        if (longitude == null) {
            longitude = (Double.parseDouble(frontLon) + Double.parseDouble(noorLon)) / 2;
        }
        return longitude;
    }

    public String getDetailBizName() {
        return this.detailBizName;
    }

    public void setDetailBizName(String detailBizName) {
        this.detailBizName = detailBizName;
    }

    public String getDetailInfoFlag() {
        return this.detailInfoFlag;
    }

    public void setDetailInfoFlag(String detailInfoFlag) {
        this.detailInfoFlag = detailInfoFlag;
    }

    public String getLowerAddrName() {
        return this.lowerAddrName;
    }

    public void setLowerAddrName(String lowerAddrName) {
        this.lowerAddrName = lowerAddrName;
    }

    public String getFrontLon() {
        return this.frontLon;
    }

    public void setFrontLon(String frontLon) {
        this.frontLon = frontLon;
    }

    public String getRoadName() {
        return this.roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getBizName() {
        return this.bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getLowerBizName() {
        return this.lowerBizName;
    }

    public void setLowerBizName(String lowerBizName) {
        this.lowerBizName = lowerBizName;
    }

    public String getUpperAddrName() {
        return this.upperAddrName;
    }

    public void setUpperAddrName(String upperAddrName) {
        this.upperAddrName = upperAddrName;
    }

    public String getFrontLat() {
        return this.frontLat;
    }

    public void setFrontLat(String frontLat) {
        this.frontLat = frontLat;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRadius() {
        return this.radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getRpFlag() {
        return this.rpFlag;
    }

    public void setRpFlag(String rpFlag) {
        this.rpFlag = rpFlag;
    }

    public String getMiddleAddrName() {
        return this.middleAddrName;
    }

    public void setMiddleAddrName(String middleAddrName) {
        this.middleAddrName = middleAddrName;
    }

    public String getDetailAddrName() {
        return this.detailAddrName;
    }

    public void setDetailAddrName(String detailAddrName) {
        this.detailAddrName = detailAddrName;
    }

    public String getSecondNo() {
        return this.secondNo;
    }

    public void setSecondNo(String secondNo) {
        this.secondNo = secondNo;
    }

    public String getTelNo() {
        return this.telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSecondBuildNo() {
        return this.secondBuildNo;
    }

    public void setSecondBuildNo(String secondBuildNo) {
        this.secondBuildNo = secondBuildNo;
    }

    public String getNoorLon() {
        return this.noorLon;
    }

    public void setNoorLon(String noorLon) {
        this.noorLon = noorLon;
    }

    public String getMiddleBizName() {
        return this.middleBizName;
    }

    public void setMiddleBizName(String middleBizName) {
        this.middleBizName = middleBizName;
    }

    public String getNoorLat() {
        return this.noorLat;
    }

    public void setNoorLat(String noorLat) {
        this.noorLat = noorLat;
    }

    public String getFirstBuildNo() {
        return this.firstBuildNo;
    }

    public void setFirstBuildNo(String firstBuildNo) {
        this.firstBuildNo = firstBuildNo;
    }

    public String getFirstNo() {
        return this.firstNo;
    }

    public void setFirstNo(String firstNo) {
        this.firstNo = firstNo;
    }

    public String getUpperBizName() {
        return this.upperBizName;
    }

    public void setUpperBizName(String upperBizName) {
        this.upperBizName = upperBizName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParkFlag() {
        return this.parkFlag;
    }

    public void setParkFlag(String parkFlag) {
        this.parkFlag = parkFlag;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return name;
    }
}
