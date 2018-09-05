
package com.eligasht.service.model.survey.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyTemplate {

    @SerializedName("BackgroundImage")
    @Expose
    private Object backgroundImage;
    @SerializedName("HeaderBackground")
    @Expose
    private Object headerBackground;
    @SerializedName("HeaderBackgroundImage")
    @Expose
    private Object headerBackgroundImage;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("ISActive")
    @Expose
    private Boolean iSActive;
    @SerializedName("IsRTL")
    @Expose
    private Boolean isRTL;
    @SerializedName("IsUserGenerated")
    @Expose
    private Boolean isUserGenerated;
    @SerializedName("PageBackground")
    @Expose
    private Object pageBackground;
    @SerializedName("Title")
    @Expose
    private String title;

    public Object getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Object backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Object getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(Object headerBackground) {
        this.headerBackground = headerBackground;
    }

    public Object getHeaderBackgroundImage() {
        return headerBackgroundImage;
    }

    public void setHeaderBackgroundImage(Object headerBackgroundImage) {
        this.headerBackgroundImage = headerBackgroundImage;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Boolean getISActive() {
        return iSActive;
    }

    public void setISActive(Boolean iSActive) {
        this.iSActive = iSActive;
    }

    public Boolean getIsRTL() {
        return isRTL;
    }

    public void setIsRTL(Boolean isRTL) {
        this.isRTL = isRTL;
    }

    public Boolean getIsUserGenerated() {
        return isUserGenerated;
    }

    public void setIsUserGenerated(Boolean isUserGenerated) {
        this.isUserGenerated = isUserGenerated;
    }

    public Object getPageBackground() {
        return pageBackground;
    }

    public void setPageBackground(Object pageBackground) {
        this.pageBackground = pageBackground;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
