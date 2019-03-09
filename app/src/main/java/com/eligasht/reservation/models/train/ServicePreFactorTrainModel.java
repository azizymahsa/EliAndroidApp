package com.eligasht.reservation.models.train;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class ServicePreFactorTrainModel {
    String ServiceNameEn;
    String ServicePrice;
    String ServiceType;
    String CityFa;
    String ServiceNameFa;
    String CountryFa;
    String Description;
    String CountPass;

    public ServicePreFactorTrainModel(String serviceNameEn, String servicePrice, String serviceType, String cityFa, String serviceNameFa, String countryFa, String Description, String CountPass) {
        this.  ServiceNameEn = serviceNameEn;
        this. ServicePrice = servicePrice;
        this. ServiceType = serviceType;
        this. CityFa = cityFa;
        this. ServiceNameFa = serviceNameFa;
        this.CountryFa = countryFa;
        this.Description = Description;
        this.CountPass = CountPass;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCountPass() {
        return CountPass;
    }

    public void setCountPass(String countPass) {
        CountPass = countPass;
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
