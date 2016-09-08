package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ThumbnailsData implements Serializable{
    private String image;
    private String id;
    private boolean lastItem = false;

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLastItem() {
        return lastItem;
    }

    public void setLastItem(boolean lastItem) {
        this.lastItem = lastItem;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }
}
