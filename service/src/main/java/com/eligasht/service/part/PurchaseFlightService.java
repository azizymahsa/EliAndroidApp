package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;


/**
 * Created by Mahsa.azizi on 3/29/2018.
 */

public class PurchaseFlightService extends BasePart {
    public PurchaseFlightService(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void flightPurchaseAvail(OnServiceStatus<ResponsePurchaseFlight> listener, RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlight(req), listener);
    }
}
