package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.about.request.RequestAbout;
import com.eligasht.service.model.about.response.ResponseAbout;
import com.eligasht.service.model.flight.request.ChangeFlight.RequestChangeFlight;
import com.eligasht.service.model.flight.response.ChangeFlight.ResponseChangeFlight;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;


public class ChangeFlight extends BasePart {
    public ChangeFlight(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void ChangeFlightAvail(OnServiceStatus<ResponseChangeFlight> listener, RequestChangeFlight req) {
        start(getServiceGenerator().createService().responsChangeFlight(req), listener);
    }

}
