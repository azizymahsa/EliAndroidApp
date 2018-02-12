package com.reserv.myapplicationeli.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.onesignal.OneSignal;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.app.UserEntranceRequest;
import com.reserv.myapplicationeli.api.hotel.GetHotelDetail;
import com.reserv.myapplicationeli.api.hotel.comment.AddComment;
import com.reserv.myapplicationeli.api.hotel.comment.GetComment;
import com.reserv.myapplicationeli.api.hotel.getHotelRoom.GetHoldRoom;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.base.GlobalApplication;
import com.reserv.myapplicationeli.lost.CommentAdapterRecycle;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequestAdd;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequsetAddComment;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.ReviewComment;
import com.reserv.myapplicationeli.models.hotel.api.detail.ImageHotel;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.GetHotelDRequest;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.GetHotelDetailRequest;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.HotelProprties;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.GetCommentRequest;
import com.reserv.myapplicationeli.models.hotel.api.getComment.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.tools.NonScrollRecyclerView;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.comment.CommentAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.comment.CommentModel;
import com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.ImageModel;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.NonScrollGridView;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelFlightActivity;
import com.reserv.myapplicationeli.views.ui.ViewPagerAttention;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AddCommnetDialog;
import com.wang.avi.AVLoadingIndicatorView;

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


public class DetailHotelActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback, AddCommnetDialog.OnCommentDialogListenerArray {
    private TextView tvTitle, tvAlertComment, tvCommentCount, tvVoteCount,tvRecommendedPercent;
    com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private ArrayList<String> arrayStringList = new ArrayList<>();
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    // RecyclerView lvComments;

    RelativeLayout rlLoading, rlRoot,rlLoading2;
    AddComment addComment;

    RoomsAdapter roomsAdapter;
    GetRoomsList getRoomsList;
    Window window;
    LinearLayout llEmakanatClick, llMapClick, llRezervClick, llCommentClick, llCommentContent, llAroundHotel, llInformation, llPolicy;
    FrameLayout flMap;
    View vEmakanat, vMap, vRezerv, vComment;
    private GoogleMap map;
    private View mapView;
    MapView mMapView;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    String eHotelId;
    String offerIds;
    GetHotelDetail getHotelDetail;
    TextView tvHotelName, tvCityName, tvAdress, tvAlert, tvAlertError;
    ImageView ivImage;
    LinearLayout llDynamic, llLoading, llComment, llEmkanat;
    AVLoadingIndicatorView avi1, aviComment;
    FancyButton btnSendComment, btnSortComment, btnOk;

