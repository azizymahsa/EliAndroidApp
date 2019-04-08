package com.eligasht.reservation.views.activities.hotel.activity;
/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.eventbus.CommentModelBus;
import com.eligasht.reservation.models.eventbus.HotelProprtiesBus;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.eligasht.service.model.newModel.hotel.hotelDetail.response.HotelImage;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.model.hotel.detail.request.HotelDetailReq;
import com.eligasht.service.model.hotel.detail.request.HotelDetailRequest;
import com.eligasht.service.model.hotel.detail.response.HotelDetailResponse;
import com.eligasht.service.model.hotel.room.response.RoomList;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.hotel.hotelDetail.HotelDetailViewPager;
import com.eligasht.reservation.views.adapters.hotel.rooms.ImageModel;
import com.eligasht.reservation.models.RoomsModel;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.ViewPagerAttention;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotel.room.request.GetRoomReq;
import com.eligasht.service.model.hotel.room.request.GetRoomRequest;
import com.eligasht.service.model.hotel.room.response.GetRoomResponse;
import com.eligasht.service.model.identity.Identity;
import com.eligasht.service.model.newModel.hotel.getRoom.request.RequestGetRoomsList;
import com.eligasht.service.model.newModel.hotel.getRoom.response.ResponseGetRoomsList;
import com.eligasht.service.model.newModel.hotel.getRoom.response.Room;
import com.eligasht.service.model.newModel.hotel.hotelDetail.request.RequestHotelDetails;
import com.eligasht.service.model.newModel.hotel.hotelDetail.response.ResponseHotelDetails;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
public class DetailHotelActivity extends BaseActivity implements View.OnClickListener, OnServiceStatus<List<ResponseGetRoomsList>> {
    private TextView tvTitle;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private RelativeLayout rlLoading2;
    private Window window;
    private HotelDetailViewPager hotelDetailViewPager;
    protected ViewPager view_pager;
    protected Toolbar toolbar;
    public static LatLng location;
    public static String hName;
    private TextView tvHotelName, tvCityName, tvAdress, tvAlert, tvAlertDesc;
    private ImageView ivImage;
    protected TextView tvDateDetail;
    private RelativeLayout elNotFound;
    private CardView cvHotel;
    private FrameLayout flViewPager;
    protected TabLayout tab_layout;
    private CommentModelBus commentModelBus;
    private AppBarLayout app_bar;
    private FancyButton btnOk;
    private SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    app_bar.setExpanded(false, true);
                    break;
                case 1:
                    hotelDetailViewPager.getCommentHotelFragment().setDataComment(commentModelBus);
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
        setContentView(R.layout.activity_detail_hotel);
        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.DetailHotel));
        window = getWindow();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    public void initView() {
        flViewPager = findViewById(R.id.flViewPager);
        cvHotel = findViewById(R.id.cvHotel);
        tvAdress = findViewById(R.id.tvAdress);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        tvCityName = findViewById(R.id.tvCityName);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        elNotFound = findViewById(R.id.elNotFound);
        tvAlert = findViewById(R.id.tvAlert);
        tvAlertDesc = findViewById(R.id.tvAlertDesc);
        rlLoading2 = findViewById(R.id.rlLoading2);
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        app_bar = findViewById(R.id.app_bar);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        tvDateDetail.setText(getIntent().getExtras().getString("DateTime"));
        hotelDetailViewPager = new HotelDetailViewPager(this, getSupportFragmentManager(), false);
        view_pager.setAdapter(hotelDetailViewPager);
        tab_layout.setupWithViewPager(view_pager);
        view_pager.setCurrentItem(4);
        try {
            if (getIntent().getExtras().getString("Type").equals("Pakage")) {
                hotelDetailViewPager = new HotelDetailViewPager(this, getSupportFragmentManager(), true);
                view_pager.setAdapter(hotelDetailViewPager);
                tab_layout.setupWithViewPager(view_pager);
                view_pager.setCurrentItem(3);
            }
        } catch (Exception e) {
        }
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        app_bar.setExpanded(false, true);
                        break;
                    case 1:
                        hotelDetailViewPager.getCommentHotelFragment().setDataComment(commentModelBus);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
        rlLoading2.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        Utility.setAnimLoading(this);
        initToolbar(toolbar);
        getRoomRequest();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                finish();
                break;
        }
    }

    public void getRoomRequest() {
        rlLoading2.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.blue2));
        }
        RequestGetRoomsList getRoomRequest = new RequestGetRoomsList();

        getRoomRequest.setCulture(getString(R.string.culture));
        getRoomRequest.setHotelId(String.valueOf(getIntent().getExtras().getInt("HotelId")));
        getRoomRequest.setPreSearchUniqueId(getIntent().getExtras().getString("ResultUniqID"));
        Log.e("RequestGetRoomsList: ", new Gson().toJson(getRoomRequest));
        SingletonService.getInstance().getHotelService().newHotelRoomListAvail(this, getRoomRequest);
    }

    @Override
    public void onReady(List<ResponseGetRoomsList> getRoomResponse) {
        Log.e("ResponseGetRoomsList: ", new Gson().toJson(getRoomResponse));
        try {
           /* if (getRoomResponse.getErrors() != null) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(getRoomResponse.getGetRoomsListResult().getErrors().get(0).getMessage());
            } else {*/
                for (ResponseGetRoomsList roomList : getRoomResponse){//getGetRoomsListResult().getRoomList()) {
           // for (int i = 0; i < getRoomResponse.size(); i++) {
                 roomsModels.add(new RoomsModel(roomList.getBoard(), roomList.getTitle(), roomList.getDescription(), roomList.getPrice().toString(),
                            roomList.getOfferId(), roomList.getHotelId(), getIntent().getExtras().getString("ResultUniqID")+"",roomList.getCurrencyCode()));//getRoomResponse.getGetRoomsListResult().getSearchKey()));
                }
                EventBus.getDefault().post(new RoomsModelBus(roomsModels));
                getHotelDetailRequest();
            //}
        } catch (Exception e) {
            elNotFound.setVisibility(View.VISIBLE);
            rlLoading2.setVisibility(View.GONE);
            tvAlert.setText(R.string.ErrorServer);
        }
    }

    @Override
    public void onError(String message) {
        elNotFound.setVisibility(View.VISIBLE);
        rlLoading2.setVisibility(View.GONE);
        tvAlert.setText(R.string.ErrorServer);
    }

    public void getHotelDetailRequest() {
        RequestHotelDetails hotelDetailRequest = new RequestHotelDetails();
       // HotelDetailReq getRoomRequest = new HotelDetailReq();
        hotelDetailRequest.setCulture(getString(R.string.culture));
        hotelDetailRequest.setHotelID(getIntent().getExtras().getInt("HotelId"));
        Log.e("RequestHotelDetails: ", new Gson().toJson(hotelDetailRequest));
        SingletonService.getInstance().getHotelService().newHotelDetailsAvail(new OnServiceStatus<ResponseHotelDetails>() {
            @Override
            public void onReady(ResponseHotelDetails hotelDetailResponse) {
                rlLoading2.setVisibility(View.GONE);
                Log.e("ResponseHotelDetails: ", new Gson().toJson(hotelDetailResponse));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.colorPrimaryDark));
                }
                ArrayList<ImageModel> imageModels = new ArrayList<>();
                try {
                    cvHotel.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .playOn(cvHotel);
                    tvHotelName.setText(hotelDetailResponse.getHotelName());
                    tvHotelName.setVisibility(View.GONE);
                    tvTitle.setText(hotelDetailResponse.getHotelName());
                    tvAdress.setText(hotelDetailResponse.getAddress());
                    try {
                        location = new LatLng(Double.valueOf(hotelDetailResponse.getLatitude()),
                                Double.valueOf(hotelDetailResponse.getLongitude()));
                    } catch (Exception e) {
                    }
                    for (HotelImage imageHotel : hotelDetailResponse.getHotelImages()) {
                        imageModels.add(new ImageModel(imageHotel.getHotelImagesURL()));
                    }
                    EventBus.getDefault().post(new HotelProprtiesBus(hotelDetailResponse.getHotelProprties()));
                    hName = hotelDetailResponse.getHotelName();
                    commentModelBus = new CommentModelBus(hotelDetailResponse.getHotelName(), String.valueOf(getIntent().getExtras().getInt("HotelId")));
                    EventBus.getDefault().post(new CommentModelBus(hotelDetailResponse.getHotelName(), String.valueOf(getIntent().getExtras().getInt("HotelId"))));
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .playOn(flViewPager);
                    Collections.reverse(imageModels);
                    new ViewPagerAttention(DetailHotelActivity.this, imageModels, R.id.intro_view_pager, hotelDetailResponse.getHotelName());
                    tvCityName.setText(hotelDetailResponse.getCityName() + " " + getString(R.string.comma) + " " + hotelDetailResponse.getCountryName());
                    switch (Integer.valueOf(hotelDetailResponse.getStarRating())) {
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

            @Override
            public void onError(String message) {
                Toast.makeText(DetailHotelActivity.this, R.string.ErrorServer, Toast.LENGTH_SHORT).show();
                finish();
            }
        }, hotelDetailRequest);
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
