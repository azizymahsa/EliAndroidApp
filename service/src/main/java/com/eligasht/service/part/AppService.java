package com.eligasht.service.part;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.startup.request.StartupServiceRequest;
import com.eligasht.service.model.startup.response.StartupServiceResponse;
public class AppService extends BasePart {


    public AppService(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void startUp(OnServiceStatus<StartupServiceResponse> listener, StartupServiceRequest req) {
        start(getServiceGenerator().createService().startUp(req), listener);
    }

}
