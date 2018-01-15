package com.reserv.myapplicationeli.presenters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.contracts.InfoRoomsContract;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.models.model.pack.ChildModel;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.adapters.pack.ChildAdapter;
import com.reserv.myapplicationeli.views.viewholders.RoomRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class RoomPresenter implements InfoRoomsContract.Presenter{

    private final InfoRoomsContract.View mView;
    private ArrayList<ModelRowCountRoom> rooms;

    public RoomPresenter(InfoRoomsContract.View mView) {
        this.mView = mView;

    }

    public void setRooms(ArrayList<ModelRowCountRoom> roomsList){
        if (roomsList == null){
            rooms = new ArrayList<>();
            ModelRowCountRoom room = new ModelRowCountRoom();
            room.setCountB(1);
            room.setCountK(0);
            rooms.add(room);
        }else {
            rooms = roomsList;
        }

    }





    @Override
    public void addRooms() {
        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        if(getRoomsCount() >= 9){
            return;
        }

        ModelRowCountRoom room = new ModelRowCountRoom();
        room.setCountB(0);
        room.setCountK(0);
        rooms.add(room);
        mView.notifyDataSetChange();
        mView.setRoomsCount(getRoomsCount());
    }

    @Override
    public void removeRooms() {
        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        if(getRoomsCount() == 1){
            return;
        }
        rooms.remove(getRoomsCount() - 1);
        mView.notifyDataSetChange();
        mView.setRoomsCount(getRoomsCount());
    }

    @Override
    public ArrayList<ModelRowCountRoom> getRooms() {
        return rooms;
    }

    @Override
    public int getRoomsCount() {
        return (null != getRooms() ? getRooms().size() : 0);
    }

    @Override
    public RoomRowHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pck_room, null);
        RoomRowHolder mh = new RoomRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void bindViewHolder(final RoomRowHolder holder, int position) {

        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        final ModelRowCountRoom room = getRooms().get(position);
        holder.txt_adult.setText(String.valueOf(room.getCountB()));
        holder.txt_child.setText(String.valueOf(room.getCountK()));
        holder.room_title.setText("اتاق" + " " + getStringPosition(position));

        holder.btn_adt_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(room.getCountB() < 1){
                    return;
                }
                room.setCountB(room.getCountB() - 1);
                holder.txt_adult.setText(String.valueOf(room.getCountB()));
            }
        });
        holder.btn_adt_pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((room.getCountB() + room.getCountK()) >= 9){
                    return;
                }
                room.setCountB(room.getCountB() + 1);
                holder.txt_adult.setText(String.valueOf(room.getCountB()));
            }
        });



        holder.btn_ch_pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((room.getCountB() + room.getCountK()) >= 9) || room.getCountK() >= 5){
                    return;
                }
                room.setCountK(room.getCountK() + 1);
                holder.txt_child.setText(String.valueOf(room.getCountK()));
                room.addChildModel(new ChildModel("کودک" + " " + getStringPosition(room.getChildModels().size())));
                Log.i("elham","size : " + room.getChildModels().size());
                ChildAdapter childAdapter = new ChildAdapter(mView.getAppContext(),room.getChildModels());
                holder.rcl_child.showList(childAdapter);

            }
        });


        holder.btn_ch_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(room.getCountK() <= 0){
                    return;
                }
                room.setCountK(room.getCountK() - 1);
                holder.txt_child.setText(String.valueOf(room.getCountK()));
                if(!ValidationTools.isEmptyOrNull(room.getChildModels())){
                    room.getChildModels().remove(room.getChildModels().size() - 1);
                    ChildAdapter childAdapter = new ChildAdapter(mView.getAppContext(),room.getChildModels());
                    holder.rcl_child.showList(childAdapter);
                }

            }
        });

    }

    private String getStringPosition(int position) {
        switch (position) {
            case 0:
                return "اول";
            case 1:
                return "دوم";
            case 2:
                return "سوم";
            case 3:
                return "چهارم";
            case 4:
                return "پنجم";
            case 5:
                return "ششم";
            case 6:
                return "هفتم";
            case 7:
                return "هشتم";
            case 8:
                return "نهم";

            default:
                return "";
        }
    }

}
