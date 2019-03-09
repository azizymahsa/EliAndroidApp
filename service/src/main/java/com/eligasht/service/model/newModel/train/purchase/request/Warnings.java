
package com.eligasht.service.model.newModel.train.purchase.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warnings {

    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("ShortText")
    @Expose
    private String shortText;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("RecordID")
    @Expose
    private String recordID;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

}
