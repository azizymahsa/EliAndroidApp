
package com.eligasht.service.model.newModel.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsuranceCoverage {

    @SerializedName("TravelInsuranceCoverageCategory")
    @Expose
    private TravelInsuranceCoverageCategory travelInsuranceCoverageCategory;
    @SerializedName("InsuranceCoverageID")
    @Expose
    private Object insuranceCoverageID;
    @SerializedName("TileFa")
    @Expose
    private String tileFa;
    @SerializedName("TileEn")
    @Expose
    private String tileEn;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("AdvanceValue")
    @Expose
    private Object advanceValue;
    @SerializedName("InsCvrg_Plan1")
    @Expose
    private Boolean insCvrgPlan1;
    @SerializedName("InsCvrg_Plan2")
    @Expose
    private Boolean insCvrgPlan2;

    public TravelInsuranceCoverageCategory getTravelInsuranceCoverageCategory() {
        return travelInsuranceCoverageCategory;
    }

    public void setTravelInsuranceCoverageCategory(TravelInsuranceCoverageCategory travelInsuranceCoverageCategory) {
        this.travelInsuranceCoverageCategory = travelInsuranceCoverageCategory;
    }

    public Object getInsuranceCoverageID() {
        return insuranceCoverageID;
    }

    public void setInsuranceCoverageID(Object insuranceCoverageID) {
        this.insuranceCoverageID = insuranceCoverageID;
    }

    public String getTileFa() {
        return tileFa;
    }

    public void setTileFa(String tileFa) {
        this.tileFa = tileFa;
    }

    public String getTileEn() {
        return tileEn;
    }

    public void setTileEn(String tileEn) {
        this.tileEn = tileEn;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getAdvanceValue() {
        return advanceValue;
    }

    public void setAdvanceValue(Object advanceValue) {
        this.advanceValue = advanceValue;
    }

    public Boolean getInsCvrgPlan1() {
        return insCvrgPlan1;
    }

    public void setInsCvrgPlan1(Boolean insCvrgPlan1) {
        this.insCvrgPlan1 = insCvrgPlan1;
    }

    public Boolean getInsCvrgPlan2() {
        return insCvrgPlan2;
    }

    public void setInsCvrgPlan2(Boolean insCvrgPlan2) {
        this.insCvrgPlan2 = insCvrgPlan2;
    }

}
