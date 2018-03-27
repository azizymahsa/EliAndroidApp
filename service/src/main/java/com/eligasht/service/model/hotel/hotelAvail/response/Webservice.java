
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Webservice {

    @SerializedName("Account_ID")
    @Expose
    private int accountID;
    @SerializedName("AgentID")
    @Expose
    private String agentID;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("HWSAcc_ID")
    @Expose
    private int hWSAccID;
    @SerializedName("Id")
    @Expose
    private int id;
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
    private int searchType;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Type")
    @Expose
    private int type;
    @SerializedName("URL")
    @Expose
    private Object uRL;
    @SerializedName("UserName")
    @Expose
    private Object userName;

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
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

    public int getHWSAccID() {
        return hWSAccID;
    }

    public void setHWSAccID(int hWSAccID) {
        this.hWSAccID = hWSAccID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}
    public String toString() {
        return new ToStringBuilder(this).append("accountID", accountID).append("agentID", agentID).append("cityCode", cityCode).append("hWSAccID", hWSAccID).append("id", id).append("nationality", nationality).append("pasword", pasword).append("residence", residence).append("searchType", searchType).append("title", title).append("type", type).append("uRL", uRL).append("userName", userName).toString();
    }

}
