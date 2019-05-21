package com.eligasht.reservation.tools;


import android.content.Context;

import com.eligasht.R;

public class HandlerCurrency {
    public static java.lang.String GetHandleCurrency(String currencyCode, Context context) {
        java.lang.String result="";

        switch (currencyCode.toString().toLowerCase()){
            case "irr":
                result= context.getString(R.string.Rial);
                break;
            case "usd":
                result= "$";
                break;
            case "eur":
                result= "€";
                break;
            case "gbp":
                result= "£";
                break;
            case "try":
                result= "₺";
                break;
            case "aed":
                result= "AED";
                break;
            default:
                result= currencyCode.toString();
        }
        return result;
    }
}
