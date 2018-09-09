
package com.eligasht.service.model.survey.response.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("ShortText")
    @Expose
    private Object shortText;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Object getShortText() {
        return shortText;
    }

    public void setShortText(Object shortText) {
        this.shortText = shortText;
    }

}
