package com.reserv.myapplicationeli.views.activities.hotel.activity;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */

import android.os.AsyncTask;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.views.adapters.hotel.HotelPagerAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class DetailHotelActivity extends BaseActivity {
    private TextView tvTitle;
    private HotelPagerAdapter hotelPagerAdapter;
    ListView lvRooms;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();

    RoomsAdapter roomsAdapter;
    GetRoomsList getRoomsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        InitUi.Toolbar(this, false, R.color.flight_status,"جزئیات هتل");
        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.colorPrimaryDark));


        tvTitle = findViewById(R.id.tvTitle);
        lvRooms = findViewById(R.id.lvRooms);
        tvTitle.setText("چهارشنبه، 28 اسفند-جمعه 30 اسفند");
        roomsAdapter=new RoomsAdapter(roomsModels,this);
        lvRooms.setAdapter(roomsAdapter);


        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        hotelPagerAdapter = new HotelPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(hotelPagerAdapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(vpPager);
        new GetRoomsAsync().execute();
        Gson gson = new Gson();
        String json = gson.toJson(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("123qwe!@#QWE",
                "EligashtMlb","Mobile"),"",String.valueOf(getIntent().
                getExtras().getInt("HotelId")),"","",getIntent().getExtras().getString("ResultUniqID"))));
        Log.e("jso", json);



    }
    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            Log.e("test1", String.valueOf(getIntent().getExtras().getInt("HotelId")));
            Log.e("test2", getIntent().getExtras().getString("ResultUniqID"));


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getRoomsList = new GetRoomsList(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("123qwe!@#QWE",
                        "EligashtMlb","Mobile"),"",String.valueOf(getIntent().getExtras().getInt("HotelId")),"","",getIntent().getExtras().getString("ResultUniqID"))));

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

                    roomsModels.add(new RoomsModel(roomList.Board,roomList.Title,roomList.Description,roomList.Price));
                    //   i++;

                }
                roomsAdapter.notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(DetailHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }

}
