
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Error {

    @SerializedName("Code")
    @Expose
    private int code;
    @SerializedName("DetailedMessage")
    @Expose
    private String detailedMessage;
    @SerializedName("Language")
    @Expose
    private Object language;
    @SerializedName("Message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
        this.language = language;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("detailedMessage", detailedMessage).append("language", language).append("message", message).toString();
    }

}
