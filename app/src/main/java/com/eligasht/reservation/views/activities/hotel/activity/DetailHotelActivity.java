package com.eligasht.reservation.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.api.hotel.GetHotelDetail;
import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.api.hotel.comment.GetComment;
import com.eligasht.reservation.api.hotel.room.GetRoomsList;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.lost.CommentAdapterRecycle;
import com.eligasht.reservation.models.eventbus.HotelProprtiesBus;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.eligasht.reservation.models.hotel.api.detail.ImageHotel;
import com.eligasht.reservation.models.hotel.api.detail.call.GetHotelDRequest;
import com.eligasht.reservation.models.hotel.api.detail.call.GetHotelDetailRequest;
import com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties;
import com.eligasht.reservation.models.hotel.api.getComment.call.GetCommentRequest;
import com.eligasht.reservation.models.hotel.api.getComment.call.Request;
import com.eligasht.reservation.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.eligasht.reservation.models.hotel.api.rooms.call.IdentityRooms;
import com.eligasht.reservation.models.hotel.api.rooms.call.RoomRequest;
import com.eligasht.reservation.models.hotel.api.rooms.response.RoomList;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.hotel.comment.CommentModel;
import com.eligasht.reservation.views.adapters.hotel.hotelDetail.HotelDetailViewPager;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.eligasht.reservation.views.adapters.hotel.rooms.ImageModel;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;
import com.eligasht.reservation.views.fragments.profile.ProfilePagerAdapter;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.NonScrollGridView;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.ViewPagerAttention;
import com.eligasht.reservation.views.ui.dialog.hotel.AddCommnetDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;

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


public class DetailHotelActivity extends BaseActivity implements View.OnClickListener, AddCommnetDialog.OnCommentDialogListenerArray {

    private TextView tvTitle, tvAlertComment, tvCommentCount, tvVoteCount, tvRecommendedPercent;
    //  private NonScrollListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private ArrayList<String> arrayStringList = new ArrayList<>();
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    private RelativeLayout rlLoading, rlLoading2;
    CoordinatorLayout rlRoot;
    private AddComment addComment;
    private RoomsAdapter roomsAdapter;
    private GetRoomsList getRoomsList;
    private Window window;
    private HotelDetailViewPager hotelDetailViewPager;
    private ViewPager view_pager;
    Toolbar toolbar;


