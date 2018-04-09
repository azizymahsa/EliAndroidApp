
package com.eligasht.service.model.hotelflight;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSearchResult {

    @SerializedName("CityImage")
    @Expose
    private String cityImage;
    @SerializedName("Counts")
    @Expose
    private Integer counts;
    @SerializedName("Facilities")
    @Expose
    private List<Facility> facilities = null;
    @SerializedName("Flights")
    @Expose
    private Flights flights;
    @SerializedName("FlightsID")
    @Expose
    private String flightsID;
    @SerializedName("HotelTypes")
    @Expose
    private List<HotelType> hotelTypes = null;
    @SerializedName("Hotels")
    @Expose
    private List<Hotel> hotels = null;
    @SerializedName("LoadDB")
    @Expose
    private Boolean loadDB;
    @SerializedName("Locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("MaxPrice")
    @Expose
    private Integer maxPrice;
    @SerializedName("MinPrice")
    @Expose
    private Integer minPrice;
    @SerializedName("NightCounts")
    @Expose
    private Integer nightCounts;
    @SerializedName("Stars")
    @Expose
    private List<Star> stars = null;
    @SerializedName("Webservices")
    @Expose
    private List<Webservice> webservices = null;

    public String getCityImage() {
        return cityImage;
    }

    public void setCityImage(String cityImage) {
        this.cityImage = cityImage;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public Flights getFlights() {
        return flights;
    }

    public void setFlights(Flights flights) {
        this.flights = flights;
    }

    public String getFlightsID() {
        return flightsID;
    }

    public void setFlightsID(String flightsID) {
        this.flightsID = flightsID;
    }

    public List<HotelType> getHotelTypes() {
        return hotelTypes;
    }

    public void setHotelTypes(List<HotelType> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Boolean getLoadDB() {
        return loadDB;
    }

    public void setLoadDB(Boolean loadDB) {
        this.loadDB = loadDB;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getNightCounts() {
        return nightCounts;
    }

    public void setNightCounts(Integer nightCounts) {
        this.nightCounts = nightCounts;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Webservice> getWebservices() {
        return webservices;
    }

    public void setWebservices(List<Webservice> webservices) {
        this.webservices = webservices;
    }

}
