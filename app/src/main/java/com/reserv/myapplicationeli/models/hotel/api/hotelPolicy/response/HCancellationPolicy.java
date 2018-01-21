package com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.response;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class HCancellationPolicy {
    public final String Currency;
    public final String DescriptionEn;
    public final String DescriptionFa;
    public final String FromDate;
    public final String ReturnAmount;
    public final String RoomNo;
    public final String ToDate;

    public HCancellationPolicy(String currency, String descriptionEn, String descriptionFa, String fromDate, String returnAmount, String roomNo, String toDate) {
        Currency = currency;
        DescriptionEn = descriptionEn;
        DescriptionFa = descriptionFa;
        FromDate = fromDate;
        ReturnAmount = returnAmount;
        RoomNo = roomNo;
        ToDate = toDate;
    }
}