    private GetHotelDetail getHotelDetail;
    private TextView tvHotelName, tvCityName, tvAdress, tvAlert, tvAlertError;
    private ImageView ivImage;
    private LinearLayout llDynamic, llLoading, llComment, llEmkanat;
    private AVLoadingIndicatorView aviComment;
    private ImageView ivLoading;
    private AddCommnetDialog addCommnetDialog;
    private String comment, userName, title;
    private CommentAdapterRecycle commentAdapter;
    private TextView tvSortComment, tvDateDetail, tvCommentClickText, tvMapClickText,
            tvEmakanatClickText, tvRezervClickText;
    boolean isNew = false;
    private ScrollView svDetail;
    private RelativeLayout elNotFound;
    private GetComment getComment;
    private boolean isComment = true;
    private CircleProgressView circleView;
    private NonScrollRecyclerView lvComments;
    private CardView cvHotel;
    private FrameLayout flViewPager;
    private TabLayout tab_layout;
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel_2);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.DetailHotel));
        window = getWindow();
        initView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void initView() {
        rlLoading = findViewById(R.id.rlLoading);
        flViewPager = findViewById(R.id.flViewPager);
        cvHotel = findViewById(R.id.cvHotel);
        rlRoot = findViewById(R.id.rlRoot);
        ivLoading = findViewById(R.id.ivLoading);
        tvCommentClickText = findViewById(R.id.tvCommentClickText);
        tvMapClickText = findViewById(R.id.tvMapClickText);
        tvEmakanatClickText = findViewById(R.id.tvEmakanatClickText);
        tvRezervClickText = findViewById(R.id.tvRezervClickText);


        tvAdress = findViewById(R.id.tvAdress);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        lvComments = findViewById(R.id.lvComments);
        tvCityName = findViewById(R.id.tvCityName);
        llDynamic = findViewById(R.id.llDynamic);
        tvAlertComment = findViewById(R.id.tvAlertComment);
        aviComment = findViewById(R.id.aviComment);
        llComment = findViewById(R.id.llComment);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        tvSortComment = findViewById(R.id.tvSortComment);
        elNotFound = findViewById(R.id.elNotFound);
        tvAlertError = findViewById(R.id.tvAlertError);
        tvAlert = findViewById(R.id.tvAlert);
        llEmkanat = findViewById(R.id.llEmkanat);
        circleView = findViewById(R.id.circleView);
        tvCommentCount = findViewById(R.id.tvCommentCount);
        tvVoteCount = findViewById(R.id.tvVoteCount);
        tvRecommendedPercent = findViewById(R.id.tvRecommendedPercent);
        rlLoading2 = findViewById(R.id.rlLoading2);
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        initToolbar(toolbar);


        tvTitle = findViewById(R.id.tvTitle);
        roomsAdapter = new RoomsAdapter(roomsModels, this, rlRoot, rlLoading, window);
        rlLoading2.setOnClickListener(this);
        Utility.setAnimLoading(this);
        tvDateDetail.setText(getIntent().getExtras().getString("DateTime"));
        hotelDetailViewPager = new HotelDetailViewPager(this, getSupportFragmentManager());
        view_pager.setAdapter(hotelDetailViewPager);
        tab_layout.setupWithViewPager(view_pager);
        view_pager.setCurrentItem(3);
        tab_layout.setOnTabSelectedListener(onTabSelectedListener);


        ViewGroup vg = (ViewGroup) tab_layout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf)));
                }
            }
        }


        new GetRoomsAsync().execute();

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
            case R.id.btnOk:
                finish();
                break;
            case R.id.btnComment:
                Intent intent = new Intent(this, CommentActivity.class);
                Log.e("HotelNname", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent.putExtra("HotelName", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent.putExtra("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));
                Log.e("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));
                startActivity(intent);
                break;
            case R.id.btnOneComment:
                Intent intent2 = new Intent(this, CommentActivity.class);
                Log.e("HotelNname", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent2.putExtra("HotelName", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent2.putExtra("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));
                Log.e("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));


                startActivity(intent2);
                break;
        }

    }


    @Override
    public void onReturnValue(String userName, String title) {
        this.userName = userName;
        this.title = title;

    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            rlLoading2.setVisibility(View.VISIBLE);


            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.blue2));
            }

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getRoomsList = new GetRoomsList(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("EligashtMlb", "123qwe!@#QWE"
                        , "Mobile"), "",
                        String.valueOf(getIntent().getExtras().getInt("HotelId")),
                        "", "", getIntent().getExtras().getString("ResultUniqID"),
                        getString(R.string.culture))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);


            try {

                if (getRoomsList.getRoomsListResponse.GetRoomsListResult.errors != null) {
                    elNotFound.setVisibility(View.VISIBLE);

                    tvAlertError.setText(getRoomsList.getRoomsListResponse.GetRoomsListResult.errors.get(0).getMessage());

                } else {

                    int i = 0;
                    for (RoomList roomList : getRoomsList.getRoomsListResponse.GetRoomsListResult.roomList) {
                        Log.e("testtest", roomList.Description);

                        roomsModels.add(new RoomsModel(roomList.Board, roomList.Title, roomList.Description, roomList.Price,
                                roomList.OfferId, roomList.EHotelId, getRoomsList.getRoomsListResponse.GetRoomsListResult.SearchKey));
                        //   i++;

                    }
                    EventBus.getDefault().post(new RoomsModelBus(roomsModels));
                    new GetHoldDetailAsync().execute();


                }
                try {
                    if (getIntent().getExtras().getString("Pakage").equals("Pakage")) {
                        // lvRooms.setVisibility(View.GONE);

                    }
                } catch (Exception e) {
                }
            } catch (Exception e) {

                elNotFound.setVisibility(View.VISIBLE);
                rlLoading2.setVisibility(View.GONE);
                tvAlertError.setText(R.string.ErrorServer);
            }


        }

    }


    private class GetHoldDetailAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHotelDetail = new GetHotelDetail(new GetHotelDetailRequest(new GetHotelDRequest(getString(R.string.culture), String.valueOf(getIntent().getExtras().getInt("HotelId")))));
                Log.e("j2j2j2", new Gson().toJson(new GetHotelDetailRequest(new GetHotelDRequest(getString(R.string.culture), String.valueOf(getIntent().getExtras().getInt("HotelId"))))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            rlLoading2.setVisibility(View.GONE);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.colorPrimaryDark));
            }


            ArrayList<ImageModel> imageModels = new ArrayList<>();

            try {
                cvHotel.setVisibility(View.VISIBLE);


                YoYo.with(Techniques.FadeIn)
                        .duration(400)
                        .playOn(cvHotel);


                tvHotelName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                tvHotelName.setVisibility(View.GONE);
                tvTitle.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);

                tvAdress.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);
                Log.e("test", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);

                try {
                    LatLng location = new LatLng(Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Latitude),
                            Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Longitude));
                    //  map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
                    //   map.addMarker(new MarkerOptions().position(location).title(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName));
                } catch (Exception e) {
                }

                for (ImageHotel imageHotel : getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelImages) {
                    imageModels.add(new ImageModel(imageHotel.HotelImagesURL));
                    Log.e("image", imageHotel.HotelImagesURL);

                }
                EventBus.getDefault().post(new HotelProprtiesBus(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelProprties));




                YoYo.with(Techniques.FadeIn)
                        .duration(400)
                        .playOn(flViewPager);

                Collections.reverse(imageModels);


                new ViewPagerAttention(DetailHotelActivity.this, imageModels, R.id.intro_view_pager);
                tvCityName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CityName + " " + getString(R.string.comma) + " " + getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CountryName);
                Log.e("star", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.StarRating + "");

                switch (getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.StarRating) {

                    case 1:
                        //todo change this
                        ivImage.setImageDrawable(ContextCompat.getDrawable(DetailHotelActivity.this, R.drawable._1star));

                        break;
                    case 2:
                        ivImage.setImageDrawable(ContextCompat.getDrawable(DetailHotelActivity.this, R.drawable._2star));

                        break;
                    case 3:
                        ivImage.setImageDrawable(ContextCompat.getDrawable(DetailHotelActivity.this, R.drawable._3star));

                        break;
                    case 4:
                        ivImage.setImageDrawable(ContextCompat.getDrawable(DetailHotelActivity.this, R.drawable._4star));

                        break;

                    case 5:
                        ivImage.setImageDrawable(ContextCompat.getDrawable(DetailHotelActivity.this, R.drawable._5star));

                        break;
                    case -1:
                        ivImage.setVisibility(View.GONE);
                        break;
                }
                if (roomsModels.isEmpty()) {

                    tvAlert.setVisibility(View.VISIBLE);
                    //   lvRooms.setVisibility(View.GONE);
                }


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, R.string.ErrorServer, Toast.LENGTH_SHORT).show();
                finish();
                //  avi1.setVisibility(View.GONE);
                //   llLoading.setVisibility(View.GONE);
            }

        }

    }



    private class GetCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            aviComment.setVisibility(View.VISIBLE);


            //  window.setStatusBarColor(getColor(R.color.blue2));
            ///   new InitUi().Loading(DetailHotelActivity.this,rlLoading, rlRoot, true,R.drawable.hotel_loading);
            Log.e("test1", String.valueOf(getIntent().getExtras().getInt("HotelId")));
            Log.e("test2", getIntent().getExtras().getString("ResultUniqID"));


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                GetCommentRequest getCommentRequest = new GetCommentRequest();
                Request request = new Request();
                request.setCulture(getString(R.string.culture));
                request.setEHotelId(String.valueOf(getIntent().getExtras().getInt("HotelId")));
                // request.setEHotelId("767");
                getCommentRequest.setRequest(request);
                Log.e("testtt", new Gson().toJson(getCommentRequest).toString());
                getComment = new GetComment(getCommentRequest);

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);
            //    llCommentContent.setVisibility(View.VISIBLE);
            aviComment.setVisibility(View.GONE);
            isComment = false;


            try {
                circleView.setDecimalFormat(new DecimalFormat("0.0"));
                Typeface face = Typeface.createFromAsset(getAssets(), "fonts/iran_sans_normal.ttf");
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


                lvComments.addItemDecoration(new DividerItemDecoration(DetailHotelActivity.this, 1));
                lvComments.setLayoutManager(new LinearLayoutManager(DetailHotelActivity.this));

                commentAdapter = new CommentAdapterRecycle(commentModels);
                lvComments.setAdapter(commentAdapter);
                lvComments.setFocusable(false);

                tvVoteCount.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.ReviewsCount + getString(R.string.UserRate));
                tvCommentCount.setText(getString(R.string.CommentUser) + getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length + getString(R.string.Comment));
                tvRecommendedPercent.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.RecommendedPercent + getString(R.string.RecomandUser));
                circleView.setValueAnimated(Float.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.AverageScore));
                Log.e("fer", Float.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.AverageScore) + "");
                if (getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews == null || getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length == 0) {

                    tvAlertComment.setVisibility(View.VISIBLE);
                  /*  btnOneComment.setVisibility(View.VISIBLE);
                    llCommentContent.setVisibility(View.GONE);*/
                }

                lvComments.setOnFlingListener(new RecyclerView.OnFlingListener() {
                    @Override
                    public boolean onFling(int velocityX, int velocityY) {
                        lvComments.dispatchNestedFling(velocityX, velocityY, false);
                        return false;
                    }
                });

              /*  View view=getLayoutInflater().inflate(R.layout.view_header,null);
                lvComments.addHeaderView(view);*/
            } catch (Exception e) {
               /* avi1.setVisibility(View.GONE);
                llLoading.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                tvAlertError.setText("?? ??? ???? ???????? ?? ???????  ??? ????? ???? ??? ???? ");
            }*/


            }
        }
    }

    protected void initToolbar(Toolbar toolbar) {
        if (toolbar == null)
            return;
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        View customView = getLayoutInflater().inflate(R.layout.toolbar, null);
        customView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }
}
