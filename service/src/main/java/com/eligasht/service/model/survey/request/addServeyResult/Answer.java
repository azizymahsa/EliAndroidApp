
package com.eligasht.service.model.survey.request.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Value")
    @Expose
    private String value;

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
