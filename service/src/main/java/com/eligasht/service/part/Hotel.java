package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.model.hotel.addReview.request.AddHotelReviewRequest;
import com.eligasht.service.model.hotel.addReview.response.AddHotelReviewResponse;
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
import com.eligasht.service.model.hotelflight.search.response.HotelFlightResponse;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicyRequest;
import com.eligasht.service.model.hotelpolicy.response.HotelPolicyResponse;
import com.eligasht.service.model.loadflight.request.LoadFlightRequest;
import com.eligasht.service.model.loadflight.response.LoadFlightResponse;
import com.eligasht.service.model.newModel.airport.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.airport.response.ResponseAirport;
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
import com.eligasht.service.model.newModel.hotelFlight.preSearch.request.RequestHotelFlight;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.ResponseHotelFlight;

import java.util.List;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Hotel extends BasePart {
    public Hotel(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    @Mock(jsonName = "hotel_avail", response = HotelAvailRes.class)
    public void hotelAvail(OnServiceStatus<HotelAvailRes> listener, HotelAvailReq req) {
        start(getServiceGenerator().createService().hotelAvail(req), listener);
    }
    @Mock(jsonName = "hotel_flight", response = HotelFlightResponse.class)
    public void hotelFlight(OnServiceStatus<HotelFlightResponse> listener, HotelFlightRequest req) {
        start(getServiceGenerator().createService().hotelFlight(req), listener);
    }
    @Mock(jsonName = "load_flight", response = LoadFlightResponse.class)
    public void loadFlight(OnServiceStatus<LoadFlightResponse> listener, LoadFlightRequest req) {
        start(getServiceGenerator().createService().loadFlight(req), listener);
    }
    @Mock(jsonName = "hotel_policy", response = HotelPolicyResponse.class)
    public void hotelPolicy(OnServiceStatus<HotelPolicyResponse> listener, HotelPolicyRequest req) {
        start(getServiceGenerator().createService().hotelPolicy(req), listener);
    }
    @Mock(jsonName = "get_room_list", response = GetRoomResponse.class)
    public void getRoom(OnServiceStatus<GetRoomResponse> listener, GetRoomRequest req) {
        start(getServiceGenerator().createService().getRoomsList(req), listener);
    }
    @Mock(jsonName = "get_hotel_detail", response = HotelDetailResponse.class)
    public void getHotelDetail(OnServiceStatus<HotelDetailResponse> listener, HotelDetailRequest req) {
        start(getServiceGenerator().createService().getHotelDetail(req), listener);
    }
    @Mock(jsonName = "hold_room", response = HoldRoomResponse.class)
    public void getHoldRoom(OnServiceStatus<HoldRoomResponse> listener, HoldRoomRequest req) {
        start(getServiceGenerator().createService().getHoldRoom(req), listener);
    }
    @Mock(jsonName = "transport", response = TransportResponse.class)
    public void getTransport(OnServiceStatus<TransportResponse> listener, TransportRequest req) {
        start(getServiceGenerator().createService().transportService(req), listener);
    }
    @Mock(jsonName = "add_review", response = AddHotelReviewResponse.class)
    public void addHotelReview(OnServiceStatus<AddHotelReviewResponse> listener, AddHotelReviewRequest req) {
        start(getServiceGenerator().createService().addHotelReview(req), listener);
    }
    @Mock(jsonName = "get_hotel_list", response = GetHotelListResponse.class)
    public void getHList(OnServiceStatus<GetHotelListResponse> listener, GetHotelListRequest req) {
        start(getServiceGenerator().createService().getHotelList(req), listener);
    }
    @Mock(jsonName = "get_comment", response = GetHotelReviewResponse.class)
    public void getComment(OnServiceStatus<GetHotelReviewResponse> listener, GetHotelReviewRequest req) {
        start(getServiceGenerator().createService().getHotelReview(req), listener);
    }
    @Mock(jsonName = "hf_purchase", response = ResponsePurchaseFlightHotel.class)
    public void getPurchase(OnServiceStatus<ResponsePurchaseFlightHotel> listener, RequestPurchaseFlightHotel req) {
        start(getServiceGenerator().createService().HotelFlightPurchaseResponse(req), listener);
    }

    public void getPishFactor(OnServiceStatus<ResponsePurchaseService> listener, RequestPurchaseService req) {
        start(getServiceGenerator().createService().RESPONSE_OBSERVABLE(req), listener);
    }

    //*************NEW*****************
    public void newHotelCitiesAvail(OnServiceStatus<List<ResponseAirport>> listener, AutoCompleteParameterModel req) {
        start(getServiceGenerator().createService().responseNewHotelCitiesObservable(req), listener);
    }
    public void newHotelPreSearchAvail(OnServiceStatus<ResponseHotelPreSearch> listener, RequestHotelPreSearch req) {
        start(getServiceGenerator().createService().responseNewHotelPreSearchObservable(req), listener);
    }
    public void newHotelSearchAvail(OnServiceStatus<ResponseHotelSearch> listener, RequestHotelSearch req) {
        start(getServiceGenerator().createService().responseNewHotelSearchObservable(req), listener);
    }
    public void newHotelDetailsAvail(OnServiceStatus<ResponseHotelDetails> listener, RequestHotelDetails req) {
        start(getServiceGenerator().createService().responseNewHotelDetailsObservable(req), listener);
    }
    public void newHotelRoomListAvail(OnServiceStatus<List<ResponseGetRoomsList>> listener, RequestGetRoomsList req) {
        start(getServiceGenerator().createService().responseNewHotelRoomListObservable(req), listener);
    }
    public void newHotelGetPolicyAvail(OnServiceStatus<List<ResponseHotelPolicy>> listener, RequestHotelPolicy req) {
        start(getServiceGenerator().createService().responseNewHotelGetPolicyObservable(req), listener);
    }
    public void newHotelHoldSelectRoomAvail(OnServiceStatus<ResponseHoldSelectRoom> listener, RequestHoldSelectRoom req) {
        start(getServiceGenerator().createService().responseNewHotelHoldSelectRoomObservable(req), listener);
    }
    public void newHotelReviewAvail(OnServiceStatus<String> listener, RequestAddHotelReview req) {
        start(getServiceGenerator().createService().responseNewHotelReviewObservable(req), listener);
    }
    public void newHotelBasketAvail(OnServiceStatus<ResponseSelectedHotelFlightBasket> listener, RequestHotelFlightBasket req) {
        start(getServiceGenerator().createService().responseNewHotelBasketObservable(req), listener);
    }

    public void newHotelFlightSearchAvail(OnServiceStatus<ResponseHotelFlight> listener, RequestHotelFlight req) {
        start(getServiceGenerator().createService().responsenewHotelFlightSearchObservable(req), listener);
    }
    public void newHotelFlightReserveAvail(OnServiceStatus<ResponseReserveFlightHotel> listener, RequestReserveFlightHotel req) {
        start(getServiceGenerator().createService().responsenewHotelFlightReserveObservable(req), listener);
    }
    public void newHotelPurchaseAvail(OnServiceStatus<com.eligasht.service.model.newModel.hotel.purchase.response.TmpReserveResult> listener, RequestHotelPurchase req) {
            start(getServiceGenerator().createService().responsenewHotelPurchaseObservable(req), listener);
        }




    public void newHotelNamesAvail(OnServiceStatus<String> listener, AutoCompleteParameterModel req) {
        start(getServiceGenerator().createService().responseNewHotelNamesObservable(req), listener);
    }
}
