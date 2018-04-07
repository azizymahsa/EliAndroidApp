package com.eligasht.service.model.flight.response.DomesticFlight;

/**
 * Created by Mahsa.azizi on 4/4/2018.
 */


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("Code")
    @Expose
    private Integer code;
    @SerializedName("DetailedMessage")
    @Expose
    private String detailedMessage;
    @SerializedName("Language")
    @Expose
    private Object language;
    @SerializedName("Message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

}