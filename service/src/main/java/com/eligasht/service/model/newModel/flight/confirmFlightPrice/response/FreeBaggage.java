
package com.eligasht.service.model.newModel.flight.confirmFlightPrice.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeBaggage {

    @SerializedName("Weight")
    @Expose
    private Integer weight;
    @SerializedName("Pieces")
    @Expose
    private Integer pieces;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

}
