
package com.eligasht.reservation.views.activities.new_survey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerModel {


    private Integer iD;
     private String value;

    public AnswerModel(Integer iD, String value) {
        this.iD = iD;
        this.value = value;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
