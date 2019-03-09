package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.newModel.train.domesticSearch.Request.RequestDomesticTrainAPI;
import com.eligasht.service.model.newModel.train.domesticSearch.response.ResponseDomesticTrainAPI;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.request.RequestDomesticTrainGetPrice;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.ResponseDomesticTrainGetPrice;
import com.eligasht.service.model.newModel.train.purchase.request.RequestTrainPurchase;
import com.eligasht.service.model.newModel.train.purchase.response.ResponseTrainPurchase;
import com.eligasht.service.model.newModel.train.trainStation.request.AutoCompleteParameterModel;
import com.eligasht.service.model.newModel.train.trainStation.response.ResponseTrainStation;
import com.eligasht.service.model.survey.request.RequestGetSurvey;
import com.eligasht.service.model.survey.request.addServeyResult.RequestSetAnswer;
import com.eligasht.service.model.survey.request.checkValid.RequestCheckValidResultDetail;
import com.eligasht.service.model.survey.request.detail.RequestGetSurveyDetails;
import com.eligasht.service.model.survey.response.ResponseGetSurvey;
import com.eligasht.service.model.survey.response.addServeyResult.ResponseSetAnswer;
import com.eligasht.service.model.survey.response.checkValid.ResponseCheckValidResultDetail;
import com.eligasht.service.model.survey.response.detail.ResponseGetSurveyDetails;

import java.util.List;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Train extends BasePart {
    public Train(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    public void newGetTrainStatAvail(OnServiceStatus<List<ResponseTrainStation>> listener, AutoCompleteParameterModel autoCompleteParameterModel) {
        start(getServiceGenerator().createService().responseGetStatTrainObservable(autoCompleteParameterModel), listener);
    }
    public void newGetTrainSearchAvail(OnServiceStatus<ResponseDomesticTrainAPI> listener, RequestDomesticTrainAPI requestDomesticTrainAPI) {
        start(getServiceGenerator().createService().responseGetDTrainObservable(requestDomesticTrainAPI), listener);
    }
    public void newGetDomesticTrainGetPriceAvail(OnServiceStatus<ResponseDomesticTrainGetPrice> listener, RequestDomesticTrainGetPrice requestDomesticTrainGetPrice) {
        start(getServiceGenerator().createService().responseGetDTrainPriceObservable(requestDomesticTrainGetPrice), listener);
    }
    public void newGetPurchaseTrainAvail(OnServiceStatus<ResponseTrainPurchase> listener, RequestTrainPurchase requestTrainPurchase) {
        start(getServiceGenerator().createService().responsenewTrainPurchase(requestTrainPurchase), listener);
    }
}
