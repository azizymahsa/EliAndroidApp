package com.eligasht.service.part;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.RawRes;
import android.util.Log;

import com.eligasht.service.BuildConfig;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.mock.MockProcessor;
import com.eligasht.service.model.BaseModel;
import com.example.type.TypeResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public abstract class BasePart {
    private ServiceGenerator serviceGenerator;


    public BasePart(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    protected abstract BasePart getPart();

    public ServiceGenerator getServiceGenerator() {
        return serviceGenerator;
    }


    public <T> void start(Observable<Response<T>> observable, OnServiceStatus<T> listener) {
        if (!BuildConfig.DEBUG) {
            call(observable, listener);
            return;
        }
        MockProcessor<T> mockProcessor = new MockProcessor<>(listener, getPart());
        if (Const.MOCK && mockProcessor.getRawRes() != null && mockProcessor.loadJSONFromAsset() != null) {
            T model = mockProcessor.getMockModel();
            if (model == null) {
                call(observable, listener);
                return;
            }
            new Handler().postDelayed(() -> listener.onReady(model), 50);
            return;
        }
        call(observable, listener);
    }


    private <T> void call(Observable<Response<T>> observable, OnServiceStatus<T> listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<T> value) {
                        listener.onReady(value.body());
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
