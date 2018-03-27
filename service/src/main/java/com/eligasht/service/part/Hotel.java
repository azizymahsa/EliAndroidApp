package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Hotel extends BasePart {
    public Hotel(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    public void hotelAvail(OnServiceStatus<HotelAvailRes> listener, HotelAvailReq req)
    {
        getServiceGenerator().createHotelService(listener,req);
    }
}
