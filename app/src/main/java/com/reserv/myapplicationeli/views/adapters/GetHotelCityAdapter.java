package com.reserv.myapplicationeli.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.HotelCity;

import java.util.List;


public class GetHotelCityAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<HotelCity> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;
	Activity activity;

	/*public GetHotelCityAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}*/
	 // create constructor to innitilize context and data sent from MainActivity
    public GetHotelCityAdapter(Activity activity,Context context, List<HotelCity> data){
        this.context=context;
        this.activity=activity;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(context);
        
      
    }
	public GetHotelCityAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(activity);
	}

	public void setData(List<HotelCity> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public void setData(String searchText) {
		this.data = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}


	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
	
	long s =position+1;
		//return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
		return s;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		
		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_airport, null);
			holder = new ViewHolder();

			holder.AirportName = (TextView) convertView.findViewById(R.id.text1);
			holder.CityName = (TextView) convertView.findViewById(R.id.text2);
			holder.llLayout = (LinearLayout) convertView.findViewById(R.id.llLayout);

			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final HotelCity current=data.get(position);
		holder.AirportName.setText(current.getCityNameFa()+ "");
		
		holder.CityName.setText(current.getCityNameEn());
		
		holder.AirportName.setTag(current.getCityNameFa());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
				//		Intent i4 = new Intent(context,HotelActivity.class);
						
						//i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						
					/*	i4.putExtra("Value-Hotel-City-Fa",current.getCityNameFa());//current.getCityName()
						i4.putExtra("Value-Hotel-City-En",current.getCityNameEn());
						i4.putExtra("Value-Hotel-City-Code",current.getCityCode());
					
					
						activity.finish();
						//context.startActivity(i4);*/
						
						

					}
				});
		holder.CityName.setTag(current.getCityNameFa());
		holder.llLayout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
			/*			Intent i4 = new Intent(context,HotelActivity.class);
						
						i4.putExtra("Value-Hotel-City-Fa",current.getCityNameFa());
						i4.putExtra("Value-Hotel-City-En",current.getCityNameEn());
						i4.putExtra("Value-Hotel-City-Code",current.getCityCode());*/


						Prefs.putString("Value-Hotel-City-Fa",current.getCityNameFa());
						Prefs.putString("Value-Hotel-City-En",current.getCityNameEn());
						Prefs.putString("Value-Hotel-City-Code",current.getCityCode());

						activity.finish();

						

					}
				});
		return convertView;
		}

	static class ViewHolder {
		TextView AirportName;
		TextView CityName;
		LinearLayout llLayout;
		
		
	}


}