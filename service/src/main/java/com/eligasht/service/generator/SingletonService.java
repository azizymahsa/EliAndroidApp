package com.eligasht.service.generator;

import android.content.Context;

import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.part.AboutService;
import com.eligasht.service.part.AppService;
import com.eligasht.service.part.ContactUs;
import com.eligasht.service.part.Flight;
import com.eligasht.service.part.Hotel;
import com.eligasht.service.part.Insurance;
import com.eligasht.service.part.XPackage;

import javax.inject.Inject;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */
public class SingletonService {
    private NetComponent netComponent;
    @Inject
    ServiceGenerator serviceGenerator;
    private Context context;


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
        return new Hotel(serviceGenerator);
    }

    public ContactUs getContactUs() {
        return new ContactUs(serviceGenerator);
    }

    public AboutService getAboutService() {
        return new AboutService(serviceGenerator);
    }

    public AppService getAppService() {
        return new AppService(serviceGenerator);
    }

    public XPackage getXPackage() {
        return new XPackage(serviceGenerator);
    }

}
