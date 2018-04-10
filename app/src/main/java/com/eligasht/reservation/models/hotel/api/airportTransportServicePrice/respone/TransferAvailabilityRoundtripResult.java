
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TransferAvailabilityRoundtripResult {

    @SerializedName("AccountID")
    @Expose
    private Integer accountID;
    @SerializedName("AirportToHotel")
    @Expose
    private AirportToHotel airportToHotel;
    @SerializedName("HotelToAirport")
    @Expose
    private HotelToAirport hotelToAirport;
    @SerializedName("OfferCode")
    @Expose
    private String offerCode;
    @SerializedName("SupplierID")
    @Expose
    private String supplierID;
    @SerializedName("TitleEn")
    @Expose
    private String titleEn;
    @SerializedName("TitleFa")
    @Expose
    private String titleFa;
    @SerializedName("TotalPrice")
    @Expose
    private TotalPrice totalPrice;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public AirportToHotel getAirportToHotel() {
        return airportToHotel;
    }

    public void setAirportToHotel(AirportToHotel airportToHotel) {
        this.airportToHotel = airportToHotel;
    }

    public HotelToAirport getHotelToAirport() {
        return hotelToAirport;
    }

    public void setHotelToAirport(HotelToAirport hotelToAirport) {
        this.hotelToAirport = hotelToAirport;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }



}
