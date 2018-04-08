package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.about.request.RequestAbout;
import com.eligasht.service.model.about.response.ResponseAbout;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class AboutService extends BasePart {
    public AboutService(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void aboutAvail(OnServiceStatus<ResponseAbout> listener, RequestAbout req) {
        start(getServiceGenerator().createService().responseAboutObservable(req), listener);
    }

}
