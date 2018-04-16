
package com.eligasht.service.model.XPackage.response.searchXPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstProwService {

    @SerializedName("PRowServiceIncluded")
    @Expose
    private Boolean pRowServiceIncluded;
    @SerializedName("PRowServiceNameE")
    @Expose
    private String pRowServiceNameE;
    @SerializedName("PRowServiceNameF")
    @Expose
    private String pRowServiceNameF;
    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;

    public Boolean getPRowServiceIncluded() {
        return pRowServiceIncluded;
    }

    public void setPRowServiceIncluded(Boolean pRowServiceIncluded) {
        this.pRowServiceIncluded = pRowServiceIncluded;
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

    public Integer getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(Integer packRowID) {
        this.packRowID = packRowID;
    }

}
