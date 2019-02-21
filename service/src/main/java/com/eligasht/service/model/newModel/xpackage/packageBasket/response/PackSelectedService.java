
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackSelectedService {

    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;
    @SerializedName("PRowServiceNameE")
    @Expose
    private String pRowServiceNameE;
    @SerializedName("PRowServiceNameF")
    @Expose
    private String pRowServiceNameF;
    @SerializedName("PRowServiceIncluded")
    @Expose
    private Boolean pRowServiceIncluded;

    public Integer getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(Integer packRowID) {
        this.packRowID = packRowID;
    }

    public String getPRowServiceNameE() {
        return pRowServiceNameE;
    }

    public void setPRowServiceNameE(String pRowServiceNameE) {
        this.pRowServiceNameE = pRowServiceNameE;
    }

    public String getPRowServiceNameF() {
        return pRowServiceNameF;
    }

    public void setPRowServiceNameF(String pRowServiceNameF) {
        this.pRowServiceNameF = pRowServiceNameF;
    }

    public Boolean getPRowServiceIncluded() {
        return pRowServiceIncluded;
    }

    public void setPRowServiceIncluded(Boolean pRowServiceIncluded) {
        this.pRowServiceIncluded = pRowServiceIncluded;
    }

}
