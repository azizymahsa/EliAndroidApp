
package com.eligasht.service.model.hotelflight.response;

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
    private Boolean bestSell;
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
    private Integer fixedProfit;
    @SerializedName("HWSAcc_ID")
    @Expose
    private Integer hWSAccID;
    @SerializedName("HotelDetailURL")
    @Expose
    private String hotelDetailURL;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelNameEn")
    @Expose
    private String hotelNameEn;
    @SerializedName("HotelNameFa")
    @Expose
    private String hotelNameFa;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Images")
    @Expose
    private List<Object> images = null;
    @SerializedName("IsNew")
    @Expose
    private Boolean isNew;
    @SerializedName("IsTop")
    @Expose
    private Boolean isTop;
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
    private Double mapLat;
    @SerializedName("MapLng")
    @Expose
    private Double mapLng;
    @SerializedName("MapZoom")
    @Expose
    private Integer mapZoom;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProfitCurrencyID")
    @Expose
    private Integer profitCurrencyID;
    @SerializedName("ProfitPercent")
    @Expose
    private Integer profitPercent;
    @SerializedName("Score")
    @Expose
    private Integer score;
    @SerializedName("ScoreReviews")
    @Expose
    private Integer scoreReviews;
    @SerializedName("ScoreText")
    @Expose
    private String scoreText;
    @SerializedName("StarRating")
    @Expose
    private Integer starRating;
    @SerializedName("TARating")
    @Expose
    private Integer tARating;
    @SerializedName("TARatingReviews")
    @Expose
    private Integer tARatingReviews;
    @SerializedName("Theme")
    @Expose
    private Integer theme;
    @SerializedName("ThemeText")
    @Expose
    private String themeText;
    @SerializedName("Type")
    @Expose
    private Integer type;
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

    public Boolean getBestSell() {
        return bestSell;
    }

    public void setBestSell(Boolean bestSell) {
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

    public Integer getFixedProfit() {
        return fixedProfit;
    }

    public void setFixedProfit(Integer fixedProfit) {
        this.fixedProfit = fixedProfit;
    }

    public Integer getHWSAccID() {
        return hWSAccID;
    }

    public void setHWSAccID(Integer hWSAccID) {
        this.hWSAccID = hWSAccID;
    }

    public String getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(String hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfitCurrencyID() {
        return profitCurrencyID;
    }

    public void setProfitCurrencyID(Integer profitCurrencyID) {
        this.profitCurrencyID = profitCurrencyID;
    }

    public Integer getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(Integer profitPercent) {
        this.profitPercent = profitPercent;
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

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    public String getThemeText() {
        return themeText;
    }

    public void setThemeText(String themeText) {
        this.themeText = themeText;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

}
