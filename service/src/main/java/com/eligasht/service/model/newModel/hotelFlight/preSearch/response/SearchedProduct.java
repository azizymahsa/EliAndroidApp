
package com.eligasht.service.model.newModel.hotelFlight.preSearch.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedProduct {

    @SerializedName("TripType")
    @Expose
    private Integer tripType;
    @SerializedName("Rooms")
    @Expose
    private List<Object> rooms = null;
    @SerializedName("Passengers")
    @Expose
    private Object passengers;
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
    private Object sourceText;
    @SerializedName("Source")
    @Expose
    private Object source;
    @SerializedName("DestinationText")
    @Expose
    private Object destinationText;
    @SerializedName("Destination")
    @Expose
    private Object destination;
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
    private Object nationalityCode;
    @SerializedName("ResidenceCode")
    @Expose
    private Object residenceCode;
    @SerializedName("Culture")
    @Expose
    private String culture;

    public Integer getTripType() {
        return tripType;
    }

    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    public List<Object> getRooms() {
        return rooms;
    }

    public void setRooms(List<Object> rooms) {
        this.rooms = rooms;
    }

    public Object getPassengers() {
        return passengers;
    }

    public void setPassengers(Object passengers) {
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

    public Object getSourceText() {
        return sourceText;
    }

    public void setSourceText(Object sourceText) {
        this.sourceText = sourceText;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getDestinationText() {
        return destinationText;
    }

    public void setDestinationText(Object destinationText) {
        this.destinationText = destinationText;
    }

    public Object getDestination() {
        return destination;
    }

    public void setDestination(Object destination) {
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

    public Object getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(Object nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public Object getResidenceCode() {
        return residenceCode;
    }

    public void setResidenceCode(Object residenceCode) {
        this.residenceCode = residenceCode;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
