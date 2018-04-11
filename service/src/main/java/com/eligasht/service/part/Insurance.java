package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.insurance.request.GetCountry.RequestGetCountry;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.response.GetCountry.ResponseGetCountry;
import com.eligasht.service.model.insurance.response.PurchaseInsurance.ResponsePurchaseInsurance;

public class Insurance extends BasePart{
    public Insurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    public void purchaseInsuranceAvail(OnServiceStatus<ResponsePurchaseInsurance> listener, RequestPurchaseInsurance req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(req), listener);
    }


    public void PreFactorDetailInsuranceAvail(OnServiceStatus<com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails> listener, com.eligasht.service.model.insurance.request.RequestPreFactorDetail.RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetailsInsurance(req), listener);
    }

    public void getCountryInsuranceAvail(OnServiceStatus<ResponseGetCountry> listener, RequestGetCountry req) {
        start(getServiceGenerator().createService().RESPONSE_GET_COUNTRY_OBSERVABLE(req), listener);
    }
}
