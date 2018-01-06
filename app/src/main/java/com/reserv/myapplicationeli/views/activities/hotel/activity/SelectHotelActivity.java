package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelAvailModelResponse;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelAvailResult;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
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
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog returnDatePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        InitUi.Toolbar(this, false, R.color.flight_status, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);
        rooms.add(new Rooms(2, 0));


        rlLoading=findViewById(R.id.rlLoading);
        rlRoot=findViewById(R.id.rlRoot);
        new GetHotelAsync().execute();

        Log.e("raft", getIntent().getExtras().getString("CheckIn"));
        Log.e("bargasht", getIntent().getExtras().getString("CheckOut"));
        Log.e("cod", 	Prefs.getString("Value-Hotel-City-Code","") );

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SelectHotelActivity.this,DetailHotelActivity.class);
                i.putExtra("HotelId",selectHotelModelArrayList.get(position).geteHotelId());
                i.putExtra("ResultUniqID",selectHotelModelArrayList.get(position).getResultUniqID());
                startActivity(i);
            }
        });




    }



    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            window.setStatusBarColor(getColor(R.color.blue2));

            new InitUi().Loading(rlLoading,rlRoot,true);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                availApi = new HotelAvailApi(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        getIntent().getExtras().getString("CheckIn"), getIntent().getExtras().getString("CheckOut"), Prefs.getString("Value-Hotel-City-Code",""), "DXB", rooms, "2,0,0,0,0,0")));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(rlLoading,rlRoot,false);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));


            try {
                int i = 0;
                for (Hotels hotels : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels) {

                    selectHotelModelArrayList.add(new SelectHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                            hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location,
                            hotels.Availability.RoomLists.get(i).OldPrice,hotels.StarRating, hotels.Availability.RoomLists.get(i).EHotelId,availApi.hotelAvailModelResponse.HotelAvailResult.ResultUniqID));
                    //   i++;

                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(SelectHotelActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                finish();
            }


        }

    }

}
