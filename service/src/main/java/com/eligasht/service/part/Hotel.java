package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotelflight.request.HotelFlightRequest;
import com.eligasht.service.model.hotelflight.response.HotelFlightResponse;
import com.eligasht.service.model.loadflight.request.LoadFlightRequest;
import com.eligasht.service.model.loadflight.response.LoadFlightResponse;
/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Hotel extends BasePart {
    public Hotel(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void hotelAvail(OnServiceStatus<HotelAvailRes> listener, HotelAvailReq req) {
        start(getServiceGenerator().createService().hotelAvail(req), listener);
    }
    public void hotelFlight(OnServiceStatus<HotelFlightResponse> listener, HotelFlightRequest req) {
        start(getServiceGenerator().createService().hotelFlight(req), listener);
    }
    public void loadFlight(OnServiceStatus<LoadFlightResponse> listener, LoadFlightRequest req) {
        start(getServiceGenerator().createService().loadFlight(req), listener);
    }

}
