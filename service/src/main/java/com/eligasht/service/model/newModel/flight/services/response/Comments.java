
package com.eligasht.service.model.newModel.flight.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("ShortText")
    @Expose
    private String shortText;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

}
