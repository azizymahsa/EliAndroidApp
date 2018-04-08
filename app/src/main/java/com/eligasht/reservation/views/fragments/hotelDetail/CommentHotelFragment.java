package com.eligasht.reservation.views.fragments.hotelDetail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.api.hotel.comment.GetComment;
import com.eligasht.reservation.lost.CommentAdapterRecycle;
import com.eligasht.reservation.models.eventbus.CommentModelBus;
import com.eligasht.reservation.models.eventbus.HotelProprtiesBus;
import com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties;
import com.eligasht.reservation.models.hotel.api.getComment.call.GetCommentRequest;
import com.eligasht.reservation.models.hotel.api.getComment.call.Request;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.views.activities.hotel.activity.CommentActivity;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.adapters.hotel.comment.CommentModel;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.ui.NonScrollGridView;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import at.grabner.circleprogress.CircleProgressView;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class CommentHotelFragment extends Fragment implements View.OnClickListener {
    private View view;
    private CircleProgressView circleView;
    private TextView  tvAlertComment, tvCommentCount, tvVoteCount, tvRecommendedPercent,tvSortComment;
    private CommentAdapterRecycle commentAdapter;
    boolean isNew = false;
    private GetComment getComment;
    private boolean isComment = true,isFirst=true;
    private String hotelName=null;
    private String hotelId=null;
    private FancyButton btnComment,btnOneComment,btnSortComment;


    private AVLoadingIndicatorView aviComment;
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    private NonScrollRecyclerView lvComments;
    private LinearLayout llCommentContent;
    public static CommentHotelFragment instance() {
        CommentHotelFragment fragment = new CommentHotelFragment();
      //  EventBus.getDefault().register(fragment);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;
        view = inflater.inflate(R.layout.fragment_room_comment, container, false);
        initView();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void setDataComment(CommentModelBus hotel) {
        this.hotelName=hotel.getHotelName();
        this.hotelId=hotel.getHotelId();
        if ((hotelName!=null||hotelId!=null)&&isFirst){
            isFirst=false;
            new GetCommentAsync().execute();

        }


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void initView() {
        circleView = view.findViewById(R.id.circleView);
        aviComment = view.findViewById(R.id.aviComment);
        lvComments = view.findViewById(R.id.lvComments);
        tvAlertComment = view.findViewById(R.id.tvAlertComment);
        tvCommentCount = view.findViewById(R.id.tvCommentCount);
        tvVoteCount = view.findViewById(R.id.tvVoteCount);
        tvRecommendedPercent = view.findViewById(R.id.tvRecommendedPercent);
        tvSortComment = view.findViewById(R.id.tvSortComment);
        btnComment = view.findViewById(R.id.btnComment);
        btnOneComment = view.findViewById(R.id.btnOneComment);
        llCommentContent = view.findViewById(R.id.llCommentContent);
        btnSortComment = view.findViewById(R.id.btnSortComment);
        btnComment.setOnClickListener(this);
        btnOneComment.setOnClickListener(this);
        btnSortComment.setOnClickListener(this);
        btnComment.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOneComment.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnSortComment.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));



    }




    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnSortComment:
                try {
                    if (isNew) {


                        tvSortComment.setText(R.string.NewComment);
                        isNew = false;
                        Collections.sort(commentModels, new Comparator<CommentModel>() {
                            public int compare(CommentModel o1, CommentModel o2) {
                                if (o1.getDate() == null || o2.getDate() == null)
                                    return 0;
                                return o2.getDate().compareTo(o1.getDate());
                            }
                        });
                        commentAdapter.notifyDataSetChanged();

                    } else {


                        isNew = true;


                        tvSortComment.setText(R.string.BenefitComment);


                        Collections.sort(commentModels, new Comparator<CommentModel>() {
                            @Override
                            public int compare(CommentModel p1, CommentModel p2) {
                                return p2.getLike() - p1.getLike(); // Ascending
                            }
                        });
                        commentAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                }


                break;
            case R.id.btnComment:
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra("HotelName", hotelName);
                intent.putExtra("HotelId", String.valueOf(hotelId));
                startActivity(intent);
                break;
            case R.id.btnOneComment:
                Intent intent2 = new Intent(getActivity(), CommentActivity.class);
                intent2.putExtra("HotelName", hotelName);
                intent2.putExtra("HotelId", hotelId);


                startActivity(intent2);
                break;
        }
    }

    private class GetCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            aviComment.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                GetCommentRequest getCommentRequest = new GetCommentRequest();
                Request request = new Request();
                request.setCulture(getString(R.string.culture));
                request.setEHotelId(hotelId);
                getCommentRequest.setRequest(request);
                Log.e("testtt", new Gson().toJson(getCommentRequest).toString());
                getComment = new GetComment(getCommentRequest);

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            llCommentContent.setVisibility(View.VISIBLE);
            aviComment.setVisibility(View.GONE);
            isComment = false;

        try {
                circleView.setDecimalFormat(new DecimalFormat("0.0"));
                //todo change this
                Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_normal.ttf");
                circleView.setTextTypeface(face);

                circleView.setMaxValue(10);
                //circleView.setUnitVisible(false);


                for (int i = 0; i < getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length; i++) {
                    commentModels.add(new CommentModel(Integer.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews[i].HelpfulAmount),
                            5, getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews[i].Title,
                            getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews[i].Content,
                            getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews[i].SubmitDate,
                            getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews[i].SubmitNickName));


                }


                lvComments.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
                lvComments.setLayoutManager(new LinearLayoutManager(getActivity()));

                commentAdapter = new CommentAdapterRecycle(commentModels);
                lvComments.setAdapter(commentAdapter);
                lvComments.setFocusable(false);

                tvVoteCount.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.ReviewsCount +" "+ getString(R.string.UserRate));
                tvCommentCount.setText(getString(R.string.CommentUser)+" "+ getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length +" "+ getString(R.string.Comment));
                tvRecommendedPercent.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.RecommendedPercent +" "+ getString(R.string.RecomandUser));
                circleView.setValueAnimated(Float.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.AverageScore));

            if (getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews == null || getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length == 0) {

                tvAlertComment.setVisibility(View.VISIBLE);
                btnOneComment.setVisibility(View.VISIBLE);
                llCommentContent.setVisibility(View.GONE);
            }

                lvComments.setOnFlingListener(new RecyclerView.OnFlingListener() {
                    @Override
                    public boolean onFling(int velocityX, int velocityY) {
                        lvComments.dispatchNestedFling(velocityX, velocityY, false);
                        return false;
                    }
                });

            } catch (Exception e) {

            }
        }
    }
}
