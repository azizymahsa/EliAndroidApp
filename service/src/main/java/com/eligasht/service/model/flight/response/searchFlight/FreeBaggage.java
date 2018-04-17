package com.eligasht.service.model.flight.response.searchFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeBaggage {

    @SerializedName("Pieces")
    @Expose
    private Integer pieces;
    @SerializedName("Weight")
    @Expose
    private Integer weight;

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
