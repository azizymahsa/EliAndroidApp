package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public abstract class BasePart {
    private ServiceGenerator serviceGenerator;
    private Observable observable;
    private Subscription subscription;

    public BasePart(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public ServiceGenerator getServiceGenerator() {
        return serviceGenerator;
    }

    public void start() {
        if (observable == null)
            throw new NullPointerException(getPart().getClass().getSimpleName() + "Service" + " Must Not be Null");
        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe();
    }

    public void stop() {
        if (subscription == null)
            throw new NullPointerException(getPart().getClass().getSimpleName() + "Service" + " Must Not be Null");
        if (subscription.isUnsubscribed())
            return;
        subscription.unsubscribe();

    }


    public void setObservable(Observable observable) {
        this.observable = observable;
    }


    public abstract BasePart getPart();
}
