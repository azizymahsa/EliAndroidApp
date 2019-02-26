
package com.eligasht.service.model.newModel.hotel.search.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAgentObject {

    @SerializedName("EndPointIpAddress")
    @Expose
    private String endPointIpAddress;
    @SerializedName("EndPointBrowserAgent")
    @Expose
    private String endPointBrowserAgent;
    @SerializedName("RequestOrigin")
    @Expose
    private String requestOrigin;
    @SerializedName("RequestDomain")
    @Expose
    private String requestDomain;

    public String getEndPointIpAddress() {
        return endPointIpAddress;
    }

    public void setEndPointIpAddress(String endPointIpAddress) {
        this.endPointIpAddress = endPointIpAddress;
    }

    public String getEndPointBrowserAgent() {
        return endPointBrowserAgent;
    }

    public void setEndPointBrowserAgent(String endPointBrowserAgent) {
        this.endPointBrowserAgent = endPointBrowserAgent;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestDomain() {
        return requestDomain;
    }

    public void setRequestDomain(String requestDomain) {
        this.requestDomain = requestDomain;
    }

}
