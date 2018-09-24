package com.eligasht.reservation.views.activities.survey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.new_survey.MainSurveyActivity;
import com.eligasht.reservation.views.activities.new_survey.model.EventModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyAnswer;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import com.eligasht.reservation.views.activities.survey.adapter.SurveyDetailsRecycleAdapter;
import com.eligasht.reservation.views.activities.survey.adapter.SurveyQuestionsRecycleAdapter;
import com.eligasht.reservation.views.activities.survey.adapter.SurveyRecycleAdapter;
import com.eligasht.reservation.views.adapters.AboutAdapter;
import com.eligasht.reservation.views.ticker.TickerView;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.survey.request.Identity;
import com.eligasht.service.model.survey.request.RequestGetSurvey;
import com.eligasht.service.model.survey.request.checkValid.RequestCheckValidResultDetail;
import com.eligasht.service.model.survey.request.detail.RequestGetSurveyDetails;
import com.eligasht.service.model.survey.response.GetSurveyResult;
import com.eligasht.service.model.survey.response.ResponseGetSurvey;
import com.eligasht.service.model.survey.response.Surveie;
import com.eligasht.service.model.survey.response.checkValid.ResponseCheckValidResultDetail;
import com.eligasht.service.model.survey.response.detail.GetSurveyDetailsResult;
import com.eligasht.service.model.survey.response.detail.ResponseGetSurveyDetails;
import com.eligasht.service.model.survey.response.detail.SurveySection;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class SurveyActivity extends BaseActivity implements SurveyRecycleAdapter.ItemClickListener,SurveyDetailsRecycleAdapter.ItemClickListener,SurveyQuestionsRecycleAdapter.ItemClickListener ,View.OnClickListener , OnServiceStatus<ResponseGetSurvey> {

    public static int COUNT_FRAG=1;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
     public static  ArrayList<SurveyQuestionToShow> surveyQuestionToShows=new ArrayList<>();
    AboutAdapter mAdapter;
    TickerView v1, v2, v3;
    ImageView hotel;
    private FancyButton btnBack;
    private ProgressDialog pdLoading;
    RelativeLayout rlLoading,rlRoot;
    private SurveyRecycleAdapter adapter;
    private SurveyDetailsRecycleAdapter surveyDetailsRecycleAdapter;
    private SurveyQuestionsRecycleAdapter surveyQuestionsRecycleAdapter;
    RecyclerView rvSurvey,rvSurveyDetails;
    private GetSurveyDetailsResult getSurveyDetailsResult=null;

    //row_rv_survey
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);

         rvSurvey = findViewById(R.id.rvSurvey);
         rvSurveyDetails = findViewById(R.id.rvSurvey);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
       // addItem();

        SendRequestGetSurvey();


    }
    private void SendRequestGetSurvey() {

        try {
            //this method will be running on UI thread
            //this method will be running on UI thread
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        RequestGetSurvey requestGetSurvey = new RequestGetSurvey();
        com.eligasht.service.model.survey.request.Request request = new com.eligasht.service.model.survey.request.Request();
        com.eligasht.service.model.survey.request.Identity identity=new Identity();

        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");

        request.setIdentity(identity);
        request.setCulture(getString(R.string.culture));

        requestGetSurvey.setRequest(request);
        Log.e("RequestGetSurvey", new Gson().toJson(requestGetSurvey));
        SingletonService.getInstance().getSurveyService().surveyAvail(this,requestGetSurvey);
    }
    private void addItem(List<Surveie> getSurveyResult) {

        // set up the RecyclerView

        rvSurvey.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SurveyRecycleAdapter(this, getSurveyResult);
        adapter.setClickListener(this);
        rvSurvey.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        if(adapter!= null){
       // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        sendCheckValidResultDetail(adapter.getItem(position));
        //SendRequestGetSurveyDetails(adapter.getItem(position));
        }
        //set Question
       /* if (surveyDetailsRecycleAdapter !=null){
            Toast.makeText(this, "You clicked Details: " + surveyDetailsRecycleAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
            setQuestionsData(surveyDetailsRecycleAdapter.getItem(position));
        }*/
    }

    private void sendCheckValidResultDetail(String item) {
        try {
            //this method will be running on UI thread
            //this method will be running on UI thread
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        RequestCheckValidResultDetail requestCheckValidResultDetail = new RequestCheckValidResultDetail();
        com.eligasht.service.model.survey.request.checkValid.Request request = new com.eligasht.service.model.survey.request.checkValid.Request();
        com.eligasht.service.model.survey.request.checkValid.Identity identity=new com.eligasht.service.model.survey.request.checkValid.Identity();

        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");

        request.setIdentity(identity);
        request.setCulture(getString(R.string.culture));
        request.setEncryptedSurveyID(item);
        request.setApplicationUserID(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID());


        requestCheckValidResultDetail.setRequest(request);
        Log.e("RequestCheckValidResultDetail:", new Gson().toJson(requestCheckValidResultDetail));
        SingletonService.getInstance().getSurveyService().CheckValidResultDetailAvail(new OnServiceStatus<ResponseCheckValidResultDetail>() {
            @Override
            public void onReady(ResponseCheckValidResultDetail responseCheckValidResultDetail) {
                try {
                    rlLoading.setVisibility(View.GONE);
                    Utility.disableEnableControls(true,rlRoot);


                    if (responseCheckValidResultDetail!=null){
                       if(responseCheckValidResultDetail.getCheckValidResultDetailResult().getSuccessResult() == 9000){
                           SendRequestGetSurveyDetails(item);
                       }else
                       {
                           AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(SurveyActivity.this,true,true);
                           AlertDialogPassenger.setText(getString(R.string._previously_survey),getString(R.string.massege)+" ");
                       }
                    }

                } catch (Exception e) {
                   // Toast.makeText(SurveyActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
                    AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(SurveyActivity.this,true,true);
                    AlertDialogPassenger.setText(getString(R.string.error_in_connection),getString(R.string._error)+" ");
                }
            }

            @Override
            public void onError(String message) {
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true,rlRoot);
            }
        },requestCheckValidResultDetail);
    }

    private void setQuestionsData(String item) {
        rvSurvey.setVisibility(View.GONE);
        adapter=null;
        surveyDetailsRecycleAdapter=null;

        rvSurveyDetails.setVisibility(View.VISIBLE);

        rvSurveyDetails.setLayoutManager(new LinearLayoutManager(this));
        surveyQuestionsRecycleAdapter = new SurveyQuestionsRecycleAdapter(this,getSurveyDetailsResult.getSurveies().get(0).getSurveySections().get(Integer.parseInt(item)).getQuestions() );
        surveyQuestionsRecycleAdapter.notifyDataSetChanged();
        surveyQuestionsRecycleAdapter.setClickListener(this);
        rvSurveyDetails.setAdapter(surveyQuestionsRecycleAdapter);
    }

    private void SendRequestGetSurveyDetails(String item) {
        try {
            //this method will be running on UI thread
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        RequestGetSurveyDetails requestGetSurveyDetails = new RequestGetSurveyDetails();
        com.eligasht.service.model.survey.request.detail.Request request = new com.eligasht.service.model.survey.request.detail.Request();
        com.eligasht.service.model.survey.request.detail.Identity identity=new com.eligasht.service.model.survey.request.detail.Identity();

        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");

        request.setIdentity(identity);
        request.setCulture(getString(R.string.culture));
        request.setEncryptedSurveyID(item);
        request.setIsEncrypted(false);

        requestGetSurveyDetails.setRequest(request);
        Log.e("RequestGetSurveyDetails:", new Gson().toJson(requestGetSurveyDetails));
        SingletonService.getInstance().getSurveyService().GetSurveyDetailsAvail(new OnServiceStatus<ResponseGetSurveyDetails>() {
            @Override
            public void onReady(ResponseGetSurveyDetails responseGetSurveyDetails) {
                try {
                    rlLoading.setVisibility(View.GONE);
                    Utility.disableEnableControls(true,rlRoot);


                    if (responseGetSurveyDetails!=null){
                         getSurveyDetailsResult = responseGetSurveyDetails.getGetSurveyDetailsResult();
                        //if (GetAirportsResult.getErrors()!=null  ){
                        addItemDetail(getSurveyDetailsResult.getSurveies().get(0).getSurveySections(),getSurveyDetailsResult.getSurveies().get(0).getID());
                        // }
                    }

                } catch (Exception e) {
                    //Toast.makeText(SurveyActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
                    AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(SurveyActivity.this,true,true);
                    AlertDialogPassenger.setText(getString(R.string.error_in_connection),getString(R.string._error)+" ");
                }

            }

            @Override
            public void onError(String message) {
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true,rlRoot);
            }
        },requestGetSurveyDetails);
    }

    public void addItemDetail(List<SurveySection> surveySections, Integer idMain) {
        // add item in list view
       /* rvSurvey.setVisibility(View.GONE);
        adapter=null;
        rvSurveyDetails.setVisibility(View.VISIBLE);
        rvSurveyDetails.setLayoutManager(new LinearLayoutManager(this));

        surveyDetailsRecycleAdapter = new SurveyDetailsRecycleAdapter(this, surveySections);
        surveyDetailsRecycleAdapter.setClickListener(this);
        rvSurveyDetails.setAdapter(surveyDetailsRecycleAdapter);*/

     //  add item bakhsh in list and show in view pager
        int counterArray=0;
        surveyQuestionToShows=new ArrayList<>();

        for (int q = 0; q < surveySections.size() ; q++) {
            Log.e( "addItemDetail: ",q +"");
      /*    SurveyQuestionToShow surveyQuestionToShow=new SurveyQuestionToShow();

            surveyQuestionToShow.setSectionText(surveySections.get(q).getText()+"");
            surveyQuestionToShow.setSectionDesc(surveySections.get(q).getDescription()+"");
            surveyQuestionToShow.setSectionID(surveySections.get(q).getID());

            surveyQuestionToShow.setQuestionID(null);
            surveyQuestionToShow.setQuestionIDType(null);
            surveyQuestionToShow.setQuestionQuestion(null);

            surveyQuestionToShow.setQuestionAnswersArr(null);

            surveyQuestionToShow.setColor(getRandomColor()+"");
            //add
            surveyQuestionToShows.add(counterArray++,surveyQuestionToShow);*/

                //Add QUestions ONe Section in (surveyQuestionToShows)

                for (int j = 0; j < surveySections.get(q).getQuestions().size(); j++) {
                    SurveyQuestionToShow surveyQuestionToShow2=new SurveyQuestionToShow();
                     /* surveyQuestionToShow2.setSectionText(null);
                        surveyQuestionToShow2.setSectionDesc(null);
                        surveyQuestionToShow2.setSectionID(null);*/
                    surveyQuestionToShow2.setMainID(idMain);

                    surveyQuestionToShow2.setSectionText(surveySections.get(q).getText()+"");
                    surveyQuestionToShow2.setSectionDesc(surveySections.get(q).getDescription()+"");
                    surveyQuestionToShow2.setSectionID(surveySections.get(q).getID());

                    surveyQuestionToShow2.setQuestionIsRequired(surveySections.get(q).getQuestions().get(j).getIsRequired());

                        surveyQuestionToShow2.setQuestionID(surveySections.get(q).getQuestions().get(j).getID());
                        surveyQuestionToShow2.setQuestionIDType(surveySections.get(q).getQuestions().get(j).getAnswerType().getID());
                        surveyQuestionToShow2.setQuestionQuestion(surveySections.get(q).getQuestions().get(j).getText());

                            //setQuestionAnswersArr
                            ArrayList<SurveyAnswer> surveyAnswers=new ArrayList<>();
                            for (int k = 0; k <surveySections.get(q).getQuestions().get(j).getQuestionAnswers().size() ; k++) {
                                    SurveyAnswer surveyAnswer=new SurveyAnswer();
                                    surveyAnswer.setText(surveySections.get(q).getQuestions().get(j).getQuestionAnswers().get(k).getText());
                                    surveyAnswer.setAnswerId(surveySections.get(q).getQuestions().get(j).getQuestionAnswers().get(k).getID()+"");

                                surveyAnswers.add(surveyAnswer);
                            }


                        surveyQuestionToShow2.setQuestionAnswersArr(surveyAnswers);
                    //add
                    surveyQuestionToShows.add(counterArray++,surveyQuestionToShow2);
                }

        }
        COUNT_FRAG=surveyQuestionToShows.size();
                     EventBus.getDefault().post(new EventModel(surveyQuestionToShows));
                     //new Handler().postDelayed(this::closeDrawer, 200);
                     Intent intent4 = new Intent(this, MainSurveyActivity.class);
                     startActivity(intent4);
                   // startActivity(intent4);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        // view.setBackgroundColor(color);
        return  color;
    }


    @Override
    public void onReady(ResponseGetSurvey responseGetSurvey) {
        try {
            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);

            //List<SectionModel> data = new ArrayList<SectionModel>();
            if (responseGetSurvey!=null){
            GetSurveyResult GetAirportsResult = responseGetSurvey.getGetSurveyResult();//responseGetSurvey.getGetSurveyResult().getSurveies().get(0).getDescription();
                //if (GetAirportsResult.getErrors()!=null  ){
                    addItem(GetAirportsResult.getSurveies());
               // }
            }

        } catch (Exception e) {
           // Toast.makeText(SurveyActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
            AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(SurveyActivity.this,true,true);
            AlertDialogPassenger.setText(getString(R.string.error_in_connection),getString(R.string._error)+" ");
        }

    }

    @Override
    public void onError(String message) {
        rlLoading.setVisibility(View.GONE);
        Utility.disableEnableControls(true,rlRoot);
      //  Toast.makeText(SurveyActivity.this, getString(R.string.error_in_connection), Toast.LENGTH_LONG).show();
        AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(SurveyActivity.this,true,true);
        AlertDialogPassenger.setText(getString(R.string.error_in_connection),getString(R.string._error)+" ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }


}
