package com.eligasht.reservation.views.activities.hotel.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.eligasht.R;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.views.adapters.HotelCountRoomAdapter;
import com.eligasht.reservation.views.components.Header;

import java.util.ArrayList;
import java.util.List;


public class GetHotelRoomCountActivity extends Activity implements Header.onSearchTextChangedListener,OnClickListener{
	   public static final int CONNECTION_TIMEOUT = 10000;
	    public static final int READ_TIMEOUT = 15000;
		Handler handler;
		ProgressDialog progressBar;
		private Handler progressBarHandler = new Handler();
		public ListView listCityHotel;
	
		//public ListView listRoomItem;
		HotelCountRoomAdapter mAdapter;
		public List<ModelRowCountRoom> data;
		private EditText searchtxt;
		public Button btn_add_room;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_count_room_hotel);
			
			btn_add_room = (Button) findViewById(R.id.btn_add_room);
			btn_add_room.setOnClickListener(this);
		    //Make call to AsyncTask
			listCityHotel=(ListView)findViewById(R.id.listCityHotel);
			
			 data=new ArrayList<ModelRowCountRoom>();
			   // for(int i=0;i<2;i++){
			    ModelRowCountRoom model=new ModelRowCountRoom();
			    model.setCountB(1);
			    model.setCountK(0);
			    model.setCountN(0);
			   
		         data.add(model);
			   // }
			    
		        mAdapter = new HotelCountRoomAdapter(GetHotelRoomCountActivity.this, data);
		      //mAdapter.setAdapter(mAdapter);
		        mAdapter.setData(data);
		        listCityHotel.setAdapter(mAdapter);
	    }//end oncreate



		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_add_room:
					ModelRowCountRoom md = new ModelRowCountRoom();
				   data.add(md);  
				   mAdapter.notifyDataSetChanged();  
				 
				   break;
			}
		}
		@Override
		public void searchTextChanged(String searchText) {
			/*this.searchText = searchText;
			if(searchText.length()>2)
			new AsyncFetch().execute();*/
			//mAdapter.setData(searchText);
			
		}
	}