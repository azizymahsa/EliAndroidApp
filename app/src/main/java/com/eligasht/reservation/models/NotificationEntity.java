
package com.eligasht.reservation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationEntity {

    @SerializedName("google.sent_time")
    @Expose
    private long googleSentTime;
    @SerializedName("google.ttl")
    @Expose
    private Integer googleTtl;
    @SerializedName("custom")
    @Expose
    private String custom;
    @SerializedName("oth_chnl")
    @Expose
    private String othChnl;
    @SerializedName("pri")
    @Expose
    private String pri;
    @SerializedName("vis")
    @Expose
    private String vis;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("grp_msg")
    @Expose
    private String grpMsg;
    @SerializedName("google.message_id")
    @Expose
    private String googleMessageId;
    @SerializedName("notificationId")
    @Expose
    private Integer notificationId;

    public long getGoogleSentTime() {
        return googleSentTime;
    }

    public void setGoogleSentTime(Integer googleSentTime) {
        this.googleSentTime = googleSentTime;
    }

    public Integer getGoogleTtl() {
        return googleTtl;
    }

    public void setGoogleTtl(Integer googleTtl) {
        this.googleTtl = googleTtl;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getOthChnl() {
        return othChnl;
    }

    public void setOthChnl(String othChnl) {
        this.othChnl = othChnl;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrpMsg() {
        return grpMsg;
    }

    public void setGrpMsg(String grpMsg) {
        this.grpMsg = grpMsg;
    }

    public String getGoogleMessageId() {
        return googleMessageId;
    }

    public void setGoogleMessageId(String googleMessageId) {
        this.googleMessageId = googleMessageId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "googleSentTime=" + googleSentTime +
                ", googleTtl=" + googleTtl +
                ", custom='" + custom + '\'' +
                ", othChnl='" + othChnl + '\'' +
                ", pri='" + pri + '\'' +
                ", vis='" + vis + '\'' +
                ", from='" + from + '\'' +
                ", alert='" + alert + '\'' +
                ", title='" + title + '\'' +
                ", grpMsg='" + grpMsg + '\'' +
                ", googleMessageId='" + googleMessageId + '\'' +
                ", notificationId=" + notificationId +
                '}';
    }
}
