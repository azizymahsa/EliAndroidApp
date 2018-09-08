package com.eligasht.reservation.views.activities.new_survey;
import android.app.FragmentManager;
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
import com.eligasht.reservation.views.activities.new_survey.model.EventModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import com.eligasht.reservation.views.activities.survey.SurveyActivity;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainSurveyActivity extends FragmentActivity implements View.OnClickListener{
    ArrayList<SurveyQuestionToShow> surveyQuestionToShows=new ArrayList<>();
    private int sizePage= SurveyActivity.COUNT_FRAG;
    private FancyButton btnBack;
    private LinearLayout btnNext,btnPrev;
    private TabLayout tabLayout;
    private TextView lblMoratabSazi;
    private int currentPagePos;
    MyPagerAdapter pagerAdapter;
  //  MyPagerAdapter myPagerAdapter=new MyPagerAdapter(MainSurveyActivity.this);
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);
        EventBus.getDefault().register(this);
        surveyQuestionToShows=SurveyActivity.surveyQuestionToShows;
         pager = (ViewPager) findViewById(R.id.viewPager);
      //  StatusBarUtil.immersive(this);
        tabLayout = findViewById(R.id.tab_layout);
        lblMoratabSazi = (TextView) findViewById(R.id.lblMoratabSazi);
        pagerAdapter=new MyPagerAdapter(getFragmentManager(),surveyQuestionToShows,sizePage,getBaseContext());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
       // tabLayout.clearOnTabSelectedListeners();

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
        btnPrev.setOnClickListener(this);

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
                   /* try {
                        int j= pager.getCurrentItem();
                        j=j-1;
                        pager.setCurrentItem(j, true);
                        if (lblMoratabSazi .getText().toString().equals(getString(R.string._finish))) {
                            lblMoratabSazi.setText(getString(R.string._new_answer)+"");
                        }
                    }catch (Exception e){
                        e.getMessage();
                    }*/
                    Fragment page = getFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + pager.getCurrentItem());
                    page.getArguments().get("tvDescM");//Bundle[{tvDescM=سوال نمونه 1, rd0=گزینه نمونه 1, rd1=گزینه نمونه 2, rd2=گزینه نمونه 3, rd3=گزینه نمونه 4, tvTitleM=بخش 1}]
                    FragmentManager fragment=page.getChildFragmentManager();
                   /* if (page.getChildFragmentManager() == new SurveyMultiRadioFragment()) {

                    }*/

                    // based on the current position you can then cast the page to the correct
                    // class and call the method:
                    int size=pager.getChildCount();
                   // for (int i = 0; i <size ; i++) {
                        if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyAnswerDateFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyAnswerDateFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyAnswerLongFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyAnswerLongFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyAnswerSelectionFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyAnswerSelectionFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyAnswerShortFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyAnswerShortFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyAnswerTimeFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyAnswerTimeFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyMultiCheckBoxFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyMultiCheckBoxFragment");
                        }else if ( pagerAdapter.getItem(currentPagePos) instanceof SurveyMultiRadioFragment){
                            Log.i( "PPPPPPPPPPPPP== ","SurveyMultiRadioFragment");
                        }
                        /*if (pager.getCurrentItem() == i && page != null) {
                            String ff=((SurveyMultiRadioFragment)page).updateList("new item");
                            Log.i( "updateList: ","FFFFFFFFFFFFFFFFFF"+ff);
                        }*/

                    //}

                    break;
                case R.id.btnNext:
                    //int size=pager.getChildCount();
                    int i= pager.getCurrentItem();
                    i=i+1;
                    pager.setCurrentItem(i, true);

                    if (lblMoratabSazi .getText().toString().equals(getString(R.string._finish))) {
                        try {
                            AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(MainSurveyActivity.this,false,true);
                            alertDialogPassenger.setText("نظر شما با موفقیت ثبت شد","پیغام");
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }
                    if ((sizePage-1)==i){
                        lblMoratabSazi.setText(getString(R.string._finish)+"");
                    }
                break;
        }
    }




}