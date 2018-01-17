
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Country;

import java.util.ArrayList;

public class GetCountryAjaxResult {

    @SerializedName("Countries")
    @Expose
    private ArrayList<Country> Countries = null;

    public ArrayList<Country> getCountries() {
        return Countries;
    }

    public void setCountries(ArrayList<Country> Countries) {
        this.Countries = Countries;
    }

}
