package com.reserv.myapplicationeli.models.hotel.getprefactor.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class PreFactor {
    public final FactorSummary FactorSummary;
    public final List<RequestPayment> RequestPayment;
    public final List<PreFactorBookingLogs> PreFactorBookingLogs;

    public PreFactor(com.reserv.myapplicationeli.models.hotel.getprefactor.response.FactorSummary factorSummary,
                     List<com.reserv.myapplicationeli.models.hotel.getprefactor.response.RequestPayment> requestPayment,
                     List<com.reserv.myapplicationeli.models.hotel.getprefactor.response.PreFactorBookingLogs> preFactorBookingLogs) {
        FactorSummary = factorSummary;
        RequestPayment = requestPayment;
        PreFactorBookingLogs = preFactorBookingLogs;
    }
}
