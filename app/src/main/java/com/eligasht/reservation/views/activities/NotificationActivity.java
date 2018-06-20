package com.eligasht.reservation.views.activities;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.eligasht.R;
import com.eligasht.reservation.base.Base;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.models.eventbus.RoomsModelBus;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.adapters.hotel.rooms.RoomsAdapter;
import com.eligasht.reservation.views.adapters.notification.NotificationAdapter;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.SingletonContext;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 22,May,2018
 */
public class NotificationActivity extends Base {
    RecyclerView recyclerView;
    RelativeLayout elNotFound,llHome;
    FancyButton btnOk;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activity);
        Prefs.putInt("notifiCounter",0);
        EventBus.getDefault().register(this);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "پیام ها");
        recyclerView =findViewById(R.id.recyclerView);
        elNotFound =findViewById(R.id.elNotFound);
        btnOk =findViewById(R.id.btnOk);
        llHome =findViewById(R.id.llHome);
        llHome.setVisibility(View.INVISIBLE);
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        onData();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(NotificationModel notificationModel) {
        onData();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        NotificationModel.deleteAll(NotificationModel.class);
       // Collections.reverse(notificationModelList);

    }



    public void onData(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<NotificationModel> notificationModelList=NotificationModel.listAll(NotificationModel.class);
        Collections.reverse(notificationModelList);
        recyclerView.setAdapter(new NotificationAdapter(notificationModelList));
        if (notificationModelList.isEmpty()){
            elNotFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else{
            elNotFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }
}
