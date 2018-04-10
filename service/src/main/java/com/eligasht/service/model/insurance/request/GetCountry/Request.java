
package com.eligasht.service.model.insurance.request.GetCountry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("Culture")
    @Expose
    private String culture;

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
