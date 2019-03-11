package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.newModel.startup.request.RequestStartup;
import com.eligasht.service.model.newModel.startup.response.ResponseStartup;
import com.eligasht.service.model.startup.request.StartupServiceRequest;
import com.eligasht.service.model.startup.response.StartupServiceResponse;

public class AppService extends BasePart {


    public AppService(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

   /* @Mock(jsonName = "mobile_app_startup_service", response = StartupServiceResponse.class)
    public void startUp(OnServiceStatus<StartupServiceResponse> listener, StartupServiceRequest req) {
        start(getServiceGenerator().createService().startUp(req),listener);
    }*/
   //************* new ****************
  // @Mock(jsonName = "mobile_app_startup_service", response = StartupServiceResponse.class)
    public void startUp(OnServiceStatus<ResponseStartup> listener, RequestStartup requestStartup) {
        start(getServiceGenerator().createService().newstartUp(requestStartup),listener);
    }

}
