
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TmpReserveResult {

    @SerializedName("BookingCode")
    @Expose
    private Integer bookingCode;
    @SerializedName("BookingMessageEn")
    @Expose
    private Object bookingMessageEn;
    @SerializedName("BookingMessageFa")
    @Expose
    private Object bookingMessageFa;

    public Integer getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(Integer bookingCode) {
        this.bookingCode = bookingCode;
    }

    public Object getBookingMessageEn() {
        return bookingMessageEn;
    }

    public void setBookingMessageEn(Object bookingMessageEn) {
        this.bookingMessageEn = bookingMessageEn;
    }

    public Object getBookingMessageFa() {
        return bookingMessageFa;
    }

    public void setBookingMessageFa(Object bookingMessageFa) {
        this.bookingMessageFa = bookingMessageFa;
    }

}
