
package com.eligasht.reservation.models.model.pack.response.responseSearch;

import com.eligasht.reservation.views.adapters.pack.LstProwHotelAdapter;
import com.eligasht.reservation.views.adapters.pack.LstProwPriceAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PRowXfer {

    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;
    @SerializedName("LstProwHotels")
    @Expose
    private List<LstProwHotel> lstProwHotels = null;
    @SerializedName("LstProwPrices")
    @Expose
    private List<LstProwPrice> lstProwPrices = null;
    @SerializedName("LstHotelAmenity")
    @Expose
    private List<LstHotelAmenity> lstHotelAmenity = null;
    @SerializedName("LstProwServices")
    @Expose
    private List<LstProwService> lstProwServices = null;
    @SerializedName("SumPrice")
    @Expose
    private SumPrice_ sumPrice;
    @SerializedName("PreviousSumPrice")
    @Expose
    private PreviousSumPrice previousSumPrice;
    @SerializedName("XferList")
    @Expose
    private XferList xferList;
    @SerializedName("LstAvailableDates")
    @Expose
    private List<LstAvailableDate> lstAvailableDates = null;
    @SerializedName("FltIDs")
    @Expose
    private String fltIDs;
    @SerializedName("XFerIDs")
    @Expose
    private String xFerIDs;


    transient private LstProwPriceAdapter lstProwPriceAdapter;
    transient private LstProwHotelAdapter lstProwHotelAdapter;

    public LstProwPriceAdapter getLstProwPriceAdapter() {
        return lstProwPriceAdapter;
    }

    public void setLstProwPriceAdapter(LstProwPriceAdapter lstProwPriceAdapter) {
        this.lstProwPriceAdapter = lstProwPriceAdapter;
    }

    public LstProwHotelAdapter getLstProwHotelAdapter() {
        return lstProwHotelAdapter;
    }

    public void setLstProwHotelAdapter(LstProwHotelAdapter lstProwHotelAdapter) {
        this.lstProwHotelAdapter = lstProwHotelAdapter;
    }

    public Integer getPackID() {
        return packID;
    }

    public void setPackID(Integer packID) {
        this.packID = packID;
    }

    public Integer getPackRowID() {
        return packRowID;
    }

    public void setPackRowID(Integer packRowID) {
        this.packRowID = packRowID;
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

    public List<LstHotelAmenity> getLstHotelAmenity() {
        return lstHotelAmenity;
    }

    public void setLstHotelAmenity(List<LstHotelAmenity> lstHotelAmenity) {
        this.lstHotelAmenity = lstHotelAmenity;
    }

    public List<LstProwService> getLstProwServices() {
        return lstProwServices;
    }

    public void setLstProwServices(List<LstProwService> lstProwServices) {
        this.lstProwServices = lstProwServices;
    }

    public SumPrice_ getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(SumPrice_ sumPrice) {
        this.sumPrice = sumPrice;
    }

    public PreviousSumPrice getPreviousSumPrice() {
        return previousSumPrice;
    }

    public void setPreviousSumPrice(PreviousSumPrice previousSumPrice) {
        this.previousSumPrice = previousSumPrice;
    }

    public XferList getXferList() {
        return xferList;
    }

    public void setXferList(XferList xferList) {
        this.xferList = xferList;
    }

    public List<LstAvailableDate> getLstAvailableDates() {
        return lstAvailableDates;
    }

    public void setLstAvailableDates(List<LstAvailableDate> lstAvailableDates) {
        this.lstAvailableDates = lstAvailableDates;
    }

    public String getFltIDs() {
        return fltIDs;
    }

    public void setFltIDs(String fltIDs) {
        this.fltIDs = fltIDs;
    }

    public String getXFerIDs() {
        return xFerIDs;
    }

    public void setXFerIDs(String xFerIDs) {
        this.xFerIDs = xFerIDs;
    }

}
