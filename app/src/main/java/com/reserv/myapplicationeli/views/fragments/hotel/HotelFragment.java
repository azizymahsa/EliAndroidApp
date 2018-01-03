package com.reserv.myapplicationeli.views.fragments.hotel;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.views.activities.hotel.activity.GetHotelCityActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.GetHotelRoomCountActivity;
import com.reserv.myapplicationeli.views.adapters.HotelCountRoomAdapter;
import com.reserv.myapplicationeli.views.activities.hotel.activity.SelectHotelActivity;


public class HotelFragment extends Fragment implements OnClickListener {
	public HotelFragment() {
	}
	public static Button searchHotel,btnPlusB,btnMinesB,btnPlusK,btnMinesK,btnPlusN,btnMinesN,btn_add_room;
	public TextView txtCity,lbl_city_english,txtTitle,txtCountB,txtCountK,txtCountN,lblRoomCount,txtRoomCount;
	public static int countNafar=1;
	public ListView listRoomItem;
	HotelCountRoomAdapter mAdapter;
	public List<ModelRowCountRoom> data;
	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_hotel, container, false);

	//	rootView = inflater.inflate(R.layout.fragment_plane, container, false);
		

		
		listRoomItem = (ListView)rootView.findViewById(R.id.listRoomItem);
		  
		lblRoomCount= (TextView) rootView.findViewById(R.id.lblRoomCount);
		lblRoomCount.setOnClickListener(this);
		txtRoomCount= (TextView) rootView.findViewById(R.id.txtRoomCount);
		txtRoomCount.setOnClickListener(this);
		
		btn_add_room= (Button) rootView.findViewById(R.id.btn_add_room);
		btn_add_room.setOnClickListener(this);
		 
		txtTitle= (TextView) rootView.findViewById(R.id.txtTitle);
		txtCity= (TextView) rootView.findViewById(R.id.txtCity);
		
		lbl_city_english= (TextView) rootView.findViewById(R.id.lbl_city_english);

	    
	    txtCity.setOnClickListener(this);
	    lbl_city_english.setOnClickListener(this);
	   
	    searchHotel= (Button) rootView.findViewById(R.id.searchHotel);
	    searchHotel.setOnClickListener(this);
        
	    
	    Bundle bundle = getActivity().getIntent().getExtras();
  		if(bundle != null){
  	        if(bundle.getString("Value-Hotel-City-Fa")!= null)
  	        {
  	        	txtCity.setText(""+bundle.getString("Value-Hotel-City-Fa")) ;
  	        	lbl_city_english.setText(""+bundle.getString("Value-Hotel-City-En")) ;
  	        }
          }
	    /////////

		//i4.putExtra("Value-Hotel-City-Code",current.getCityCode());
	
	    
	     data=new ArrayList<ModelRowCountRoom>();
	   // for(int i=0;i<2;i++){
	    ModelRowCountRoom model=new ModelRowCountRoom();
	    model.setCountB(1);
	    model.setCountK(0);
	    model.setCountN(0);
	   
         data.add(model);
	   // }
	    
        mAdapter = new HotelCountRoomAdapter(getActivity(), data);
      //mAdapter.setAdapter(mAdapter);
        mAdapter.setData(data);
        listRoomItem.setAdapter(mAdapter);
        return rootView;
	}//end oncreat
	 public boolean isInRange(int a, int b, int c) {
	        return b > a ? c >= a && c <= b : c >= b && c <= a;
	 }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lblRoomCount:
			Intent intent2=new Intent(getActivity(),GetHotelRoomCountActivity.class);
			Bundle bundle2 = getActivity().getIntent().getExtras();
      		if(bundle2 != null ){
      			intent2.putExtra("Value-Maghsad-City",  bundle2.getString("Value-Maghsad-City"));
				intent2.putExtra("Value-Maghsad-Airport", bundle2.getString("Value-Maghsad-Airport"));
				intent2.putExtra("Value-Maghsad-Airport-Code", bundle2.getString("Value-Maghsad-Airport-Code"));//*
      		}
			
            startActivityForResult(intent2, 2);
			break;
		case R.id.txtRoomCount:
			Intent intent4=new Intent(getActivity(),GetHotelRoomCountActivity.class);
			Bundle bundle4 = getActivity().getIntent().getExtras();
      		if(bundle4 != null ){
      			intent4.putExtra("Value-Maghsad-City",  bundle4.getString("Value-Maghsad-City"));
				intent4.putExtra("Value-Maghsad-Airport", bundle4.getString("Value-Maghsad-Airport"));
				intent4.putExtra("Value-Maghsad-Airport-Code", bundle4.getString("Value-Maghsad-Airport-Code"));//*
      		}
			
            startActivityForResult(intent4, 2);
			break;
		case R.id.txtCity:
			Intent intent5=new Intent(getActivity(),GetHotelCityActivity.class);
			startActivityForResult(intent5,1);
			break;
		case R.id.btn_add_room:
			ModelRowCountRoom md = new ModelRowCountRoom();  
		   data.add(md);  
		   mAdapter.notifyDataSetChanged();  
		  // editTextView.setText("");
		   break;
		case R.id.searchHotel:
			
			
			Intent intent6=new Intent(getActivity(),SelectHotelActivity.class);
			Bundle bundle6 = getActivity().getIntent().getExtras();
      		if(bundle6 != null ){
      			intent6.putExtra("Value-Maghsad-City",  bundle6.getString("Value-Maghsad-City"));
				intent6.putExtra("Value-Maghsad-Airport", bundle6.getString("Value-Maghsad-Airport"));
				intent6.putExtra("Value-Maghsad-Airport-Code", bundle6.getString("Value-Maghsad-Airport-Code"));//*
      		}
			
            startActivityForResult(intent6, 2);
		break;
		
	
		}
	}
}
