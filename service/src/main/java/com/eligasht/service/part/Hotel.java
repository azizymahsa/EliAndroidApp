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



    public Hotel hotelAvail(OnServiceStatus<HotelAvailRes> listener, HotelAvailReq req)
    {
        setObservable(getServiceGenerator().createHotelService(listener,req));
        return this;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}
    public BasePart getPart() {
        return this;
    }

}
