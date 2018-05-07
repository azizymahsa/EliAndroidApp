
package com.eligasht.service.model.test.markdown;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeaderTestService {

    @SerializedName("headerName")
    @Expose
    private String headerName;
    @SerializedName("serviceTestModel")
    @Expose
    private List<ServiceTestModel> serviceTestModel = null;

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public List<ServiceTestModel> getServiceTestModel() {
        return serviceTestModel;
    }

    public void setServiceTestModel(List<ServiceTestModel> serviceTestModel) {
        this.serviceTestModel = serviceTestModel;
    }

    public HeaderTestService(String headerName, List<ServiceTestModel> serviceTestModel) {
        this.headerName = headerName;
        this.serviceTestModel = serviceTestModel;
    }
}
