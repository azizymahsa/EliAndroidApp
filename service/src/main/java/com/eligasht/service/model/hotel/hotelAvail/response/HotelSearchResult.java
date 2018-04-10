
package com.eligasht.service.model.hotel.hotelAvail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotelSearchResult {

    @SerializedName("CityImage")
    @Expose
    private String cityImage;
    @SerializedName("Counts")
    @Expose
    private int counts;
    @SerializedName("Facilities")
    @Expose
    private List<Facility> facilities = null;
    @SerializedName("Flights")
    @Expose
    private Object flights;
    @SerializedName("FlightsID")
    @Expose
    private Object flightsID;
    @SerializedName("HotelTypes")
    @Expose
    private List<HotelType> hotelTypes = null;
    @SerializedName("Hotels")
    @Expose
    private List<Hotel> hotels = null;
    @SerializedName("LoadDB")
    @Expose
    private boolean loadDB;
    @SerializedName("Locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("MaxPrice")
    @Expose
    private int maxPrice;
    @SerializedName("MinPrice")
    @Expose
    private int minPrice;
    @SerializedName("NightCounts")
    @Expose
    private int nightCounts;
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

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public Object getFlights() {
        return flights;
    }

    public void setFlights(Object flights) {
        this.flights = flights;
    }

    public Object getFlightsID() {
        return flightsID;
    }

    public void setFlightsID(Object flightsID) {
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

    public boolean isLoadDB() {
        return loadDB;
    }

    public void setLoadDB(boolean loadDB) {
        this.loadDB = loadDB;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getNightCounts() {
        return nightCounts;
    }

    public void setNightCounts(int nightCounts) {
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

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
