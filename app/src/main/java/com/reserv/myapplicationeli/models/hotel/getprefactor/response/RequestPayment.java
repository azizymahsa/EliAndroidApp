package com.reserv.myapplicationeli.models.hotel.getprefactor.response;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class RequestPayment {
    public final String PaymentAmount;
    public final String PaymentSaleReferenceId;

    public RequestPayment(String paymentAmount, String paymentSaleReferenceId) {
        PaymentAmount = paymentAmount;
        PaymentSaleReferenceId = paymentSaleReferenceId;
    }
}
