
package com.eligasht.service.model.hotel.hotelAvail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Hotel {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Availability")
    @Expose
    private Availability availability;
    @SerializedName("BestSell")
    @Expose
    private boolean bestSell;
    @SerializedName("CheckInPolicy")
    @Expose
    private Object checkInPolicy;
    @SerializedName("CheckOutPolicy")
    @Expose
    private Object checkOutPolicy;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("CityNameEn")
    @Expose
    private Object cityNameEn;
    @SerializedName("CityNameFa")
    @Expose
    private Object cityNameFa;
    @SerializedName("CountryNameEn")
    @Expose
    private Object countryNameEn;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("ETheme")
    @Expose
    private String eTheme;
    @SerializedName("EType")
    @Expose
    private String eType;
    @SerializedName("Facilities")
    @Expose
    private List<Facility_> facilities = null;
    @SerializedName("FixedProfit")
    @Expose
    private int fixedProfit;
    @SerializedName("HWSAcc_ID")
    @Expose
    private int hWSAccID;
    @SerializedName("HotelDetailURL")
    @Expose
    private String hotelDetailURL;
    @SerializedName("HotelID")
    @Expose
    private int hotelID;
    @SerializedName("HotelNameEn")
    @Expose
    private Object hotelNameEn;
    @SerializedName("HotelNameFa")
    @Expose
    private Object hotelNameFa;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Images")
    @Expose
    private List<Object> images = null;
    @SerializedName("IsNew")
    @Expose
    private boolean isNew;
    @SerializedName("IsTop")
    @Expose
    private boolean isTop;
    @SerializedName("LanguageSpoken")
    @Expose
    private Object languageSpoken;
    @SerializedName("LastBooking")
    @Expose
    private String lastBooking;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("MainImage")
    @Expose
    private String mainImage;
    @SerializedName("MapLat")
    @Expose
    private double mapLat;
    @SerializedName("MapLng")
    @Expose
    private double mapLng;
    @SerializedName("MapZoom")
    @Expose
    private int mapZoom;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProfitCurrencyID")
    @Expose
    private int profitCurrencyID;
    @SerializedName("ProfitPercent")
    @Expose
    private int profitPercent;
    @SerializedName("Score")
    @Expose
    private int score;
    @SerializedName("ScoreReviews")
    @Expose
    private int scoreReviews;
    @SerializedName("ScoreText")
    @Expose
    private String scoreText;
    @SerializedName("StarRating")
    @Expose
    private int starRating;
    @SerializedName("TARating")
    @Expose
    private int tARating;
    @SerializedName("TARatingReviews")
    @Expose
    private int tARatingReviews;
    @SerializedName("Theme")
    @Expose
    private int theme;
    @SerializedName("ThemeText")
    @Expose
    private String themeText;
    @SerializedName("Type")
    @Expose
    private int type;
    @SerializedName("TypeText")
    @Expose
    private String typeText;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("ZipCode")
    @Expose
    private Object zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public boolean isBestSell() {
        return bestSell;
    }

    public void setBestSell(boolean bestSell) {
        this.bestSell = bestSell;
    }

    public Object getCheckInPolicy() {
        return checkInPolicy;
    }

    public void setCheckInPolicy(Object checkInPolicy) {
        this.checkInPolicy = checkInPolicy;
    }

    public Object getCheckOutPolicy() {
        return checkOutPolicy;
    }

    public void setCheckOutPolicy(Object checkOutPolicy) {
        this.checkOutPolicy = checkOutPolicy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(Object cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public Object getCityNameFa() {
        return cityNameFa;
    }

    public void setCityNameFa(Object cityNameFa) {
        this.cityNameFa = cityNameFa;
    }

    public Object getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(Object countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getETheme() {
        return eTheme;
    }

    public void setETheme(String eTheme) {
        this.eTheme = eTheme;
    }

    public String getEType() {
        return eType;
    }

    public void setEType(String eType) {
        this.eType = eType;
    }

    public List<Facility_> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility_> facilities) {
        this.facilities = facilities;
    }

    public int getFixedProfit() {
        return fixedProfit;
    }

    public void setFixedProfit(int fixedProfit) {
        this.fixedProfit = fixedProfit;
    }

    public int getHWSAccID() {
        return hWSAccID;
    }

    public void setHWSAccID(int hWSAccID) {
        this.hWSAccID = hWSAccID;
    }

    public String getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(String hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public Object getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(Object hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public Object getHotelNameFa() {
        return hotelNameFa;
    }

    public void setHotelNameFa(Object hotelNameFa) {
        this.hotelNameFa = hotelNameFa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public Object getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(Object languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getLastBooking() {
        return lastBooking;
    }

    public void setLastBooking(String lastBooking) {
        this.lastBooking = lastBooking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public double getMapLat() {
        return mapLat;
    }

    public void setMapLat(double mapLat) {
        this.mapLat = mapLat;
    }

    public double getMapLng() {
        return mapLng;
    }

    public void setMapLng(double mapLng) {
        this.mapLng = mapLng;
    }

    public int getMapZoom() {
        return mapZoom;
    }

    public void setMapZoom(int mapZoom) {
        this.mapZoom = mapZoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfitCurrencyID() {
        return profitCurrencyID;
    }

    public void setProfitCurrencyID(int profitCurrencyID) {
        this.profitCurrencyID = profitCurrencyID;
    }

    public int getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(int profitPercent) {
        this.profitPercent = profitPercent;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScoreReviews() {
        return scoreReviews;
    }

    public void setScoreReviews(int scoreReviews) {
        this.scoreReviews = scoreReviews;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public int getTARating() {
        return tARating;
    }

    public void setTARating(int tARating) {
        this.tARating = tARating;
    }

    public int getTARatingReviews() {
        return tARatingReviews;
    }

    public void setTARatingReviews(int tARatingReviews) {
        this.tARatingReviews = tARatingReviews;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public String getThemeText() {
        return themeText;
    }

    public void setThemeText(String themeText) {
        this.themeText = themeText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Object getZipCode() {
        return zipCode;
    }

    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("availability", availability).append("bestSell", bestSell).append("checkInPolicy", checkInPolicy).append("checkOutPolicy", checkOutPolicy).append("city", city).append("cityNameEn", cityNameEn).append("cityNameFa", cityNameFa).append("countryNameEn", countryNameEn).append("description", description).append("eId", eId).append("eTheme", eTheme).append("eType", eType).append("facilities", facilities).append("fixedProfit", fixedProfit).append("hWSAccID", hWSAccID).append("hotelDetailURL", hotelDetailURL).append("hotelID", hotelID).append("hotelNameEn", hotelNameEn).append("hotelNameFa", hotelNameFa).append("id", id).append("images", images).append("isNew", isNew).append("isTop", isTop).append("languageSpoken", languageSpoken).append("lastBooking", lastBooking).append("location", location).append("mainImage", mainImage).append("mapLat", mapLat).append("mapLng", mapLng).append("mapZoom", mapZoom).append("name", name).append("profitCurrencyID", profitCurrencyID).append("profitPercent", profitPercent).append("score", score).append("scoreReviews", scoreReviews).append("scoreText", scoreText).append("starRating", starRating).append("tARating", tARating).append("tARatingReviews", tARatingReviews).append("theme", theme).append("themeText", themeText).append("type", type).append("typeText", typeText).append("website", website).append("zipCode", zipCode).toString();
    }

}
