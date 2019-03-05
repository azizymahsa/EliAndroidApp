package com.eligasht.reservation.views.adapters.hotel.rooms;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
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
import com.eligasht.reservation.models.RoomsModel;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.call.HoldSelectedRoomRequest;
import com.eligasht.reservation.models.hotel.api.holdSelectedRoom.call.RoomRequest;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.request.PolicyRequest;
import com.eligasht.reservation.models.hotel.api.hotelPolicy.request.RequestPolicy;
import com.eligasht.reservation.models.hotel.api.rooms.call.IdentityRooms;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.PassengerHotelActivity;
import com.eligasht.reservation.views.ui.PassengerHotelFlightActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPolicy;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hold.request.HoldRoomReq;
import com.eligasht.service.model.hotel.hold.request.HoldRoomRequest;
import com.eligasht.service.model.hotel.hold.response.HoldRoomResponse;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicyRequest;
import com.eligasht.service.model.hotelpolicy.request.HotelPolicySubRequest;
import com.eligasht.service.model.hotelpolicy.response.HotelPolicyResponse;
import com.eligasht.service.model.newModel.hotel.holdSelectRoom.request.RequestHoldSelectRoom;
import com.eligasht.service.model.newModel.hotel.holdSelectRoom.response.ResponseHoldSelectRoom;
import com.eligasht.service.model.newModel.hotel.policy.request.RequestHotelPolicy;
import com.eligasht.service.model.newModel.hotel.policy.response.ResponseHotelPolicy;
import com.eligasht.service.model.newModel.hotel.reserve.request.RequestReserveFlightHotel;
import com.eligasht.service.model.newModel.hotel.reserve.response.ResponseReserveFlightHotel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza.nejati on 1/6/2018.
 */
public class RoomsAdapter extends BaseAdapter implements OnServiceStatus<List<ResponseHotelPolicy>> {
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private LayoutInflater inflater;
    protected ViewHolder holder;
    private Activity activity;
    private String EHotelId;
    private String OfferId;
    private String SearchKey;
    private String eHotelId;
    private String offerIds;
    private String flightId;
    private RelativeLayout rlLoading;
    private ViewGroup rlRoot;
    private Window window;
    private AlertDialogPolicy alertDialogPolicy;

