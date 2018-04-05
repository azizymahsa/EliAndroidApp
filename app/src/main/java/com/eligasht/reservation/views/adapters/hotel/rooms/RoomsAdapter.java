package com.eligasht.reservation.views.adapters.hotel.rooms;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
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

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.api.hotel.getHotelPolicy.GetHotelPolicyApi;
import com.eligasht.reservation.api.hotel.getHotelRoom.GetHoldRoom;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.call.RoomRequest;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.request.PolicyRequest;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.request.RequestPolicy;
import com.eligasht.reservation.models.hotel.api.rooms.call.IdentityRooms;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.ticker.TickerView;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.PassengerHotelActivity;
import com.eligasht.reservation.views.ui.PassengerHotelFlightActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPolicy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
    RelativeLayout rlLoading;
    RelativeLayout rlRoot;
    Window window;
    AlertDialogPolicy alertDialogPolicy;


    public RoomsAdapter(ArrayList<RoomsModel> roomsModels, Activity context, RelativeLayout rlRoot, RelativeLayout rlLoading, Window window) {
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
            holder.tvBoard = convertView.findViewById(R.id.tvBoard);
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.btnPolicy = convertView.findViewById(R.id.btnPolicy);
            holder.llSelectHotel = convertView.findViewById(R.id.llSelectHotel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvBoard.setText(roomsModels.get(position).getBoard());
        holder.tvTitle.setText(roomsModels.get(position).getTitle());
        holder.tvPrice.setText("");
        holder.tvPrice.setText(Utility.priceFormat(roomsModels.get(position).getPrice()) + "");
        holder.tvDesc.setText(roomsModels.get(position).getDesc());

        holder.btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EHotelId = roomsModels.get(position).getHotelId();
                OfferId = roomsModels.get(position).getOfferId();
                SearchKey = roomsModels.get(position).getSearchKey();
                alertDialogPolicy = new AlertDialogPolicy(context);
                alertDialogPolicy.setTitle(context.getString(R.string.HotelPolicy));
                alertDialogPolicy.setRoomName(roomsModels.get(position).getTitle() + " : ");
                new GetHotelPolicyAsync().execute();
            }
        });

        holder.llSelectHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    offerIds = roomsModels.get(position).getOfferId();
                    eHotelId = roomsModels.get(position).getHotelId();
                    new GetHoldRoomAsync().execute();
                } catch (Exception e) {
                }

            }
        });


        return convertView;
    }


    public class ViewHolder {
        TextView tvBoard, tvTitle, tvDesc;
        TextView  tvPrice;
        FancyButton btnPolicy;
        CardView llSelectHotel;
        LottieAnimationView lottieInfo;

    }

    private class GetHotelPolicyAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            // alertDialogPolicy.setTitle("قوانین هتل");
            Log.e("okoktesttest", new Gson().toJson(new PolicyRequest(new RequestPolicy(new IdentityRooms("EligashtMlb",
                    "123qwe!@#QWE", "Mobile"), EHotelId, OfferId, SearchKey, context.getString(R.string.culture), false))));


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHotelPolicyApi = new GetHotelPolicyApi(new PolicyRequest(new RequestPolicy(new IdentityRooms("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"), EHotelId, OfferId, SearchKey, context.getString(R.string.culture), false)));


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            try {

                if (getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getErrors() != null) {
                    alertDialogPolicy.setText(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getErrors().get(0).DetailedMessage);

                } else if (getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().length == 0) {
                    alertDialogPolicy.setText(context.getResources().getString(R.string.NoResult));
                } else {

                    Log.d("TAGGGG", "onPostExecute: " +
                            getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                    getHCancellationPolicies()[0].getHCancellationPolicy()[0].getToDate());

                /*    alertDialogPolicy.setText(context.getString(R.string.room) +" "+ getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                            getHCancellationPolicies()[0].getHCancellationPolicy()[0].getRoomNo() + " : " + " از تاریخ " +
                            DateUtil.getShortStringDateFromMilis(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                    getHCancellationPolicies()[0].getHCancellationPolicy()[0].getFromDate(),"dd MMM HH:mm",false) +
                            " " + context.getString(R.string.to) + " " +
                            Utility.dateShowPolicy(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                    getHCancellationPolicies()[0].getHCancellationPolicy()[0].getToDate()) + " " + context.getString(R.string.Contains) + " " +
                            getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                    getHCancellationPolicies()[0].getHCancellationPolicy()[0].getReturnAmount() + " " +
                            getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                    getHCancellationPolicies()[0].getHCancellationPolicy()[0].getCurrency() + " " + context.getString(R.string.penalty));*/

                    if (Prefs.getString("lang", "fa").equals("fa"))

                    {

                        alertDialogPolicy.setText(context.getString(R.string.room) +
                                " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies()[0].getHCancellationPolicy()[0].getRoomNo()
                                + " : " +
                                context.getString(R.string.departTo)
                                + " " +
                                CustomDate.longToString(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getFromDate_d())
                                + " " + context.getString(R.string.departFrom) + " " +
                                CustomDate.longToString(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getToDate_d())
                                + " " + context.getString(R.string.Contains) + " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getReturnAmount()
                                + " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getCurrency()
                                + " " +
                                context.getString(R.string.penalty));


                    } else {
                        alertDialogPolicy.setText(context.getString(R.string.room) +
                                " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies()[0].getHCancellationPolicy()[0].getRoomNo()
                                + " : " +
                                "Cancellation" + " from"
                                + " " +
                                CustomDate.longToString(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getFromDate_d())
                                + " " + "to" + " " +
                                CustomDate.longToString(getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getToDate_d())
                                + " " + "will be penalized" + " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getReturnAmount()
                                + " " +
                                getHotelPolicyApi.getHotelPolicyResponse.getGetHotelPolicyResult().
                                        getHCancellationPolicies()[0].getHCancellationPolicy()[0].getCurrency() + ".");


                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
                if (!Utility.isNetworkAvailable(context)) {

                    alertDialogPolicy.setText(context.getString(R.string.InternetError));

                } else {

                    alertDialogPolicy.setText(context.getString(R.string.ErrorServer));

                }

            }

        }

    }

    private class GetHoldRoomAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {


            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                window.setStatusBarColor(ContextCompat.getColor(context, R.color.status_loading));
            }


            new InitUi().Loading(context, rlLoading, rlRoot, true, R.drawable.hotel_loading);



        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getHoldRoom = new GetHoldRoom(new HoldSelectedRoomRequest(new
                        RoomRequest(new Identity("123qwe!@#QWE",
                        "EligashtMlb", "Mobile"), context.getString(R.string.culture), eHotelId
                        , offerIds, context.getIntent().getExtras().getString("ResultUniqID"))));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //  new InitUi().Loading(rlLoading,rlRoot,false);

            new InitUi().Loading(context, rlLoading, rlRoot, false, R.drawable.hotel_loading);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            try {

            /*    if (Prefs.getLong("time",0)>=50000){*/
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
            /*    }else{
                    sendDetailFinish();
                }*/


            } catch (Exception e) {
                Toast.makeText(context, context.getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
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