
package com.eligasht.service.model.hotel.hotelAvail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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
    private String checkInPolicy;
    @SerializedName("CheckOutPolicy")
    @Expose
    private String checkOutPolicy;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("CityNameEn")
    @Expose
    private String cityNameEn;
    @SerializedName("CityNameFa")
    @Expose
    private String cityNameFa;
    @SerializedName("CountryNameEn")
    @Expose
    private String countryNameEn;
    @SerializedName("Description")
    @Expose
    private String description;
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
    private String hotelNameEn;
    @SerializedName("HotelNameFa")
    @Expose
    private String hotelNameFa;
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
    private String languageSpoken;
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
    private String zipCode;

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

    public String getCheckInPolicy() {
        return checkInPolicy;
    }

    public void setCheckInPolicy(String checkInPolicy) {
        this.checkInPolicy = checkInPolicy;
    }

    public String getCheckOutPolicy() {
        return checkOutPolicy;
    }

    public void setCheckOutPolicy(String checkOutPolicy) {
        this.checkOutPolicy = checkOutPolicy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCityNameFa() {
        return cityNameFa;
    }

    public void setCityNameFa(String cityNameFa) {
        this.cityNameFa = cityNameFa;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getHotelNameFa() {
        return hotelNameFa;
    }

    public void setHotelNameFa(String hotelNameFa) {
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

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
