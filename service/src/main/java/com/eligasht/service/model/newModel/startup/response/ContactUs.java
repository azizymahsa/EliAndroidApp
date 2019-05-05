
package com.eligasht.service.model.newModel.startup.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUs {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