    ImageView ivLoading;
    EditText etComment;
    AddCommnetDialog addCommnetDialog;
    String comment, userName, title;
    CommentAdapterRecycle commentAdapter;
    TextView tvSortComment;
    boolean isNew = false;
    ScrollView svDetail;
    RelativeLayout elNotFound;
    GetComment getComment;
    boolean isComment = true;
    CircleProgressView circleView;
    NonScrollRecyclerView lvComments;
    CardView cvHotel;
    FrameLayout flViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "جزئیات هتل");

        window = getWindow();

        initView();
        initMap();
        try {
            if (getIntent().getExtras().getString("Type").equals("Pakage")) {

                llRezervClick.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                vComment.setVisibility(View.GONE);
                tvAlert.setVisibility(View.GONE);

                llDynamic.setVisibility(View.VISIBLE);

                vEmakanat.setVisibility(View.VISIBLE);

            }

        } catch (Exception e) {
        }


        //flights

        new GetRoomsAsync().execute();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void initView() {
        //window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        rlLoading = findViewById(R.id.rlLoading);
        flViewPager = findViewById(R.id.flViewPager);
        cvHotel = findViewById(R.id.cvHotel);
        rlRoot = findViewById(R.id.rlRoot);
        llEmakanatClick = findViewById(R.id.llEmakanatClick);
        ivLoading = findViewById(R.id.ivLoading);
        llMapClick = findViewById(R.id.llMapClick);
        tvAdress = findViewById(R.id.tvAdress);
        llRezervClick = findViewById(R.id.llRezervClick);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        lvComments = findViewById(R.id.lvComments);
        tvCityName = findViewById(R.id.tvCityName);
        llDynamic = findViewById(R.id.llDynamic);
        tvAlertComment = findViewById(R.id.tvAlertComment);
        llCommentContent = findViewById(R.id.llCommentContent);
        aviComment = findViewById(R.id.aviComment);
        llComment = findViewById(R.id.llComment);
        btnSortComment = findViewById(R.id.btnSortComment);
        vComment = findViewById(R.id.vComment);
        llCommentClick = findViewById(R.id.llCommentClick);
        btnSendComment = findViewById(R.id.btnSendComment);
        etComment = findViewById(R.id.etComment);
        tvSortComment = findViewById(R.id.tvSortComment);
        btnOk = findViewById(R.id.btnOk);
        elNotFound = findViewById(R.id.elNotFound);
        tvAlertError = findViewById(R.id.tvAlertError);
        tvAlert = findViewById(R.id.tvAlert);
        svDetail = findViewById(R.id.svDetail);
        llEmkanat = findViewById(R.id.llEmkanat);
        llAroundHotel = findViewById(R.id.llAroundHotel);
        llInformation = findViewById(R.id.llInformation);
        circleView = findViewById(R.id.circleView);
        llPolicy = findViewById(R.id.llPolicy);
        tvCommentCount = findViewById(R.id.tvCommentCount);
        tvVoteCount = findViewById(R.id.tvVoteCount);
        tvRecommendedPercent = findViewById(R.id.tvRecommendedPercent);
        rlLoading2 = findViewById(R.id.rlLoading2);
        avi1 = findViewById(R.id.avi1);
        llEmakanatClick.setOnClickListener(this);
        llMapClick.setOnClickListener(this);
        llRezervClick.setOnClickListener(this);
        llCommentClick.setOnClickListener(this);
        btnSendComment.setOnClickListener(this);
        btnSortComment.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        flMap = findViewById(R.id.flMap);

        vEmakanat = findViewById(R.id.vEmakanat);
        vMap = findViewById(R.id.vMap);
        vRezerv = findViewById(R.id.vRezerv);

        tvTitle = findViewById(R.id.tvTitle);
        //  gvEmakanat = findViewById(R.id.gvEmakanat);
        lvRooms = findViewById(R.id.lvRooms);
        roomsAdapter = new RoomsAdapter(roomsModels, this, rlRoot, rlLoading, window);
        lvRooms.setAdapter(roomsAdapter);
        btnSendComment.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        lvRooms.setFocusable(false);
        llComment.setFocusable(false);
        svDetail.setFocusable(false);
        llDynamic.setFocusable(false);
        llCommentContent.setFocusable(false);
        svDetail.setFocusable(false);
        tvSortComment.setText("جدیدترین نظرات");
       /* svDetail.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                svDetail.fullScroll(View.FOCUS_UP);
            }
        });*/

        Utility.setAnimLoading(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.llRezervClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.VISIBLE);
                llDynamic.setVisibility(View.GONE);
                llComment.setVisibility(View.GONE);
                if (roomsModels.isEmpty()) {

                    tvAlert.setVisibility(View.VISIBLE);
                    lvRooms.setVisibility(View.GONE);
                    aviComment.setVisibility(View.GONE);

                }

                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(lvRooms);

                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vComment.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.VISIBLE);


                break;
            case R.id.llMapClick:
                flMap.setVisibility(View.VISIBLE);
                lvRooms.setVisibility(View.GONE);
                llDynamic.setVisibility(View.GONE);
                llComment.setVisibility(View.GONE);
                tvAlert.setVisibility(View.GONE);

                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(flMap);

                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.VISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);
                vComment.setVisibility(View.INVISIBLE);

                break;
            case R.id.llEmakanatClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                llComment.setVisibility(View.GONE);
                tvAlert.setVisibility(View.GONE);
                llDynamic.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(llDynamic);
                vEmakanat.setVisibility(View.VISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);
                vComment.setVisibility(View.INVISIBLE);

                break;
            case R.id.llCommentClick:
      /*          svDetail.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        svDetail.fullScroll(ScrollView.FOCUS_UP);
                    }
                }, 100);*/
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                tvAlert.setVisibility(View.GONE);
                llComment.setVisibility(View.VISIBLE);
                llDynamic.setVisibility(View.GONE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(llComment);
                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);
                vComment.setVisibility(View.VISIBLE);
                if (isComment) {
                    new GetCommentAsync().execute();

                }


                break;
            case R.id.btnSendComment:

                if (TextUtils.isEmpty(etComment.getText())) {
                    GradientDrawable drawable = (GradientDrawable) etComment.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke width and stroke color
                } else {
                    comment = etComment.getText().toString();
                    addCommnetDialog = new AddCommnetDialog(DetailHotelActivity.this, this);


                }


                break;
            case R.id.btnSortComment:
                if (isNew) {


                    tvSortComment.setText("جدیدترین نظرات");
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


                    tvSortComment.setText("مفیدترین نظرات");


                    Collections.sort(commentModels, new Comparator<CommentModel>() {
                        @Override
                        public int compare(CommentModel p1, CommentModel p2) {
                            return p2.getLike() -p1.getLike(); // Ascending
                        }
                    });
                    commentAdapter.notifyDataSetChanged();

                }
                break;
            case R.id.btnOk:
                finish();
                break;
        }

    }


    public void initMap() {


        if (serviceOK()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            // TODO: 9/18/2016
        }


    }


    public boolean serviceOK() {
        try {
            int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
            if (isAvailable == ConnectionResult.SUCCESS) {
                return true;
            } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
                        this, GPS_ERRORDIALOG_REQUEST);
                dialog.show();
            } else {
                Toast.makeText(this, "امکان دسترسی وجود ندارد", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        map = googleMap;

        map.getUiSettings().setTiltGesturesEnabled(false);
        map.getUiSettings().setScrollGesturesEnabled(false);
        map.getUiSettings().setZoomGesturesEnabled(false);

    }

    @Override
    public void onReturnValue(String userName, String title) {
        this.userName = userName;
        this.title = title;
        new AddCommentAsync().execute();

    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute()
        {
            rlLoading2.setVisibility(View.VISIBLE);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this,R.color.blue2));
            }            ///   new InitUi().Loading(DetailHotelActivity.this,rlLoading, rlRoot, true,R.drawable.hotel_loading);
         /*   Log.e("test1", String.valueOf(getIntent().getExtras().getInt("HotelId")));
            Log.e("test2", new Gson().toJson(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("EligashtMlb",
                    "123qwe!@#QWE", "Mobile"), "",
                    String.valueOf(getIntent().getExtras().getInt("HotelId")),
                    "", "", getIntent().getExtras().getString("ResultUniqID"),
                    "fa-IR"))));*/


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getRoomsList = new GetRoomsList(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("EligashtMlb","123qwe!@#QWE"
                        , "Mobile"), "",
                        String.valueOf(getIntent().getExtras().getInt("HotelId")),
                        "", "", getIntent().getExtras().getString("ResultUniqID"),
                        "fa-IR")));

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

                        roomsModels.add(new RoomsModel(roomList.Board, roomList.Title, roomList.Description, roomList.Price, roomList.OfferId, roomList.EHotelId, getRoomsList.getRoomsListResponse.GetRoomsListResult.SearchKey));
                        //   i++;

                    }
                    roomsAdapter.notifyDataSetChanged();
                    new GetHoldDetailAsync().execute();
                    YoYo.with(Techniques.FadeIn)
                            .duration(600)
                            .playOn(lvRooms);



                }
                try {
                    if (getIntent().getExtras().getString("Pakage").equals("Pakage")) {
                        lvRooms.setVisibility(View.GONE);

                    }
                } catch (Exception e) {
                }


                //  setListViewHeightBasedOnChildren(lvRooms);
            } catch (Exception e) {
                avi1.setVisibility(View.GONE);
                llLoading.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                tvAlertError.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");
            }


        }

    }


    private class GetHoldDetailAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHotelDetail = new GetHotelDetail(new GetHotelDetailRequest(new GetHotelDRequest("fa-IR", String.valueOf(getIntent().getExtras().getInt("HotelId")))));
                Log.e("j2j2j2", new Gson().toJson(new GetHotelDetailRequest(new GetHotelDRequest("fa-IR", String.valueOf(getIntent().getExtras().getInt("HotelId"))))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            rlLoading2.setVisibility(View.GONE);

            //new InitUi().Loading(DetailHotelActivity.this,rlLoading, rlRoot, false,R.drawable.hotel_loading);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(DetailHotelActivity.this,R.color.colorPrimaryDark));
            }

            ImageLoader imageLoader = ImageLoader.getInstance();
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(DetailHotelActivity.this));

            ArrayList<ImageModel> imageModels = new ArrayList<>();
            //  new InitUi().Loading(rlLoading,rlRoot,false);

         /*   new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));*/
            try {
                cvHotel.setVisibility(View.VISIBLE);


                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(cvHotel);


                tvHotelName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                tvTitle.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);

                tvAdress.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);
                Log.e("test", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);
                LatLng location = new LatLng(Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Latitude),
                        Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Longitude));

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17));
                map.addMarker(new MarkerOptions().position(location).title(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName));
                for (ImageHotel imageHotel : getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelImages) {
                    imageModels.add(new ImageModel(imageHotel.HotelImagesURL));
                    Log.e("image", imageHotel.HotelImagesURL);

                }

                for (HotelProprties hotelProprties : getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelProprties) {
                    if (hotelProprties.CategoryID != 4) {


                        arrayStringList.add(hotelProprties.Category);
                        if (hotelProprties.CategoryID != 2){
                            hotelProprtiesModels.add(new HotelProprtiesModels(hotelProprties.PropertyTitle, hotelProprties.Category, hotelProprties.PropertyIconFont, hotelProprties.PropertyDescription, hotelProprties.CategoryID));


                        }else{

                            if (hotelProprties.PropertyDescription.equals("0")||hotelProprties.PropertyDescription.equals(" ")||
                                    hotelProprties.PropertyDescription.equals("")||TextUtils.isEmpty(hotelProprties.PropertyDescription)){

                            }else{
                                hotelProprtiesModels.add(new HotelProprtiesModels(hotelProprties.PropertyTitle, hotelProprties.Category, hotelProprties.PropertyIconFont, hotelProprties.PropertyDescription, hotelProprties.CategoryID));

                            }
                        }




                    }


                    //add_textView(hotelProprties.PropertyTitle);

                }

                Set<String> hs = new HashSet<>();
                hs.addAll(arrayStringList);
                arrayStringList.clear();
                arrayStringList.addAll(hs);
                hs.size();


              /*  String toMoveUp = "امکانات هتل";
                while (arrayStringList.indexOf(toMoveUp) != 0) {
                    int i = arrayStringList.indexOf(toMoveUp);
                    Collections.swap(arrayStringList, i, i - 2);
                }
*/
                HashMap<String, ArrayList<HotelProprtiesModels>> myMap = new HashMap<String, ArrayList<HotelProprtiesModels>>();
                for (int i = 0; i < arrayStringList.size(); i++) {
                    ArrayList<HotelProprtiesModels> test = new ArrayList<>();

                    for (int j = 0; j < hotelProprtiesModels.size(); j++) {

                        if (arrayStringList.get(i).equals(hotelProprtiesModels.get(j).getPropertyCat())) {
                            test.add(new HotelProprtiesModels(hotelProprtiesModels.get(j).getPropertyTitle(), hotelProprtiesModels.get(j).getPropertyCat(),
                                    hotelProprtiesModels.get(j).getImage(), hotelProprtiesModels.get(j).getPropertyDescription(), hotelProprtiesModels.get(j).getCategoryID()));


                        }


                    }
                    myMap.put(arrayStringList.get(i), test);

                }


                for (Map.Entry<String, ArrayList<HotelProprtiesModels>> entry : myMap.entrySet()) {
                    String key = entry.getKey();
                    ArrayList<HotelProprtiesModels> value = entry.getValue();
                    if (key.contains("امکانات")) {
                        add_view(key, value, llEmkanat);

                    }
                    if (key.contains("اطراف")) {

                        add_view(key, value, llAroundHotel);

                    }
                    if (key.contains("قوانین")) {

                        //add_view(key, value, llPolicy);
                        TextView textView = new TextView(DetailHotelActivity.this);
                        textView.setText(key);

                        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/iran_sans_bold.ttf");
                        textView.setTypeface(t);
                        textView.setPadding(10, 10, 10, 10);

                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView.setTextSize(getResources().getInteger(R.integer.text12));
                        textView.setGravity(Gravity.CENTER);
                        textView.setBackgroundColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.title_background));
                        llPolicy.addView(textView);


                        NonScrollListView nonScrollGridView = new NonScrollListView(DetailHotelActivity.this);
                        nonScrollGridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        // nonScrollGridView.setNumColumns(2);

                        nonScrollGridView.setAdapter(new HotelProprtiesAdapter(value, DetailHotelActivity.this, null, true));
                      nonScrollGridView.setFocusable(false);
                        llPolicy.addView(nonScrollGridView);

                    }
                    if (key.contains("اطلاعات")) {

                        add_view(key, value, llInformation);

                    }


                }

                flViewPager.setVisibility(View.VISIBLE);

                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(flViewPager);



                new ViewPagerAttention(DetailHotelActivity.this, imageModels, R.id.intro_view_pager);
                tvCityName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CityName + "، " + getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CountryName);


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
                }
                if (roomsModels.isEmpty()) {

                    tvAlert.setVisibility(View.VISIBLE);
                    lvRooms.setVisibility(View.GONE);
                }


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ", Toast.LENGTH_SHORT).show();
                finish();
                avi1.setVisibility(View.GONE);
                llLoading.setVisibility(View.GONE);
            }

        }

    }

    public void add_view(String key, ArrayList<HotelProprtiesModels> hotelProprtiesModels, LinearLayout linearLayout) {

        TextView textView = new TextView(DetailHotelActivity.this);
        textView.setText(key);

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/iran_sans_bold.ttf");
        textView.setTypeface(t);
        textView.setPadding(10, 10, 10, 10);

        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(getResources().getInteger(R.integer.text12));
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(ContextCompat.getColor(DetailHotelActivity.this, R.color.title_background));
        linearLayout.addView(textView);


        NonScrollGridView nonScrollGridView = new NonScrollGridView(DetailHotelActivity.this);
        nonScrollGridView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        // nonScrollGridView.setNumColumns(2);


        nonScrollGridView.setAdapter(new HotelProprtiesAdapter(hotelProprtiesModels, DetailHotelActivity.this, nonScrollGridView, false));
       nonScrollGridView.setFocusable(false);
        linearLayout.addView(nonScrollGridView);
    }


    private class AddCommentAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            addCommnetDialog.onPost();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                addComment = new AddComment(new RequsetAddComment(new RequestAdd(new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"), "fa-IR", new ReviewComment(0, comment,
                        0, 1, "Developer@eligasht.com", userName, title, 0))));


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            addCommnetDialog.onEx();
            addCommnetDialog.setFinish(true);

            try {


                if (addComment.addCommentsResult.AddHotelReviewCommentsResult.errors != null) {
                    addCommnetDialog.setTitle(addComment.addCommentsResult.AddHotelReviewCommentsResult.errors.get(0).Message);


                } else {

                    addCommnetDialog.setTitle(addComment.addCommentsResult.AddHotelReviewCommentsResult.ResultText);


                }


            } catch (Exception e) {
                tvAlert.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");

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
                request.setCulture("fa-IR");
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
            llCommentContent.setVisibility(View.VISIBLE);
            aviComment.setVisibility(View.GONE);
            isComment = false;


            try {
                circleView.setDecimalFormat(new DecimalFormat("0.0"));
                Typeface face = Typeface.createFromAsset(getAssets(),"fonts/iran_sans_normal.ttf");
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

                tvVoteCount.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.ReviewsCount  + " کاربر امتیاز داده اند");
                tvCommentCount.setText(" نظرات کاربران  ("+getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length + " نظر)");
                tvRecommendedPercent.setText(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.RecommendedPercent+"% این هتل را پیشنهاد داده اند");
                circleView.setValueAnimated(Float.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.AverageScore));
                Log.e("fer", Float.valueOf(getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.AverageScore) + "");
                if (getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews == null || getComment.getHotelReviewResult.GetHotelReviewResult.HotelReview.Reviews.length == 0) {

                    tvAlertComment.setVisibility(View.VISIBLE);
                    llCommentContent.setVisibility(View.GONE);
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
                tvAlertError.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");
            }*/


            }
        }
    }


}
