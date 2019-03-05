package com.eligasht.service.generator;
import android.content.Context;

import com.eligasht.ServiceApplication;
import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.part.AboutService;
import com.eligasht.service.part.AppService;
import com.eligasht.service.part.ContactUs;
import com.eligasht.service.part.Flight;
import com.eligasht.service.part.Hotel;
import com.eligasht.service.part.Insurance;
import com.eligasht.service.part.LoginProfile;
import com.eligasht.service.part.Survey;
import com.eligasht.service.part.Train;
import com.eligasht.service.part.WeatherPart;
import com.eligasht.service.part.XPackage;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
/**
 * Created by Ahmad.nemati on 3/26/2018.
 */
public class SingletonService {
    private NetComponent netComponent;
    @Inject
    ServiceGenerator serviceGenerator;
    @Inject
    OkHttpClient okHttpClient;
    private ServiceApplication serviceApplication;
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

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private SingletonService() {
    }

    public ServiceApplication getContext() {
        return serviceApplication;
    }

    public void setContext(ServiceApplication context) {
        this.serviceApplication = context;
    }

    public Flight getFlight() {
        return new Flight(serviceGenerator);
    }
    public Train getTrain() {
            return new Train(serviceGenerator);
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
    public Survey getSurveyService() {
        return new Survey(serviceGenerator);
    }

    public AppService getAppService() {
        return new AppService(serviceGenerator);
    }

    public XPackage getXPackage() {
        return new XPackage(serviceGenerator);
    }

    public LoginProfile getLoginProfile() {
        return new LoginProfile(serviceGenerator);
    }

    public WeatherPart getWeatherPart() {
        return new WeatherPart(serviceGenerator);
    }
}
