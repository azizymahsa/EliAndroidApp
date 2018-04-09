package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.response.ResponsePurchaseInsurance;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class PurchaseInsurance extends BasePart {
    public PurchaseInsurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void purchaseInsuranceAvail(OnServiceStatus<ResponsePurchaseInsurance> listener, RequestPurchaseInsurance req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(req), listener);
    }

}
