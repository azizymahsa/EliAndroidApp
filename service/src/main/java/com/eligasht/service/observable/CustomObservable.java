package com.eligasht.service.observable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class CustomObservable<T> extends Observable<T> {

    public CustomObservable(OnSubscribe<T> f) {
        super(f);
        subscribeOn(Schedulers.io());
        observeOn(AndroidSchedulers.mainThread());
        unsubscribeOn(Schedulers.io());
    }
}
