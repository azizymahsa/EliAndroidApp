
package com.eligasht.reservation.models.model.pack.response.responseSearch;

import com.eligasht.service.model.newModel.xpackage.searchPack.response.AdlPrice;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstProwPrice {

    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("PackRow_ID")
    @Expose
    private Integer packRowID;
    @SerializedName("PackRowRoomType_ID")
    @Expose
    private Integer packRowRoomTypeID;
    @SerializedName("RoomNo")
    @Expose
    private Integer roomNo;
    @SerializedName("HRoomTypeID")
    @Expose
    private Integer hRoomTypeID;
    @SerializedName("HRoomTypeName")
    @Expose
    private String hRoomTypeName;
    @SerializedName("HRoomTypeNameF")
    @Expose
    private String hRoomTypeNameF;
    @SerializedName("AdlCount")
    @Expose
    private Integer adlCount;
    @SerializedName("ChdWBCount")
    @Expose
    private Integer chdWBCount;
    @SerializedName("ChdNBCount")
    @Expose
    private Integer chdNBCount;
    @SerializedName("InfCount")
    @Expose
    private Integer infCount;
    @SerializedName("ExtQty")
    @Expose
    private Integer extQty;
    @SerializedName("AdlPrice")
    @Expose
    private AdlPrice adlPrice;
    @SerializedName("ChWbPrice")
    @Expose
    private ChWbPrice chWbPrice;
    @SerializedName("ChNbPrice")
    @Expose
    private ChNbPrice chNbPrice;
    @SerializedName("InfPrice")
    @Expose
    private InfPrice infPrice;
    @SerializedName("ExtPrice")
    @Expose
    private ExtPrice extPrice;
    @SerializedName("SumPrice")
    @Expose
    private SumPrice sumPrice;
    @SerializedName("PreviousSumPrice")
    @Expose
    private Object previousSumPrice;
    @SerializedName("StatusE")
    @Expose
    private String statusE;
    @SerializedName("StatusF")
    @Expose
    private String statusF;
    @SerializedName("Available")
    @Expose
    private String available;
    @SerializedName("HRroomList")
    @Expose
    private String hRroomList;
    @SerializedName("HRroomListF")
    @Expose
    private String hRroomListF;
    @SerializedName("HService")
    @Expose
    private String hService;
    @SerializedName("HServiceF")
    @Expose
    private String hServiceF;
    @SerializedName("MaxAdlOccupancy")
    @Expose
    private Integer maxAdlOccupancy;
    @SerializedName("MaxChdOccupancy")
    @Expose
    private Integer maxChdOccupancy;
    @SerializedName("DiscountPercent")
    @Expose
    private Integer discountPercent;


    transient private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public Integer getPackRowRoomTypeID() {
        return packRowRoomTypeID;
    }

    public void setPackRowRoomTypeID(Integer packRowRoomTypeID) {
        this.packRowRoomTypeID = packRowRoomTypeID;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getHRoomTypeID() {
        return hRoomTypeID;
    }

    public void setHRoomTypeID(Integer hRoomTypeID) {
        this.hRoomTypeID = hRoomTypeID;
    }

    public String getHRoomTypeName() {
        return hRoomTypeName;
    }

    public void setHRoomTypeName(String hRoomTypeName) {
        this.hRoomTypeName = hRoomTypeName;
    }

    public String getHRoomTypeNameF() {
        return hRoomTypeNameF;
    }

    public void setHRoomTypeNameF(String hRoomTypeNameF) {
        this.hRoomTypeNameF = hRoomTypeNameF;
    }

    public Integer getAdlCount() {
        return adlCount;
    }

    public void setAdlCount(Integer adlCount) {
        this.adlCount = adlCount;
    }

    public Integer getChdWBCount() {
        return chdWBCount;
    }

    public void setChdWBCount(Integer chdWBCount) {
        this.chdWBCount = chdWBCount;
    }

    public Integer getChdNBCount() {
        return chdNBCount;
    }

    public void setChdNBCount(Integer chdNBCount) {
        this.chdNBCount = chdNBCount;
    }

    public Integer getInfCount() {
        return infCount;
    }

    public void setInfCount(Integer infCount) {
        this.infCount = infCount;
    }

    public Integer getExtQty() {
        return extQty;
    }

    public void setExtQty(Integer extQty) {
        this.extQty = extQty;
    }

    public AdlPrice getAdlPrice() {
        return adlPrice;
    }

    public void setAdlPrice(AdlPrice adlPrice) {
        this.adlPrice = adlPrice;
    }

    public ChWbPrice getChWbPrice() {
        return chWbPrice;
    }

    public void setChWbPrice(ChWbPrice chWbPrice) {
        this.chWbPrice = chWbPrice;
    }

    public ChNbPrice getChNbPrice() {
        return chNbPrice;
    }

    public void setChNbPrice(ChNbPrice chNbPrice) {
        this.chNbPrice = chNbPrice;
    }

    public InfPrice getInfPrice() {
        return infPrice;
    }

    public void setInfPrice(InfPrice infPrice) {
        this.infPrice = infPrice;
    }

    public ExtPrice getExtPrice() {
        return extPrice;
    }

    public void setExtPrice(ExtPrice extPrice) {
        this.extPrice = extPrice;
    }

    public SumPrice getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(SumPrice sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Object getPreviousSumPrice() {
        return previousSumPrice;
    }

    public void setPreviousSumPrice(Object previousSumPrice) {
        this.previousSumPrice = previousSumPrice;
    }

    public String getStatusE() {
        return statusE;
    }

    public void setStatusE(String statusE) {
        this.statusE = statusE;
    }

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getHRroomList() {
        return hRroomList;
    }

    public void setHRroomList(String hRroomList) {
        this.hRroomList = hRroomList;
    }

    public String getHRroomListF() {
        return hRroomListF;
    }

    public void setHRroomListF(String hRroomListF) {
        this.hRroomListF = hRroomListF;
    }

    public String getHService() {
        return hService;
    }

    public void setHService(String hService) {
        this.hService = hService;
    }

    public String getHServiceF() {
        return hServiceF;
    }

    public void setHServiceF(String hServiceF) {
        this.hServiceF = hServiceF;
    }

    public Integer getMaxAdlOccupancy() {
        return maxAdlOccupancy;
    }

    public void setMaxAdlOccupancy(Integer maxAdlOccupancy) {
        this.maxAdlOccupancy = maxAdlOccupancy;
    }

    public Integer getMaxChdOccupancy() {
        return maxChdOccupancy;
    }

    public void setMaxChdOccupancy(Integer maxChdOccupancy) {
        this.maxChdOccupancy = maxChdOccupancy;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

}
