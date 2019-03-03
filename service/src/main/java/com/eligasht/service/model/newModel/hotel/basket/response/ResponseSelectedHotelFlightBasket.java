
package com.eligasht.service.model.newModel.hotel.basket.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSelectedHotelFlightBasket {

    @SerializedName("HotelOfferId")
    @Expose
    private Object hotelOfferId;
    @SerializedName("FlightOfferId")
    @Expose
    private Object flightOfferId;
    @SerializedName("Residence")
    @Expose
    private Object residence;
    @SerializedName("Nationality")
    @Expose
    private Object nationality;
    @SerializedName("Checkin")
    @Expose
    private String checkin;
    @SerializedName("Checkout")
    @Expose
    private String checkout;
    @SerializedName("RoomLists")
    @Expose
    private Object roomLists;
    @SerializedName("HotelOffer")
    @Expose
    private Object hotelOffer;
    @SerializedName("Hotel")
    @Expose
    private Object hotel;
    @SerializedName("HotelOfferRoomList")
    @Expose
    private Object hotelOfferRoomList;
    @SerializedName("SelectedFlight")
    @Expose
    private Object selectedFlight;
    @SerializedName("SelectedEId")
    @Expose
    private Object selectedEId;
    @SerializedName("SelectedFlightGuid")
    @Expose
    private Object selectedFlightGuid;
    @SerializedName("IsDomestic")
    @Expose
    private Boolean isDomestic;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
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

    public Object getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(Object hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }

    public Object getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(Object flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public Object getResidence() {
        return residence;
    }

    public void setResidence(Object residence) {
        this.residence = residence;
    }

    public Object getNationality() {
        return nationality;
    }

    public void setNationality(Object nationality) {
        this.nationality = nationality;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Object getRoomLists() {
        return roomLists;
    }

    public void setRoomLists(Object roomLists) {
        this.roomLists = roomLists;
    }

    public Object getHotelOffer() {
        return hotelOffer;
    }

    public void setHotelOffer(Object hotelOffer) {
        this.hotelOffer = hotelOffer;
    }

    public Object getHotel() {
        return hotel;
    }

    public void setHotel(Object hotel) {
        this.hotel = hotel;
    }

    public Object getHotelOfferRoomList() {
        return hotelOfferRoomList;
    }

    public void setHotelOfferRoomList(Object hotelOfferRoomList) {
        this.hotelOfferRoomList = hotelOfferRoomList;
    }

    public Object getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Object selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public Object getSelectedEId() {
        return selectedEId;
    }

    public void setSelectedEId(Object selectedEId) {
        this.selectedEId = selectedEId;
    }

    public Object getSelectedFlightGuid() {
        return selectedFlightGuid;
    }

    public void setSelectedFlightGuid(Object selectedFlightGuid) {
        this.selectedFlightGuid = selectedFlightGuid;
    }

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
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
