
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("RqBase_ID")
    @Expose
    private Integer rqBaseID;
    @SerializedName("EncryptedRqBase_ID")
    @Expose
    private String encryptedRqBaseID;
    @SerializedName("EncryptedContractNo")
    @Expose
    private String encryptedContractNo;
    @SerializedName("ContractNo")
    @Expose
    private String contractNo;
    @SerializedName("RegisterDate")
    @Expose
    private String registerDate;
    @SerializedName("RegisterUserFa")
    @Expose
    private String registerUserFa;
    @SerializedName("IsRegistered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("RqBaseCategory")
    @Expose
    private Integer rqBaseCategory;
    @SerializedName("PurchaseType")
    @Expose
    private Integer purchaseType;
    @SerializedName("PurchaseDate")
    @Expose
    private String purchaseDate;
    @SerializedName("PurchaseDateFa")
    @Expose
    private String purchaseDateFa;
    @SerializedName("PurchaseTypeEn")
    @Expose
    private String purchaseTypeEn;
    @SerializedName("FollowerFa")
    @Expose
    private String followerFa;
    @SerializedName("FollowerEn")
    @Expose
    private String followerEn;
    @SerializedName("TotalPrice")
    @Expose
    private TotalPrice totalPrice;
    @SerializedName("TotalCom")
    @Expose
    private TotalCom totalCom;
    @SerializedName("TotalPlusCom")
    @Expose
    private TotalPlusCom totalPlusCom;
    @SerializedName("TotalDiscount")
    @Expose
    private TotalDiscount totalDiscount;
    @SerializedName("Discount")
    @Expose
    private Discount discount;
    @SerializedName("FactorTitle")
    @Expose
    private String factorTitle;
    @SerializedName("PassTotalCom")
    @Expose
    private PassTotalCom passTotalCom;
    @SerializedName("PassTotalSpecialDiscount")
    @Expose
    private PassTotalSpecialDiscount passTotalSpecialDiscount;
    @SerializedName("PassTotalClubDiscount")
    @Expose
    private PassTotalClubDiscount passTotalClubDiscount;
    @SerializedName("PassTotalDiscount")
    @Expose
    private Object passTotalDiscount;
    @SerializedName("TotalHotel")
    @Expose
    private TotalHotel totalHotel;
    @SerializedName("TotalFlight")
    @Expose
    private TotalFlight totalFlight;
    @SerializedName("FinalPrice")
    @Expose
    private FinalPrice finalPrice;
    @SerializedName("AdultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("ChildCount")
    @Expose
    private Integer childCount;
    @SerializedName("InfantCount")
    @Expose
    private Integer infantCount;
    @SerializedName("AdultTotalPrice")
    @Expose
    private AdultTotalPrice adultTotalPrice;
    @SerializedName("ChildTotalPrice")
    @Expose
    private ChildTotalPrice childTotalPrice;
    @SerializedName("InfantTotalPrice")
    @Expose
    private InfantTotalPrice infantTotalPrice;
    @SerializedName("FollowerIntTel")
    @Expose
    private String followerIntTel;
    @SerializedName("OnlinePaymentURL")
    @Expose
    private String onlinePaymentURL;
    @SerializedName("OnlinePaymentReturnURL")
    @Expose
    private Object onlinePaymentReturnURL;
    @SerializedName("PromoDisount")
    @Expose
    private Integer promoDisount;
    @SerializedName("PromoCode")
    @Expose
    private String promoCode;
    @SerializedName("UniqueID")
    @Expose
    private String uniqueID;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;

    public Integer getRqBaseID() {
        return rqBaseID;
    }

    public void setRqBaseID(Integer rqBaseID) {
        this.rqBaseID = rqBaseID;
    }

    public String getEncryptedRqBaseID() {
        return encryptedRqBaseID;
    }

    public void setEncryptedRqBaseID(String encryptedRqBaseID) {
        this.encryptedRqBaseID = encryptedRqBaseID;
    }

    public String getEncryptedContractNo() {
        return encryptedContractNo;
    }

    public void setEncryptedContractNo(String encryptedContractNo) {
        this.encryptedContractNo = encryptedContractNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterUserFa() {
        return registerUserFa;
    }

    public void setRegisterUserFa(String registerUserFa) {
        this.registerUserFa = registerUserFa;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Integer getRqBaseCategory() {
        return rqBaseCategory;
    }

    public void setRqBaseCategory(Integer rqBaseCategory) {
        this.rqBaseCategory = rqBaseCategory;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseDateFa() {
        return purchaseDateFa;
    }

    public void setPurchaseDateFa(String purchaseDateFa) {
        this.purchaseDateFa = purchaseDateFa;
    }

    public String getPurchaseTypeEn() {
        return purchaseTypeEn;
    }

    public void setPurchaseTypeEn(String purchaseTypeEn) {
        this.purchaseTypeEn = purchaseTypeEn;
    }

    public String getFollowerFa() {
        return followerFa;
    }

    public void setFollowerFa(String followerFa) {
        this.followerFa = followerFa;
    }

    public String getFollowerEn() {
        return followerEn;
    }

    public void setFollowerEn(String followerEn) {
        this.followerEn = followerEn;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TotalCom getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(TotalCom totalCom) {
        this.totalCom = totalCom;
    }

    public TotalPlusCom getTotalPlusCom() {
        return totalPlusCom;
    }

    public void setTotalPlusCom(TotalPlusCom totalPlusCom) {
        this.totalPlusCom = totalPlusCom;
    }

    public TotalDiscount getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(TotalDiscount totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getFactorTitle() {
        return factorTitle;
    }

    public void setFactorTitle(String factorTitle) {
        this.factorTitle = factorTitle;
    }

    public PassTotalCom getPassTotalCom() {
        return passTotalCom;
    }

    public void setPassTotalCom(PassTotalCom passTotalCom) {
        this.passTotalCom = passTotalCom;
    }

    public PassTotalSpecialDiscount getPassTotalSpecialDiscount() {
        return passTotalSpecialDiscount;
    }

    public void setPassTotalSpecialDiscount(PassTotalSpecialDiscount passTotalSpecialDiscount) {
        this.passTotalSpecialDiscount = passTotalSpecialDiscount;
    }

    public PassTotalClubDiscount getPassTotalClubDiscount() {
        return passTotalClubDiscount;
    }

    public void setPassTotalClubDiscount(PassTotalClubDiscount passTotalClubDiscount) {
        this.passTotalClubDiscount = passTotalClubDiscount;
    }

    public Object getPassTotalDiscount() {
        return passTotalDiscount;
    }

    public void setPassTotalDiscount(Object passTotalDiscount) {
        this.passTotalDiscount = passTotalDiscount;
    }

    public TotalHotel getTotalHotel() {
        return totalHotel;
    }

    public void setTotalHotel(TotalHotel totalHotel) {
        this.totalHotel = totalHotel;
    }

    public TotalFlight getTotalFlight() {
        return totalFlight;
    }

    public void setTotalFlight(TotalFlight totalFlight) {
        this.totalFlight = totalFlight;
    }

    public FinalPrice getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(FinalPrice finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(Integer infantCount) {
        this.infantCount = infantCount;
    }

    public AdultTotalPrice getAdultTotalPrice() {
        return adultTotalPrice;
    }

    public void setAdultTotalPrice(AdultTotalPrice adultTotalPrice) {
        this.adultTotalPrice = adultTotalPrice;
    }

    public ChildTotalPrice getChildTotalPrice() {
        return childTotalPrice;
    }

    public void setChildTotalPrice(ChildTotalPrice childTotalPrice) {
        this.childTotalPrice = childTotalPrice;
    }

    public InfantTotalPrice getInfantTotalPrice() {
        return infantTotalPrice;
    }

    public void setInfantTotalPrice(InfantTotalPrice infantTotalPrice) {
        this.infantTotalPrice = infantTotalPrice;
    }

    public String getFollowerIntTel() {
        return followerIntTel;
    }

    public void setFollowerIntTel(String followerIntTel) {
        this.followerIntTel = followerIntTel;
    }

    public String getOnlinePaymentURL() {
        return onlinePaymentURL;
    }

    public void setOnlinePaymentURL(String onlinePaymentURL) {
        this.onlinePaymentURL = onlinePaymentURL;
    }

    public Object getOnlinePaymentReturnURL() {
        return onlinePaymentReturnURL;
    }

    public void setOnlinePaymentReturnURL(Object onlinePaymentReturnURL) {
        this.onlinePaymentReturnURL = onlinePaymentReturnURL;
    }

    public Integer getPromoDisount() {
        return promoDisount;
    }

    public void setPromoDisount(Integer promoDisount) {
        this.promoDisount = promoDisount;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
