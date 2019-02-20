
package com.eligasht.service.model.newModel.xpackage.searchPack.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XferList {

    @SerializedName("XFlightsList")
    @Expose
    private List<XFlightsList> xFlightsList = null;
    @SerializedName("XFlighstID")
    @Expose
    private String xFlighstID;
    @SerializedName("XFerID")
    @Expose
    private Object xFerID;

    public List<XFlightsList> getXFlightsList() {
        return xFlightsList;
    }

    public void setXFlightsList(List<XFlightsList> xFlightsList) {
        this.xFlightsList = xFlightsList;
    }

    public String getXFlighstID() {
        return xFlighstID;
    }

    public void setXFlighstID(String xFlighstID) {
        this.xFlighstID = xFlighstID;
    }

    public Object getXFerID() {
        return xFerID;
    }

    public void setXFerID(Object xFerID) {
        this.xFerID = xFerID;
    }

}
