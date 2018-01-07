package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.lazyloading.ImageLoader;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;


public class SelectHotelActivity extends BaseActivity {


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private HotelAvailApi availApi;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading,rlRoot;
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        InitUi.Toolbar(this, false, R.color.color_hotel, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        window.setStatusBarColor(getColor(R.color.color_hotel_dark));
        list = findViewById(R.id.lvHoteResult);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);
        rooms.add(new Rooms(2, 0));


        rlLoading=findViewById(R.id.rlLoading);
        rlRoot=findViewById(R.id.rlRoot);
     //   new GetHotelAsync().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SelectHotelActivity.this,DetailHotelActivity.class));
            }
        });





    }


/*
    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            window.setStatusBarColor(getColor(R.color.blue2));

            new InitUi().Loading(SelectHotelActivity.this,rlLoading,rlRoot,true,R.drawable.hotel_loading);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                availApi = new HotelAvailApi(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        "2018-02-02", "2018-02-10", "c24452", "DXB", rooms, "2,0,0,0,0,0")));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelActivity.this,rlLoading,rlRoot,false,R.drawable.hotel_loading);
            window.setStatusBarColor(getColor(R.color.color_hotel_dark));


            try {
                int i = 0;
                for (Hotels hotels : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels) {

                */
/*    selectHotelModelArrayList.add(new SelectHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                            hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location, hotels.Availability.RoomLists.get(i).OldPrice));*//*

                    //   i++;

                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(SelectHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }
*/

}
