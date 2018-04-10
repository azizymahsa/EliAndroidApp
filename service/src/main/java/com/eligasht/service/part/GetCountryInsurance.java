package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.insurance.request.GetCountry.RequestGetCountry;
import com.eligasht.service.model.insurance.response.GetCountry.ResponseGetCountry;

/**
 * Created by Mahsa.azizi on 3/26/2018.
 */

public class GetCountryInsurance extends BasePart {
    public GetCountryInsurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void getCountryInsuranceAvail(OnServiceStatus<ResponseGetCountry> listener, RequestGetCountry req) {
        start(getServiceGenerator().createService().RESPONSE_GET_COUNTRY_OBSERVABLE(req), listener);
    }

}
