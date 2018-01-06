package com.reserv.myapplicationeli.contracts;

import android.content.Context;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.views.viewholders.RoomRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public interface InfoRoomsContract {
    interface View {
        Context getAppContext();
        void initViews();
        void showLoading();
        void hideLoading();
        void showRooms();
        void setRoomsCount(int count);
        void notifyDataSetChange();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRemoved(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void onClickRoomItem(ModelRowCountRoom room);
    }

    interface Presenter {
       void addRooms();
       void removeRooms();
        ArrayList<ModelRowCountRoom> getRooms();
        int getRoomsCount();
        RoomRowHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(RoomRowHolder holder, int position);
    }
}
