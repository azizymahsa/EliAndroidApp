package com.eligasht.reservation.lost.service;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class ServicePreFactorModel {
    String ServiceNameEn;
    String ServicePrice;
    String ServiceType;
    String CityFa;
    String ServiceNameFa;
    String CountryFa;

    public ServicePreFactorModel(String serviceNameEn, String servicePrice, String serviceType, String cityFa, String serviceNameFa, String countryFa) {
        ServiceNameEn = serviceNameEn;
        ServicePrice = servicePrice;
        ServiceType = serviceType;
        CityFa = cityFa;
        ServiceNameFa = serviceNameFa;
        CountryFa = countryFa;
    }

    public String getServiceNameEn() {
        return ServiceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        ServiceNameEn = serviceNameEn;
    }

    public String getServicePrice() {
        return ServicePrice;
    }

    public void setServicePrice(String servicePrice) {
        ServicePrice = servicePrice;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getCityFa() {
        return CityFa;
    }

    public void setCityFa(String cityFa) {
        CityFa = cityFa;
    }

    public String getServiceNameFa() {
        return ServiceNameFa;
    }

    public void setServiceNameFa(String serviceNameFa) {
        ServiceNameFa = serviceNameFa;
    }

    public String getCountryFa() {
        return CountryFa;
    }

    public void setCountryFa(String countryFa) {
        CountryFa = countryFa;
    }
}
