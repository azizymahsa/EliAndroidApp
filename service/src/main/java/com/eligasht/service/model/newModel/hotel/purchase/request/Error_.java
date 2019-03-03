
package com.eligasht.service.model.newModel.hotel.purchase.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error_ {

    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("DetailedMessage")
    @Expose
    private String detailedMessage;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Severity")
    @Expose
    private Integer severity;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

}
