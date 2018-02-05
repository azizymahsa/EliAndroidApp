package com.reserv.myapplicationeli.views.adapters.hotel.rooms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.getHotelPolicy.GetHotelPolicyApi;
import com.reserv.myapplicationeli.api.hotel.getHotelRoom.GetHoldRoom;
import com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.request.PolicyRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelPolicy.request.RequestPolicy;
import com.reserv.myapplicationeli.models.hotel.api.rooms.call.IdentityRooms;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.PassengerHotelActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelFlightActivity;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialogPolicy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomsAdapter extends BaseAdapter {
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    GetHotelPolicyApi getHotelPolicyApi;
    Activity context;

    String EHotelId;
    String OfferId;
    String SearchKey;
    String TranslteToPersian;
    String eHotelId;
    String offerIds;
    GetHoldRoom getHoldRoom;
    String flightId;
    RelativeLayout rlLoading,rlRoot;
    Window window;
    AlertDialogPolicy alertDialogPolicy;



    public RoomsAdapter(ArrayList<RoomsModel> roomsModels, Activity context, RelativeLayout rlRoot, RelativeLayout rlLoading,Window window) {
        this.roomsModels = roomsModels;
        this.context = context;
        this.rlRoot = rlRoot;
        this.rlLoading = rlLoading;
        this.window = window;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return roomsModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_hotel_item_rooms, null);
            holder = new ViewHolder();
            holder.tvBoard = (TextView) convertView.findViewById(R.id.tvBoard);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
            holder.btnPolicy = (FancyButton) convertView.findViewById(R.id.btnPolicy);
            holder.llSelectHotel = (CardView) convertView.findViewById(R.id.llSelectHotel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvBoard.setText(roomsModels.get(position).getBoard());
        holder.tvTitle.setText(roomsModels.get(position).getTitle());
        holder.tvPrice.setText(Utility.priceFormat(roomsModels.get(position).getPrice())+"");
        holder.tvDesc.setText(roomsModels.get(position).getDesc());



        holder.btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EHotelId=roomsModels.get(position).getHotelId();
                OfferId=roomsModels.get(position).getOfferId();
                SearchKey=roomsModels.get(position).getSearchKey();
                 alertDialogPolicy =  new AlertDialogPolicy(context);
                new GetHotelPolicyAsync().execute();
            }
        });


        holder.llSelectHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerIds = roomsModels.get(position).getOfferId();
                eHotelId = roomsModels.get(position).getHotelId();
                Log.e("testdddd1", context.getIntent().getExtras().getString("ResultUniqID"));


                new GetHoldRoomAsync().execute();
            }
        });



        return convertView;
    }


    public class ViewHolder{
        TextView tvBoard,tvTitle,tvPrice,tvDesc;
        FancyButton btnPolicy;
        CardView llSelectHotel;

    }

    private class GetHotelPolicyAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {



        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHotelPolicyApi = new GetHotelPolicyApi(new PolicyRequest(new RequestPolicy(new IdentityRooms("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"),EHotelId,OfferId,SearchKey,"fa-IR",false)));


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {


            try{

                Log.e("testtest",  getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies()[0].getKey()+"");


              alertDialogPolicy.setText("اتاق"+getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                      getHCancellationPolicies()[0].getHCancellationPolicy()[0].getRoomNo()+": کنسل کردن از "+
                      getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                              getHCancellationPolicies()[0].getHCancellationPolicy()[0].getFromDate()+" تا "+
                      getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                              getHCancellationPolicies()[0].getHCancellationPolicy()[0].getToDate()+" شامل "+
                      getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                              getHCancellationPolicies()[0].getHCancellationPolicy()[0].getReturnAmount()+" "+getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                      getHCancellationPolicies()[0].getHCancellationPolicy()[0].getCurrency()+" جریمه می شود.");


            /*    JSONObject jsonObject= new JSONObject(getHotelPolicyApi.getHotelPolicyResponse.GetHotelPolicyResult.toString());

                JSONArray jArray = jsonObject.getJSONArray("HCancellationPolicies");


                for (int i =0 ; i<jArray.length();i++){
                    Log.e("test",  jArray.getJSONObject(i).getString("Key"));



                }




*/

            }catch (Exception e){


            }

        }

    }
    private class GetHoldRoomAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

            window.setStatusBarColor(context.getColor(R.color.status_loading));
            new InitUi().Loading(context,rlLoading, rlRoot, true,R.drawable.hotel_loading);


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHoldRoom = new GetHoldRoom(new HoldSelectedRoomRequest(new
                        com.reserv.myapplicationeli.models.hotel.api
                                .holdSelectedRoom.call.RoomRequest(new Identity("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"), "fa-IR", eHotelId
                        , offerIds, context.getIntent().getExtras().getString("ResultUniqID"))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);

            new InitUi().Loading(context,rlLoading, rlRoot, false,R.drawable.hotel_loading);
            window.setStatusBarColor(context.getColor(R.color.colorPrimaryDark));
            try {


                if (Prefs.getLong("time",0)>=50000){
                    if (context.getIntent().getExtras().getInt("type") == 1) {
                        flightId = context.getIntent().getExtras().getString("FlightID");

                        Intent intent = new Intent(context, PassengerHotelFlightActivity.class);
                        intent.putExtra("HotelOfferId", getHoldRoom.holdSelectRoomResponse.HoldSelectedRoomResult.OfferId);
                        intent.putExtra("FlightGuID", flightId);
                        intent.putExtra("CheckIn", context.getIntent().getExtras().getString("CheckInHF"));
                        intent.putExtra("CheckOut", context.getIntent().getExtras().getString("CheckOutHF"));
                        intent.putExtra("flightId", context.getIntent().getExtras().getString("ResultUniqID"));

                        context.startActivity(intent);
                        context.finish();
                    }
                    //hotel
                    if (context.getIntent().getExtras().getInt("type") == 2) {
                        flightId = "";

                        Intent intent = new Intent(context, PassengerHotelActivity.class);
                        intent.putExtra("HotelOfferId", getHoldRoom.holdSelectRoomResponse.HoldSelectedRoomResult.OfferId);
                        intent.putExtra("FlightGuID", context.getIntent().getExtras().getString("ResultUniqID"));
                        intent.putExtra("CheckIn", context.getIntent().getExtras().getString("CheckIn"));
                        intent.putExtra("CheckOut", context.getIntent().getExtras().getString("CheckOut"));

                        context.startActivity(intent);
                        context.finish();


                    }
                }else{
                    sendDetailFinish();
                }




            } catch (Exception e) {
                Toast.makeText(context, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                String flightId;
                context.finish();
            }


        }

    }


    public void sendDetailFinish() {

        Intent intent = new Intent("sendDetailFinish");

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}