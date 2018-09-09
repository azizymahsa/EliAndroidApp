package com.eligasht.service.api;

import com.eligasht.service.model.XPackage.request.GetPreFactorDetails.RequestGePreFactorDetails;
import com.eligasht.service.model.XPackage.request.PurchasePackage.RequestPurchasePackage;
import com.eligasht.service.model.XPackage.response.GetPreFactorDetails.ResponseGePreFactorDetails;
import com.eligasht.service.model.XPackage.response.PurchasePackage.ResponsePurchasePackage;
import com.eligasht.service.model.XPackage.request.searchXPackage.RequestSearchXPackage;
import com.eligasht.service.model.XPackage.response.searchXPackage.ResponseSearchXPackage;
import com.eligasht.service.model.about.request.RequestAbout;
import com.eligasht.service.model.about.response.ResponseAbout;
import com.eligasht.service.model.hotel.addReview.request.AddHotelReviewRequest;
import com.eligasht.service.model.hotel.addReview.response.AddHotelReviewResponse;
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
import com.eligasht.service.model.hotel.detail.request.HotelDetailRequest;
import com.eligasht.service.model.hotel.detail.response.HotelDetailResponse;
import com.eligasht.service.model.hotel.getHotelList.request.GetHotelListRequest;
import com.eligasht.service.model.hotel.getHotelList.response.GetHotelListResponse;
import com.eligasht.service.model.hotel.getHotelReview.request.GetHotelReviewRequest;
import com.eligasht.service.model.hotel.getHotelReview.response.GetHotelReviewResponse;
import com.eligasht.service.model.hotel.hold.request.HoldRoomRequest;
import com.eligasht.service.model.hotel.hold.response.HoldRoomResponse;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotel.room.request.GetRoomRequest;
import com.eligasht.service.model.hotel.room.response.GetRoomResponse;
import com.eligasht.service.model.hotel.transport.request.TransportRequest;
import com.eligasht.service.model.hotel.transport.response.TransportResponse;
import com.eligasht.service.model.hotelflight.purchase.request.PishFactor.RequestPurchaseService;
import com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.RequestPurchaseFlightHotel;
import com.eligasht.service.model.hotelflight.purchase.response.PishFactor.ResponsePurchaseService;
import com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.ResponsePurchaseFlightHotel;
import com.eligasht.service.model.hotelflight.search.request.HotelFlightRequest;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicyRequest;
import com.eligasht.service.model.hotelpolicy.response.HotelPolicyResponse;
import com.eligasht.service.model.insurance.request.GetCountry.RequestGetCountry;
import com.eligasht.service.model.insurance.request.PurchaseInsurance.RequestPurchaseInsurance;
import com.eligasht.service.model.insurance.request.SearchInsurance.RequestSearchInsurance;
import com.eligasht.service.model.insurance.response.GetCountry.ResponseGetCountry;
import com.eligasht.service.model.insurance.response.PurchaseInsurance.ResponsePurchaseInsurance;
import com.eligasht.service.model.hotelflight.search.response.HotelFlightResponse;
import com.eligasht.service.model.insurance.response.SearchInsurance.ResponseSearchInsurance;
import com.eligasht.service.model.loadflight.request.LoadFlightRequest;
import com.eligasht.service.model.loadflight.response.LoadFlightResponse;
import com.eligasht.service.model.login.request.LoginRequestModel;
import com.eligasht.service.model.login.response.LoginResponse;
import com.eligasht.service.model.startup.request.StartupServiceRequest;
import com.eligasht.service.model.startup.response.StartupServiceResponse;
import com.eligasht.service.model.survey.request.RequestGetSurvey;
import com.eligasht.service.model.survey.request.addServeyResult.RequestSetAnswer;
import com.eligasht.service.model.survey.request.detail.RequestGetSurveyDetails;
import com.eligasht.service.model.survey.response.ResponseGetSurvey;
import com.eligasht.service.model.survey.response.addServeyResult.ResponseSetAnswer;
import com.eligasht.service.model.survey.response.detail.ResponseGetSurveyDetails;
import com.eligasht.service.model.test.entity.TestRes;
import com.eligasht.service.model.weather.response.WeatherApi;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public interface RetroClient {
    //*************start up*************
    @POST(Const.StartUp)
    Observable<Response<StartupServiceResponse>> startUp(
            @Body StartupServiceRequest startupServiceRequest
    );

    //*************hotel flight*************
    @POST(Const.HotelFlightSearch)
    Observable<Response<HotelFlightResponse>> hotelFlight(
            @Body HotelFlightRequest hotelFlightRequest
    );

    @POST(Const.LoadFlight)
    Observable<Response<LoadFlightResponse>> loadFlight(
            @Body LoadFlightRequest loadFlightRequest
    );

    @POST(Const.AirportTransportServicePrice)
    Observable<Response<TransportResponse>> transportService(
            @Body TransportRequest transportRequest
    );

    @POST(Const.HotelFlightPurchaseServic)
    Observable<Response<ResponsePurchaseService>> RESPONSE_OBSERVABLE(
            @Body RequestPurchaseService requestPurchaseService
    );

    @POST(Const.PurchaseFlightHotel)
    Observable<Response<ResponsePurchaseFlightHotel>> HotelFlightPurchaseResponse(
            @Body RequestPurchaseFlightHotel hotelFlightPurchaseRequest
    );

    //*************hotel*************
    @POST(Const.HotelAvail)
    Observable<Response<HotelAvailRes>> hotelAvail(
            @Body HotelAvailReq hotelAvailReq
    );

    @POST(Const.HotelPolicy)
    Observable<Response<HotelPolicyResponse>> hotelPolicy(
            @Body HotelPolicyRequest hotelPolicyRequest
    );

    @POST(Const.GetRoomsList)
    Observable<Response<GetRoomResponse>> getRoomsList(
            @Body GetRoomRequest getRoomRequest
    );

    @POST(Const.GetHotelDetail)
    Observable<Response<HotelDetailResponse>> getHotelDetail(
            @Body HotelDetailRequest hotelDetailRequest
    );

    @POST(Const.HoldSelectedRoom)
    Observable<Response<HoldRoomResponse>> getHoldRoom(
            @Body HoldRoomRequest holdRoomRequest
    );

    @POST(Const.AddHotelReview)
    Observable<Response<AddHotelReviewResponse>> addHotelReview(
            @Body AddHotelReviewRequest hotelReviewRequest
    );

    @POST(Const.GetHotelList)
    Observable<Response<GetHotelListResponse>> getHotelList(
            @Body GetHotelListRequest hotelReviewRequest
    );

    @POST(Const.GetHotelReview)
    Observable<Response<GetHotelReviewResponse>> getHotelReview(
            @Body GetHotelReviewRequest hotelAvailReq
    );

    //*************flight*************


    @POST(Const.AirportAvail)
    Observable<Response<ResponsAirports>> responsAirports(
            @Body RequestAirports requestAirports
    );

    //*************flight*************
    @POST(Const.FlightSearchAvail)
    Observable<Response<ResponsSearchFlight>> responsSearchFlight(
            @Body RequestSearchFlight requestSearchFlight
    );

    @POST(Const.PurchaseServiceFlightAvil)
    Observable<Response<ResponsePurchaseFlight>> responsePurchaseFlight(
            @Body RequestPurchaseFlight requestPurchaseFlight
    );

    @POST(Const.PreFactorDetailsAvil)
    Observable<Response<ResponsePreFactorDetails>> responsePreFactorDetails(
            @Body RequestPreFactorDetails requestPreFactorDetails
    );

    @POST(Const.CheckFlightAvail)
    Observable<Response<ResponseDomesticFlight>> responseDomesticFlightObservable(
            @Body RequestDomesticFlight requestDomesticFlight
    );

    @POST(Const.PurchaseFlightAvil)
    Observable<Response<com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight>> responsePurchaseFlightObservable(
            @Body com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight requestPurchaseFlightPassenger
    );

    @POST(Const.ChangeFlightAvil)
    Observable<Response<ResponseChangeFlight>> responsChangeFlight(
            @Body RequestChangeFlight requestChangeFlight
    );

    //*************contactUs*************
    @POST(Const.ContactUsAvil)
    Observable<Response<ResponseContactUs>> responsContactUs(
            @Body RequestContactUs requestContactUs
    );

    //*************About*************
    @POST(Const.AboutAvil)
    Observable<Response<ResponseAbout>> responseAboutObservable(
            @Body RequestAbout requestAbout
    );
    //*************Survey*************
    @POST(Const.GetSurveyAvil)
    Observable<Response<ResponseGetSurvey>> responseGetSurveyObservable(
            @Body RequestGetSurvey requestGetSurvey
    );
    @POST(Const.GetSurveyDetailsAvil)
    Observable<Response<ResponseGetSurveyDetails>> responseGetSurveyDetailsObservable(
            @Body RequestGetSurveyDetails requestGetSurveyDetails
    );
    @POST(Const.AddSurveyResultAvil)
    Observable<Response<ResponseSetAnswer>> RESPONSE_SETANSWER(
            @Body RequestSetAnswer requestSetAnswer
    );

    //*************insurance*************
    @POST(Const.PurchaseInsuranceAvil)
    Observable<Response<ResponsePurchaseInsurance>> RESPONSE_PURCHASE_INSURANCE_OBSERVABLE(
            @Body RequestPurchaseInsurance requestPurchaseInsurance
    );

    @POST(Const.PreFactorDetailsInsuranceAvil)
    Observable<Response<com.eligasht.service.model.insurance.response.ResponsePreFactorDetail.ResponsePreFactorDetails>> responsePreFactorDetailsInsurance(
            @Body com.eligasht.service.model.insurance.request.RequestPreFactorDetail.RequestPreFactorDetails requestPreFactorDetails
    );

    @POST(Const.GetCountryInsuranceAvil)
    Observable<Response<ResponseGetCountry>> RESPONSE_GET_COUNTRY_OBSERVABLE(
            @Body RequestGetCountry requestGetCountry
    );

    @POST(Const.SearchInsuranceAvil)
    Observable<Response<ResponseSearchInsurance>> RESPONSE_SEARCH_INSURANCE_OBSERVABLE(
            @Body RequestSearchInsurance requestSearchInsurance2
    );

    //*************package*************
    @POST(Const.SearchXPackageAvil)
    Observable<Response<ResponseSearchXPackage>> RESPONSE_SEARCH_X_PACKAGE_OBSERVABLE(
            @Body RequestSearchXPackage requestSearchXPackage
    );

    @POST(Const.PurchasePackageAvil)
    Observable<Response<ResponsePurchasePackage>> RESPONSE_PURCHASE_PACKAGE_OBSERVABLE(
            @Body RequestPurchasePackage requestPurchasePackage
    );

    @POST(Const.GetPreFactorDetailsPackageAvil)
    Observable<Response<ResponseGePreFactorDetails>> RESPONSE_GE_PRE_FACTOR_DETAILS_OBSERVABLE(
            @Body RequestGePreFactorDetails requestGePreFactorDetailsPack
    );


    //*************Login & Profile*************

    @POST(Const.Login)
    Observable<Response<LoginResponse>> login(
            @Body LoginRequestModel loginRequestModel
    );

    @Headers("PRIVATE-TOKEN: veyZ8vRQLged8ukLr65R")
    @PUT("issues/{issue}")
    Observable<Response<TestRes>> issueTrack(
            @Path("issue") int id,
            @Query("state_event") String state,
            @Query("labels") String label,
            @Query("description") String description
    );



    @GET("yql")
    Observable<Response<WeatherApi>> yahooWeather(
            @Query("q") String query,
            @Query("format") String format
    );





   /* @POST(Const.GetPreFactorDetailsPackageAvil)
    Observable<ResponseGePreFactorDetails> RESPONSE_GE_PRE_FACTOR_DETAILS_OBSERVABLE(
            @Body RequestGePreFactorDetails requestGePreFactorDetailsPack
    );*/
}
