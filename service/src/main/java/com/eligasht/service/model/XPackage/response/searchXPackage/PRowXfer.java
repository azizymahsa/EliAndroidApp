
package com.eligasht.service.model.XPackage.response.searchXPackage;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PRowXfer implements Serializable {

    @SerializedName("FltIDs")
    @Expose
    private String fltIDs;
    @SerializedName("LstAvailableDates")
    @Expose
    private List<LstAvailableDate> lstAvailableDates = null;
    @SerializedName("LstHotelAmenity")
    @Expose
    private List<LstHotelAmenity> lstHotelAmenity = null;
    @SerializedName("LstProwHotels")
    @Expose
    private List<LstProwHotel> lstProwHotels = null;
    @SerializedName("LstProwPrices")
    @Expose
    private List<LstProwPrice> lstProwPrices = null;
    @SerializedName("LstProwServices")
    @Expose
    private List<LstProwService> lstProwServices = null;
    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;
    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("PreviousSumPrice")
    @Expose
    private Integer previousSumPrice;
    @SerializedName("SumPrice")
    @Expose
    private Integer sumPrice;
    @SerializedName("XFerIDs")
    @Expose
    private String xFerIDs;
    @SerializedName("XferList")
    @Expose
    private XferList xferList;

    public String getFltIDs() {
        return fltIDs;
    }

    public void setFltIDs(String fltIDs) {
        this.fltIDs = fltIDs;
    }

    public List<LstAvailableDate> getLstAvailableDates() {
        return lstAvailableDates;
    }

    public void setLstAvailableDates(List<LstAvailableDate> lstAvailableDates) {
        this.lstAvailableDates = lstAvailableDates;
    }

    public List<LstHotelAmenity> getLstHotelAmenity() {
        return lstHotelAmenity;
    }

    public void setLstHotelAmenity(List<LstHotelAmenity> lstHotelAmenity) {
        this.lstHotelAmenity = lstHotelAmenity;
    }

    public List<LstProwHotel> getLstProwHotels() {
        return lstProwHotels;
    }

    public void setLstProwHotels(List<LstProwHotel> lstProwHotels) {
        this.lstProwHotels = lstProwHotels;
    }

    public List<LstProwPrice> getLstProwPrices() {
        return lstProwPrices;
    }

    public void setLstProwPrices(List<LstProwPrice> lstProwPrices) {
        this.lstProwPrices = lstProwPrices;
    }

    public List<LstProwService> getLstProwServices() {
        return lstProwServices;
    }

    public void setLstProwServices(List<LstProwService> lstProwServices) {
        this.lstProwServices = lstProwServices;
    }

    public Integer getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(Integer packRowID) {
        this.packRowID = packRowID;
    }

    public Integer getPackID() {
        return packID;
    }

    public void setPackID(Integer packID) {
        this.packID = packID;
    }

    public Integer getPreviousSumPrice() {
        return previousSumPrice;
    }

    public void setPreviousSumPrice(Integer previousSumPrice) {
        this.previousSumPrice = previousSumPrice;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getXFerIDs() {
        return xFerIDs;
    }

    public void setXFerIDs(String xFerIDs) {
        this.xFerIDs = xFerIDs;
    }

    public XferList getXferList() {
        return xferList;
    }

    public void setXferList(XferList xferList) {
        this.xferList = xferList;
    }

}
