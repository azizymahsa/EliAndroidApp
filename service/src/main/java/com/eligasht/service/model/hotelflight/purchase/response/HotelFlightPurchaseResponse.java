
package com.eligasht.service.model.hotelflight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelFlightPurchaseResponse {

    @SerializedName("PurchaseFlightHotelResult")
    @Expose
    private PurchaseFlightHotelResult purchaseFlightHotelResult;

    public PurchaseFlightHotelResult getPurchaseFlightHotelResult() {
        return purchaseFlightHotelResult;
    }

    public void setPurchaseFlightHotelResult(PurchaseFlightHotelResult purchaseFlightHotelResult) {
        this.purchaseFlightHotelResult = purchaseFlightHotelResult;
    }

}
