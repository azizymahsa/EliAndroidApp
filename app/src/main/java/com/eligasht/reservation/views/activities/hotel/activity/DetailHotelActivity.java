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
import com.eligasht.reservation.models.eventbus.CommentModelBus;
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


public class DetailHotelActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvTitle;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    private RelativeLayout rlLoading, rlLoading2;
    CoordinatorLayout rlRoot;
    private GetRoomsList getRoomsList;
    private Window window;
    private HotelDetailViewPager hotelDetailViewPager;
    private ViewPager view_pager;
    private Toolbar toolbar;
    private LatLng location;
    private GetHotelDetail getHotelDetail;
    private TextView tvHotelName, tvCityName, tvAdress, tvAlertError;
    private ImageView ivImage;
    private TextView tvSortComment, tvDateDetail;
    boolean isNew = false;
    private RelativeLayout elNotFound;
    private CardView cvHotel;
    private FrameLayout flViewPager;
    private TabLayout tab_layout;
    CommentModelBus commentModelBus;
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    hotelDetailViewPager.getCommentHotelFragment().setDataComment(commentModelBus);
                    break;
                case 1:
                    hotelDetailViewPager.getMapHotelFragment().setMarker(location,commentModelBus);
                    break;
            }

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
        tvAdress = findViewById(R.id.tvAdress);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        tvCityName = findViewById(R.id.tvCityName);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        tvSortComment = findViewById(R.id.tvSortComment);
        elNotFound = findViewById(R.id.elNotFound);
        tvAlertError = findViewById(R.id.tvAlertError);
        rlLoading2 = findViewById(R.id.rlLoading2);
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        initToolbar(toolbar);
        tvTitle = findViewById(R.id.tvTitle);
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

            case R.id.btnOk:
                finish();
                break;

        }

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
                    location = new LatLng(Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Latitude),
                            Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Longitude));

                } catch (Exception e) {
                }

                for (ImageHotel imageHotel : getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelImages) {
                    imageModels.add(new ImageModel(imageHotel.HotelImagesURL));
                    Log.e("image", imageHotel.HotelImagesURL);

                }
                EventBus.getDefault().post(new HotelProprtiesBus(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelProprties));
                commentModelBus = new CommentModelBus(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName, String.valueOf(getIntent().getExtras().getInt("HotelId")));

                EventBus.getDefault().post(new CommentModelBus(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName, String.valueOf(getIntent().getExtras().getInt("HotelId"))));


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


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, R.string.ErrorServer, Toast.LENGTH_SHORT).show();
                finish();

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
