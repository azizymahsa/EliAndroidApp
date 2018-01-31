package com.reserv.myapplicationeli.models.hotel.getprefactor.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class PreFactor {
    public final FactorSummary FactorSummary;
    public final List<RequestPayment> RequestPayment;
    public final List<PreFactorBookingLogs> PreFactorBookingLogs;
    public final List<RequestPartner> RequestPartner;

    public PreFactor(com.reserv.myapplicationeli.models.hotel.getprefactor.response.FactorSummary factorSummary, List<com.reserv.myapplicationeli.models.hotel.getprefactor.response.RequestPayment> requestPayment,
                     List<com.reserv.myapplicationeli.models.hotel.getprefactor.response.PreFactorBookingLogs> preFactorBookingLogs, List<RequestPartner> requestPartner) {
        FactorSummary = factorSummary;
        RequestPayment = requestPayment;
        PreFactorBookingLogs = preFactorBookingLogs;
        RequestPartner = requestPartner;
    }
}
