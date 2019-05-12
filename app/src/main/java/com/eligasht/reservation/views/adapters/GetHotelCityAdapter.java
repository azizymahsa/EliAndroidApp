package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.models.model.HotelCity;
import com.eligasht.reservation.tools.db.local.RecentCityHotel_Table;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.views.activities.hotel.activity.GetHotelCityActivity;
import com.eligasht.reservation.views.ui.SingletonContext;

import java.util.List;


public class GetHotelCityAdapter extends RecyclerView.Adapter<GetHotelCityAdapter.MyViewHolder> {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<Airport> data;

	Activity activity;
	int type;

	public class MyViewHolder extends RecyclerView.ViewHolder {
		public TextView AirportName, txtLongdes, txtTag;
		public TextView txtIconBaseFantastic, txtIconBaseLocation, txtIcon;
		public LinearLayout lnrAll;

		public MyViewHolder(View view) {
			super(view);

			AirportName = (TextView)view.findViewById(R.id.text1);
			txtLongdes = (TextView)view.findViewById(R.id.txtLongdes);
			txtTag = (TextView)view.findViewById(R.id.txtTag);
			txtIconBaseFantastic = (TextView)view.findViewById(R.id.txtIconBaseFantastic);
			txtIconBaseLocation = (TextView)view.findViewById(R.id.txtIconBaseLocation);
			txtIcon = (TextView)view.findViewById(R.id.txtIcon);
			lnrAll = (LinearLayout)view.findViewById(R.id.lnrAll);
		}
	}
	@Override
	public int getItemCount() {
		return data == null ? 0 : data.size();
	}
	@Override
	public GetHotelCityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_airport, parent, false);

		return new GetHotelCityAdapter.MyViewHolder(itemView);
	}
	// create constructor to innitilize context and data sent from MainActivity
	public GetHotelCityAdapter(Activity activity,Context context, List<Airport> data,int type){
		this.context=context;
		this.activity=activity;
		this.type=type;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(context);


	}


	public GetHotelCityAdapter(Context context, List<Airport> data, Activity activity){
		this.context=context;
		this.activity=activity;
		myInflater = LayoutInflater.from(activity);
		this.data = null;
		notifyDataSetChanged();
	}


    public void setData(List<Airport> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public void setData(String searchText) {
		this.data = data;
		notifyDataSetChanged();
	}



	@Override
	public long getItemId(int position) {
		long s =position+1;
		return s;
	}
	@Override
	public void onBindViewHolder(GetHotelCityAdapter.MyViewHolder holder, int position) {
		Airport current = data.get(position);
		holder.AirportName.setText(current.getTesxt());
		// holder.txtShortDes.setVisibility(View.GONE);

		if(current.isPopular()){
			holder.txtTag.setVisibility(View.VISIBLE);
			holder.txtTag.setText( current.isPopular()  ? context.getString(R.string._Popular) : "");
		}
        /*if(current.getShortDes().length()>0){
        holder.txtShortDes.setVisibility(View.VISIBLE);
            holder.txtShortDes.setText(current.getShortDes());
        }*/
		holder.txtLongdes.setTag(current.getLongDes());
		if(current.getLongDes().length()>0 ){
			holder.txtLongdes.setVisibility(View.VISIBLE);
			holder.txtIcon.setVisibility(View.VISIBLE);
			holder.txtLongdes.setText(Html.fromHtml(current.getLongDes().toString()));
		}

		holder.txtIconBaseFantastic.setTag("/");
		holder.txtIconBaseLocation.setTag(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));

		if(current.getIcon().contains("hotel")){
			holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
			holder.txtIconBaseFantastic.setText("=");//icon_hotel
			holder.txtIcon.setVisibility(View.GONE);
           /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holder.lnrAll.getLayoutParams();
            params.setMargins(0, 50, 50, 0);

            holder.lnrAll.setLayoutParams(params);*/
			// tv1.setLayoutParams(params);
			//holder.lnrAll.set
		}else if(current.getIcon().contains("location")){
			holder.txtIconBaseLocation.setVisibility(View.VISIBLE);
			holder.txtIconBaseLocation.setText(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));//icon_location
			if(current.isSelectable()==false){
				holder.AirportName.setTextColor(Color.parseColor("#a9a9a9"));
				holder.txtIconBaseLocation.setTextColor(Color.parseColor("#a9a9a9"));
				holder.txtIconBaseLocation.setLongClickable(false);
				holder.txtIconBaseLocation.setClickable(false);
				holder.AirportName.setLongClickable(false);
				holder.AirportName.setClickable(false);

			}
		}
		//  "IsSelectable": false,



		holder.AirportName.setTag(current.getTesxt());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(current.isSelectable()) {
					RecentCityHotel_Table recentCity_table = new RecentCityHotel_Table(activity);
					// recentCity_table.insertData(current.getTesxt(), current.getTesxt(), current.getShortDes(), 1);//mabda

					int flagSelect=1;
					if(current.isSelectable())
						flagSelect=1;
					else
						flagSelect=0;


					recentCity_table.insertData(current.getTesxt(),current.getTextFa(),current.getShortDes(),current.getLongDes(),current.getTag(),current.getIcon(),current.getIconDown(),flagSelect,current.getCityCode());

					if (type==0){
						Prefs.putString("Value-Hotel-City-Fa",current.getTextFa());
						Prefs.putString("Value-Hotel-City-En",current.getTesxt());
						Prefs.putString("Value-Hotel-City-Code",current.getCityCode());
						Prefs.putString("Value-Hotel-Country-Code",current.getLongDes());
					}

					activity.finish();
				}
			}
		});
		holder.txtLongdes.setTag(Html.fromHtml(current.getLongDes().toString()));
		holder.txtLongdes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RecentCityHotel_Table recentCity_table = new RecentCityHotel_Table(activity);
				// recentCity_table.insertData(current.getTesxt(), current.getTesxt(), current.getShortDes(), 1);//mabda

				int flagSelect=1;
				if(current.isSelectable())
					flagSelect=1;
				else
					flagSelect=0;


				recentCity_table.insertData(current.getTesxt(),current.getTextFa(),current.getShortDes(),current.getLongDes(),current.getTag(),current.getIcon(),current.getIconDown(),flagSelect,current.getCityCode());

				if (type==0){
					Prefs.putString("Value-Hotel-City-Fa",current.getTextFa());
					Prefs.putString("Value-Hotel-City-En",current.getTesxt());
					Prefs.putString("Value-Hotel-City-Code",current.getCityCode());
					Prefs.putString("Value-Hotel-Country-Code",current.getLongDes());
				}

				activity.finish();
			}
		});
	}
	/*public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
		//	Log.e("POSITION", "" + position);
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
		final Airport current=data.get(position);
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
	}*/

	static class ViewHolder {
		TextView AirportName;
		TextView CityName;
		LinearLayout llLayout;

	}


}