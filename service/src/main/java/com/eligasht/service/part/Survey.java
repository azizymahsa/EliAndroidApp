package com.eligasht.service.part;

import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.survey.request.RequestGetSurvey;
import com.eligasht.service.model.survey.request.addServeyResult.RequestSetAnswer;
import com.eligasht.service.model.survey.request.checkValid.RequestCheckValidResultDetail;
import com.eligasht.service.model.survey.request.detail.RequestGetSurveyDetails;
import com.eligasht.service.model.survey.response.ResponseGetSurvey;
import com.eligasht.service.model.survey.response.addServeyResult.ResponseSetAnswer;
import com.eligasht.service.model.survey.response.checkValid.ResponseCheckValidResultDetail;
import com.eligasht.service.model.survey.response.detail.ResponseGetSurveyDetails;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Survey extends BasePart {
    public Survey(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

 /*   @Mock(jsonName = "get_about_us_with_culture", response = ResponseAbout.class)*/
    public void surveyAvail(OnServiceStatus<ResponseGetSurvey> listener, RequestGetSurvey req) {
        start(getServiceGenerator().createService().responseGetSurveyObservable(req), listener);
    }
    public void GetSurveyDetailsAvail(OnServiceStatus<ResponseGetSurveyDetails> listener, RequestGetSurveyDetails req) {
        start(getServiceGenerator().createService().responseGetSurveyDetailsObservable(req), listener);
    }
    public void AddSurveyResultAvail(OnServiceStatus<ResponseSetAnswer> listener, RequestSetAnswer req) {
        start(getServiceGenerator().createService().RESPONSE_SETANSWER(req), listener);
    }
    public void CheckValidResultDetailAvail(OnServiceStatus<ResponseCheckValidResultDetail> listener, RequestCheckValidResultDetail req) {
        start(getServiceGenerator().createService().RESPONSE_Check_valid(req), listener);
    }
   /*@Mock(jsonName = "flight_pre_factor_detail_avail", response = ResponsePreFactorDetails.class)
    public void flightPreFactorDetailAvail(OnServiceStatus<ResponsePreFactorDetails> listener, RequestPreFactorDetails req) {
        start(getServiceGenerator().createService().responsePreFactorDetails(req), listener);
    }*/
}
