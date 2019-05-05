package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
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
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.airport.response.ResponseAirport;
import com.eligasht.service.model.newModel.auth.request.RequestAuth;
import com.eligasht.service.model.newModel.auth.response.ResponseAuth;
import com.eligasht.service.model.newModel.flight.confirmFlightPrice.request.RequestConfirmFlightPrice;
import com.eligasht.service.model.newModel.flight.prefactor.request.RequestGetPreFactor;
import com.eligasht.service.model.newModel.flight.prefactor.response.ResponseGetPreFactor;
import com.eligasht.service.model.newModel.flight.purchaseFlight.request.PurchaseFlightParameterModel;
import com.eligasht.service.model.newModel.flight.purchaseFlight.response.TmpReserveResult;
import com.eligasht.service.model.newModel.flight.purchaseServices.request.RequestGetPurchaseServices;
import com.eligasht.service.model.newModel.flight.purchaseServices.response.ResponsePurchaseServices;
import com.eligasht.service.model.newModel.flight.services.request.RequestGetServices;
import com.eligasht.service.model.newModel.flight.services.response.ResponseGetServices;
import com.eligasht.service.model.newModel.hotelFlight.loadFlight.request.RequestLoadFlight;
import com.eligasht.service.model.newModel.hotelFlight.loadFlight.response.ResponseLoadFlight;
import com.eligasht.service.model.newModel.promotion.request.RequestPromotionCode;
import com.eligasht.service.model.newModel.promotion.response.ResponsePromotionCode;


import java.util.List;


public class Flight extends BasePart {
    public Flight(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    @Mock(jsonName = "get_airport_with_parents_with_culture", response = ResponsAirports.class)
    public void airPortsAvail(OnServiceStatus<ResponsAirports> listener, RequestAirports req) {
        start(getServiceGenerator().createService().responsAirports(req), listener);
    }

    @Mock(jsonName = "search_flights", response = ResponsSearchFlight.class)
    public void flightSearchAvail(OnServiceStatus<ResponsSearchFlight> listener, RequestSearchFlight req) {
        start(getServiceGenerator().createService().responsSearchFlight(req), listener);
    }

    @Mock(jsonName = "flight_pre_factor_detail_avail", response = ResponsePreFactorDetails.class)
    public void flightPreFactorDetailAvail(OnServiceStatus<ResponsePreFactorDetails> listener, RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetails(req), listener);
    }

    @Mock(jsonName = "purchase_flight", response = ResponsePurchaseFlight.class)
    public void purchaseFlightAvail(OnServiceStatus<ResponsePurchaseFlight> listener, RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlightObservable(req), listener);
    }

    @Mock(jsonName = "purchase_service", response = com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight.class)
    public void flightPurchaseAvail(OnServiceStatus<com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight> listener, com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight req) {
        start(getServiceGenerator().createService().responsePurchaseFlight(req), listener);
    }

    @Mock(jsonName = "change_flight_avail", response = ResponseChangeFlight.class)
    public void ChangeFlightAvail(OnServiceStatus<ResponseChangeFlight> listener, RequestChangeFlight req) {
        start(getServiceGenerator().createService().responsChangeFlight(req), listener);
    }

    @Mock(jsonName = "get_is_domestic", response = ResponseDomesticFlight.class)
    public void domesticFlightAvail(OnServiceStatus<ResponseDomesticFlight> listener, RequestDomesticFlight req) {
        start(getServiceGenerator().createService().responseDomesticFlightObservable(req), listener);
    }
    //*************NEW******************************************************************
    //*************Flight*****************
  //  @Mock(jsonName = "get_new_airports", response = String)
    public void newAirportsAvail(OnServiceStatus<List<ResponseAirport>> listener, AutoCompleteParameterModel req) {
        start(getServiceGenerator().createService().responseNewAirportsObservable(req), listener);
    }
    public void newFlightSearchAvail(OnServiceStatus<com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight> listener, com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight requestSearchFlightt) {
        start(getServiceGenerator().createService().responseSearchFlightObservable(requestSearchFlightt), listener);
    }
    public void newConfirmFlightPriceAvail(OnServiceStatus<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.ResponseSearchFlight> listener, RequestConfirmFlightPrice requestSearchFlightt) {
        start(getServiceGenerator().createService().responseConfirmFlightPriceObservable(requestSearchFlightt), listener);
    }
    public void newPurchaseFlightAvail(OnServiceStatus<TmpReserveResult> listener, PurchaseFlightParameterModel purchaseFlightParameterModel) {
        start(getServiceGenerator().createService().responsePurchaseFlightObservable(purchaseFlightParameterModel), listener);
    }
    public void newGetServicesAvail(OnServiceStatus<ResponseGetServices> listener, RequestGetServices requestGetServices) {
        start(getServiceGenerator().createService().responseGetServicesObservable(requestGetServices), listener);
    }
    public void newGetPurchaseServiceAvail(OnServiceStatus<ResponsePurchaseServices> listener, RequestGetPurchaseServices requestGetPurchaseServices) {
        start(getServiceGenerator().createService().responseGetGetPurchaseServiceObservable(requestGetPurchaseServices), listener);
    }
    public void newGetPreFactorServiceAvail(OnServiceStatus<ResponseGetPreFactor> listener, RequestGetPreFactor requestGetPreFactor) {
        start(getServiceGenerator().createService().responseGetPreFactorObservable(requestGetPreFactor), listener);
    }
    //*************PromotionCode*****************
    public void newGetPromotionCodeAvail(OnServiceStatus<ResponsePromotionCode> listener, RequestPromotionCode requestPromotionCode) {
        start(getServiceGenerator().createService().responsePromotionCodeObservable(requestPromotionCode), listener);
    }

    public void newGetAuthAvail(OnServiceStatus<ResponseAuth> listener, RequestAuth reqAuth) {
        start(getServiceGenerator().createService().responseGetAuthObservable(reqAuth), listener);
    }

    public void newGetChangeFlight(OnServiceStatus<com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ResponseChangeFlight> listener, com.eligasht.service.model.newModel.hotelFlight.changeFlight.request.RequestChangeFlight requestChangeFlight) {
        start(getServiceGenerator().createService().responseChangeFlighObs(requestChangeFlight), listener);
    }
    public void newGetLoadFlight(OnServiceStatus<ResponseLoadFlight> listener, RequestLoadFlight requestLoadFlight) {
        start(getServiceGenerator().createService().responseLoadFlight(requestLoadFlight), listener);
    }
}
