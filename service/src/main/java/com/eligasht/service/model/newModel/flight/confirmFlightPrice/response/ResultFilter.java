
package com.eligasht.service.model.newModel.flight.confirmFlightPrice.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultFilter {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Icon")
    @Expose
    private Object icon;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ResultFilters")
    @Expose
    private List<ResultFilter_> resultFilters = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ResultFilter_> getResultFilters() {
        return resultFilters;
    }

    public void setResultFilters(List<ResultFilter_> resultFilters) {
        this.resultFilters = resultFilters;
    }

}
