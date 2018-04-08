
package com.eligasht.service.model.about.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IconCode")
    @Expose
    private Object iconCode;
    @SerializedName("IconName")
    @Expose
    private Object iconName;
    @SerializedName("ImageAddress")
    @Expose
    private String imageAddress;
    @SerializedName("SectionName")
    @Expose
    private String sectionName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getIconCode() {
        return iconCode;
    }

    public void setIconCode(Object iconCode) {
        this.iconCode = iconCode;
    }

    public Object getIconName() {
        return iconName;
    }

    public void setIconName(Object iconName) {
        this.iconName = iconName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}
