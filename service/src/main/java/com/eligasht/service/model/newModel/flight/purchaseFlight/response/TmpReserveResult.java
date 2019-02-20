package com.eligasht.service.model.newModel.flight.purchaseFlight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TmpReserveResult {

    @SerializedName("BookingMessageFa")
    @Expose
    private Object bookingMessageFa;
    @SerializedName("BookingMessageEn")
    @Expose
    private Object bookingMessageEn;
    @SerializedName("BookingCode")
    @Expose
    private Integer bookingCode;
    @SerializedName("Errors")
    @Expose
    private List<java.lang.Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public Object getBookingMessageFa() {
        return bookingMessageFa;
    }

    public void setBookingMessageFa(Object bookingMessageFa) {
        this.bookingMessageFa = bookingMessageFa;
    }

    public Object getBookingMessageEn() {
        return bookingMessageEn;
    }

    public void setBookingMessageEn(Object bookingMessageEn) {
        this.bookingMessageEn = bookingMessageEn;
    }

    public Integer getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(Integer bookingCode) {
        this.bookingCode = bookingCode;
    }

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public void setErrors(List<java.lang.Error> errors) {
        this.errors = errors;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
