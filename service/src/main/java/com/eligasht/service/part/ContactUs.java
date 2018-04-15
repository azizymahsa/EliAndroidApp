package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.contactUs.RequestContactUs;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.flight.response.contactUs.ResponseContactUs;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;
import com.eligasht.service.model.startup.response.StartupServiceResponse;

import java.util.List;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class ContactUs extends BasePart {
    public ContactUs(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    @Mock(jsonName = "get_contact_us_with_cuture", response = ResponseContactUs.class)
    public void contactUsAvail(OnServiceStatus<ResponseContactUs> listener, RequestContactUs req) {
        start(getServiceGenerator().createService().responsContactUs(req), listener);
    }


}
