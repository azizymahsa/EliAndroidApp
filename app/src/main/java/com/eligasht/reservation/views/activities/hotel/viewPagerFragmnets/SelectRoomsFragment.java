/*
package com.reserv.myapplicationeli.views.activities.hotel.viewPagerFragmnets;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import GetRoomsList;
import GetRoomsHotelRequest;
import IdentityRooms;
import RoomRequest;
import RoomList;
import Prefs;
import RoomsAdapter;
import RoomsModel;
import InitUi;

import java.util.ArrayList;

*/
/**
 * Created by Reza.nejati on 1/7/2018.
 *//*


public class SelectRoomsFragment extends Fragment{
    ListView lvRooms;
    RoomsAdapter roomsAdapter;
    RelativeLayout rlLoading,rlRoot;
    Window window;
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();

    GetRoomsList getRoomsList;
    public static SelectRoomsFragment newInstance(int page, String title, ArrayList<RoomsModel> roomsModels) {
        SelectRoomsFragment fragmentFirst = new SelectRoomsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_room, container, false);
        window = getActivity().getWindow();

        // TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        //  tvLabel.setText(page + " -- " + title);


        lvRooms = view.findViewById(R.id.lvRooms);
        rlLoading=getActivity().findViewById(R.id.rlLoading);
        rlRoot=getActivity().findViewById(R.id.rlRoot);
        roomsAdapter=new RoomsAdapter(roomsModels,getActivity(),rlRoot,rlLoading,window);
        lvRooms.setAdapter(roomsAdapter);
        new GetRoomsAsync().execute();

        return view;
    }

    private class GetRoomsAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

            window.setStatusBarColor(getActivity().getColor(R.color.blue2));
            new InitUi().Loading(getActivity(),rlLoading, rlRoot, true, R.drawable.hotel_loading);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                getRoomsList = new GetRoomsList(new GetRoomsHotelRequest(new RoomRequest(new IdentityRooms("123qwe!@#QWE",
                        "EligashtMlb","Mobile", Prefs.getString("userId","-1")),"",String.valueOf(getActivity().getIntent().getExtras().getInt("HotelId")),"","",getActivity().getIntent().getExtras().getString("ResultUniqID"),"fa-IR")));

            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(getActivity(),rlLoading, rlRoot, false, R.drawable.hotel_loading);
            window.setStatusBarColor(getActivity().getColor(R.color.colorPrimaryDark));
            try {
                int i = 0;
                for (RoomList roomList : getRoomsList.getRoomsListResponse.GetRoomsListResult.roomList) {
                    Log.e("testtest", roomList.Description);
                   // roomsModels.add(new RoomsModel(roomList.Board,roomList.Title,roomList.Description,roomList.Price));
                }
                roomsAdapter.notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(getActivity(), "خطا در ارتباط", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }


        }

    }

}
*/
