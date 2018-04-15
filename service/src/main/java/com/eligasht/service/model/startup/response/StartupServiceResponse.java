
package com.eligasht.service.model.startup.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartupServiceResponse {

    @SerializedName("MobileAppStartupServiceResult")
    @Expose
    private MobileAppStartupServiceResult mobileAppStartupServiceResult;

    public MobileAppStartupServiceResult getMobileAppStartupServiceResult() {
        return mobileAppStartupServiceResult;
    }

    public void setMobileAppStartupServiceResult(MobileAppStartupServiceResult mobileAppStartupServiceResult) {
        this.mobileAppStartupServiceResult = mobileAppStartupServiceResult;
    }

}
