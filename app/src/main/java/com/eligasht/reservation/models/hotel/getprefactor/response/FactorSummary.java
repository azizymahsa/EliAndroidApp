package com.eligasht.reservation.models.hotel.getprefactor.response;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class FactorSummary {
    public final int ContractNo;
    public final String OnlinePaymentURL;

    public FactorSummary(int contractNo, String onlinePaymentURL) {
        ContractNo = contractNo;
        OnlinePaymentURL = onlinePaymentURL;
    }
}
