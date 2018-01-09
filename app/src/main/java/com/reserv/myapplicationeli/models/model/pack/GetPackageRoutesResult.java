
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.HotelCity;

import java.util.ArrayList;

public class GetPackageRoutesResult {

    @SerializedName("Cities")
    @Expose
    private ArrayList<HotelCity> Cities = null;

    public ArrayList<HotelCity> getCities() {
        return Cities;
    }

    public void setCities(ArrayList<HotelCity> cities) {
        this.Cities = cities;
    }

}
