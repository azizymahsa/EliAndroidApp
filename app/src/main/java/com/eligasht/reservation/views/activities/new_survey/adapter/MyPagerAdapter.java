package com.eligasht.reservation.views.activities.new_survey.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import com.eligasht.reservation.views.activities.new_survey.MainSurveyActivity;
import com.eligasht.reservation.views.activities.new_survey.SurveyAnswerDateFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyAnswerLongFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyAnswerSelectionFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyAnswerShortFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyAnswerTimeFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyMultiCheckBoxFragment;
import com.eligasht.reservation.views.activities.new_survey.SurveyMultiRadioFragment;
import com.eligasht.reservation.views.activities.new_survey.ThirdSurveyFragment;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<SurveyQuestionToShow> surveyQuestionToShows=new ArrayList<>();
    private int sizePage=0;
    private Context context;
    public MyPagerAdapter(FragmentManager fm, ArrayList<SurveyQuestionToShow> surveyQuestionToShows, int sizePage, Context context) {
        super(fm);
        this.surveyQuestionToShows=surveyQuestionToShows;
        this.sizePage=sizePage;
        this.context=context;
    }
    public MyPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }
    // Force a refresh of the page when a different fragment is displayed
    @Override
    public int getItemPosition(Object object) {
        // this method will be called for every fragment in the ViewPager
        if (object instanceof MainSurveyActivity) {
            return POSITION_UNCHANGED; // don't force a reload
        } else {
            // POSITION_NONE means something like: this fragment is no longer valid
            // triggering the ViewPager to re-build the instance of this fragment.
            return POSITION_NONE;
        }
    }
    @Override
    public Fragment getItem(int pos) {

        for (int i = 0; i < surveyQuestionToShows.size(); i++) {
            if(pos==i){
                //Question
                if(surveyQuestionToShows.get(i).getQuestionIDType()!=null){
                    try{
                        if (surveyQuestionToShows.get(i).getQuestionIDType() == 1) {
                           // MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //جواب کوتاه
                            return SurveyAnswerShortFragment.newInstance("SurveyAnswerShortFragment, جواب کوتاه",surveyQuestionToShows.get(i));
                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 2){
                          //  MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //جواب بلند
                            return SurveyAnswerLongFragment.newInstance("SurveyAnswerLongFragment, جواب بلند 1",surveyQuestionToShows.get(i));
                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 3){
                            //MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //چندگزینه ای
                            return SurveyMultiRadioFragment.newInstance("SurveyMultiRadioFragment, چندگزینه ای",surveyQuestionToShows.get(i));//
                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 4){
                          //  MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //چند جوابی
                            return SurveyMultiCheckBoxFragment.newInstance("ThirdSurveyFragment, چند جوابی ای"+"Bakhsh=",surveyQuestionToShows.get(i));

                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 5){
                          //  MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //لیست انتخاب
                            return SurveyAnswerSelectionFragment.newInstance("SurveyAnswerSelectionFragment,لیست انتخاب",surveyQuestionToShows.get(i));

                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 6){
                          //  MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //تاریخ
                            return SurveyAnswerDateFragment.newInstance("ThirdSurveyFragment, تاریخ"+"Bakhsh=",surveyQuestionToShows.get(i),context);

                        }else if(surveyQuestionToShows.get(i).getQuestionIDType() == 7){
                           // MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.get(i).getSectionText() != null ) ? surveyQuestionToShows.get(i).getSectionText() : " ";
                            //ساعت
                            return SurveyAnswerTimeFragment.newInstance("ThirdSurveyFragment, ساعت"+"Bakhsh=",surveyQuestionToShows.get(i),context);

                        }
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
                //Section
                if(surveyQuestionToShows.get(i).getSectionID()!=null){
                    return ThirdSurveyFragment.newInstance("ThirdSurveyFragment, Default"+"Bakhsh="+surveyQuestionToShows.get(i).getSectionText().toString());
                }
            }

        }
        return ThirdSurveyFragment.newInstance("FirstFragment, Instance 1");
    }

    @Override
    public int getCount() {
        return sizePage;
    }

}
