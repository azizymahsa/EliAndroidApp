package com.reserv.myapplicationeli.lost;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.app.GetPreFactor;
import com.reserv.myapplicationeli.api.hotel.room.GetRoomsList;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.GetRoomsHotelRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.RoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.rooms.response.RoomList;
import com.reserv.myapplicationeli.models.hotel.getprefactor.call.RequestPre;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.RoomsModel;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.List;

public class FinalResult extends BaseActivity {
    private String factorId;
    TextView tvFactor,tvFactor2;
    GetPreFactor getPreFactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);



        try {
            InitUi.Toolbar(this, false, R.color.toolbar_color, "تایید نهایی فاکتور");
            tvFactor = findViewById(R.id.tvFactor);
            tvFactor2 = findViewById(R.id.tvFactor2);
            if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
                final List<String> segments = getIntent().getData().getPathSegments();
                if (segments.size() > 0) {
                    factorId = segments.get(0);
                    tvFactor.setText(factorId + "");
                    tvFactor2.setText(factorId + "");
                }
            }
        } catch (Exception e) {
        }
    }





    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {


            ///   new InitUi().Loading(DetailHotelActivity.this,rlLoading, rlRoot, true,R.drawable.hotel_loading);
            Log.e("test1", String.valueOf(getIntent().getExtras().getInt("HotelId")));
            Log.e("test2", getIntent().getExtras().getString("ResultUniqID"));


        }

        @Override
        protected String doInBackground(String... params) {
            try {
              //  getPreFactor= new GetPreFactor(new RequestPre("fa-IR",factorId +"",))
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);


            try {




                //  setListViewHeightBasedOnChildren(lvRooms);
            } catch (Exception e) {

            }


        }

    }

}