
package com.eligasht.service.model.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Error {



    @SerializedName("Code")
    @Expose
    private int code;
    @SerializedName("DetailedMessage")
    @Expose
    private String detailedMessage;
    @SerializedName("Language")
    @Expose
    private String language;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
