package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.ChangeFlight.RequestChangeFlight;
import com.eligasht.service.model.flight.request.DomesticFlight.RequestDomesticFlight;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.ChangeFlight.ResponseChangeFlight;
import com.eligasht.service.model.flight.response.DomesticFlight.ResponseDomesticFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.response.PurchaseInsurance.ResponsePurchaseInsurance;

public class Flight extends BasePart {
    public Flight(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    public void airPortsAvail(OnServiceStatus<ResponsAirports> listener, RequestAirports req) {
        start(getServiceGenerator().createService().responsAirports(req), listener);
    }

    public void flightSearchAvail(OnServiceStatus<ResponsSearchFlight> listener, RequestSearchFlight req) {
        start(getServiceGenerator().createService().responsSearchFlight(req), listener);
    }

    public void flightPreFactorDetailAvail(OnServiceStatus<ResponsePreFactorDetails> listener, RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetails(req), listener);
    }

    public void PreFactorDetailInsuranceAvail(OnServiceStatus<com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails> listener, com.eligasht.service.model.insurance.request.RequestPreFactorDetail.RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetailsInsurance(req), listener);
    }

    public void purchaseFlightAvail(OnServiceStatus<ResponsePurchaseFlight> listener, RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlightObservable(req), listener);
    }

    public void flightPurchaseAvail(OnServiceStatus<com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight> listener, com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlight(req), listener);
    }

    public void purchaseInsuranceAvail(OnServiceStatus<ResponsePurchaseInsurance> listener, RequestPurchaseInsurance req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(req), listener);
    }

    public void ChangeFlightAvail(OnServiceStatus<ResponseChangeFlight> listener, RequestChangeFlight req) {
        start(getServiceGenerator().createService().responsChangeFlight(req), listener);
    }

    public void domesticFlightAvail(OnServiceStatus<ResponseDomesticFlight> listener, RequestDomesticFlight req) {
        start(getServiceGenerator().createService().responseDomesticFlightObservable(req), listener);
    }
}
