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
import com.eligasht.service.model.newModel.hotel.basket.request.RequestHotelFlightBasket;
import com.eligasht.service.model.newModel.hotel.basket.response.ResponseSelectedHotelFlightBasket;
import com.eligasht.service.model.newModel.hotel.getRoom.request.RequestGetRoomsList;
import com.eligasht.service.model.newModel.hotel.getRoom.response.ResponseGetRoomsList;
import com.eligasht.service.model.newModel.hotel.holdSelectRoom.request.RequestHoldSelectRoom;
import com.eligasht.service.model.newModel.hotel.holdSelectRoom.response.ResponseHoldSelectRoom;
import com.eligasht.service.model.newModel.hotel.hotelDetail.request.RequestHotelDetails;
import com.eligasht.service.model.newModel.hotel.hotelDetail.response.ResponseHotelDetails;
import com.eligasht.service.model.newModel.hotel.policy.request.RequestHotelPolicy;
import com.eligasht.service.model.newModel.hotel.policy.response.ResponseHotelPolicy;
import com.eligasht.service.model.newModel.hotel.preSearch.request.RequestHotelPreSearch;
import com.eligasht.service.model.newModel.hotel.preSearch.response.ResponseHotelPreSearch;
import com.eligasht.service.model.newModel.hotel.purchase.request.RequestHotelPurchase;
import com.eligasht.service.model.newModel.hotel.reserve.request.RequestReserveFlightHotel;
import com.eligasht.service.model.newModel.hotel.reserve.response.ResponseReserveFlightHotel;
import com.eligasht.service.model.newModel.hotel.review.request.RequestAddHotelReview;
import com.eligasht.service.model.newModel.hotel.search.request.RequestHotelSearch;
import com.eligasht.service.model.newModel.hotel.search.response.ResponseHotelSearch;
import com.eligasht.service.model.newModel.hotelFlight.request.RequestHotelFlight;
import com.eligasht.service.model.newModel.hotelFlight.response.ResponseHotelFlight;
import com.eligasht.service.model.newModel.insurance.request.searchInsurance.RequestInsuranceSearchResultViewModel;
import com.eligasht.service.model.newModel.insurance.request.purchase.PurchaseParameterModel;
import com.eligasht.service.model.newModel.insurance.response.InsuranceCountry.ResponseInsuranceCountry;
import com.eligasht.service.model.newModel.insurance.response.ResponseInsuranceSearchResult;
import com.eligasht.service.model.newModel.insurance.response.purchase.ResponseInsurancePurchase;
import com.eligasht.service.model.newModel.xpackage.packageBasket.request.GetPackageBasketParameterModel;
import com.eligasht.service.model.newModel.xpackage.packageBasket.response.ResponseGetPackageBasket;
import com.eligasht.service.model.newModel.xpackage.packageCity.response.ResponseGetPackageCity;
import com.eligasht.service.model.startup.request.StartupServiceRequest;
import com.eligasht.service.model.startup.response.StartupServiceResponse;
import com.eligasht.service.model.survey.request.RequestGetSurvey;
import com.eligasht.service.model.survey.request.addServeyResult.RequestSetAnswer;
import com.eligasht.service.model.survey.request.checkValid.RequestCheckValidResultDetail;
import com.eligasht.service.model.survey.request.detail.RequestGetSurveyDetails;
import com.eligasht.service.model.survey.response.ResponseGetSurvey;
import com.eligasht.service.model.survey.response.addServeyResult.ResponseSetAnswer;
import com.eligasht.service.model.survey.response.checkValid.ResponseCheckValidResultDetail;
import com.eligasht.service.model.survey.response.detail.ResponseGetSurveyDetails;
import com.eligasht.service.model.test.entity.TestRes;
import com.eligasht.service.model.weather.response.WeatherApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
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
    @POST(Const.CheckValidSurveyAvil)
    Observable<Response<ResponseCheckValidResultDetail>> RESPONSE_Check_valid(
            @Body RequestCheckValidResultDetail requestCheckValidResultDetail
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

    //*************NEW**********************************************************************************************************
    //*************package*************
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPackageCities)
    Observable<Response<List<ResponseGetPackageCity>>> responseGetPackCityObservable(
            @Body com.eligasht.service.model.newModel.xpackage.packageCity.request.AutoCompleteParameterModel requestGetPackCity
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPackageBasket)
    Observable<Response<ResponseGetPackageBasket>> responseGetPackageBasketObservable(
            @Body GetPackageBasketParameterModel requestGetPackCity
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPurchasePackage)
    Observable<Response<com.eligasht.service.model.newModel.xpackage.PurchasePackage.response.ResponsePurchasePackage>> responsePurchasePackageObservable(
            @Body com.eligasht.service.model.newModel.xpackage.PurchasePackage.request.RequestPurchasePackage requestPackagePurchase
    );
    //*************Flight*************

    @Headers("Content-Type: application/json")
    @POST(Const.GetNewAirports)
    Observable<Response<List<ResponseAirport>>> responseNewAirportsObservable(
            @Body AutoCompleteParameterModel requestAutoCompleteParameterModel
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewFlightSearch)
    Observable<Response<com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight>> responseSearchFlightObservable(
            @Body com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight requestSearchFlight
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewConfirmFlightPrice)
    Observable<Response<com.eligasht.service.model.newModel.flight.confirmFlightPrice.response.ResponseSearchFlight>> responseConfirmFlightPriceObservable(
            @Body RequestConfirmFlightPrice requestConfirmFlightPrice
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPurchaseFlight)
    Observable<Response<TmpReserveResult>> responsePurchaseFlightObservable(
            @Body PurchaseFlightParameterModel requestPurchaseFlightParameterModel
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewServices)
    Observable<Response<ResponseGetServices>> responseGetServicesObservable(
            @Body RequestGetServices requestGetServices
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPurchaseServices)
    Observable<Response<ResponsePurchaseServices>> responseGetGetPurchaseServiceObservable(
            @Body RequestGetPurchaseServices requestGetPurchaseServices
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewPreFactServices)
    Observable<Response<ResponseGetPreFactor>> responseGetPreFactorObservable(
            @Body RequestGetPreFactor requestGetPreFactor
    );
   // @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST(Const.GetToken)
    Observable<Response<ResponseAuth>> responseGetAuthObservable(
            @Body RequestAuth requestAuth
    );
    //*************Insurance*************

    @Headers("Content-Type: application/json")
    @POST(Const.GetNewInsuranceCountries)
    Observable<Response<List<ResponseInsuranceCountry>>> responseNewInsuranceCountriesObservable(
            @Body AutoCompleteParameterModel requestAutoCompleteParameterModel
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewInsuranceSearchResult)
    Observable<Response<ResponseInsuranceSearchResult>> responseInsuranceSearchResult(
            @Body RequestInsuranceSearchResultViewModel requestInsuranceSearchResultViewModel
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewInsurancePurchase)
    Observable<Response<ResponseInsurancePurchase>> responsePurchaseParameterModel(
            @Body PurchaseParameterModel requestPurchaseParameterModel
    );
    //*************Hotel*************

    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelCities)
    Observable<Response<List<ResponseAirport>>> responseNewHotelCitiesObservable(
            @Body AutoCompleteParameterModel requestAutoCompleteParameterModel
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelPreSearch)
    Observable<Response<ResponseHotelPreSearch>> responseNewHotelPreSearchObservable(
            @Body RequestHotelPreSearch requestHotelPreSearch
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelSearch)
    Observable<Response<ResponseHotelSearch>> responseNewHotelSearchObservable(
            @Body RequestHotelSearch requestHotelSearch
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelDetail)
    Observable<Response<ResponseHotelDetails>> responseNewHotelDetailsObservable(
            @Body RequestHotelDetails requestHotelDetails
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelRoom)
    Observable<Response<List<ResponseGetRoomsList>>> responseNewHotelRoomListObservable(
            @Body RequestGetRoomsList requestGetRoomsList
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelPolicy)
    Observable<Response<List<ResponseHotelPolicy>>> responseNewHotelGetPolicyObservable(
            @Body RequestHotelPolicy requestGetRoomsList
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHoldSelectRoom)
    Observable<Response<ResponseHoldSelectRoom>> responseNewHotelHoldSelectRoomObservable(
            @Body RequestHoldSelectRoom requestHoldSelectRoom
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelReview)
    Observable<Response<String>> responseNewHotelReviewObservable(
            @Body RequestAddHotelReview requestAddHotelReview
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelBasket)
    Observable<Response<ResponseSelectedHotelFlightBasket>> responseNewHotelBasketObservable(
            @Body RequestHotelFlightBasket requestHotelFlightBasket
    );

    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelFlightSearch)
    Observable<Response<ResponseHotelFlight>> responsenewHotelFlightSearchObservable(
            @Body RequestHotelFlight requestHotelFlight
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelFlightReserve)
    Observable<Response<ResponseReserveFlightHotel>> responsenewHotelFlightReserveObservable(
            @Body RequestReserveFlightHotel requestHotelFlight
    );
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelPurchaseReserve)
    Observable<Response<com.eligasht.service.model.newModel.hotel.purchase.response.TmpReserveResult>> responsenewHotelPurchaseObservable(
            @Body RequestHotelPurchase requestHotelFlight
    );


    //**************************
    @Headers("Content-Type: application/json")
    @POST(Const.GetNewHotelNames)
    Observable<Response<String>> responseNewHotelNamesObservable(
            @Body AutoCompleteParameterModel requestAutoCompleteParameterModel
    );
}
