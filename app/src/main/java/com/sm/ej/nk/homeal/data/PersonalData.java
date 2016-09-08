package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

public class PersonalData implements Serializable {
    private String image;
    private int country;
    private String address;
    private String gender;
    private String phone;
    private String introduce;
    private int grade;
    private String name;
    private String birth;
    private String type;


    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public String getCountry() {
//        return this.country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }


    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
