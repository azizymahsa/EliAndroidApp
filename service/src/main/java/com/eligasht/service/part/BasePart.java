package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public abstract class BasePart {
    private ServiceGenerator serviceGenerator;


    public BasePart(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public ServiceGenerator getServiceGenerator() {
        return serviceGenerator;
    }

    public <T> void start(Observable<T> observable, OnServiceStatus<T> listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T value) {
                        listener.onReady(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
