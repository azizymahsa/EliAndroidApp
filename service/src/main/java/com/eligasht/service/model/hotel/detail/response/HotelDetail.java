
package com.eligasht.service.model.hotel.detail.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetail {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("AnnexeBuildings")
    @Expose
    private Integer annexeBuildings;
    @SerializedName("Announcements")
    @Expose
    private String announcements;
    @SerializedName("BestSeller")
    @Expose
    private Boolean bestSeller;
    @SerializedName("Bestdealsforyourdates")
    @Expose
    private String bestdealsforyourdates;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("Construction")
    @Expose
    private String construction;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("HDetailRooms")
    @Expose
    private List<Object> hDetailRooms = null;
    @SerializedName("HType")
    @Expose
    private String hType;
    @SerializedName("HotelID")
    @Expose
    private Integer hotelID;
    @SerializedName("HotelImages")
    @Expose
    private List<HotelImage> hotelImages = null;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("HotelProprties")
    @Expose
    private List<HotelProprty> hotelProprties = null;
    @SerializedName("HotelReviews")
    @Expose
    private HotelReviews hotelReviews;
    @SerializedName("ImgPath")
    @Expose
    private String imgPath;
    @SerializedName("IsDescriptionPersian")
    @Expose
    private Boolean isDescriptionPersian;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Neighborhood")
    @Expose
    private String neighborhood;
    @SerializedName("NumberOfJuniorSuites")
    @Expose
    private Integer numberOfJuniorSuites;
    @SerializedName("NumberOfRooms")
    @Expose
    private Integer numberOfRooms;
    @SerializedName("NumberOfSuits")
    @Expose
    private Integer numberOfSuits;
    @SerializedName("NumberOfVillas")
    @Expose
    private Integer numberOfVillas;
    @SerializedName("PageCanonical")
    @Expose
    private String pageCanonical;
    @SerializedName("PageDescription")
    @Expose
    private String pageDescription;
    @SerializedName("PageKeywords")
    @Expose
    private String pageKeywords;
    @SerializedName("PageTitle")
    @Expose
    private String pageTitle;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("PriceType")
    @Expose
    private String priceType;
    @SerializedName("Renovation")
    @Expose
    private String renovation;
    @SerializedName("StarRating")
    @Expose
    private String starRating;
    @SerializedName("Website")
    @Expose
    private String website;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAnnexeBuildings() {
        return annexeBuildings;
    }

    public void setAnnexeBuildings(Integer annexeBuildings) {
        this.annexeBuildings = annexeBuildings;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public Boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getBestdealsforyourdates() {
        return bestdealsforyourdates;
    }

    public void setBestdealsforyourdates(String bestdealsforyourdates) {
        this.bestdealsforyourdates = bestdealsforyourdates;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object> getHDetailRooms() {
        return hDetailRooms;
    }

    public void setHDetailRooms(List<Object> hDetailRooms) {
        this.hDetailRooms = hDetailRooms;
    }

    public String getHType() {
        return hType;
    }

    public void setHType(String hType) {
        this.hType = hType;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public List<HotelImage> getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(List<HotelImage> hotelImages) {
        this.hotelImages = hotelImages;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<HotelProprty> getHotelProprties() {
        return hotelProprties;
    }

    public void setHotelProprties(List<HotelProprty> hotelProprties) {
        this.hotelProprties = hotelProprties;
    }

    public HotelReviews getHotelReviews() {
        return hotelReviews;
    }

    public void setHotelReviews(HotelReviews hotelReviews) {
        this.hotelReviews = hotelReviews;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Boolean getIsDescriptionPersian() {
        return isDescriptionPersian;
    }

    public void setIsDescriptionPersian(Boolean isDescriptionPersian) {
        this.isDescriptionPersian = isDescriptionPersian;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNumberOfJuniorSuites() {
        return numberOfJuniorSuites;
    }

    public void setNumberOfJuniorSuites(Integer numberOfJuniorSuites) {
        this.numberOfJuniorSuites = numberOfJuniorSuites;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfSuits() {
        return numberOfSuits;
    }

    public void setNumberOfSuits(Integer numberOfSuits) {
        this.numberOfSuits = numberOfSuits;
    }

    public Integer getNumberOfVillas() {
        return numberOfVillas;
    }

    public void setNumberOfVillas(Integer numberOfVillas) {
        this.numberOfVillas = numberOfVillas;
    }

    public String getPageCanonical() {
        return pageCanonical;
    }

    public void setPageCanonical(String pageCanonical) {
        this.pageCanonical = pageCanonical;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageKeywords() {
        return pageKeywords;
    }

    public void setPageKeywords(String pageKeywords) {
        this.pageKeywords = pageKeywords;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
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

}
