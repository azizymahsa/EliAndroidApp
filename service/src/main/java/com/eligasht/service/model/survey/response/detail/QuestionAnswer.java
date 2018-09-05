
package com.eligasht.service.model.survey.response.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionAnswer {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Priority")
    @Expose
    private Integer priority;
    @SerializedName("QAID")
    @Expose
    private Object qAID;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Value")
    @Expose
    private String value;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Object getQAID() {
        return qAID;
    }

    public void setQAID(Object qAID) {
        this.qAID = qAID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
