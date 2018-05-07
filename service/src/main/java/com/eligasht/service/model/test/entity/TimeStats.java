
package com.eligasht.service.model.test.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeStats {

    @SerializedName("time_estimate")
    @Expose
    private Integer timeEstimate;
    @SerializedName("total_time_spent")
    @Expose
    private Integer totalTimeSpent;
    @SerializedName("human_time_estimate")
    @Expose
    private Object humanTimeEstimate;
    @SerializedName("human_total_time_spent")
    @Expose
    private Object humanTotalTimeSpent;

    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public Integer getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Integer totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Object getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(Object humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public Object getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(Object humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

}
