package com.eligasht.reservation.base;

import android.os.Bundle;

import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Reza Nejati on 29,May,2018
 */
public class SingletonAnalysis {
    private FirebaseAnalytics firebaseAnalytics;
    private static final SingletonAnalysis ourInstance = new SingletonAnalysis();

    public static SingletonAnalysis getInstance() {
        return ourInstance;
    }

    private SingletonAnalysis() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(SingletonContext.getInstance().getContext());
    }

    public void LogSeen(ServiceType serviceType) {
        Bundle bundle = new Bundle();
        bundle.putString("Seen", "Seen");
        sendLog(serviceType, bundle);
    }

    public void logTransfer(ServiceType serviceType, String origin, String dest) {
        Bundle bundle = new Bundle();
        bundle.putString("Origin", origin);
        bundle.putString("Destination", dest);
        sendLog(serviceType, bundle);
    }


    private void sendLog(ServiceType serviceType, Bundle bundle) {
        String eventName = null;
        switch (serviceType) {
            case FLIGHT:
                eventName = "FLIGHT";
                break;
            case HOTEL:
                eventName = "HOTEL";
                break;
            case HOTELFLIGHT:
                eventName = "HOTEL_FLIGHT";
                break;
            case PACKAGE:
                eventName = "PACKAGE";
                break;
            case INSURANCE:
                eventName = "INSURANCE";
                break;

        }

        firebaseAnalytics.logEvent(eventName, bundle);
    }

}
