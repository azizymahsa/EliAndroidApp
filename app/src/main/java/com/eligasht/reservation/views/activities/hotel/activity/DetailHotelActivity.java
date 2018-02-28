package com.eligasht.reservation.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.eligasht.R;

import com.eligasht.reservation.api.hotel.GetHotelDetail;
import com.eligasht.reservation.api.hotel.comment.AddComment;
import com.eligasht.reservation.api.hotel.comment.GetComment;
import com.eligasht.reservation.api.hotel.room.GetRoomsList;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.lost.CommentAdapterRecycle;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequestAdd;
import com.eligasht.reservation.models.hotel.api.addcomment.call.RequsetAddComment;
import com.eligasht.reservation.models.hotel.api.addcomment.call.ReviewComment;
import com.eligasht.reservation.models.hotel.api.detail.ImageHotel;
import com.eligasht.reservation.models.hotel.api.detail.call.GetHotelDRequest;
import com.eligasht.reservation.models.hotel.api.detail.call.GetHotelDetailRequest;
import com.eligasht.reservation.models.hotel.api.detail.call.HotelProprties;
import com.eligasht.reservation.models.hotel.api.getComment.call.GetCommentRequest;
import com.eligasht.reservation.models.hotel.api.getComment.call.Request;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.eligasht.reservation.models.hotel.api.rooms.call.IdentityRooms;
import com.eligasht.reservation.models.hotel.api.rooms.call.RoomRequest;
import com.eligasht.reservation.models.hotel.api.rooms.response.RoomList;
import com.eligasht.reservation.tools.NonScrollRecyclerView;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.hotel.comment.CommentModel;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.eligasht.reservation.views.adapters.hotel.rooms.ImageModel;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsModel;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.NonScrollGridView;
import com.eligasht.reservation.views.ui.ViewPagerAttention;
import com.eligasht.reservation.views.ui.dialog.hotel.AddCommnetDialog;
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
    private NonScrollListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private ArrayList<String> arrayStringList = new ArrayList<>();
    private ArrayList<CommentModel> commentModels = new ArrayList<>();
    boolean updateGoogle=false;
    private RelativeLayout rlLoading, rlRoot,rlLoading2;
    private AddComment addComment;
    private RoomsAdapter roomsAdapter;
    private GetRoomsList getRoomsList;
    private Window window;
    private LinearLayout llEmakanatClick, llMapClick, llRezervClick, llCommentClick, llCommentContent, llAroundHotel, llInformation, llPolicy;
    private FrameLayout flMap;
    private View vEmakanat, vMap, vRezerv, vComment;
    private GoogleMap map;
    private View mapView;
    private MapView mMapView;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    private String eHotelId;
    private String offerIds;
    private GetHotelDetail getHotelDetail;
    private TextView tvHotelName, tvCityName, tvAdress, tvAlert, tvAlertError;
    private ImageView ivImage;
    private LinearLayout llDynamic, llLoading, llComment, llEmkanat;
    private AVLoadingIndicatorView  aviComment;
    private FancyButton  btnSortComment, btnOk,btnComment,btnOneComment;
    private ImageView ivLoading;
    private AddCommnetDialog addCommnetDialog;
    private String comment, userName, title;
    private CommentAdapterRecycle commentAdapter;
    private TextView tvSortComment,tvDateDetail;
    boolean isNew = false;
    private ScrollView svDetail;
    private RelativeLayout elNotFound;
    private GetComment getComment;
    private boolean isComment = true;
    private CircleProgressView circleView;
    private NonScrollRecyclerView lvComments;
    private CardView cvHotel;
    private FrameLayout flViewPager;

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
        tvDateDetail = findViewById(R.id.tvDateDetail);
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
        btnOneComment = findViewById(R.id.btnOneComment);
        rlLoading2 = findViewById(R.id.rlLoading2);
       // avi1 = findViewById(R.id.avi1);
        llEmakanatClick.setOnClickListener(this);
        llMapClick.setOnClickListener(this);
        llRezervClick.setOnClickListener(this);
        llCommentClick.setOnClickListener(this);
        btnSortComment.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        flMap = findViewById(R.id.flMap);
        btnComment = findViewById(R.id.btnComment);
        btnComment.setOnClickListener(this);

        vEmakanat = findViewById(R.id.vEmakanat);
        vMap = findViewById(R.id.vMap);
        vRezerv = findViewById(R.id.vRezerv);

        tvTitle = findViewById(R.id.tvTitle);
        //  gvEmakanat = findViewById(R.id.gvEmakanat);
        lvRooms = findViewById(R.id.lvRooms);
        roomsAdapter = new RoomsAdapter(roomsModels, this, rlRoot, rlLoading, window);
        lvRooms.setAdapter(roomsAdapter);
        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnComment.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOneComment.setCustomTextFont("fonts/iran_sans_normal.ttf");
        lvRooms.setFocusable(false);
        llComment.setFocusable(false);
        svDetail.setFocusable(false);
        llDynamic.setFocusable(false);
        llCommentContent.setFocusable(false);
        svDetail.setFocusable(false);
        tvSortComment.setText("جدیدترین نظرات");
        rlLoading2.setOnClickListener(this);
        btnOneComment.setOnClickListener(this);

        Utility.setAnimLoading(this);
        tvDateDetail.setText(getIntent().getExtras().getString("DateTime"));
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
                if (updateGoogle){
                    Toast.makeText(this, "سرویس Google Play شما نیاز به بروزرسانی دارد.", Toast.LENGTH_SHORT).show();
                }else{
                    flMap.setVisibility(View.VISIBLE);
                    lvRooms.setVisibility(View.GONE);
                    llDynamic.setVisibility(View.GONE);
                    llComment.setVisibility(View.GONE);
                    tvAlert.setVisibility(View.GONE);

                    YoYo.with(Techniques.FadeIn)
                            .duration(400)
                            .playOn(flMap);

                    vEmakanat.setVisibility(View.INVISIBLE);
                    vMap.setVisibility(View.VISIBLE);
                    vRezerv.setVisibility(View.INVISIBLE);
                    vComment.setVisibility(View.INVISIBLE);}


                break;
            case R.id.llEmakanatClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                llComment.setVisibility(View.GONE);
                tvAlert.setVisibility(View.GONE);
                llDynamic.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(400)
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
                        .duration(400)
                        .playOn(llComment);
                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);
                vComment.setVisibility(View.VISIBLE);
                if (isComment) {
                    new GetCommentAsync().execute();

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
            case R.id.btnComment:
                Intent intent=new Intent(this,CommentActivity.class);
                Log.e("HotelNname", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName );
                intent.putExtra("HotelName",getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent.putExtra("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));
                Log.e("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")) );
                startActivity(intent);
                break;
            case R.id.btnOneComment:
                Intent intent2=new Intent(this,CommentActivity.class);
                Log.e("HotelNname", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName );
                intent2.putExtra("HotelName",getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                intent2.putExtra("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")));
                Log.e("HotelId", String.valueOf(getIntent().getExtras().getInt("HotelId")) );


                startActivity(intent2);
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
        new GetRoomsAsync().execute();


    }


    public boolean serviceOK() {
        try {
            int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
            if (isAvailable == ConnectionResult.SUCCESS) {
                return true;
            } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {

                updateGoogle=true;
            /*    Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
                        this, GPS_ERRORDIALOG_REQUEST);
                dialog.show();*/
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
        map.getUiSettings().setZoomControlsEnabled(true);


    }

    @Override
    public void onReturnValue(String userName, String title) {
        this.userName = userName;
        this.title = title;

    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute()
        {
            rlLoading2.setVisibility(View.VISIBLE);
         //   Utility.disableEnableControls(false,rlRoot);


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
                            .duration(400)
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
                //avi1.setVisibility(View.GONE);
               // llLoading.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                rlLoading2.setVisibility(View.GONE);
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
           // Utility.disableEnableControls(true,rlRoot);


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
                        .duration(400)
                        .playOn(cvHotel);


                tvHotelName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
                tvHotelName.setVisibility(View.GONE);
                tvTitle.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);

                tvAdress.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);
                Log.e("test", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);

                try{
                    LatLng location = new LatLng(Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Latitude),
                            Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Longitude));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
                    map.addMarker(new MarkerOptions().position(location).title(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName));
                }catch (Exception e){}

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
                        .duration(400)
                        .playOn(flViewPager);

                Collections.reverse(imageModels);


                new ViewPagerAttention(DetailHotelActivity.this, imageModels, R.id.intro_view_pager);
                tvCityName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CityName + "، " + getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CountryName);
                Log.e("star", getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.StarRating+"");

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
                    lvRooms.setVisibility(View.GONE);
                }


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ", Toast.LENGTH_SHORT).show();
                finish();
              //  avi1.setVisibility(View.GONE);
             //   llLoading.setVisibility(View.GONE);
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
