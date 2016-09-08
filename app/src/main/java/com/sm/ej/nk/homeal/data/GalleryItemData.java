package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class GalleryItemData {
    private String imagePath;
    private boolean selected = false;

    public GalleryItemData(String imagePath, boolean selected){
        this.imagePath = imagePath;
        this.selected = selected;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
