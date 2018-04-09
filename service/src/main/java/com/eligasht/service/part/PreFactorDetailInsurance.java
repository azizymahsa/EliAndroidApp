package com.eligasht.service.part;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.insurance.request.RequestPreFactorDetail.RequestPreFactorDetails;
import com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails;


/**
 * Created by Mahsa.azizi on 3/29/2018.
 */

public class PreFactorDetailInsurance extends BasePart {
    public PreFactorDetailInsurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    public void PreFactorDetailInsuranceAvail(OnServiceStatus<ResponsePreFactorDetails> listener , RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetailsInsurance(req), listener);
    }

}

/*

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;


*/
/**
 * Created by Mahsa.azizi on 4/9/2018.
 *//*


public class PreFactorDetailInsurance {
    public PreFactorDetailInsurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void preFactorDetailInsuranceAvail(OnServiceStatus<ResponsePreFactorDetails> listener, RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().(req), listener);
    }
}
*/
