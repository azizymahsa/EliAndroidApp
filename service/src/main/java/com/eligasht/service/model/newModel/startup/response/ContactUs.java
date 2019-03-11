
package com.eligasht.service.model.newModel.startup.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUs {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("ContactInfo")
    @Expose
    private List<String> contactInfo = null;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("MainTitle")
    @Expose
    private Object mainTitle;
    @SerializedName("MapAddress")
    @Expose
    private String mapAddress;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<String> contactInfo) {
        this.contactInfo = contactInfo;
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

    public Object getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(Object mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
