package com.eligasht.service.generator;

import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.part.AirPorts;
import com.eligasht.service.part.FlightSearch;
import com.eligasht.service.part.Hotel;

import javax.inject.Inject;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class SingletonService {
    private NetComponent netComponent;

    @Inject
    ServiceGenerator serviceGenerator;


    Hotel hotel;
    AirPorts airPorts;
    FlightSearch flightSearch;
    private static final SingletonService ourInstance = new SingletonService();

    public static SingletonService getInstance() {
        return ourInstance;
    }

    public SingletonService setNetComponent(NetComponent netComponent) {
        this.netComponent = netComponent;
        return this;
    }

    public void inject() {
        ComponentService componentService = DaggerComponentService.builder().netComponent(netComponent).build();
        componentService.inject(this);
    }

    private SingletonService() {
    }

    public Hotel getHotelService() {
        if (hotel == null)
            hotel = new Hotel(serviceGenerator);
        return hotel;
    }
    public AirPorts getAirPortsService() {
        if (airPorts == null)
            airPorts = new AirPorts(serviceGenerator);
        return airPorts;
    }
    public FlightSearch getFlightSearchService() {
        if (flightSearch == null)
            flightSearch = new FlightSearch(serviceGenerator);
        return flightSearch;
    }
}
