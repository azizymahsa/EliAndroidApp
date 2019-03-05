
package com.eligasht.service.model.newModel.hotelFlight.preSearch.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("ShortText")
    @Expose
    private String shortText;
    @SerializedName("Language")
    @Expose
    private String language;

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
