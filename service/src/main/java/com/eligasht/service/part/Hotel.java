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
import com.eligasht.service.model.hotelflight.request.HotelFlightRequest;
import com.eligasht.service.model.hotelflight.response.HotelFlightResponse;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicyRequest;
import com.eligasht.service.model.hotelpolicy.response.HotelPolicyResponse;
import com.eligasht.service.model.loadflight.request.LoadFlightRequest;
import com.eligasht.service.model.loadflight.response.LoadFlightResponse;


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

    public void hotelFlight(OnServiceStatus<HotelFlightResponse> listener, HotelFlightRequest req) {
        start(getServiceGenerator().createService().hotelFlight(req), listener);
    }

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
    @Mock(jsonName = "hold_room", response = HoldRoomRequest.class)
    public void getHoldRoom(OnServiceStatus<HoldRoomResponse> listener, HoldRoomRequest req) {
        start(getServiceGenerator().createService().getHoldRoom(req), listener);
    }

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
}
