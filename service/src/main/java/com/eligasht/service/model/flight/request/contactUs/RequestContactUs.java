package com.eligasht.service.model.flight.request.contactUs;

/**
 * Created by Mahsa.azizi on 4/7/2018.
 */

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RequestContactUs {

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
