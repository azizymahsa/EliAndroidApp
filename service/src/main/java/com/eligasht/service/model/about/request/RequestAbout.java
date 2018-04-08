package com.eligasht.service.model.about.request;

/**
 * Created by Mahsa.azizi on 4/7/2018.
 */

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RequestAbout {

    @SerializedName("culture")
    @Expose
    private String culture;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
