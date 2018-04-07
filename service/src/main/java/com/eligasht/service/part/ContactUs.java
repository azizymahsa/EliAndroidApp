package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class ContactUs extends BasePart {
    public ContactUs(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void contactUsAvail(OnServiceStatus<ResponseContactUs> listener, RequestContactUs req) {
        start(getServiceGenerator().createService().responsContactUs(req), listener);
    }

}
