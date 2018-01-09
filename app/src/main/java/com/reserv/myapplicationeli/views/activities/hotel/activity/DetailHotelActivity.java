package com.reserv.myapplicationeli.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.GetHotelDetail;
import com.reserv.myapplicationeli.api.hotel.getHotelRoom.GetHoldRoom;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.detail.ImageHotel;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.GetHotelDRequest;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.GetHotelDetailRequest;
import com.reserv.myapplicationeli.models.hotel.api.detail.call.HotelProprties;
import com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter.HotelProprtiesModels;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.ImageModel;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.NonScrollGridView;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.ViewPagerAttention;

import java.util.ArrayList;


public class DetailHotelActivity extends BaseActivity implements View.OnClickListener, OnMapReadyCallback {
    private TextView tvTitle;
    com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView lvRooms;
   NonScrollGridView gvEmakanat;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();


    RelativeLayout rlLoading, rlRoot;

    RoomsAdapter roomsAdapter;
    GetRoomsList getRoomsList;
    Window window;
    LinearLayout llEmakanatClick, llMapClick, llRezervClick;
    FrameLayout flMap;
    View vEmakanat, vMap, vRezerv;
    private GoogleMap map;
    private View mapView;
    MapView mMapView;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    GetHoldRoom getHoldRoom;
    String eHotelId;
    String offerIds;
    GetHotelDetail getHotelDetail;
    TextView tvHotelName,tvCityName,tvAdress;
    ImageView ivImage;
    HotelProprtiesAdapter hotelProprtiesAdapter ;
    LinearLayout llDynamic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        InitUi.Toolbar(this, false, R.color.flight_status, "جزئیات هتل");
        window = getWindow();

        initView();
        initMap();


        Log.e("testdddd", getIntent().getExtras().getString("ResultUniqID"));

