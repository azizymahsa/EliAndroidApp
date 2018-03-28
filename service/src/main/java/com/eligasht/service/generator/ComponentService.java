package com.eligasht.service.generator;

import com.eligasht.service.scope.CustomScope;
import com.eligasht.service.di.component.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */
@CustomScope
@Component(dependencies = NetComponent.class)
public interface ComponentService {
    void inject(SingletonService singletonService);
}
