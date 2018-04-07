package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class PurchaseFlightPassenger extends BasePart {
    public PurchaseFlightPassenger(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void purchaseFlightAvail(OnServiceStatus<ResponsePurchaseFlight> listener, RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlightObservable(req), listener);
    }

}
