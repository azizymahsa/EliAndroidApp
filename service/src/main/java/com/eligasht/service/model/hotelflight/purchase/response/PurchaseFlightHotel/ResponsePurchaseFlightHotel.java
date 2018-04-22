
package com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePurchaseFlightHotel {

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
