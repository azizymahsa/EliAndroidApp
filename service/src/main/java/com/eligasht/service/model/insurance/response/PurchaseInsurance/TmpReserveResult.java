
package com.eligasht.service.model.insurance.response.PurchaseInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TmpReserveResult {

    @SerializedName("BookingCode")
    @Expose
    private Integer bookingCode;
    @SerializedName("BookingMessageEn")
    @Expose
    private String bookingMessageEn;
    @SerializedName("BookingMessageFa")
    @Expose
    private String bookingMessageFa;

    public Integer getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(Integer bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getBookingMessageEn() {
        return bookingMessageEn;
    }

    public void setBookingMessageEn(String bookingMessageEn) {
        this.bookingMessageEn = bookingMessageEn;
    }

    public String getBookingMessageFa() {
        return bookingMessageFa;
    }

    public void setBookingMessageFa(String bookingMessageFa) {
        this.bookingMessageFa = bookingMessageFa;
    }

}
