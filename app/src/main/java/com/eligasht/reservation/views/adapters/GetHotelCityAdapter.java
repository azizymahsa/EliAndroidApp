package com.eligasht.reservation.views.adapters;

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
import com.eligasht.R;
import com.eligasht.reservation.models.model.HotelCity;
import com.eligasht.reservation.tools.db.local.RecentCityHotel_Table;
import com.eligasht.reservation.views.activities.hotel.activity.GetHotelCityActivity;

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
	int type;

	/*public GetHotelCityAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}*/
	// create constructor to innitilize context and data sent from MainActivity
	public GetHotelCityAdapter(Activity activity,Context context, List<HotelCity> data,int type){
		this.context=context;
		this.activity=activity;
		this.type=type;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(context);


	}
	public GetHotelCityAdapter(Activity activity,Context context, List<HotelCity> data, GetHotelCityActivity getHotelCityActivity1) {
		this.context=context;
		this.activity=activity;
		this.type=type;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(context);
	}

	public GetHotelCityAdapter(Context context, List<HotelCity> data, Activity activity){
		this.context=context;
		this.activity=activity;
		myInflater = LayoutInflater.from(activity);
		this.data = null;
		notifyDataSetChanged();
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

			holder.AirportName = convertView.findViewById(R.id.text1);
			holder.CityName = convertView.findViewById(R.id.text2);
			holder.llLayout = convertView.findViewById(R.id.llLayout);

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

		holder.CityName.setTag(current.getCityNameFa());
		holder.llLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				RecentCityHotel_Table recentCity_table=new RecentCityHotel_Table(activity);
				recentCity_table.insertData(current.getCityNameFa(),current.getCityNameEn(),current.getCityCode());

				if (type==0){
					Prefs.putString("Value-Hotel-City-Fa",current.getCityNameFa());
					Prefs.putString("Value-Hotel-City-En",current.getCityNameEn());
					Prefs.putString("Value-Hotel-City-Code",current.getCityCode());
				}





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