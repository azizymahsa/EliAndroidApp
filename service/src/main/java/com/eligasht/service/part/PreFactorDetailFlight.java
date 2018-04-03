package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;


/**
 * Created by Mahsa.azizi on 3/29/2018.
 */

public class PreFactorDetailFlight extends BasePart {
    public PreFactorDetailFlight(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    public void flightPreFactorDetailAvail(OnServiceStatus<ResponsePreFactorDetails> listener , RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetails(req), listener);
    }

}
