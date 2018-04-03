package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class AirPorts extends BasePart {
    public AirPorts(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }


    public void airPortsAvail(OnServiceStatus<ResponsAirports> listener, RequestAirports req) {
        start(getServiceGenerator().createService().responsAirports(req), listener);
    }

}
