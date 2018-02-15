
package com.eligasht.reservation.models.model.pack.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.pack.PartnerList;
import com.eligasht.reservation.models.model.pack.PassList;

import java.util.ArrayList;
import java.util.List;

public class Request {

    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("PassList")
    @Expose
    private ArrayList<PassList> passList = null;
    @SerializedName("PartnerList")
    @Expose
    private PartnerList partnerList;
    @SerializedName("PackRow_ID")
    @Expose
    private String packRowID;
    @SerializedName("PackXfer_IDs")
    @Expose
    private String packXferIDs;
    @SerializedName("Flt_IDs")
    @Expose
    private String fltIDs;
    @SerializedName("Rooms")
    @Expose
    private String rooms;

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(ArrayList<PassList> passList) {
        this.passList = passList;
    }

    public PartnerList getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(PartnerList partnerList) {
        this.partnerList = partnerList;
    }

    public String getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(String packRowID) {
        this.packRowID = packRowID;
    }

    public String getPackXferIDs() {
        return packXferIDs;
    }

    public void setPackXferIDs(String packXferIDs) {
        this.packXferIDs = packXferIDs;
    }

    public String getFltIDs() {
        return fltIDs;
    }

    public void setFltIDs(String fltIDs) {
        this.fltIDs = fltIDs;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

}
