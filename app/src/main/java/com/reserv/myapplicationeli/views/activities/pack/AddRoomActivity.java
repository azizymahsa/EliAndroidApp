package com.reserv.myapplicationeli.views.activities.pack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.contracts.InfoRoomsContract;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.presenters.RoomPresenter;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.adapters.pack.RoomAdapter;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;


public class AddRoomActivity extends BaseActivity implements View.OnClickListener, InfoRoomsContract.View {

    public SimpleRecycleView rcl_room;
    public ImageView btn_add;
    public ImageView btn_remove;
    public TextView count_room;
    public RoomAdapter roomAdapter;
    public RoomPresenter roomPresenter;
    private Button btn_confirm;
    ArrayList<ModelRowCountRoom> roomArrayList;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        InitUi.Toolbar(this, false, R.color.app_base_color, "اطلاعات اتاق");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimary));
        }
        initViews();
        roomPresenter = new RoomPresenter(this);
        Log.i("eli", "roomPresenter.getRooms().get(0).getChildModels().get(0).getChildAgeRange().getValue()");


try {
    Bundle bundle = getIntent().getExtras();
    Gson gson = new GsonBuilder().create();
    if (bundle != null) {
        roomArrayList = gson.fromJson(bundle.getString("roomList"), new TypeToken<List<ModelRowCountRoom>>() {
        }.getType());
    }
}catch (Exception e){}
        roomPresenter.setRooms(roomArrayList);

        showRooms();
    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public void initViews() {
        count_room = (TextView) findViewById(R.id.count_room);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_add = (ImageView) findViewById(R.id.btn_add);
        btn_remove = (ImageView) findViewById(R.id.btn_remove);
        rcl_room = (SimpleRecycleView) findViewById(R.id.rcl_room);
        rcl_room.setLayoutManager(new LinearLayoutManager(getAppContext()));
        hideLoading();
        setRoomsCount(1);

        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        rcl_room.showLoading();
    }

    @Override
    public void hideLoading() {
        rcl_room.hideLoading();
    }

    @Override
    public void showRooms() {
        roomAdapter = new RoomAdapter(roomPresenter);
        rcl_room.showList(roomAdapter);
    }

    @Override
    public void setRoomsCount(int count) {
        count_room.setText("( " + count + " )");
    }

    @Override
    public void notifyDataSetChange() {
        if (roomAdapter != null) {
            roomAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void notifyItemInserted(int layoutPosition) {
        if (roomAdapter != null) {
            roomAdapter.notifyItemInserted(layoutPosition);
        }
    }

    @Override
    public void notifyItemRemoved(int layoutPosition) {
        if (roomAdapter != null) {
            roomAdapter.notifyItemRemoved(layoutPosition);
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (roomAdapter != null) {
            roomAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public void onClickRoomItem(ModelRowCountRoom room) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btn_add:
                roomPresenter.addRooms();
                break;
            case R.id.btn_remove:
                roomPresenter.removeRooms();
                break;
            case R.id.btn_confirm:
                if(!isValidCountPassenegr(roomPresenter.getRooms())){
                    Toast.makeText(this, "تعداد نباید بیشتر از ۹ نفر باشد .", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new GsonBuilder().create();
                Intent intent = new Intent();
                intent.putExtra("Rooms",gson.toJson(roomPresenter.getRooms()));
                Prefs.putString("Rooms",gson.toJson(roomPresenter.getRooms()));
//                startActivity(intent);
              setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean isValidCountPassenegr(ArrayList<ModelRowCountRoom> rooms){
        if(ValidationTools.isEmptyOrNull(rooms)){
            return false;
        }
        int sum = 0;
        for(ModelRowCountRoom room : rooms){
            sum = sum + room.getCountB() + room.getCountK();
        }

        if(sum > 9){
            return false;
        }
        return true;
    }
}
