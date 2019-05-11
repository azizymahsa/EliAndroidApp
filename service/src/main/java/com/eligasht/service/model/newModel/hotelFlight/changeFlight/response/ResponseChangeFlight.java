
package com.eligasht.service.model.newModel.hotelFlight.changeFlight.response;

import java.util.List;

import com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Comments;
import com.eligasht.service.model.newModel.hotel.search.response.Error;
import com.eligasht.service.model.newModel.train.purchase.request.Warnings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseChangeFlight {

    @SerializedName("Flights")
    @Expose
    private List<Flight> flights = null;
    @SerializedName("ResultFilter")
    @Expose
    private List<ResultFilter> resultFilter = null;
    @SerializedName("Hotel")
    @Expose
    private Object hotel;
    @SerializedName("TotalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("FlightId")
    @Expose
    private String flightId;
    @SerializedName("CurrentFlight")
    @Expose
    private CurrentFlight currentFlight;
    @SerializedName("OldFlightAmount")
    @Expose
    private Integer oldFlightAmount;
    @SerializedName("NewFlightAmount")
    @Expose
    private Integer newFlightAmount;
    @SerializedName("UniqId")
    @Expose
    private String uniqId;
    @SerializedName("ChangeFlightUniqId")
    @Expose
    private Object changeFlightUniqId;
    @SerializedName("PreviousFlight")
    @Expose
    private Object previousFlight;
    @SerializedName("HotelFlightsAmount")
    @Expose
    private Integer hotelFlightsAmount;
    @SerializedName("currentAmount")
    @Expose
    private Object currentAmount;
    @SerializedName("roomPrice")
    @Expose
    private Integer roomPrice;


    @SerializedName("Errors")
    @Expose
    private List<Error> Errors;

    @SerializedName("Warningss")
    @Expose
    private  List<Warnings> Warningss;

    @SerializedName("Comments")
    @Expose
    private  List<Comments> Comments;

    public List<Error> getErrors() {
        return Errors;
    }

    public void setErrors(List<Error> errors) {
        Errors = errors;
    }

    public List<Warnings> getWarningss() {
        return Warningss;
    }

    public void setWarningss(List<Warnings> warningss) {
        Warningss = warningss;
    }

    public List<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Comments> getComments() {
        return Comments;
    }

    public void setComments(List<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Comments> comments) {
        Comments = comments;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<ResultFilter> getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(List<ResultFilter> resultFilter) {
        this.resultFilter = resultFilter;
    }

    public Object getHotel() {
        return hotel;
    }

    public void setHotel(Object hotel) {
        this.hotel = hotel;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public CurrentFlight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(CurrentFlight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public Integer getOldFlightAmount() {
        return oldFlightAmount;
    }

    public void setOldFlightAmount(Integer oldFlightAmount) {
        this.oldFlightAmount = oldFlightAmount;
    }

    public Integer getNewFlightAmount() {
        return newFlightAmount;
    }

    public void setNewFlightAmount(Integer newFlightAmount) {
        this.newFlightAmount = newFlightAmount;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public Object getChangeFlightUniqId() {
        return changeFlightUniqId;
    }

    public void setChangeFlightUniqId(Object changeFlightUniqId) {
        this.changeFlightUniqId = changeFlightUniqId;
    }

    public Object getPreviousFlight() {
        return previousFlight;
    }

    public void setPreviousFlight(Object previousFlight) {
        this.previousFlight = previousFlight;
    }

    public Integer getHotelFlightsAmount() {
        return hotelFlightsAmount;
    }

    public void setHotelFlightsAmount(Integer hotelFlightsAmount) {
        this.hotelFlightsAmount = hotelFlightsAmount;
    }

    public Object getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Object currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

}
