package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.mock.Mock;
import com.eligasht.service.model.XPackage.request.PurchasePackage.RequestPurchasePackage;
import com.eligasht.service.model.XPackage.request.searchXPackage.RequestSearchXPackage;
import com.eligasht.service.model.XPackage.response.GetPreFactorDetails.ResponseGePreFactorDetails;
import com.eligasht.service.model.XPackage.response.PurchasePackage.ResponsePurchasePackage;
import com.eligasht.service.model.XPackage.response.searchXPackage.ResponseSearchXPackage;
import com.eligasht.service.model.newModel.xpackage.packageBasket.request.GetPackageBasketParameterModel;
import com.eligasht.service.model.newModel.xpackage.packageBasket.response.ResponseGetPackageBasket;
import com.eligasht.service.model.newModel.xpackage.packageCity.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.xpackage.packageCity.response.ResponseGetPackageCity;

import java.util.List;

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
    @Mock(jsonName = "purchase_pack",response = ResponsePurchasePackage.class)
    public void PurchasePackageAvail(OnServiceStatus<ResponsePurchasePackage> listener, RequestPurchasePackage req) {
        start(getServiceGenerator().createService().RESPONSE_PURCHASE_PACKAGE_OBSERVABLE(req), listener);
    }
    @Mock(jsonName = "pre_factor_detail_pack",response = ResponseGePreFactorDetails.class)
    public void GetPreFactorDetailsAvail(OnServiceStatus<com.eligasht.service.model.XPackage.response.GetPreFactorDetails.ResponseGePreFactorDetails> listener, com.eligasht.service.model.XPackage.request.GetPreFactorDetails.RequestGePreFactorDetails req) {
        start(getServiceGenerator().createService().RESPONSE_GE_PRE_FACTOR_DETAILS_OBSERVABLE(req), listener);
    }
    //*************NEW******************************************************************
    //@Mock(jsonName = "pre_factor_detail_pack",response = ResponseGetPackageCity.class)
    public void GetPreFactorDetailsAvail(OnServiceStatus<List<ResponseGetPackageCity>> listener, AutoCompleteParameterModel req) {
        start(getServiceGenerator().createService().responseGetPackCityObservable(req), listener);
    }
   // @Mock(jsonName = "pre_factor_detail_pack",response = ResponseGetPackageCity.class)
    public void GetPackageBasketAvail(OnServiceStatus<ResponseGetPackageBasket> listener, GetPackageBasketParameterModel req) {
        start(getServiceGenerator().createService().responseGetPackageBasketObservable(req), listener);
    }
    public void GetPurchasePackageAvail(OnServiceStatus<com.eligasht.service.model.newModel.xpackage.PurchasePackage.response.ResponsePurchasePackage> listener, com.eligasht.service.model.newModel.xpackage.PurchasePackage.request.RequestPurchasePackage req) {
        start(getServiceGenerator().createService().responsePurchasePackageObservable(req), listener);
    }
}
