package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.generator.ServiceGenerator_Factory;

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
}
