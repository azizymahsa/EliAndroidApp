
package com.eligasht.reservation.models.model.pack.response.responseSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstProwHotel {

    @SerializedName("Index")
    @Expose
    private Integer index;
    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;
    @SerializedName("Hotel_ID")
    @Expose
    private Integer hotelID;
    @SerializedName("Hotel_NameE")
    @Expose
    private String hotelNameE;
    @SerializedName("Hotel_NameF")
    @Expose
    private String hotelNameF;
    @SerializedName("Hotel_StarRating")
    @Expose
    private String hotelStarRating;
    @SerializedName("Hotel_LastBooking")
    @Expose
    private String hotelLastBooking;
    @SerializedName("Hotel_LastBookingShamsi")
    @Expose
    private String hotelLastBookingShamsi;
    @SerializedName("Hotel_Address")
    @Expose
    private String hotelAddress;
    @SerializedName("Hotel_CityID")
    @Expose
    private Integer hotelCityID;
    @SerializedName("City_EnglishName")
    @Expose
    private String cityEnglishName;
    @SerializedName("City_PersianName")
    @Expose
    private String cityPersianName;
    @SerializedName("Location_FullNameEn")
    @Expose
    private String locationFullNameEn;
    @SerializedName("Location_FullNameFa")
    @Expose
    private String locationFullNameFa;
    @SerializedName("Location_ID")
    @Expose
    private Integer locationID;
    @SerializedName("Hotel_MapLat")
    @Expose
    private String hotelMapLat;
    @SerializedName("Hotel_MapLng")
    @Expose
    private String hotelMapLng;
    @SerializedName("Hotel_Website")
    @Expose
    private String hotelWebsite;
    @SerializedName("Hotel_ImgPath")
    @Expose
    private String hotelImgPath;
    @SerializedName("Hotel_TaRating")
    @Expose
    private Integer hotelTaRating;
    @SerializedName("Hotel_SiteScore")
    @Expose
    private Integer hotelSiteScore;
    @SerializedName("HotelTheme_NameE")
    @Expose
    private String hotelThemeNameE;
    @SerializedName("HotelTheme_NameF")
    @Expose
    private String hotelThemeNameF;
    @SerializedName("HType_NameE")
    @Expose
    private String hTypeNameE;
    @SerializedName("HType_NameF")
    @Expose
    private String hTypeNameF;
    @SerializedName("CheckIn")
    @Expose
    private String checkIn;
    @SerializedName("CheckOut")
    @Expose
    private String checkOut;
    @SerializedName("CheckInShamsi")
    @Expose
    private String checkInShamsi;
    @SerializedName("CheckOutShamsi")
    @Expose
    private String checkOutShamsi;
    @SerializedName("WeatherStatusCode")
    @Expose
    private String weatherStatusCode;
    @SerializedName("WeatherStatusText")
    @Expose
    private String weatherStatusText;
    @SerializedName("WeatherTemp")
    @Expose
    private Integer weatherTemp;
    @SerializedName("CommPercent")
    @Expose
    private Integer commPercent;
    @SerializedName("PlusCommPercent")
    @Expose
    private Integer plusCommPercent;
    @SerializedName("HotelDetailURL")
    @Expose
    private String hotelDetailURL;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPackID() {
        return packID;
    }

    public void setPackID(Integer packID) {
        this.packID = packID;
    }

    public Integer getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(Integer packRowID) {
        this.packRowID = packRowID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelNameE() {
        return hotelNameE;
    }

    public void setHotelNameE(String hotelNameE) {
        this.hotelNameE = hotelNameE;
    }

    public String getHotelNameF() {
        return hotelNameF;
    }

    public void setHotelNameF(String hotelNameF) {
        this.hotelNameF = hotelNameF;
    }

    public String getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(String hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }

    public String getHotelLastBooking() {
        return hotelLastBooking;
    }

    public void setHotelLastBooking(String hotelLastBooking) {
        this.hotelLastBooking = hotelLastBooking;
    }

    public String getHotelLastBookingShamsi() {
        return hotelLastBookingShamsi;
    }

    public void setHotelLastBookingShamsi(String hotelLastBookingShamsi) {
        this.hotelLastBookingShamsi = hotelLastBookingShamsi;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public Integer getHotelCityID() {
        return hotelCityID;
    }

    public void setHotelCityID(Integer hotelCityID) {
        this.hotelCityID = hotelCityID;
    }

    public String getCityEnglishName() {
        return cityEnglishName;
    }

    public void setCityEnglishName(String cityEnglishName) {
        this.cityEnglishName = cityEnglishName;
    }

    public String getCityPersianName() {
        return cityPersianName;
    }

    public void setCityPersianName(String cityPersianName) {
        this.cityPersianName = cityPersianName;
    }

    public String getLocationFullNameEn() {
        return locationFullNameEn;
    }

    public void setLocationFullNameEn(String locationFullNameEn) {
        this.locationFullNameEn = locationFullNameEn;
    }

    public String getLocationFullNameFa() {
        return locationFullNameFa;
    }

    public void setLocationFullNameFa(String locationFullNameFa) {
        this.locationFullNameFa = locationFullNameFa;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public String getHotelMapLat() {
        return hotelMapLat;
    }

    public void setHotelMapLat(String hotelMapLat) {
        this.hotelMapLat = hotelMapLat;
    }

    public String getHotelMapLng() {
        return hotelMapLng;
    }

    public void setHotelMapLng(String hotelMapLng) {
        this.hotelMapLng = hotelMapLng;
    }

    public String getHotelWebsite() {
        return hotelWebsite;
    }

    public void setHotelWebsite(String hotelWebsite) {
        this.hotelWebsite = hotelWebsite;
    }

    public String getHotelImgPath() {
        return hotelImgPath;
    }

    public void setHotelImgPath(String hotelImgPath) {
        this.hotelImgPath = hotelImgPath;
    }

    public Integer getHotelTaRating() {
        return hotelTaRating;
    }

    public void setHotelTaRating(Integer hotelTaRating) {
        this.hotelTaRating = hotelTaRating;
    }

    public Integer getHotelSiteScore() {
        return hotelSiteScore;
    }

    public void setHotelSiteScore(Integer hotelSiteScore) {
        this.hotelSiteScore = hotelSiteScore;
    }

    public String getHotelThemeNameE() {
        return hotelThemeNameE;
    }

    public void setHotelThemeNameE(String hotelThemeNameE) {
        this.hotelThemeNameE = hotelThemeNameE;
    }

    public String getHotelThemeNameF() {
        return hotelThemeNameF;
    }

    public void setHotelThemeNameF(String hotelThemeNameF) {
        this.hotelThemeNameF = hotelThemeNameF;
    }

    public String getHTypeNameE() {
        return hTypeNameE;
    }

    public void setHTypeNameE(String hTypeNameE) {
        this.hTypeNameE = hTypeNameE;
    }

    public String getHTypeNameF() {
        return hTypeNameF;
    }

    public void setHTypeNameF(String hTypeNameF) {
        this.hTypeNameF = hTypeNameF;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCheckInShamsi() {
        return checkInShamsi;
    }

    public void setCheckInShamsi(String checkInShamsi) {
        this.checkInShamsi = checkInShamsi;
    }

    public String getCheckOutShamsi() {
        return checkOutShamsi;
    }

    public void setCheckOutShamsi(String checkOutShamsi) {
        this.checkOutShamsi = checkOutShamsi;
    }

    public String getWeatherStatusCode() {
        return weatherStatusCode;
    }

    public void setWeatherStatusCode(String weatherStatusCode) {
        this.weatherStatusCode = weatherStatusCode;
    }

    public String getWeatherStatusText() {
        return weatherStatusText;
    }

    public void setWeatherStatusText(String weatherStatusText) {
        this.weatherStatusText = weatherStatusText;
    }

    public Integer getWeatherTemp() {
        return weatherTemp;
    }

    public void setWeatherTemp(Integer weatherTemp) {
        this.weatherTemp = weatherTemp;
    }

    public Integer getCommPercent() {
        return commPercent;
    }

    public void setCommPercent(Integer commPercent) {
        this.commPercent = commPercent;
    }

    public Integer getPlusCommPercent() {
        return plusCommPercent;
    }

    public void setPlusCommPercent(Integer plusCommPercent) {
        this.plusCommPercent = plusCommPercent;
    }

    public String getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(String hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

}
