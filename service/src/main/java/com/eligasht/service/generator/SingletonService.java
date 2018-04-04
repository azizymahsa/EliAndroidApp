package com.eligasht.service.generator;

import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactor;
import com.eligasht.service.part.AirPorts;
import com.eligasht.service.part.CheckFlight;
import com.eligasht.service.part.FlightSearch;
import com.eligasht.service.part.Hotel;
import com.eligasht.service.part.PreFactorDetailFlight;
import com.eligasht.service.part.PurchaseFlight;

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
    PurchaseFlight purchaseFlight;
    PreFactorDetailFlight preFactorDetailFlight;
    CheckFlight checkFlight;
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
    public PurchaseFlight getPurchaseFlight() {
        if (purchaseFlight == null)
            purchaseFlight = new PurchaseFlight(serviceGenerator);
        return purchaseFlight;
    }
    public PreFactorDetailFlight getPreFactorDetailFlight() {
        if (preFactorDetailFlight == null)
            preFactorDetailFlight = new PreFactorDetailFlight(serviceGenerator);
        return preFactorDetailFlight;
    }
    public CheckFlight getCheckFlight() {
        if (checkFlight == null)
            checkFlight = new CheckFlight(serviceGenerator);
        return checkFlight;
    }
}
