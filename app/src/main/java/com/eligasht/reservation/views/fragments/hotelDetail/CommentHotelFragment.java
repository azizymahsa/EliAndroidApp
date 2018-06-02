package com.eligasht.reservation.views.fragments.hotelDetail;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.api.hotel.comment.GetComment;
import com.eligasht.reservation.lost.CommentAdapterRecycle;
import com.eligasht.reservation.models.eventbus.CommentModelBus;
import com.eligasht.reservation.models.hotel.api.getComment.call.GetCommentRequest;
import com.eligasht.reservation.models.hotel.api.getComment.call.Request;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.views.activities.hotel.activity.CommentActivity;
import com.eligasht.reservation.models.CommentModel;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.getHotelReview.request.GetHReviewReq;
import com.eligasht.service.model.hotel.getHotelReview.request.GetHotelReviewRequest;
import com.eligasht.service.model.hotel.getHotelReview.response.GetHotelReviewResponse;
import com.eligasht.service.model.hotel.getHotelReview.response.Review;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import at.grabner.circleprogress.CircleProgressView;
import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza.nejati on 4/7/2018.
 */
public class CommentHotelFragment extends Fragment implements View.OnClickListener, OnServiceStatus<GetHotelReviewResponse> {
    private View view;
    private CircleProgressView circleView;
    private TextView tvAlertComment, tvCommentCount, tvVoteCount, tvRecommendedPercent, tvSortComment;
    private CommentAdapterRecycle commentAdapter;
    boolean isNew = false;
    private boolean isFirst = true;
    private String hotelName = null;
    private String hotelId = null;
    private FancyButton btnComment, btnOneComment, btnSortComment;
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
        try {
            this.hotelName = hotel.getHotelName();
            this.hotelId = hotel.getHotelId();
            if ((hotelName != null || hotelId != null) && isFirst && view != null) {
                isFirst = false;
                request();
            }
        }
        catch (Exception e)
        {

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
                        tvSortComment.setText(R.string.BenefitComment);
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
                        tvSortComment.setText(R.string.NewComment);
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
                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(intent)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
            case R.id.btnOneComment:
                Intent intent2 = new Intent(getActivity(), CommentActivity.class);
                intent2.putExtra("HotelName", hotelName);
                intent2.putExtra("HotelId", hotelId);
                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(intent2)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
        }
    }

    public void request() {
        aviComment.setVisibility(View.VISIBLE);
        GetHotelReviewRequest getHotelReviewRequest = new GetHotelReviewRequest();
        GetHReviewReq getHReviewReq = new GetHReviewReq();
        getHReviewReq.setCulture(getString(R.string.culture));
        getHReviewReq.setEHotelId(hotelId);
        getHotelReviewRequest.setRequest(getHReviewReq);
        SingletonService.getInstance().getHotelService().getComment(this, getHotelReviewRequest);
    }

    @Override
    public void onReady(GetHotelReviewResponse getHotelReviewResponse) {
        llCommentContent.setVisibility(View.VISIBLE);
        aviComment.setVisibility(View.GONE);
        try {
            circleView.setDecimalFormat(new DecimalFormat("0.0"));
            //todo change this
            Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_normal.ttf");
            circleView.setTextTypeface(face);
            circleView.setMaxValue(10);
            //circleView.setUnitVisible(false);
            for (Review review : getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getReviews()) {
                commentModels.add(new CommentModel(review.getHelpfulAmount(),
                        5, review.getTitle(),
                        review.getContent(),
                        review.getSubmitDate(),
                        review.getSubmitNickName()));
            }
            lvComments.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
            lvComments.setLayoutManager(new LinearLayoutManager(getActivity()));
            commentAdapter = new CommentAdapterRecycle(commentModels);
            lvComments.setAdapter(commentAdapter);
            lvComments.setFocusable(false);
            tvVoteCount.setText(getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getReviewsCount() + " " + getString(R.string.UserRate));
            tvCommentCount.setText(getString(R.string.CommentUser) + " " + getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getReviews().size() + " " + getString(R.string.Comment));
            tvRecommendedPercent.setText(getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getRecommendedPercent() + " " + getString(R.string.RecomandUser));
            circleView.setValueAnimated(Float.valueOf(getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getAverageScore()));
            if (getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getReviews() == null || getHotelReviewResponse.getGetHotelReviewResult().getHotelReview().getReviews().size() == 0) {
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

    @Override
    public void onError(String message) {
        llCommentContent.setVisibility(View.VISIBLE);
        aviComment.setVisibility(View.GONE);
    }
}
