
package com.eligasht.service.model.XPackage.response.searchXPackage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XferList {

    @SerializedName("XFerID")
    @Expose
    private Object xFerID;
    @SerializedName("XFlighstID")
    @Expose
    private String xFlighstID;
    @SerializedName("XFlightsList")
    @Expose
    private List<XFlightsList> xFlightsList = null;

    public Object getXFerID() {
        return xFerID;
    }

    public void setXFerID(Object xFerID) {
        this.xFerID = xFerID;
    }

    public String getXFlighstID() {
        return xFlighstID;
    }

    public void setXFlighstID(String xFlighstID) {
        this.xFlighstID = xFlighstID;
    }

    public List<XFlightsList> getXFlightsList() {
        return xFlightsList;
    }

    public void setXFlightsList(List<XFlightsList> xFlightsList) {
        this.xFlightsList = xFlightsList;
    }

}