    public RoomsAdapter(ArrayList<RoomsModel> roomsModels, Activity activity, ViewGroup rlRoot, RelativeLayout rlLoading, Window window) {
        this.roomsModels = roomsModels;
        this.activity = activity;
        this.rlRoot = rlRoot;
        this.rlLoading = rlLoading;
        this.window = window;
        inflater = LayoutInflater.from(activity);
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
                alertDialogPolicy = new AlertDialogPolicy(activity,true);
                alertDialogPolicy.setTitle(activity.getString(R.string.HotelPolicy));
                alertDialogPolicy.setRoomName(roomsModels.get(position).getTitle() + " : ");
                hotelPolicyRequest();
            }
        });
        holder.llSelectHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    offerIds = roomsModels.get(position).getOfferId();
                    eHotelId = roomsModels.get(position).getHotelId();
                   // eHotelId = roomsModels.get(position).get();
                    getHoldRoomRequest();
                } catch (Exception e) {
                }
            }
        });
        return convertView;
    }

    @Override
    public void onReady(List<ResponseHotelPolicy> hotelPolicyResponse) {

        Log.e("ResponseHotelPolicy:", new Gson().toJson(hotelPolicyResponse));
        try {
            /*if (hotelPolicyResponse.getGetHotelPolicyResult().getErrors() != null) {
                alertDialogPolicy.setText(hotelPolicyResponse.getGetHotelPolicyResult().getErrors().get(0).getDetailedMessage());
            } else if (hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().size() == 0) {
                alertDialogPolicy.setText(activity.getResources().getString(R.string.NoResult));
            } else {
                Log.d("TAGGGG", "onPostExecute: " +
                        hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getToDate());
                if (Prefs.getString("lang", "fa").equals("fa")) {
                    alertDialogPolicy.setText(activity.getString(R.string.room) +
                            " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getRoomNo()
                            + " : " +
                            activity.getString(R.string.departTo)
                            + " " +
                            CustomDate.longToString(hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getFromDateD())
                            + " " + activity.getString(R.string.departFrom) + " " +
                            CustomDate.longToString(hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getToDateD())
                            + " " + activity.getString(R.string.Contains) + " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getReturnAmount()
                            + " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getCurrency()
                            + " " +
                            activity.getString(R.string.penalty));
                } else {
                    alertDialogPolicy.setText(activity.getString(R.string.room) +
                            " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getRoomNo()
                            + " : " +
                            "Cancellation" + " from"
                            + " " +
                            CustomDate.longToString(hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getFromDateD())
                            + " " + "to" + " " +
                            CustomDate.longToString(hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getToDateD())
                            + " " + "will be penalized" + " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getReturnAmount()
                            + " " +
                            hotelPolicyResponse.getGetHotelPolicyResult().getHCancellationPolicies().get(0).getHCancellationPolicy().get(0).getCurrency() + ".");
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            if (!Utility.isNetworkAvailable(activity)) {
                alertDialogPolicy.setText(activity.getString(R.string.InternetError));
            } else {
                alertDialogPolicy.setText(activity.getString(R.string.ErrorServer));
            }
        }
    }



    @Override
    public void onError(String message) {
        if (!Utility.isNetworkAvailable(activity)) {
            alertDialogPolicy.setText(activity.getString(R.string.InternetError));
        } else {
            alertDialogPolicy.setText(activity.getString(R.string.ErrorServer));
        }
    }

    public class ViewHolder {
        TextView tvBoard, tvTitle, tvDesc;
        TextView tvPrice;
        FancyButton btnPolicy;
        CardView llSelectHotel;
    }

    private void hotelPolicyRequest() {
        /*HotelPolicyRequest hotelPolicyRequest = new HotelPolicyRequest();
        HotelPolicySubRequest hotelPolicySubRequest = new HotelPolicySubRequest();
        hotelPolicySubRequest.setCulture(activity.getString(R.string.culture));
        hotelPolicySubRequest.setEHotelId(EHotelId);
        com.eligasht.service.model.hotelpolicy.request.Identity identity = new com.eligasht.service.model.hotelpolicy.request.Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setUserName("EligashtMlb");
        identity.setTermianlId("Mobile");
        hotelPolicySubRequest.setIdentity(identity);
        hotelPolicySubRequest.setOfferId(OfferId);
        hotelPolicySubRequest.setTranslteToPersian(false);
        hotelPolicySubRequest.setSearchKey(SearchKey);
        hotelPolicyRequest.setRequest(hotelPolicySubRequest);*/
        RequestHotelPolicy holdRoomRequest = new RequestHotelPolicy();
        // HoldRoomReq roomReq = new HoldRoomReq();
        holdRoomRequest.setCultureName(activity.getString(R.string.culture));
        holdRoomRequest.setEHotelId(EHotelId);
        /*"eHotelId": "string",
                "offerId": "string",
                "searchKey": "string",
                "translteToPersian": true,
                "cultureName": "string"*/
        holdRoomRequest.setOfferId(OfferId);
        holdRoomRequest.setSearchKey(SearchKey);
        holdRoomRequest.setTranslteToPersian(false);
        Log.e("RequestHotelPolicy:", new Gson().toJson(holdRoomRequest));

        SingletonService.getInstance().getHotelService().newHotelGetPolicyAvail(this, holdRoomRequest);
    }
//ReserveHotelFlight+Hotel
    private void getHoldRoomRequest() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.status_loading));
        }
        new InitUi().Loading(activity, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        RequestReserveFlightHotel holdRoomRequest = new RequestReserveFlightHotel();
        holdRoomRequest.setHotelId(eHotelId);
        holdRoomRequest.setHotelOfferId(offerIds);
        holdRoomRequest.setPreSearchUniqueId(activity.getIntent().getExtras().getString("ResultUniqID"));
//for Hotel+flight
        holdRoomRequest.setFlightGuid(activity.getIntent().getExtras().getString("FlightGuID"));
        holdRoomRequest.setFlightOfferId(activity.getIntent().getExtras().getString("FlightOfferID"));//FlightOfferID


        Log.e("RequestReserveFlightHotel:", new Gson().toJson(holdRoomRequest));


      SingletonService.getInstance().getHotelService().newHotelFlightReserveAvail(new OnServiceStatus<ResponseReserveFlightHotel>() {
            @Override
            public void onReady(ResponseReserveFlightHotel holdRoomResponse) {
                Log.e("ResponseReserveFlightHotel:", new Gson().toJson(holdRoomResponse));
                new InitUi().Loading(activity, rlLoading, rlRoot, false, R.drawable.hotel_loading);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
                }
                try {


                    if (activity.getIntent().getExtras().getInt("type") == 1) {
                        flightId = activity.getIntent().getExtras().getString("FlightID");
                        Intent intent = new Intent(activity, PassengerHotelFlightActivity.class);
                        intent.putExtra("HotelOfferId", holdRoomResponse.getOfferId());
                        intent.putExtra("FlightGuID", flightId);//changeFlight
                        intent.putExtra("CheckIn", activity.getIntent().getExtras().getString("CheckInHF"));
                        intent.putExtra("CheckOut", activity.getIntent().getExtras().getString("CheckOutHF"));
                        intent.putExtra("flightId", activity.getIntent().getExtras().getString("ResultUniqID"));
                        intent.putExtra("flightOfferId", activity.getIntent().getExtras().getString("FlightOfferID"));
                        intent.putExtra("PreSearchUniqueId", holdRoomResponse.getPreSearchUniqueId());
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    //hotel
                    if (activity.getIntent().getExtras().getInt("type") == 2) {
                        flightId = "";
                        Intent intent = new Intent(activity, PassengerHotelActivity.class);
                        intent.putExtra("HotelOfferId", holdRoomResponse.getOfferId());
                        intent.putExtra("FlightGuID", activity.getIntent().getExtras().getString("ResultUniqID"));//setPreSearchUniqueId
                        intent.putExtra("CheckIn", activity.getIntent().getExtras().getString("CheckIn"));
                        intent.putExtra("CheckOut", activity.getIntent().getExtras().getString("CheckOut"));
                        activity.startActivity(intent);
                        activity.finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(activity, activity.getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
            }

          @Override
          public void onError(String message) {
              Toast.makeText(activity, activity.getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
              activity.finish();
          }


      }, holdRoomRequest);


    }

    public void sendDetailFinish() {
        Intent intent = new Intent("sendDetailFinish");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
    }
}