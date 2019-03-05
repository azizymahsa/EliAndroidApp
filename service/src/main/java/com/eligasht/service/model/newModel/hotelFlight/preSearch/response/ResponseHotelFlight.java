
package com.eligasht.service.model.newModel.hotelFlight.preSearch.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHotelFlight {

    @SerializedName("FilterHtml")
    @Expose
    private Object filterHtml;
    @SerializedName("ResultHtml")
    @Expose
    private Object resultHtml;
    @SerializedName("SearchKey")
    @Expose
    private Object searchKey;
    @SerializedName("SearchedProduct")
    @Expose
    private SearchedProduct searchedProduct;
    @SerializedName("MinPrice")
    @Expose
    private double minPrice;
    @SerializedName("MaxPrice")
    @Expose
    private double maxPrice;
    @SerializedName("Hotels")
    @Expose
    private List<Hotel> hotels = null;
    @SerializedName("Counts")
    @Expose
    private Integer counts;
    @SerializedName("Locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("Facilities")
    @Expose
    private List<Facility> facilities = null;
    @SerializedName("HotelTypes")
    @Expose
    private List<HotelType> hotelTypes = null;
    @SerializedName("Stars")
    @Expose
    private List<Star> stars = null;
    @SerializedName("Webservices")
    @Expose
    private List<Object> webservices = null;
    @SerializedName("DestinationCity")
    @Expose
    private DestinationCity destinationCity;
    @SerializedName("NightCounts")
    @Expose
    private Integer nightCounts;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("Flights")
    @Expose
    private Flights flights;
    @SerializedName("FlightsID")
    @Expose
    private String flightsID;
    @SerializedName("QueryString")
    @Expose
    private Object queryString;
    @SerializedName("BookPercent")
    @Expose
    private Integer bookPercent;
    @SerializedName("IsDomestic")
    @Expose
    private Boolean isDomestic;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public Object getFilterHtml() {
        return filterHtml;
    }

    public void setFilterHtml(Object filterHtml) {
        this.filterHtml = filterHtml;
    }

    public Object getResultHtml() {
        return resultHtml;
    }

    public void setResultHtml(Object resultHtml) {
        this.resultHtml = resultHtml;
    }

    public Object getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Object searchKey) {
        this.searchKey = searchKey;
    }

    public SearchedProduct getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(SearchedProduct searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<HotelType> getHotelTypes() {
        return hotelTypes;
    }

    public void setHotelTypes(List<HotelType> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Object> getWebservices() {
        return webservices;
    }

    public void setWebservices(List<Object> webservices) {
        this.webservices = webservices;
    }

    public DestinationCity getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(DestinationCity destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Integer getNightCounts() {
        return nightCounts;
    }

    public void setNightCounts(Integer nightCounts) {
        this.nightCounts = nightCounts;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Flights getFlights() {
        return flights;
    }

    public void setFlights(com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Flights flights) {
        this.flights = flights;
    }

    public String getFlightsID() {
        return flightsID;
    }

    public void setFlightsID(String flightsID) {
        this.flightsID = flightsID;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public Integer getBookPercent() {
        return bookPercent;
    }

    public void setBookPercent(Integer bookPercent) {
        this.bookPercent = bookPercent;
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

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
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
