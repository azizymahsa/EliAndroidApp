
package com.eligasht.service.model.flight.response.contactUs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetContactUsWithCutureResult {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("ContactInfos")
    @Expose
    private List<ContactInfo> contactInfos = null;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("MainTitle")
    @Expose
    private String mainTitle;
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

    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
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
