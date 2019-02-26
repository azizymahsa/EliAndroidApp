
package com.eligasht.service.model.newModel.hotel.search.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedProduct {

    @SerializedName("TripType")
    @Expose
    private Integer tripType;
    @SerializedName("Rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("Passengers")
    @Expose
    private String passengers;
    @SerializedName("Adultscount")
    @Expose
    private Integer adultscount;
    @SerializedName("Childscount")
    @Expose
    private Integer childscount;
    @SerializedName("Infantscount")
    @Expose
    private Integer infantscount;
    @SerializedName("SourceText")
    @Expose
    private String sourceText;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("DestinationText")
    @Expose
    private String destinationText;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("Checkin")
    @Expose
    private String checkin;
    @SerializedName("Checkout")
    @Expose
    private String checkout;
    @SerializedName("FlightFromDate")
    @Expose
    private String flightFromDate;
    @SerializedName("FlightToDate")
    @Expose
    private String flightToDate;
    @SerializedName("NationalityCode")
    @Expose
    private String nationalityCode;
    @SerializedName("ResidenceCode")
    @Expose
    private String residenceCode;
    @SerializedName("Culture")
    @Expose
    private String culture;

    public Integer getTripType() {
        return tripType;
    }

    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public Integer getAdultscount() {
        return adultscount;
    }

    public void setAdultscount(Integer adultscount) {
        this.adultscount = adultscount;
    }

    public Integer getChildscount() {
        return childscount;
    }

    public void setChildscount(Integer childscount) {
        this.childscount = childscount;
    }

    public Integer getInfantscount() {
        return infantscount;
    }

    public void setInfantscount(Integer infantscount) {
        this.infantscount = infantscount;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestinationText() {
        return destinationText;
    }

    public void setDestinationText(String destinationText) {
        this.destinationText = destinationText;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getFlightFromDate() {
        return flightFromDate;
    }

    public void setFlightFromDate(String flightFromDate) {
        this.flightFromDate = flightFromDate;
    }

    public String getFlightToDate() {
        return flightToDate;
    }

    public void setFlightToDate(String flightToDate) {
        this.flightToDate = flightToDate;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getResidenceCode() {
        return residenceCode;
    }

    public void setResidenceCode(String residenceCode) {
        this.residenceCode = residenceCode;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
