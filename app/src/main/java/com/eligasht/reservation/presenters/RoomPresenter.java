package com.eligasht.reservation.presenters;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.contracts.InfoRoomsContract;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.models.model.login.Contract;
import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.adapters.pack.ChildAdapter;
import com.eligasht.reservation.views.viewholders.RoomRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class RoomPresenter implements InfoRoomsContract.Presenter {

    private final InfoRoomsContract.View mView;
    private ArrayList<ModelRowCountRoom> rooms;
    Context context;
    RoomRowHolder holder;

    public RoomPresenter(InfoRoomsContract.View mView) {
        this.mView = mView;

    }

    public void setRooms(ArrayList<ModelRowCountRoom> roomsList) {
        if (roomsList == null) {
            rooms = new ArrayList<>();
            ModelRowCountRoom room = new ModelRowCountRoom();
            room.setCountB(1);
            room.setCountK(0);
            room.setAnim(true);

            rooms.add(room);
        } else {
            rooms = roomsList;
        }

    }


    @Override
    public void addRooms() {
        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        if (getRoomsCount() >= 9) {
            return;
        }

        ModelRowCountRoom room = new ModelRowCountRoom();
        room.setCountB(1);
        room.setCountK(0);
        room.setAnim(true);
        rooms.add(room);
        mView.notifyDataSetChange();
        mView.setRoomsCount(getRoomsCount());
    }

    @Override
    public void removeRooms() {
        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        if (getRoomsCount() == 1) {
            return;
        }
        if (getRoomsCount() != 1) {
            Animation animations = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            holder.itemView.startAnimation(animations);
            Handler handle = new Handler();
            handle.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub

                    if (getRoomsCount() != 1) {
                        rooms.remove(getRoomsCount() - 1);
                        mView.notifyDataSetChange();
                        mView.setRoomsCount(getRoomsCount());
                    }
                }
            }, 400);

        }

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
        context = parent.getContext();

        return mh;
    }

    @Override
    public void bindViewHolder(final RoomRowHolder holder, final int position) {
        this.holder = holder;
        if (ValidationTools.isEmptyOrNull(getRooms())) {
            return;
        }

        final ModelRowCountRoom room = getRooms().get(position);
        holder.txt_adult.setText(String.valueOf(room.getCountB()));
        holder.txt_child.setText(String.valueOf(room.getCountK()));
        holder.room_title.setText("اتاق" + " " + getStringPosition(position));

        if (!ValidationTools.isEmptyOrNull(room.getChildModels())) {
            ChildAdapter childAdapter = new ChildAdapter(mView.getAppContext(), room.getChildModels());
            holder.rcl_child.showList(childAdapter);


        }
        holder.btn_adt_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (room.getCountB() <= 1) {
                    return;
                }
                room.setCountB(room.getCountB() - 1);
                holder.txt_adult.setText(String.valueOf(room.getCountB()));
            }
        });
        holder.btn_adt_pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((room.getCountB() + room.getCountK()) >= 9) {
                    return;
                }
                room.setCountB(room.getCountB() + 1);
                holder.txt_adult.setText(String.valueOf(room.getCountB()));
            }
        });


        holder.btn_ch_pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((room.getCountB() + room.getCountK()) >= 9) || room.getCountK() >= 5) {
                    return;
                }
                room.setCountK(room.getCountK() + 1);
                holder.txt_child.setText(String.valueOf(room.getCountK()));
                room.addChildModel(new ChildModel("کودک" + " " + getStringPosition(room.getChildModels().size()), true));
                ChildAdapter childAdapter = new ChildAdapter(mView.getAppContext(), room.getChildModels());
                holder.rcl_child.showList(childAdapter);

            }
        });


        holder.btn_ch_mines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (room.getCountK() <= 0) {
                    return;
                }
                room.setCountK(room.getCountK() - 1);
                holder.txt_child.setText(String.valueOf(room.getCountK()));
                if (!ValidationTools.isEmptyOrNull(room.getChildModels())) {



                    room.getChildModels().remove(room.getChildModels().size() - 1);
                    ChildAdapter childAdapter = new ChildAdapter(mView.getAppContext(), room.getChildModels());
                    holder.rcl_child.showList(childAdapter);


                }

            }
        });

        if (rooms.get(position).isAnim()) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            holder.itemView.startAnimation(animation);
            ModelRowCountRoom room2 = new ModelRowCountRoom();
            room2.setCountB(getRooms().get(position).getCountB());
            room2.setCountK(getRooms().get(position).getCountK());
            room2.setAnim(false);
            rooms.set(position, room2);
        }

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
