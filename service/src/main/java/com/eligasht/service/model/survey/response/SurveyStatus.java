
package com.eligasht.service.model.survey.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyStatus {

    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
