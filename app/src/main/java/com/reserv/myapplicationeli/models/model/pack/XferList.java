
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class XferList {

    @SerializedName("XFerID")
    @Expose
    private Integer xFerID;
    @SerializedName("XFlighstID")
    @Expose
    private String xFlighstID;
    @SerializedName("XFlightsList")
    @Expose
    private ArrayList<XFlightsList> xFlightsList = null;

    public Integer getXFerID() {
        return xFerID;
    }

    public void setXFerID(Integer xFerID) {
        this.xFerID = xFerID;
    }

    public String getXFlighstID() {
        return xFlighstID;
    }

    public void setXFlighstID(String xFlighstID) {
        this.xFlighstID = xFlighstID;
    }

    public ArrayList<XFlightsList> getXFlightsList() {
        return xFlightsList;
    }

    public void setXFlightsList(ArrayList<XFlightsList> xFlightsList) {
        this.xFlightsList = xFlightsList;
    }

}
