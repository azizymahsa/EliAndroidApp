
package com.eligasht.service.model.newModel.hotel.search.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelNameEn")
    @Expose
    private Object hotelNameEn;
    @SerializedName("HotelNameFa")
    @Expose
    private String hotelNameFa;
    @SerializedName("CountryNameEn")
    @Expose
    private String countryNameEn;
    @SerializedName("CityNameFa")
    @Expose
    private Object cityNameFa;
    @SerializedName("CityNameEn")
    @Expose
    private String cityNameEn;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("CheckInPolicy")
    @Expose
    private String checkInPolicy;
    @SerializedName("CheckOutPolicy")
    @Expose
    private String checkOutPolicy;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("StarRating")
    @Expose
    private Integer starRating;
    @SerializedName("TARating")
    @Expose
    private Integer tARating;
    @SerializedName("TARatingReviews")
    @Expose
    private Integer tARatingReviews;
    @SerializedName("IsTop")
    @Expose
    private Boolean isTop;
    @SerializedName("BestSell")
    @Expose
    private Boolean bestSell;
    @SerializedName("IsNew")
    @Expose
    private Boolean isNew;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("ZipCode")
    @Expose
    private String zipCode;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("EType")
    @Expose
    private String eType;
    @SerializedName("TypeText")
    @Expose
    private String typeText;
    @SerializedName("Theme")
    @Expose
    private Integer theme;
    @SerializedName("ETheme")
    @Expose
    private String eTheme;
    @SerializedName("ThemeText")
    @Expose
    private String themeText;
    @SerializedName("Score")
    @Expose
    private Integer score;
    @SerializedName("ScoreReviews")
    @Expose
    private Integer scoreReviews;
    @SerializedName("ScoreText")
    @Expose
    private String scoreText;
    @SerializedName("LanguageSpoken")
    @Expose
    private String languageSpoken;
    @SerializedName("MainImage")
    @Expose
    private String mainImage;
    @SerializedName("MapLat")
    @Expose
    private Double mapLat;
    @SerializedName("MapLng")
    @Expose
    private Double mapLng;
    @SerializedName("MapZoom")
    @Expose
    private Integer mapZoom;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("WSIDs")
    @Expose
    private WSIDs wSIDs;
    @SerializedName("LastBooking")
    @Expose
    private String lastBooking;
    @SerializedName("LastBookingString")
    @Expose
    private String lastBookingString;
    @SerializedName("Images")
    @Expose
    private List<Object> images = null;
    @SerializedName("Facilities")
    @Expose
    private List<Facility> facilities = null;
    @SerializedName("HWSAcc_ID")
    @Expose
    private Integer hWSAccID;
    @SerializedName("ProfitPercent")
    @Expose
    private Integer profitPercent;
    @SerializedName("FixedProfit")
    @Expose
    private Integer fixedProfit;
    @SerializedName("ProfitCurrencyID")
    @Expose
    private Integer profitCurrencyID;
    @SerializedName("Availability")
    @Expose
    private Availability availability;
    @SerializedName("HotelDetailURL")
    @Expose
    private String hotelDetailURL;
    @SerializedName("QueryString")
    @Expose
    private Object queryString;
    @SerializedName("Errors")
    @Expose
    private Object errors;
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

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Object getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(Object hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getHotelNameFa() {
        return hotelNameFa;
    }

    public void setHotelNameFa(String hotelNameFa) {
        this.hotelNameFa = hotelNameFa;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public Object getCityNameFa() {
        return cityNameFa;
    }

    public void setCityNameFa(Object cityNameFa) {
        this.cityNameFa = cityNameFa;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Integer getTARating() {
        return tARating;
    }

    public void setTARating(Integer tARating) {
        this.tARating = tARating;
    }

    public Integer getTARatingReviews() {
        return tARatingReviews;
    }

    public void setTARatingReviews(Integer tARatingReviews) {
        this.tARatingReviews = tARatingReviews;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Boolean getBestSell() {
        return bestSell;
    }

    public void setBestSell(Boolean bestSell) {
        this.bestSell = bestSell;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEType() {
        return eType;
    }

    public void setEType(String eType) {
        this.eType = eType;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    public String getETheme() {
        return eTheme;
    }

    public void setETheme(String eTheme) {
        this.eTheme = eTheme;
    }

    public String getThemeText() {
        return themeText;
    }

    public void setThemeText(String themeText) {
        this.themeText = themeText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScoreReviews() {
        return scoreReviews;
    }

    public void setScoreReviews(Integer scoreReviews) {
        this.scoreReviews = scoreReviews;
    }

    public String getScoreText() {
        return scoreText;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Double getMapLat() {
        return mapLat;
    }

    public void setMapLat(Double mapLat) {
        this.mapLat = mapLat;
    }

    public Double getMapLng() {
        return mapLng;
    }

    public void setMapLng(Double mapLng) {
        this.mapLng = mapLng;
    }

    public Integer getMapZoom() {
        return mapZoom;
    }

    public void setMapZoom(Integer mapZoom) {
        this.mapZoom = mapZoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WSIDs getWSIDs() {
        return wSIDs;
    }

    public void setWSIDs(WSIDs wSIDs) {
        this.wSIDs = wSIDs;
    }

    public String getLastBooking() {
        return lastBooking;
    }

    public void setLastBooking(String lastBooking) {
        this.lastBooking = lastBooking;
    }

    public String getLastBookingString() {
        return lastBookingString;
    }

    public void setLastBookingString(String lastBookingString) {
        this.lastBookingString = lastBookingString;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public Integer getHWSAccID() {
        return hWSAccID;
    }

    public void setHWSAccID(Integer hWSAccID) {
        this.hWSAccID = hWSAccID;
    }

    public Integer getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(Integer profitPercent) {
        this.profitPercent = profitPercent;
    }

    public Integer getFixedProfit() {
        return fixedProfit;
    }

    public void setFixedProfit(Integer fixedProfit) {
        this.fixedProfit = fixedProfit;
    }

    public Integer getProfitCurrencyID() {
        return profitCurrencyID;
    }

    public void setProfitCurrencyID(Integer profitCurrencyID) {
        this.profitCurrencyID = profitCurrencyID;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(String hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
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
