
package com.eligasht.service.model.XPackage.request.PurchasePackage;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("PassList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("PartnerList")
    @Expose
    private PartnerList partnerList;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("WebUserID")
    @Expose
    private String webUserID;
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
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
    }

    public PartnerList getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(PartnerList partnerList) {
        this.partnerList = partnerList;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(String webUserID) {
        this.webUserID = webUserID;
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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
