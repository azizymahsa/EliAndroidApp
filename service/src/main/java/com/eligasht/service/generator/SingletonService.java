package com.eligasht.service.generator;

import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.part.AboutService;
import com.eligasht.service.part.AirPorts;
import com.eligasht.service.part.ChangeFlight;
import com.eligasht.service.part.CheckFlight;
import com.eligasht.service.part.ContactUs;
import com.eligasht.service.part.FlightSearch;
import com.eligasht.service.part.GetCountryInsurance;
import com.eligasht.service.part.Hotel;
import com.eligasht.service.part.PreFactorDetailFlight;
import com.eligasht.service.part.PreFactorDetailInsurance;
import com.eligasht.service.part.PurchaseFlightPassenger;
import com.eligasht.service.part.PurchaseFlightService;
import com.eligasht.service.part.PurchaseInsurance;

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
    PurchaseFlightService purchaseFlight;
    PreFactorDetailFlight preFactorDetailFlight;
    CheckFlight checkFlight;
    PurchaseFlightPassenger purchaseFlightPassenger;
    ContactUs contactUs;
    AboutService aboutService;
    ChangeFlight changeFlight;
    PurchaseInsurance purchaseInsurance;
    PreFactorDetailInsurance preFactorDetailInsurance;
    GetCountryInsurance getCountryInsurance;
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
    public PurchaseFlightService getPurchaseFlight() {
        if (purchaseFlight == null)
            purchaseFlight = new PurchaseFlightService(serviceGenerator);
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
    public PurchaseFlightPassenger getPurchaseFlightPassenger() {
        if (purchaseFlightPassenger == null)
            purchaseFlightPassenger = new PurchaseFlightPassenger(serviceGenerator);
        return purchaseFlightPassenger;
    }
    public ContactUs getContactUs() {
        if (contactUs == null)
           contactUs = new ContactUs(serviceGenerator);
        return contactUs;
    }
    public AboutService getAboutService() {
        if (aboutService == null)
            aboutService = new AboutService(serviceGenerator);
        return aboutService;
    }
    public ChangeFlight getChangeFlight() {
        if (changeFlight == null)
            changeFlight = new ChangeFlight(serviceGenerator);
        return changeFlight;
    }
    public PurchaseInsurance getPurchaseInsurance() {
        if (purchaseInsurance == null)
            purchaseInsurance = new PurchaseInsurance(serviceGenerator);
        return purchaseInsurance;
    }
    public PreFactorDetailInsurance getPreFactorDetailInsurance() {
        if (preFactorDetailInsurance == null)
            preFactorDetailInsurance = new PreFactorDetailInsurance(serviceGenerator);
        return preFactorDetailInsurance;
    }
    public GetCountryInsurance getCountryInsurance() {
        if (getCountryInsurance == null)
            getCountryInsurance = new GetCountryInsurance(serviceGenerator);
        return getCountryInsurance;
    }
}