        new GetRoomsAsync().execute();


    }

    public void initView() {
        //window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        llEmakanatClick = findViewById(R.id.llEmakanatClick);
        llMapClick = findViewById(R.id.llMapClick);
        tvAdress = findViewById(R.id.tvAdress);
        llRezervClick = findViewById(R.id.llRezervClick);
        ivImage = findViewById(R.id.ivImage);
        tvHotelName = findViewById(R.id.tvHotelName);
        tvCityName = findViewById(R.id.tvCityName);
        gvEmakanat = findViewById(R.id.gvEmakanat);
        llDynamic = findViewById(R.id.llDynamic);
        llEmakanatClick.setOnClickListener(this);
        llMapClick.setOnClickListener(this);
        llRezervClick.setOnClickListener(this);

        flMap = findViewById(R.id.flMap);

        vEmakanat = findViewById(R.id.vEmakanat);
        vMap = findViewById(R.id.vMap);
        vRezerv = findViewById(R.id.vRezerv);

        tvTitle = findViewById(R.id.tvTitle);
      //  gvEmakanat = findViewById(R.id.gvEmakanat);
        lvRooms = findViewById(R.id.lvRooms);
        tvTitle.setText("چهارشنبه، 28 اسفند-جمعه 30 اسفند");
        roomsAdapter = new RoomsAdapter(roomsModels, this);
        lvRooms.setAdapter(roomsAdapter);


        lvRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                offerIds = roomsModels.get(position).getOfferId();
                eHotelId = roomsModels.get(position).getHotelId();
                Log.e("testdddd1", getIntent().getExtras().getString("ResultUniqID"));

                Log.e("offerIds", offerIds);
                Log.e("eHotelId", eHotelId);
                Log.e("eHotelId", eHotelId);
                Log.e("eHotelId", roomsModels.get(position).getOfferId());
                Gson gson = new Gson();
                String json = gson.toJson(new HoldSelectedRoomRequest(new
                        com.reserv.myapplicationeli.models.hotel.api
                                .holdSelectedRoom.call.RoomRequest(new Identity("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"), "fa-IR", eHotelId
                        , offerIds, getIntent().getExtras().getString("ResultUniqID"))));
                Log.e("jsonnnnn", json);
                new GetHoldRoomAsync().execute();
            }
        });

        hotelProprtiesAdapter = new HotelProprtiesAdapter(hotelProprtiesModels,this);
        gvEmakanat.setAdapter(hotelProprtiesAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llRezervClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.VISIBLE);
                gvEmakanat.setVisibility(View.GONE);


                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.VISIBLE);


                break;
            case R.id.llMapClick:
                flMap.setVisibility(View.VISIBLE);
                lvRooms.setVisibility(View.GONE);
                gvEmakanat.setVisibility(View.GONE);


                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.VISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);

                break;
            case R.id.llEmakanatClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                gvEmakanat.setVisibility(View.VISIBLE);

                vEmakanat.setVisibility(View.VISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);

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
        //init Map for MyLocation - Default Camera - Default views----------------------------------
        //  map.setMyLocationEnabled(true);

        map.getUiSettings().setScrollGesturesEnabled(false);


        //  View locationButton = ((View) mapView.findViewById(1).getParent()).findViewById(2);
        //  RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        //  rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        //  rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        // rlp.setMargins(0, 0, 30, 210);
      /*  map.setTrafficEnabled(true);
        map.setOnMapClickListener(this);
        map.setOnCameraIdleListener(this);
        map.setOnCameraMoveListener(this);
        */


    }


    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

            window.setStatusBarColor(getColor(R.color.blue2));
            new InitUi().Loading(rlLoading, rlRoot, true);
            Log.e("test1", String.valueOf(getIntent().getExtras().getInt("HotelId")));
            Log.e("test2", getIntent().getExtras().getString("ResultUniqID"));


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getRoomsList = new GetRoomsList(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"), "",
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
                int i = 0;
                for (RoomList roomList : getRoomsList.getRoomsListResponse.GetRoomsListResult.roomList) {
                    Log.e("testtest", roomList.Description);

                    roomsModels.add(new RoomsModel(roomList.Board, roomList.Title, roomList.Description, roomList.Price, roomList.OfferId, roomList.EHotelId));
                    //   i++;

                }
                roomsAdapter.notifyDataSetChanged();
                new GetHoldDetailAsync().execute();

                //  setListViewHeightBasedOnChildren(lvRooms);
            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }

    private class GetHoldRoomAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

            window.setStatusBarColor(getColor(R.color.blue2));
            new InitUi().Loading(rlLoading, rlRoot, true);


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHoldRoom = new GetHoldRoom(new HoldSelectedRoomRequest(new
                        com.reserv.myapplicationeli.models.hotel.api
                                .holdSelectedRoom.call.RoomRequest(new Identity("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"), "fa-IR", eHotelId
                        , offerIds, getIntent().getExtras().getString("ResultUniqID"))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);

            new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
            try {
                int i = 0;
                // Toast.makeText(DetailHotelActivity.this, getHoldRoom.holdSelectRoomResponse.HoldSelectedRoomResult.OfferId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailHotelActivity.this, PassengerActivity.class);
                intent.putExtra("HotelOfferId", getHoldRoom.holdSelectRoomResponse.HoldSelectedRoomResult.OfferId);
                intent.putExtra("FlightGuID", getIntent().getExtras().getString("ResultUniqID"));
                intent.putExtra("CheckIn", getIntent().getExtras().getString("CheckIn"));
                intent.putExtra("CheckOut", getIntent().getExtras().getString("CheckOut"));

                startActivity(intent);
                finish();


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }
    private class GetHoldDetailAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

          /*  window.setStatusBarColor(getColor(R.color.blue2));
            new InitUi().Loading(rlLoading, rlRoot, true);*/


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHotelDetail = new GetHotelDetail(new GetHotelDetailRequest(new GetHotelDRequest("fa-IR",   String.valueOf(getIntent().getExtras().getInt("HotelId")))));
                Log.e("j2j2j2",new Gson().toJson(new GetHotelDetailRequest(new GetHotelDRequest("fa-IR",   String.valueOf(getIntent().getExtras().getInt("HotelId"))))) );

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
            ImageLoader imageLoader = ImageLoader.getInstance();
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(DetailHotelActivity.this));

            ArrayList<ImageModel> imageModels = new ArrayList<>();
            //  new InitUi().Loading(rlLoading,rlRoot,false);

            new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
       try {
             tvHotelName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName);
             tvAdress.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Address);
             LatLng location=new LatLng(Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Latitude),
                     Double.valueOf(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.Longitude));

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            map.addMarker(new MarkerOptions().position(location).title(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelName));
             for (ImageHotel imageHotel  : getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelImages){
                 imageModels.add(new ImageModel(imageHotel.HotelImagesURL));
                 Log.e("image", imageHotel.HotelImagesURL);

             }

             for (HotelProprties hotelProprties :getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.HotelProprties){
                 hotelProprtiesModels.add(new HotelProprtiesModels(hotelProprties.PropertyTitle));
                 //add_textView(hotelProprties.PropertyTitle);

             }
         hotelProprtiesAdapter.notifyDataSetChanged();
             new ViewPagerAttention(DetailHotelActivity.this,imageModels,R.id.intro_view_pager);
             tvCityName.setText(getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CityName+"، "+getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.CountryName);


             switch (getHotelDetail.getHotelDetailResult.GetHotelDetailResult.HotelDetail.StarRating){

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


            } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }

        }

    }






        public void add_textView(String label) {


            LinearLayout linearLayout = new LinearLayout(this);

            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            llDynamic.addView(linearLayout);
            TextView textView = new TextView(this);
            textView.setText(label);
            textView.setTextSize(16);
          /*  Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "irsans.ttf");
            textView.setTypeface(type);
            textView.setTextColor(getResources().getColor(R.color.textColor));*/
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER | Gravity.RIGHT | Gravity.BOTTOM);
            linearLayout.addView(textView);





    }


}
