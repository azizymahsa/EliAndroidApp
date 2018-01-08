package com.reserv.myapplicationeli.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.getHotelDetail.GetHoldRoom;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.views.adapters.hotel.HotelPagerAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;

import java.util.ArrayList;
import java.util.List;



public class DetailHotelActivity extends BaseActivity implements View.OnClickListener,OnMapReadyCallback {
    private TextView tvTitle;
    com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;

    RoomsAdapter roomsAdapter;
    GetRoomsList getRoomsList;
    Window window;
    LinearLayout llEmakanatClick, llMapClick,llRezervClick;
    LinearLayout llEmkanat;
    FrameLayout flMap;
    View vEmakanat,vMap,vRezerv;
    private GoogleMap map;
    private View mapView;
    MapView mMapView;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    GetHoldRoom getHoldRoom;
    String eHotelId;
    String offerIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        InitUi.Toolbar(this, false, R.color.flight_status, "جزئیات هتل");
        window = getWindow();

        initView();
        initMap();

        Log.e("testdddd",  getIntent().getExtras().getString("ResultUniqID") );

     new GetRoomsAsync().execute();


    }

    public void initView(){
        //window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        llEmakanatClick=findViewById(R.id.llEmakanatClick);
        llMapClick=findViewById(R.id.llMapClick);
        llRezervClick=findViewById(R.id.llRezervClick);
        llEmakanatClick.setOnClickListener(this);
        llMapClick.setOnClickListener(this);
        llRezervClick.setOnClickListener(this);

        flMap=findViewById(R.id.flMap);

        vEmakanat=findViewById(R.id.vEmakanat);
        vMap=findViewById(R.id.vMap);
        vRezerv=findViewById(R.id.vRezerv);

        tvTitle = findViewById(R.id.tvTitle);
        llEmkanat = findViewById(R.id.llEmakanat);
        lvRooms = findViewById(R.id.lvRooms);
        tvTitle.setText("چهارشنبه، 28 اسفند-جمعه 30 اسفند");
        roomsAdapter = new RoomsAdapter(roomsModels, this);
        lvRooms.setAdapter(roomsAdapter);



        lvRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                offerIds=roomsModels.get(position).getOfferId();
                eHotelId=roomsModels.get(position).getHotelId();
                Log.e("testdddd1",  getIntent().getExtras().getString("ResultUniqID") );

                Log.e("offerIds",  offerIds);
                Log.e("eHotelId",  eHotelId);
                Log.e("eHotelId",  eHotelId);
                Log.e("eHotelId",  roomsModels.get(position).getOfferId());
                Gson gson = new Gson();
                String json = gson.toJson(new HoldSelectedRoomRequest (new
                        com.reserv.myapplicationeli.models.hotel.api
                                .holdSelectedRoom.call.RoomRequest(new Identity("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"),"fa-IR", eHotelId
                        ,offerIds, getIntent().getExtras().getString("ResultUniqID"))));
                Log.e("jsonnnnn", json);
              new GetHoldRoomAsync().execute();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llRezervClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.VISIBLE);
                llEmkanat.setVisibility(View.GONE);



                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.VISIBLE);



                break;
            case R.id.llMapClick:
                flMap.setVisibility(View.VISIBLE);
                lvRooms.setVisibility(View.GONE);
                llEmkanat.setVisibility(View.GONE);


                vEmakanat.setVisibility(View.INVISIBLE);
                vMap.setVisibility(View.VISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);

                break;
            case R.id.llEmakanatClick:
                flMap.setVisibility(View.GONE);
                lvRooms.setVisibility(View.GONE);
                llEmkanat.setVisibility(View.VISIBLE);

                vEmakanat.setVisibility(View.VISIBLE);
                vMap.setVisibility(View.INVISIBLE);
                vRezerv.setVisibility(View.INVISIBLE);

                break;
        }

    }











    public void initMap(){


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
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.6961, 51.4231), 11));
        map.addMarker(new MarkerOptions().position(new LatLng(35.6961, 51.4231)).title("Marker Title").snippet("Marker Description"));
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

            new InitUi().Loading(rlLoading, rlRoot, false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
            try {
                int i = 0;
                for (RoomList roomList : getRoomsList.getRoomsListResponse.GetRoomsListResult.roomList) {
                    Log.e("testtest", roomList.Description);

                    roomsModels.add(new RoomsModel(roomList.Board, roomList.Title, roomList.Description, roomList.Price,roomList.OfferId,roomList.EHotelId));
                    //   i++;

                }
                roomsAdapter.notifyDataSetChanged();

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
                        "EligashtMlb", "Mobile"),"fa-IR", eHotelId
                ,offerIds, getIntent().getExtras().getString("ResultUniqID"))));

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
           intent.putExtra("HotelOfferId",getHoldRoom.holdSelectRoomResponse.HoldSelectedRoomResult.OfferId);
         intent.putExtra("FlightGuID",getIntent().getExtras().getString("ResultUniqID"));
           //intent.putExtra("Checkout","111111111");

           startActivity(intent);
           finish();




       } catch (Exception e) {
                Toast.makeText(DetailHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }



}
