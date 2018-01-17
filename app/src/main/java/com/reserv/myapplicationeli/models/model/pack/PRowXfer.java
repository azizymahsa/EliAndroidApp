
package com.reserv.myapplicationeli.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.views.adapters.pack.LstProwHotelAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.LstProwPriceAdapter;

import java.util.ArrayList;

public class PRowXfer {

    @SerializedName("FltIDs")
    @Expose
    private String FltIDs;
    @SerializedName("LstAvailableDates")
    @Expose
    private ArrayList<LstAvailableDate> lstAvailableDates = null;
    @SerializedName("LstHotelAmenity")
    @Expose
    private ArrayList<LstHotelAmenity> lstHotelAmenity = null;
    @SerializedName("LstProwHotels")
    @Expose
    private ArrayList<LstProwHotel> lstProwHotels = null;
    @SerializedName("LstProwPrices")
    @Expose
    private ArrayList<LstProwPrice> lstProwPrices = null;
    @SerializedName("LstProwServices")
    @Expose
    private ArrayList<LstProwService> lstProwServices = null;
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

    public String getFltIDs() {
        return FltIDs;
    }

    public void setFltIDs(String fltIDs) {
        this.FltIDs = fltIDs;
    }

    public ArrayList<LstAvailableDate> getLstAvailableDates() {
        return lstAvailableDates;
    }

    public void setLstAvailableDates(ArrayList<LstAvailableDate> lstAvailableDates) {
        this.lstAvailableDates = lstAvailableDates;
    }

    public ArrayList<LstHotelAmenity> getLstHotelAmenity() {
        return lstHotelAmenity;
    }

    public void setLstHotelAmenity(ArrayList<LstHotelAmenity> lstHotelAmenity) {
        this.lstHotelAmenity = lstHotelAmenity;
    }

    public ArrayList<LstProwHotel> getLstProwHotels() {
        return lstProwHotels;
    }

    public void setLstProwHotels(ArrayList<LstProwHotel> lstProwHotels) {
        this.lstProwHotels = lstProwHotels;
    }

    public ArrayList<LstProwPrice> getLstProwPrices() {
        return lstProwPrices;
    }

    public void setLstProwPrices(ArrayList<LstProwPrice> lstProwPrices) {
        this.lstProwPrices = lstProwPrices;
    }

    public ArrayList<LstProwService> getLstProwServices() {
        return lstProwServices;
    }

    public void setLstProwServices(ArrayList<LstProwService> lstProwServices) {
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
