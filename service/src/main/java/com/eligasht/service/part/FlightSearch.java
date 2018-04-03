package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;


/**
 * Created by Mahsa.azizi on 3/29/2018.
 */

public class FlightSearch extends BasePart {
    public FlightSearch(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void flightSearchAvail(OnServiceStatus<ResponsSearchFlight> listener, RequestSearchFlight req) {
        start(getServiceGenerator().createService().responsSearchFlight(req), listener);
    }
}
