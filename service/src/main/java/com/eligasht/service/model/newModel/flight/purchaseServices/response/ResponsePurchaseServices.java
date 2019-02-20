
package com.eligasht.service.model.newModel.flight.purchaseServices.response;

import com.eligasht.service.model.newModel.insurance.response.purchase.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseServices {

    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("Warning")
    @Expose
    private Object warning;
    @SerializedName("URL")
    @Expose
    private Object uRL;
    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("Comment")
    @Expose
    private Object comment;
    @SerializedName("ResultCode")
    @Expose
    private Integer resultCode;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("Line")
    @Expose
    private Object line;
    @SerializedName("Method")
    @Expose
    private Object method;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Object getWarning() {
        return warning;
    }

    public void setWarning(Object warning) {
        this.warning = warning;
    }

    public Object getURL() {
        return uRL;
    }

    public void setURL(Object uRL) {
        this.uRL = uRL;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getLine() {
        return line;
    }

    public void setLine(Object line) {
        this.line = line;
    }

    public Object getMethod() {
        return method;
    }

    public void setMethod(Object method) {
        this.method = method;
    }

}
