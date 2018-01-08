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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.views.activities.hotel.activity.GetHotelCityActivity;
import com.reserv.myapplicationeli.views.activities.pack.AddRoomActivity;
import com.reserv.myapplicationeli.views.adapters.HotelCountRoomAdapter;
import com.reserv.myapplicationeli.views.activities.hotel.activity.SelectHotelActivity;
import com.reserv.myapplicationeli.views.ui.dialog.DatePickerDialog;


public class HotelFragment extends Fragment implements OnClickListener {
	public HotelFragment() {
	}
	public static Button searchHotel,btnPlusB,btnMinesB,btnPlusK,btnMinesK,btnPlusN,btnMinesN;
	public TextView txtCity,lbl_city_english,txtTitle,tarikh_be,txtCountK,txtCountN,lblRoomCount,txtRoomCount;
	public static int countNafar=1;
	LinearLayout btn_add_room,llRoom;
	public ListView listRoomItem;
	HotelCountRoomAdapter mAdapter;
	public List<ModelRowCountRoom> data;
	private View rootView;
	RelativeLayout citySearch;
	TextView tvRaft,tvBargasht;
	DatePickerDialog datePickerDialogRaft,datePickerDialogBargasht;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_hotel2, container, false);

	//	rootView = inflater.inflate(R.layout.fragment_plane, container, false);



		//listRoomItem = (ListView)rootView.findViewById(R.id.listRoomItem);
		  
		lblRoomCount= (TextView) rootView.findViewById(R.id.lblRoomCount);
		tarikh_be= (TextView) rootView.findViewById(R.id.tarikh_be);
		lblRoomCount.setOnClickListener(this);
		tarikh_be.setOnClickListener(this);
		txtRoomCount= (TextView) rootView.findViewById(R.id.txtRoomCount);
		tvRaft= (TextView) rootView.findViewById(R.id.tvRaft);
		tvBargasht= (TextView) rootView.findViewById(R.id.tvBargasht);
		txtRoomCount.setOnClickListener(this);
		tvRaft.setOnClickListener(this);
		tvBargasht.setOnClickListener(this);

		btn_add_room= (LinearLayout) rootView.findViewById(R.id.btn_add_room);
		llRoom= (LinearLayout) rootView.findViewById(R.id.llRoom);
		btn_add_room.setOnClickListener(this);
		llRoom.setOnClickListener(this);

		//txtTitle= (TextView) rootView.findViewById(R.id.txtTitle);
		citySearch= (RelativeLayout) rootView.findViewById(R.id.citySearch);
		
		lbl_city_english= (TextView) rootView.findViewById(R.id.lbl_city_english);
		txtCity= (TextView) rootView.findViewById(R.id.txtCity);

	    
	    citySearch.setOnClickListener(this);
	    lbl_city_english.setOnClickListener(this);
	   
	    searchHotel= (Button) rootView.findViewById(R.id.searchHotel);
	    searchHotel.setOnClickListener(this);
        

	   /* Bundle bundle = getActivity().getIntent().getExtras();
  		if(bundle != null){
  	        if(bundle.getString("Value-Hotel-City-Fa")!= null)
  	        {
  	        	citySearch.setText(""+bundle.getString("Value-Hotel-City-Fa")) ;
  	        	lbl_city_english.setText(""+bundle.getString("Value-Hotel-City-En")) ;
  	        }
          }
	    /////////

		//i4.putExtra("Value-Hotel-City-Code",current.getCityCode());
	*/

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
     //   listRoomItem.setAdapter(mAdapter);
        return rootView;
	}//end oncreat

	@Override
	public void onResume() {
		super.onResume();

		if(!Prefs.getString("Value-Hotel-City-Fa","").equals(""))
		{
			txtCity.setText(Prefs.getString("Value-Hotel-City-Fa",""));
			lbl_city_english.setText(Prefs.getString("Value-Hotel-City-En","")) ;

		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		Prefs.putString("Value-Hotel-City-Fa","");
		Prefs.putString("Value-Hotel-City-En","");
		Prefs.putString("Value-Hotel-City-Code","");
	}

	public boolean isInRange(int a, int b, int c) {
	        return b > a ? c >= a && c <= b : c >= b && c <= a;
	 }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.citySearch:
			Intent intent5=new Intent(getActivity(),GetHotelCityActivity.class);
			startActivityForResult(intent5,1);
			break;

		case R.id.searchHotel:
			boolean ok=true;

			if (tvRaft.getText().toString().equals("انتخاب کنید")&&tvBargasht.getText().toString().equals("انتخاب کنید")){
				Toast.makeText(getActivity(), "تاریخ رفت و برگشت را انتخاب کنید", Toast.LENGTH_SHORT).show();
				ok=false;
			}else {
				if (tvRaft.getText().toString().equals("انتخاب کنید")) {
					Toast.makeText(getActivity(), "تاریخ رفت ", Toast.LENGTH_SHORT).show();
					ok = false;
				}
				if (tvBargasht.getText().toString().equals("انتخاب کنید")) {
					Toast.makeText(getActivity(), "تاریخ رفت ", Toast.LENGTH_SHORT).show();
					ok = false;
				}
			}

			if (ok){
				Intent intent=new Intent(getActivity(),SelectHotelActivity.class);

				intent.putExtra("CheckIn",datePickerDialogRaft.getDate() );
				intent.putExtra("CheckOut",datePickerDialogBargasht.getDate());


				startActivity(intent);
			}

		break;
			case R.id.tvRaft:
				datePickerDialogRaft =new DatePickerDialog(getActivity(),tvRaft,"تاریخ رفت");
				break;
			case R.id.tvBargasht:
				datePickerDialogBargasht=new DatePickerDialog(getActivity(),tvBargasht,"تاریخ برگشت");

				break;
	case R.id.llRoom:
		Intent room = new Intent(getActivity(), AddRoomActivity.class);
		startActivity(room);

				break;
				case R.id.tarikh_be:
				//	new FilterHotelDialog(getActivity());


				break;

	
		}
	}
}
