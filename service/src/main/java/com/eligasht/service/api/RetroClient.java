package com.eligasht.service.api;

import com.eligasht.service.model.about.request.RequestAbout;
import com.eligasht.service.model.about.response.ResponseAbout;
import com.eligasht.service.model.flight.request.ChangeFlight.RequestChangeFlight;
import com.eligasht.service.model.flight.request.DomesticFlight.RequestDomesticFlight;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.ChangeFlight.ResponseChangeFlight;
import com.eligasht.service.model.flight.response.DomesticFlight.ResponseDomesticFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.response.ResponsePurchaseInsurance;
import com.eligasht.service.part.FlightSearch;
import com.eligasht.service.model.hotelflight.response.HotelFlightResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface RetroClient {

    //*************hotel flight*************
    @POST(Const.HotelFlightSearch)
    Observable<HotelFlightResponse> hotelFlight(
            @Body HotelAvailReq hotelAvailReq
    );


    //*************hotel*************

    @POST(Const.HotelAvail)
    Observable<HotelAvailRes> hotelAvail(
            @Body HotelAvailReq hotelAvailReq
    );

    @POST(Const.AirportAvail)
    Observable<ResponsAirports> responsAirports(
                    @Body RequestAirports requestAirports
            );
    @POST(Const.FlightSearchAvail)
    Observable<ResponsSearchFlight> responsSearchFlight(
            @Body RequestSearchFlight requestSearchFlight
    );
    @POST(Const.PurchaseServiceFlightAvil)
    Observable<ResponsePurchaseFlight> responsePurchaseFlight(
            @Body RequestPurchaseFlight requestPurchaseFlight
    );
    @POST(Const.PreFactorDetailsAvil)
    Observable<ResponsePreFactorDetails> responsePreFactorDetails(
            @Body RequestPreFactorDetails requestPreFactorDetails
    );
    @POST(Const.CheckFlightAvail)
    Observable<ResponseDomesticFlight> responseDomesticFlightObservable(
            @Body RequestDomesticFlight requestDomesticFlight
    );
    @POST(Const.PurchaseFlightAvil)
    Observable<com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight> responsePurchaseFlightObservable(
            @Body com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight requestPurchaseFlightPassenger
    );
    @POST(Const.ContactUsAvil)
    Observable<ResponseContactUs> responsContactUs(
            @Body RequestContactUs requestContactUs
    );
    @POST(Const.AboutAvil)
    Observable<ResponseAbout> responseAboutObservable(
            @Body RequestAbout requestAbout
    );
    @POST(Const.ChangeFlightAvil)
    Observable<ResponseChangeFlight> responsChangeFlight(
            @Body RequestChangeFlight requestChangeFlight
    );
    @POST(Const.PurchaseInsuranceAvil)
    Observable<ResponsePurchaseInsurance> RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(
            @Body RequestPurchaseInsurance requestPurchaseInsurance
    );




}
