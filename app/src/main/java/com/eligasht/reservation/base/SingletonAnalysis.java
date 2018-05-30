package com.eligasht.reservation.base;
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
        firebaseAnalytics=FirebaseAnalytics.getInstance(SingletonContext.getInstance().getContext());
    }

    public FirebaseAnalytics getFirebaseAnalytics() {
        return firebaseAnalytics;
    }
}
