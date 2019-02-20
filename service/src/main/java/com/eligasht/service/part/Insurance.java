package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.model.insurance.request.GetCountry.RequestGetCountry;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.request.SearchInsurance.RequestSearchInsurance;
import com.eligasht.service.model.insurance.response.GetCountry.ResponseGetCountry;
import com.eligasht.service.model.insurance.response.PurchaseInsurance.ResponsePurchaseInsurance;
import com.eligasht.service.model.insurance.response.SearchInsurance.ResponseSearchInsurance;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.insurance.request.searchInsurance.RequestInsuranceSearchResultViewModel;
import com.eligasht.service.model.newModel.insurance.request.purchase.PurchaseParameterModel;
import com.eligasht.service.model.newModel.insurance.response.InsuranceCountry.ResponseInsuranceCountry;
import com.eligasht.service.model.newModel.insurance.response.ResponseInsuranceSearchResult;
import com.eligasht.service.model.newModel.insurance.response.purchase.ResponseInsurancePurchase;

import java.util.List;

public class Insurance extends BasePart {
    public Insurance(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    @Mock(jsonName = "purchase_insurance", response = ResponsePurchaseInsurance.class)
    public void purchaseInsuranceAvail(OnServiceStatus<ResponsePurchaseInsurance> listener, RequestPurchaseInsurance req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(req), listener);
    }

    @Mock(jsonName = "get_pre_factor_details", response = com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails.class)
    public void PreFactorDetailInsuranceAvail(OnServiceStatus<com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails> listener, com.eligasht.service.model.insurance.request.RequestPreFactorDetail.RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetailsInsurance(req), listener);
    }

    @Mock(jsonName = "get_country_ajax_with_culture", response = ResponseGetCountry.class)
    public void getCountryInsuranceAvail(OnServiceStatus<ResponseGetCountry> listener, RequestGetCountry req) {
        start(getServiceGenerator().createService().RESPONSE_GET_COUNTRY_OBSERVABLE(req), listener);
    }
    @Mock(jsonName = "search_insurance_avail", response =ResponseSearchInsurance.class )
    public void getSearchInsuranceAvail(OnServiceStatus<ResponseSearchInsurance> listener, RequestSearchInsurance req) {
        start(getServiceGenerator().createService().RESPONSE_SEARCH_INSURANCE_OBSERVABLE(req), listener);
    }
    //*************NEW*****************
    public void newInsuranceCountriesAvail(OnServiceStatus<List<ResponseInsuranceCountry>> listener, AutoCompleteParameterModel req) {
        start(getServiceGenerator().createService().responseNewInsuranceCountriesObservable(req), listener);
    }
    public void newInsuranceSearchAvail(OnServiceStatus<ResponseInsuranceSearchResult> listener, RequestInsuranceSearchResultViewModel req) {
        start(getServiceGenerator().createService().responseInsuranceSearchResult(req), listener);
    }
    public void newInsurancePurchaseAvail(OnServiceStatus<ResponseInsurancePurchase> listener, PurchaseParameterModel req) {
        start(getServiceGenerator().createService().responsePurchaseParameterModel(req), listener);
    }
}
