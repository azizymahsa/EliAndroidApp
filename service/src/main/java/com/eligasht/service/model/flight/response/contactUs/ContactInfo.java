
package com.eligasht.service.model.flight.response.contactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactInfo {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Email")
    @Expose
    private Object email;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("IconNumber")
    @Expose
    private String iconNumber;
    @SerializedName("PhoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconNumber() {
        return iconNumber;
    }

    public void setIconNumber(String iconNumber) {
        this.iconNumber = iconNumber;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
