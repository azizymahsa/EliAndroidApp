package com.eligasht.reservation.models.model.pack.call;


import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.pack.PassList;

import java.util.ArrayList;

public class PurchasePackageRq {

    private Identity identity;
    private Integer PackRow_ID;
    private String PackXfer_IDs;
    private String Flt_IDs;
    private String Culture;
    private String Rooms;
    private ArrayList<PassList> PassList;
    private ArrayList<com.eligasht.reservation.models.model.pack.PartnerList> PartnerList;

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Integer getPackRow_ID() {
        return PackRow_ID;
    }

    public void setPackRow_ID(Integer packRow_ID) {
        PackRow_ID = packRow_ID;
    }

    public String getPackXfer_IDs() {
        return PackXfer_IDs;
    }

    public void setPackXfer_IDs(String packXfer_IDs) {
        PackXfer_IDs = packXfer_IDs;
    }

    public String getFlt_IDs() {
        return Flt_IDs;
    }

    public void setFlt_IDs(String flt_IDs) {
        Flt_IDs = flt_IDs;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public ArrayList<com.eligasht.reservation.models.model.pack.PassList> getPassList() {
        return PassList;
    }

    public void setPassList(ArrayList<com.eligasht.reservation.models.model.pack.PassList> passList) {
        PassList = passList;
    }

    public ArrayList<com.eligasht.reservation.models.model.pack.PartnerList> getPartnerList() {
        return PartnerList;
    }

    public void setPartnerList(ArrayList<com.eligasht.reservation.models.model.pack.PartnerList> partnerList) {
        PartnerList = partnerList;
    }
}
