package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.DomesticFlight.RequestDomesticFlight;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.response.DomesticFlight.ResponseDomesticFlight;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;


/**
 * Created by Mahsa.azizi on 3/29/2018.
 */

public class CheckFlight extends BasePart {
    public CheckFlight(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void domesticFlightAvail(OnServiceStatus<ResponseDomesticFlight> listener, RequestDomesticFlight req) {
        start(getServiceGenerator().createService().responseDomesticFlightObservable(req), listener);
    }
}
