package com.eligasht.reservation.views.activities.new_survey;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.adapter.MyPagerAdapter;
import com.eligasht.reservation.views.activities.new_survey.model.AnswerModel;
import com.eligasht.reservation.views.activities.new_survey.model.EventModel;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyResultDetailsFake;
import com.eligasht.reservation.views.activities.survey.SurveyActivity;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.Identity;
import com.eligasht.service.model.flight.request.airPort.Request;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.survey.request.addServeyResult.Answer;
import com.eligasht.service.model.survey.request.addServeyResult.RequestSetAnswer;
import com.eligasht.service.model.survey.request.addServeyResult.SurveyResult;
import com.eligasht.service.model.survey.request.addServeyResult.SurveyResultDetail;
import com.eligasht.service.model.survey.response.addServeyResult.ResponseSetAnswer;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainSurveyActivity extends FragmentActivity implements View.OnClickListener, OnServiceStatus<ResponseSetAnswer> {
    ArrayList<SurveyQuestionToShow> surveyQuestionToShows=new ArrayList<>();
    private int sizePage= SurveyActivity.COUNT_FRAG;
    public static String TV_TITLE="";
    private FancyButton btnBack;
    private LinearLayout btnNext,btnPrev;
    private TabLayout tabLayout;
    private TextView lblMoratabSazi,tvTitle,txtPrev,txtNext;
    private int currentPagePos;
    MyPagerAdapter pagerAdapter;
    ArrayList<SurveyResultDetailsFake> surveyResultDetailsFakes;
  //  MyPagerAdapter myPagerAdapter=new MyPagerAdapter(MainSurveyActivity.this);
    ViewPager pager;
    private boolean FlagSend=false;
    private boolean FlagIsRequestable=true;
    private boolean FlagAnswer=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

      //  Typeface type = Typeface.createFromAsset(getApplicationContext().getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        Typeface type = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/iran_sans_normal.ttf");
        EventBus.getDefault().register(this);
        surveyQuestionToShows=SurveyActivity.surveyQuestionToShows;
         pager = (ViewPager) findViewById(R.id.viewPager);
      //  StatusBarUtil.immersive(this);
        tabLayout = findViewById(R.id.tab_layout);
        lblMoratabSazi = (TextView) findViewById(R.id.txtNext);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTypeface(type);


        if (surveyQuestionToShows !=null)
        tvTitle.setText((surveyQuestionToShows.get(0).getSectionText() != null ) ? surveyQuestionToShows.get(0).getSectionText() : " ");

        pagerAdapter=new MyPagerAdapter(getFragmentManager(),surveyQuestionToShows,sizePage,getBaseContext());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
       // tabLayout.clearOnTabSelectedListeners();
        ///initialize list Answer
         surveyResultDetailsFakes=new ArrayList<>();
        List<AnswerModel> answerModelss=new ArrayList<>();
        SurveyResultDetailsFake surveyResultDetailsFakee=new SurveyResultDetailsFake(0, answerModelss);
        if(surveyResultDetailsFakes.size()<1)
            for (int i = 0; i <surveyQuestionToShows.size() ; i++) {
                surveyResultDetailsFakes.add(i,surveyResultDetailsFakee);
            }

        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }


        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnPrev = findViewById(R.id.btnPrev);
        txtPrev = findViewById(R.id.txtPrev);
        txtNext = findViewById(R.id.txtNext);
        btnPrev.setOnClickListener(this);
        txtPrev.setTypeface(type);
        txtNext.setTypeface(type);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Log.i( "onPageSelected: ",position+"");
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
                Log.i( "onPageScrolled: ",position+"");
                currentPagePos=position;
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
                Log.i( "onPageScrollStateChanged: ",state+"");
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)//ThreadMode.MAIN dar ui support mishe
    public void onMessageEvent(EventModel event) {
        surveyQuestionToShows=event.surveyQuestionToShows;
        sizePage=surveyQuestionToShows.size();
        Log.e("get eventBus value:", "gereftaaaaaaaaaam"+surveyQuestionToShows.size());//gereftane meghdare modele event bus
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnPrev:

                try {
                    int j = pager.getCurrentItem();
                    j = j - 1;
                    if (surveyQuestionToShows !=null)
                        tvTitle.setText((surveyQuestionToShows.get(j).getSectionText() != null ) ? surveyQuestionToShows.get(j).getSectionText() : " ");
                    pager.setCurrentItem(j, true);

                    if (lblMoratabSazi.getText().toString().equals(getString(R.string._finish))) {
                        lblMoratabSazi.setText(getString(R.string._new_answer) + "");
                    }
                   // tvTitle.setText(TV_TITLE+"");
                } catch (Exception e) {
                    e.getMessage();
                }




                break;
            case R.id.btnNext:
                //int size=pager.getChildCount();
                int i = pager.getCurrentItem();
                try{
                    if (surveyQuestionToShows !=null )
                        tvTitle.setText((surveyQuestionToShows.get(i+1).getSectionText() != null ) ? surveyQuestionToShows.get(i+1).getSectionText() : " ");
                }catch (Exception e){
                    e.getMessage();
                }
                setdataAnswer(i);
                if (FlagAnswer){

                    i = i + 1;
                    pager.setCurrentItem(i, true);

                    if (lblMoratabSazi.getText().toString().equals(getString(R.string._finish))) {
                        if (FlagSend) {
                            AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this, true, true);
                            alertDialogPassenger.setText(getString(R.string._register_already_opinion)+"", getString(R.string.massege));
                        } else {
                            sendRequestAnswer();
                        }

                    }

                    if ((sizePage - 1) == i) {
                        lblMoratabSazi.setText(getString(R.string._finish) + "");
                    }
                 }else if (FlagIsRequestable) {
                    AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this, true, false);
                    alertDialogPassenger.setText(getString(R.string._answer_question_mandatory), getString(R.string.massege));
                    try{
                        if (surveyQuestionToShows !=null )
                            tvTitle.setText((surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ");
                    }catch (Exception e){
                        e.getMessage();
                    }
                }else{
                    i = i + 1;
                    pager.setCurrentItem(i, true);

                    if (lblMoratabSazi.getText().toString().equals(getString(R.string._finish))) {
                        if (FlagSend) {
                            AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this, true, true);
                            alertDialogPassenger.setText(getString(R.string._register_already_opinion), getString(R.string.massege));
                        } else {
                            sendRequestAnswer();
                        }

                    }

                    if ((sizePage - 1) == i) {
                        lblMoratabSazi.setText(getString(R.string._finish) + "");
                    }
                }
              // tvTitle.setText(TV_TITLE+"");
                break;
        }
    }



    private void setdataAnswer(int prevPagePos) {
        Log.i( "prevPagePos: ",prevPagePos+"");
        Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + pager.getCurrentItem());
        page.getArguments().get("tvDescM");//Bundle[{tvDescM=سوال نمونه 1, rd0=گزینه نمونه 1, rd1=گزینه نمونه 2, rd2=گزینه نمونه 3, rd3=گزینه نمونه 4, tvTitleM=بخش 1}]
        FragmentManager fragment=page.getChildFragmentManager();
       // int size=pager.getChildCount();

        if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyAnswerDateFragment){
                FlagAnswer=false;
                Log.i( " == ","SurveyAnswerDateFragment");
                ArrayList<GetReplyModel> ff= ((SurveyAnswerDateFragment)page).updateList();


                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if(ff.get(i).Value.length()>2){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;

                }
                if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }


        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyAnswerLongFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyAnswerLongFragment");
                ArrayList<GetReplyModel> ff=((SurveyAnswerLongFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if (ff.get(i).Value.length()>2){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;


                }
                
               if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }


        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyAnswerSelectionFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyAnswerSelectionFragment");
                ArrayList<GetReplyModel> ff =((SurveyAnswerSelectionFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if(ff.get(i).Value.length()>1){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;


                }
               if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }

        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyAnswerShortFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyAnswerShortFragment");
                ArrayList<GetReplyModel> ff =((SurveyAnswerShortFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if(ff.get(i).Value.length()>2){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;


                }
               if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }

        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyAnswerTimeFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyAnswerTimeFragment");
                ArrayList<GetReplyModel> ff =((SurveyAnswerTimeFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if(ff.get(i).Value.length()>2){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;

                }
               if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }

        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyMultiCheckBoxFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyMultiCheckBoxFragment");
                ArrayList<GetReplyModel> ff =((SurveyMultiCheckBoxFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;
                    FlagAnswer=true;

                }
                int SurveyQuestionID=0;
                if(ff.size()>0){
                    SurveyQuestionID=ff.get(0).SurveyQuestionID;

                SurveyResultDetailsFake surveyResultDetailsFake=new SurveyResultDetailsFake(SurveyQuestionID,answerModels);
                surveyResultDetailsFakes.set(prevPagePos,surveyResultDetailsFake);
                }


        }else if ( pagerAdapter.getItem(prevPagePos) instanceof SurveyMultiRadioFragment){
            FlagAnswer=false;
                Log.i( " == ","SurveyMultiRadioFragment");
                ArrayList<GetReplyModel> ff =((SurveyMultiRadioFragment)page).updateList();

                List<AnswerModel> answerModels=new ArrayList<>();
                for (int i = 0; i < ff.size(); i++) {
                    if (ff.get(i).Value.length()>2){
                        FlagAnswer=true;
                    }
                    AnswerModel answerModel=new AnswerModel(ff.get(i).ID,ff.get(i).Value);
                    answerModels.add(i,answerModel);
                    FlagIsRequestable=ff.get(0).IsRequired;

                }
               if (ff.size()>0) {
                    SurveyResultDetailsFake surveyResultDetailsFake = new SurveyResultDetailsFake(ff.get(0).SurveyQuestionID, answerModels);
                    surveyResultDetailsFakes.set(prevPagePos, surveyResultDetailsFake);
                }

        }
        //print
        for (int i = 0; i <  surveyResultDetailsFakes.size(); i++) {
            Log.e("surveyResultDetailsFakes: ",i+" "+ surveyResultDetailsFakes.get(i).getAnswerModels().toString());
        }

    }

    private void sendRequestAnswer() {
        //ID=12
        surveyQuestionToShows.get(0).getMainID();
        for (int i = 0; i <  surveyResultDetailsFakes.size(); i++) {
            Log.e("surveyAnswerModel: ",i+" "+ surveyResultDetailsFakes.get(i).getAnswerModels().toString());
        }
//
       // avi.setVisibility(View.VISIBLE);

        RequestSetAnswer requestAirports = new RequestSetAnswer();
        com.eligasht.service.model.survey.request.addServeyResult.Request request = new  com.eligasht.service.model.survey.request.addServeyResult.Request();

        com.eligasht.service.model.survey.request.addServeyResult.Identity identity = new com.eligasht.service.model.survey.request.addServeyResult.Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");

        request.setCulture(getString(R.string.culture));
        request.setIdentity(identity);
//////////////////////////
        SurveyResult surveyResult=new SurveyResult();
        surveyResult.setSurveyID(surveyQuestionToShows.get(0).getMainID());
        List<SurveyResultDetail> surveyResultDetails=new ArrayList<>();


        for (int i = 0; i <  surveyResultDetailsFakes.size(); i++){
            SurveyResultDetail surveyResultDetail=new SurveyResultDetail();
            surveyResultDetail.setSurveyQuestionID(surveyResultDetailsFakes.get(i).getSurveyQuestionID());

             List<Answer> answers=new ArrayList<>();
            for (int j = 0; j <  surveyResultDetailsFakes.get(i).getAnswerModels().size(); j++) {
                    com.eligasht.service.model.survey.request.addServeyResult.Answer answer=new com.eligasht.service.model.survey.request.addServeyResult.Answer();
                    answer.setID(surveyResultDetailsFakes.get(i).getAnswerModels().get(j).getID());
                    answer.setValue(surveyResultDetailsFakes.get(i).getAnswerModels().get(j).getValue());
                    answers.add(j,answer);
            }
            surveyResultDetail.setAnswers(answers);
            surveyResultDetails.add(surveyResultDetail);
        }



        surveyResult.setSurveyResultDetails(surveyResultDetails);

        request.setSurveyResult(surveyResult);
//////////////////////////
        requestAirports.setRequest(request);
        Log.i("sendRequestAnswer: ",new Gson().toJson(requestAirports) );
        SingletonService.getInstance().getSurveyService().AddSurveyResultAvail(this, requestAirports);

    }
    @Override
    public void onReady(ResponseSetAnswer responseSetAnswer) {
        Log.i("ResponseSetAnswer: ",new Gson().toJson(responseSetAnswer.getAddSurveyResultResult().getSuccessResult()) );
        try {
            if (responseSetAnswer != null){
               Integer okey= responseSetAnswer.getAddSurveyResultResult().getSuccessResult();
               if (okey==2){
                   //show dialog
                         FlagSend=true;
                         btnPrev.setEnabled(false);
                         btnPrev.setBackgroundResource(R.drawable.background_strock_gray);
                         txtPrev.setTextColor(Color.parseColor("#a9a9a9"));

                       AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this,true,true);
                       alertDialogPassenger.setText(getString(R.string.comment_successfully),getString(R.string.massege));//

                   ///
               }else{
                   AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this,true,true);
                   alertDialogPassenger.setText(responseSetAnswer.getAddSurveyResultResult().getErrors().get(0).getMessage()+"",getString(R.string._error));
               }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void onError(String message){
        try {

            //show dialog
            FlagSend=false;
            AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this,false,true);
            alertDialogPassenger.setText(getString(R.string._comment_not_successfully),getString(R.string._error));//

            ///

        }catch (Exception e){
            e.getMessage();
        }
    }
}