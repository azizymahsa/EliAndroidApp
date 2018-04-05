package com.eligasht.service.api;

import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.part.FlightSearch;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface RetroClient {
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
}
