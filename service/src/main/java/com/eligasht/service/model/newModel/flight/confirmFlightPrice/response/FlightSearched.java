
package com.eligasht.service.model.newModel.flight.confirmFlightPrice.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightSearched {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("QueryString")
    @Expose
    private String queryString;
    @SerializedName("FlightSearchSegments")
    @Expose
    private List<FlightSearchSegment> flightSearchSegments = null;
    @SerializedName("PreferredClass")
    @Expose
    private Integer preferredClass;
    @SerializedName("PreferredAirline")
    @Expose
    private Object preferredAirline;
    @SerializedName("NewQuery")
    @Expose
    private Object newQuery;
    @SerializedName("Passengers")
    @Expose
    private Passengers passengers;
    @SerializedName("TripType")
    @Expose
    private Integer tripType;
    @SerializedName("FlightSearchGeneratedKey")
    @Expose
    private String flightSearchGeneratedKey;
    @SerializedName("Culture")
    @Expose
    private Object culture;
    @SerializedName("Currency")
    @Expose
    private Object currency;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public List<FlightSearchSegment> getFlightSearchSegments() {
        return flightSearchSegments;
    }

    public void setFlightSearchSegments(List<FlightSearchSegment> flightSearchSegments) {
        this.flightSearchSegments = flightSearchSegments;
    }

    public Integer getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(Integer preferredClass) {
        this.preferredClass = preferredClass;
    }

    public Object getPreferredAirline() {
        return preferredAirline;
    }

    public void setPreferredAirline(Object preferredAirline) {
        this.preferredAirline = preferredAirline;
    }

    public Object getNewQuery() {
        return newQuery;
    }

    public void setNewQuery(Object newQuery) {
        this.newQuery = newQuery;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }

    public Integer getTripType() {
        return tripType;
    }

    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }

    public String getFlightSearchGeneratedKey() {
        return flightSearchGeneratedKey;
    }

    public void setFlightSearchGeneratedKey(String flightSearchGeneratedKey) {
        this.flightSearchGeneratedKey = flightSearchGeneratedKey;
    }

    public Object getCulture() {
        return culture;
    }

    public void setCulture(Object culture) {
        this.culture = culture;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

}
