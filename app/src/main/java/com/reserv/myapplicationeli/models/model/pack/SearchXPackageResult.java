
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchXPackageResult {

    @SerializedName("Error")
    @Expose
    private JSONObject error;
    @SerializedName("PRowXfers")
    @Expose
    private ArrayList<PRowXfer> PRowXfers = null;

    public JSONObject getError() {
        return error;
    }

    public void setError(JSONObject error) {
        this.error = error;
    }

    public ArrayList<PRowXfer> getPRowXfers() {
        return PRowXfers;
    }

    public void setPRowXfers(ArrayList<PRowXfer> pRowXfers) {
        this.PRowXfers = pRowXfers;
    }

}
