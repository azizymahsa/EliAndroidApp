
package com.eligasht.service.model.newModel.flight.confirmFlightPrice.response;

import  com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSearchFlight {

    @SerializedName("Flights")
    @Expose
    private List<Flight> flights = null;
    @SerializedName("ResultUniqID")
    @Expose
    private String resultUniqID;
    @SerializedName("ResultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("SearchedProduct")
    @Expose
    private SearchedProduct searchedProduct;
    @SerializedName("IsDomestic")
    @Expose
    private Boolean isDomestic;
    @SerializedName("FlightSearched")
    @Expose
    private FlightSearched flightSearched;
    @SerializedName("ResultFilter")
    @Expose
    private List<ResultFilter> resultFilter = null;
    @SerializedName("Errors")
    @Expose
    private List<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Error> errors = null;

    public void setErrors(List<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.Error> errors) {
        this.errors = errors;
    }

    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private String resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getResultUniqID() {
        return resultUniqID;
    }

    public void setResultUniqID(String resultUniqID) {
        this.resultUniqID = resultUniqID;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public SearchedProduct getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(SearchedProduct searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    public FlightSearched getFlightSearched() {
        return flightSearched;
    }

    public void setFlightSearched(FlightSearched flightSearched) {
        this.flightSearched = flightSearched;
    }

    public List<ResultFilter> getResultFilter() {
        return resultFilter;
    }

    public void setResultFilter(List<ResultFilter> resultFilter) {
        this.resultFilter = resultFilter;
    }

    public List<Error> getErrors() {
        return errors;
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

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
