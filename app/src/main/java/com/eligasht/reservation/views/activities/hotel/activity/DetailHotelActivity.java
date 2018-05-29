package com.eligasht.reservation.views.activities.hotel.activity;
/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
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
import com.eligasht.service.model.hotel.detail.response.HotelImage;
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
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
public class DetailHotelActivity extends BaseActivity implements View.OnClickListener, OnServiceStatus<GetRoomResponse> {
    private TextView tvTitle;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private RelativeLayout rlLoading2;
    private Window window;
    private HotelDetailViewPager hotelDetailViewPager;
    protected ViewPager view_pager;
    protected Toolbar toolbar;
    public static LatLng location;
    public static String hName;
    private TextView tvHotelName, tvCityName, tvAdress, tvAlertError;
    private ImageView ivImage;
    protected TextView tvDateDetail;
    private RelativeLayout elNotFound;
    private CardView cvHotel;
    private FrameLayout flViewPager;
    protected TabLayout tab_layout;
    private CommentModelBus commentModelBus;
    private SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
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

    public void initView() {
        flViewPager = findViewById(R.id.flViewPager);
        cvHotel = findViewById(R.id.cvHotel);
        tvAdress = findViewById(R.id.tvAdress);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        tvCityName = findViewById(R.id.tvCityName);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        elNotFound = findViewById(R.id.elNotFound);
        tvAlertError = findViewById(R.id.tvAlertError);
        rlLoading2 = findViewById(R.id.rlLoading2);
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
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
        GetRoomRequest getRoomRequest = new GetRoomRequest();
        GetRoomReq getRoomReq = new GetRoomReq();
        getRoomReq.setCulture(getString(R.string.culture));
        getRoomReq.setEHotelId(String.valueOf(getIntent().getExtras().getInt("HotelId")));
        getRoomReq.setFltGUID("");
        getRoomReq.setFltId("");
        Identity identity = new Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");
        getRoomReq.setIdentity(identity);
        getRoomReq.setResultUniqID(getIntent().getExtras().getString("ResultUniqID"));
        getRoomRequest.setRequest(getRoomReq);
        SingletonService.getInstance().getHotelService().getRoom(this, getRoomRequest);
    }

    @Override
    public void onReady(GetRoomResponse getRoomResponse) {
        try {
            if (getRoomResponse.getGetRoomsListResult().getErrors() != null) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlertError.setText(getRoomResponse.getGetRoomsListResult().getErrors().get(0).getMessage());
            } else {
                for (RoomList roomList : getRoomResponse.getGetRoomsListResult().getRoomList()) {
                    roomsModels.add(new RoomsModel(roomList.getBoard(), roomList.getTitle(), roomList.getDescription(), roomList.getPrice().toString(),
                            roomList.getOfferId(), roomList.getHotelId(), getRoomResponse.getGetRoomsListResult().getSearchKey()));
                }
                EventBus.getDefault().post(new RoomsModelBus(roomsModels));
                getHotelDetailRequest();
            }
        } catch (Exception e) {
            elNotFound.setVisibility(View.VISIBLE);
            rlLoading2.setVisibility(View.GONE);
            tvAlertError.setText(R.string.ErrorServer);
        }
    }

    @Override
    public void onError(String message) {
        elNotFound.setVisibility(View.VISIBLE);
        rlLoading2.setVisibility(View.GONE);
        tvAlertError.setText(R.string.ErrorServer);
    }

    public void getHotelDetailRequest() {
        HotelDetailRequest hotelDetailRequest = new HotelDetailRequest();
        HotelDetailReq getRoomRequest = new HotelDetailReq();
        getRoomRequest.setCulture(getString(R.string.culture));
        getRoomRequest.setEHotelId(String.valueOf(getIntent().getExtras().getInt("HotelId")));
        hotelDetailRequest.setRequest(getRoomRequest);
        SingletonService.getInstance().getHotelService().getHotelDetail(new OnServiceStatus<HotelDetailResponse>() {
            @Override
            public void onReady(HotelDetailResponse hotelDetailResponse) {
                rlLoading2.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.colorPrimaryDark));
                }
                ArrayList<ImageModel> imageModels = new ArrayList<>();
                try {
                    cvHotel.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .playOn(cvHotel);
                    tvHotelName.setText(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName());
                    tvHotelName.setVisibility(View.GONE);
                    tvTitle.setText(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName());
                    tvAdress.setText(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getAddress());
                    try {
                        location = new LatLng(Double.valueOf(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getLatitude()),
                                Double.valueOf(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getLongitude()));
                    } catch (Exception e) {
                    }
                    for (HotelImage imageHotel : hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelImages()) {
                        imageModels.add(new ImageModel(imageHotel.getHotelImagesURL()));
                    }
                    EventBus.getDefault().post(new HotelProprtiesBus(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelProprties()));
                    hName=hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName();
                    commentModelBus = new CommentModelBus(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName(), String.valueOf(getIntent().getExtras().getInt("HotelId")));
                    EventBus.getDefault().post(new CommentModelBus(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName(), String.valueOf(getIntent().getExtras().getInt("HotelId"))));
                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .playOn(flViewPager);
                    Collections.reverse(imageModels);
                    new ViewPagerAttention(DetailHotelActivity.this, imageModels, R.id.intro_view_pager, hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getHotelName());
                    tvCityName.setText(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getCityName() + " " + getString(R.string.comma) + " " + hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getCountryName());
                    switch (Integer.valueOf(hotelDetailResponse.getGetHotelDetailResult().getHotelDetail().getStarRating())) {
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
