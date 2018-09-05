
package com.eligasht.service.model.survey.response.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyTemplate {

    @SerializedName("BackgroundImage")
    @Expose
    private Object backgroundImage;
    @SerializedName("HeaderBackground")
    @Expose
    private String headerBackground;
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
    private String pageBackground;
    @SerializedName("Title")
    @Expose
    private String title;

    public Object getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Object backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(String headerBackground) {
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

    public String getPageBackground() {
        return pageBackground;
    }

    public void setPageBackground(String pageBackground) {
        this.pageBackground = pageBackground;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
