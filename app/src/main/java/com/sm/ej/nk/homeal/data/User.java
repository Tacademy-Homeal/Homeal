package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class User implements Serializable {
    public long id;
    public String userName;
    public String email;
    public String facebookId;
    public String type;
    public String PictureUrl;
    public int totalScore;
    public int etPoint;

    public int getEtPoint() {
        return etPoint;
    }

    public void setEtPoint(int etPoint) {
        this.etPoint = etPoint;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //  public String userImageUrl;

/*    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
