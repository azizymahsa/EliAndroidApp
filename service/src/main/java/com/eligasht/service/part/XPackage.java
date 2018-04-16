package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.XPackage.request.GetPreFactorDetails.RequestGePreFactorDetails;
import com.eligasht.service.model.XPackage.request.PurchasePackage.RequestPurchasePackage;
import com.eligasht.service.model.XPackage.request.searchXPackage.RequestSearchXPackage;
import com.eligasht.service.model.XPackage.response.GetPreFactorDetails.ResponseGePreFactorDetails;
import com.eligasht.service.model.XPackage.response.PurchasePackage.ResponsePurchasePackage;
import com.eligasht.service.model.XPackage.response.searchXPackage.ResponseSearchXPackage;

public class XPackage extends BasePart {
    public XPackage(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    public void searchXPackageAvail(OnServiceStatus<ResponseSearchXPackage> listener, RequestSearchXPackage req) {
        start(getServiceGenerator().createService().RESPONSE_SEARCH_X_PACKAGE_OBSERVABLE(req), listener);
    }


    public void PurchasePackageAvail(OnServiceStatus<ResponsePurchasePackage> listener, RequestPurchasePackage req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_PACKAGE_OBSERVABLE(req), listener);
    }
    public void GetPreFactorDetailsAvail(OnServiceStatus<com.eligasht.service.model.XPackage.response.GetPreFactorDetails.ResponseGePreFactorDetails> listener, com.eligasht.service.model.XPackage.request.GetPreFactorDetails.RequestGePreFactorDetails req) {
        start(getServiceGenerator().createService().RESPONSE_GE_PRE_FACTOR_DETAILS_OBSERVABLE(req), listener);
    }


}
