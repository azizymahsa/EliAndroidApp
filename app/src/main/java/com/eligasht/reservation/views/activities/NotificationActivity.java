package com.eligasht.reservation.views.activities;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.notification.NotificationAdapter;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.PassengerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;
/**
 * Created by Reza Nejati on 22,May,2018
 */
public class NotificationActivity extends BaseActivity {
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activity);
        EventBus.getDefault().register(this);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "پیام ها");
        recyclerView =findViewById(R.id.recyclerView);
        onData();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(NotificationModel notificationModel) {
        onData();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
    public void onData(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<NotificationModel> notificationModelList=NotificationModel.listAll(NotificationModel.class);
        Collections.reverse(notificationModelList);
        recyclerView.setAdapter(new NotificationAdapter(notificationModelList));

    }
}
