
package com.eligasht.service.model.test.markdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceTestModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tryTime")
    @Expose
    private String tryTime;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("totalCall")
    @Expose
    private Integer totalCall;
    @SerializedName("close")
    @Expose
    private boolean close;
    @SerializedName("issueLink")
    @Expose
    private String issueLink;

    @SerializedName("size")
    @Expose
    private String size = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTryTime() {
        return tryTime;
    }

    public void setTryTime(String tryTime) {
        this.tryTime = tryTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(Integer totalCall) {
        this.totalCall = totalCall;
    }

    public boolean getClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getIssueLink() {
        return issueLink;
    }

    public void setIssueLink(String issueLink) {
        this.issueLink = issueLink;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void update(ServiceTestModel serviceTestModel) {
        this.message = serviceTestModel.message;
        this.tryTime = serviceTestModel.tryTime;
        this.statusCode = serviceTestModel.statusCode;
        this.totalCall = serviceTestModel.totalCall;
        this.close = serviceTestModel.close;
        this.issueLink = serviceTestModel.issueLink;
        this.size = serviceTestModel.size;
    }

    @Override
    public String toString() {
        return "ServiceTestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", tryTime='" + tryTime + '\'' +
                ", statusCode=" + statusCode +
                ", totalCall=" + totalCall +
                ", close=" + close +
                ", issueLink='" + issueLink + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
