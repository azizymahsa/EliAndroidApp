package com.eligasht.reservation.views.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eligasht.reservation.R;
import com.eligasht.reservation.base.GlobalApplication;
import com.eligasht.reservation.views.components.SimpleRecycleView;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class RoomRowHolder extends RecyclerView.ViewHolder {


    public TextView room_title;
    public TextView txt_adult;
    public TextView txt_child;
    public Button btn_adt_pluse;
    public Button btn_adt_mines;
    public Button btn_ch_pluse;
    public Button btn_ch_mines;
    public ViewGroup card_room;
    public SimpleRecycleView rcl_child;



    public RoomRowHolder(View view) {
        super(view);

        this.room_title = view.findViewById(R.id.room_title);
        this.txt_adult = view.findViewById(R.id.txtCountAdt);
        this.txt_child = view.findViewById(R.id.txtCountCh);
        this.btn_adt_mines = view.findViewById(R.id.btnMinesAdt);
        this.btn_adt_pluse = view.findViewById(R.id.btnPlusAdt);
        this.btn_ch_mines = view.findViewById(R.id.btnMinesCh);
        this.btn_ch_pluse = view.findViewById(R.id.btnPlusCh);
        this.card_room =view.findViewById(R.id.card_room);
        this.rcl_child =view.findViewById(R.id.rcl_child);
        rcl_child.hideLoading();
        rcl_child.setLayoutManager(new LinearLayoutManager(GlobalApplication.applicationContext));

    }
}
