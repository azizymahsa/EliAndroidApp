package com.eligasht.service.generator;

import android.content.Context;

import com.eligasht.service.di.component.NetComponent;

import com.eligasht.service.part.AboutService;
import com.eligasht.service.part.ContactUs;
import com.eligasht.service.part.Flight;
import com.eligasht.service.part.Hotel;
import com.eligasht.service.part.Insurance;

import javax.inject.Inject;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class SingletonService {
    private NetComponent netComponent;

    @Inject
    ServiceGenerator serviceGenerator;


    Hotel hotel;
    ContactUs contactUs;
    AboutService aboutService;
    private Context context;
    private Boolean mock=false;

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

    public Context getContext() {
        return context;
    }


    public Boolean isMock() {
        return mock;
    }

    public void setMock(Boolean mock) {
        this.mock = mock;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Flight getFlight() {
        return new Flight(serviceGenerator);
    }

    public Insurance getInsurance() {
        return new Insurance(serviceGenerator);
    }

    public Hotel getHotelService() {
        if (hotel == null)
            hotel = new Hotel(serviceGenerator);
        return hotel;
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

}
