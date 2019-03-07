
package com.eligasht.service.model.newModel.train.domesticSearch.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainFacility {

    @SerializedName("HasAirConditioning")
    @Expose
    private Boolean hasAirConditioning;
    @SerializedName("HasMedia")
    @Expose
    private Boolean hasMedia;

    public Boolean getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

}
