package com.microlearning.model;

import java.io.Serializable;

public class GalleryItem implements Serializable {

    public String imageUri;
    public String imageName;
    public boolean isSelected = false;
    public boolean isLastItem = false;

    public GalleryItem(String imageUri, String imageName,boolean isLastItem) {
        this.imageUri = imageUri;
        this.imageName = imageName;
        this.isLastItem=isLastItem;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isLastItem() {
        return isLastItem;
    }

    public void setLastItem(boolean lastItem) {
        isLastItem = lastItem;
    }
}
