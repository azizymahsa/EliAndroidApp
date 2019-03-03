
package com.eligasht.service.model.newModel.hotel.search.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHotelSearch {

    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("FilterHtml")
    @Expose
    private Object filterHtml;
    @SerializedName("ResultHtml")
    @Expose
    private Object resultHtml;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("SearchedProduct")
    @Expose
    private SearchedProduct searchedProduct;
    @SerializedName("MinPrice")
    @Expose
    private Integer minPrice;
    @SerializedName("MaxPrice")
    @Expose
    private Integer maxPrice;
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
    private Object webservices;
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
    private Object flights;
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
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;

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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

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

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public SearchedProduct getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(SearchedProduct searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
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

    public Object getWebservices() {
        return webservices;
    }

    public void setWebservices(Object webservices) {
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

    public Object getFlights() {
        return flights;
    }

    public void setFlights(Object flights) {
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

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

}
