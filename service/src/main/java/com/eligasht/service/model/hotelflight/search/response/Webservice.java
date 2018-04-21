
package com.eligasht.service.model.hotelflight.search.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Webservice {

    @SerializedName("Account_ID")
    @Expose
    private Integer accountID;
    @SerializedName("AgentID")
    @Expose
    private String agentID;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("HWSAcc_ID")
    @Expose
    private Integer hWSAccID;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Pasword")
    @Expose
    private Object pasword;
    @SerializedName("Residence")
    @Expose
    private Object residence;
    @SerializedName("SearchType")
    @Expose
    private Integer searchType;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("URL")
    @Expose
    private Object uRL;
    @SerializedName("UserName")
    @Expose
    private Object userName;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getHWSAccID() {
        return hWSAccID;
    }

    public void setHWSAccID(Integer hWSAccID) {
        this.hWSAccID = hWSAccID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Object getPasword() {
        return pasword;
    }

    public void setPasword(Object pasword) {
        this.pasword = pasword;
    }

    public Object getResidence() {
        return residence;
    }

    public void setResidence(Object residence) {
        this.residence = residence;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getURL() {
        return uRL;
    }

    public void setURL(Object uRL) {
        this.uRL = uRL;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

}
