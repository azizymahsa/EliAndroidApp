
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Error;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchXPackageResult {

    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("PRowXfers")
    @Expose
    private ArrayList<PRowXfer> PRowXfers = null;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ArrayList<PRowXfer> getPRowXfers() {
        return PRowXfers;
    }

    public void setPRowXfers(ArrayList<PRowXfer> pRowXfers) {
        this.PRowXfers = pRowXfers;
    }

}
