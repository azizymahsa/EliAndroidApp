
package com.eligasht.service.model.newModel.hotel.hotelDetail.response;

import java.util.List;

import com.eligasht.service.model.newModel.insurance.response.purchase.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHotelDetails {

    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("StarRating")
    @Expose
    private String starRating;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("ImgPath")
    @Expose
    private String imgPath;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("PriceType")
    @Expose
    private String priceType;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("HType")
    @Expose
    private String hType;
    @SerializedName("LastBooking")
    @Expose
    private String lastBooking;
    @SerializedName("Construction")
    @Expose
    private String construction;
    @SerializedName("Renovation")
    @Expose
    private String renovation;
    @SerializedName("BestSeller")
    @Expose
    private Boolean bestSeller;
    @SerializedName("HotelProprties")
    @Expose
    private List<HotelProprty> hotelProprties = null;
    @SerializedName("HotelImages")
    @Expose
    private List<HotelImage> hotelImages = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsDescriptionPersian")
    @Expose
    private Boolean isDescriptionPersian;
    @SerializedName("NumberOfRooms")
    @Expose
    private Integer numberOfRooms;
    @SerializedName("NumberOfVillas")
    @Expose
    private Integer numberOfVillas;
    @SerializedName("AnnexeBuildings")
    @Expose
    private Integer annexeBuildings;
    @SerializedName("NumberOfJuniorSuites")
    @Expose
    private Integer numberOfJuniorSuites;
    @SerializedName("NumberOfSuits")
    @Expose
    private Integer numberOfSuits;
    @SerializedName("Neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("Bestdealsforyourdates")
    @Expose
    private String bestdealsforyourdates;
    @SerializedName("Announcements")
    @Expose
    private String announcements;
    @SerializedName("HotelReviews")
    @Expose
    private HotelReviews hotelReviews;
    @SerializedName("PageTitle")
    @Expose
    private String pageTitle;
    @SerializedName("PageKeywords")
    @Expose
    private String pageKeywords;
    @SerializedName("PageDescription")
    @Expose
    private String pageDescription;
    @SerializedName("PageCanonical")
    @Expose
    private String pageCanonical;
    @SerializedName("HDetailRooms")
    @Expose
    private List<Object> hDetailRooms = null;
    @SerializedName("Errors")
    @Expose
    private Error errors;
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

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getHType() {
        return hType;
    }

    public void setHType(String hType) {
        this.hType = hType;
    }

    public String getLastBooking() {
        return lastBooking;
    }

    public void setLastBooking(String lastBooking) {
        this.lastBooking = lastBooking;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    public Boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public List<HotelProprty> getHotelProprties() {
        return hotelProprties;
    }

    public void setHotelProprties(List<HotelProprty> hotelProprties) {
        this.hotelProprties = hotelProprties;
    }

    public List<HotelImage> getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(List<HotelImage> hotelImages) {
        this.hotelImages = hotelImages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDescriptionPersian() {
        return isDescriptionPersian;
    }

    public void setIsDescriptionPersian(Boolean isDescriptionPersian) {
        this.isDescriptionPersian = isDescriptionPersian;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfVillas() {
        return numberOfVillas;
    }

    public void setNumberOfVillas(Integer numberOfVillas) {
        this.numberOfVillas = numberOfVillas;
    }

    public Integer getAnnexeBuildings() {
        return annexeBuildings;
    }

    public void setAnnexeBuildings(Integer annexeBuildings) {
        this.annexeBuildings = annexeBuildings;
    }

    public Integer getNumberOfJuniorSuites() {
        return numberOfJuniorSuites;
    }

    public void setNumberOfJuniorSuites(Integer numberOfJuniorSuites) {
        this.numberOfJuniorSuites = numberOfJuniorSuites;
    }

    public Integer getNumberOfSuits() {
        return numberOfSuits;
    }

    public void setNumberOfSuits(Integer numberOfSuits) {
        this.numberOfSuits = numberOfSuits;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getBestdealsforyourdates() {
        return bestdealsforyourdates;
    }

    public void setBestdealsforyourdates(String bestdealsforyourdates) {
        this.bestdealsforyourdates = bestdealsforyourdates;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public HotelReviews getHotelReviews() {
        return hotelReviews;
    }

    public void setHotelReviews(HotelReviews hotelReviews) {
        this.hotelReviews = hotelReviews;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageKeywords() {
        return pageKeywords;
    }

    public void setPageKeywords(String pageKeywords) {
        this.pageKeywords = pageKeywords;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageCanonical() {
        return pageCanonical;
    }

    public void setPageCanonical(String pageCanonical) {
        this.pageCanonical = pageCanonical;
    }

    public List<Object> getHDetailRooms() {
        return hDetailRooms;
    }

    public void setHDetailRooms(List<Object> hDetailRooms) {
        this.hDetailRooms = hDetailRooms;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
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
