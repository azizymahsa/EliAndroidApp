package com.reserv.myapplicationeli.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;

import java.text.NumberFormat;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	Activity activity;

	List<SearchParvazActivity.ParentItemExpandingPlan>  dataExpandingList;

	public ExpandableListAdapter(Context context,List<SearchParvazActivity.ParentItemExpandingPlan> dataList) {
		this._context = context;

		this.dataExpandingList = dataList;




	}






	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.dataExpandingList.get(groupPosition).Items.get(childPosititon);

	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		System.out.println("groupPosition:"+groupPosition+"childPosition:"+childPosition);
		final SearchParvazActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.row_select_parvaz_two_detail, null);
		}


		TextView lblFlightTimeR = (TextView) convertView.findViewById(R.id.lblFlightTimeR);
		TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);
		TextView lblDepurtureAirportR = (TextView) convertView.findViewById(R.id.lblDepurtureAirportR);
		TextView lblArrivalAirportR=(TextView)convertView.findViewById(R.id.lblArrivalAirportR);
		TextView lblFlightNumberR = (TextView) convertView.findViewById(R.id.lblFlightNumberR);

		LinearLayout linearTableNerkh = (LinearLayout) convertView.findViewById(R.id.linearTableNerkh);//
		LinearLayout linearButton = (LinearLayout) convertView.findViewById(R.id.linearButton);
		//nerkh
		TextView txtAdlCostP = (TextView) convertView.findViewById(R.id.txtAdlCostP);
		TextView txtTaxes=(TextView)convertView.findViewById(R.id.txtTaxes);
		TextView txtTotalFareCost = (TextView) convertView.findViewById(R.id.txtTotalFareCost);



		Button btnSelect = (Button) convertView.findViewById(R.id.btnSelect);


		//nerkh
		txtAdlCostP.setText(String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
		txtTaxes.setText(String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
		txtTotalFareCost.setText(String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));

		lblFlightTimeR.setText(item.FlightTimeR+"");
		lblFlightArrivalTimeR.setText(item.FlightArrivalTimeR+"");
		lblDepurtureAirportR.setText(item.DepartureCityNameFa+" , "+item.DepartureAirportNameFaR);
		lblArrivalAirportR.setText(item.ArrivalCityNameFa+" , "+item.ArrivalAirportNameFaR);
		lblFlightNumberR.setText(item.AirlineCode+item.FlightNumberR+" , "+ item.AirlineNameFaR);
		//txtListChildSumPrice.setText("جمع :"+String.valueOf(NumberFormat.getInstance().format(item.fee*item.amount)));
		int size=this.dataExpandingList.get(groupPosition).Items.size();
		int childSize=childPosition+1;
		System.out.println(size +"ggg"+childPosition);
		if(childSize != size){
			btnSelect.setTag(childPosition);
			btnSelect.setVisibility(View.GONE);
			linearTableNerkh.setVisibility(View.GONE);
			linearButton.setVisibility(View.GONE);
		}else{
			btnSelect.setTag(childPosition);
			btnSelect.setVisibility(View.VISIBLE);
			linearTableNerkh.setVisibility(View.VISIBLE);
			linearButton.setVisibility(View.VISIBLE);
		}
		// this.dataExpandingList.get(groupPosition).Items.size();
		btnSelect.setTag(childPosition);
		btnSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i4 = new Intent(_context,PassengerActivity.class);

				//i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i4.putExtra("Flight_GUID",item.flGUID+"");//current.getCityName()
					/*i4.putExtra("Value-Mabda-Airport",current.getAirportName());
					i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());*/
				_context.startActivity(i4);
				System.out.println("item.flGUID:"+item.flGUID);
				//	Toast.makeText(v.getContext(),"fffff"+childPosition+"GUID:"+item.flGUID,Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;

	}

	/*	public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return this.listItem.get(groupPosition).size();
        }*/
	@Override
	public int getChildrenCount(int groupPosition) {
		//return this.dataExpandingList.get(groupPosition).Items.size();
		System.out.println("groupPositionRR"+groupPosition);
		System.out.println("groupPositionRR2"+this.dataExpandingList.get(groupPosition).Items.size());
		//return 10;
		return this.dataExpandingList.get(groupPosition).Items.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.dataExpandingList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.dataExpandingList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}



	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		//ArrayList<SearchParvazActivity.HeaderExpandingPlan> item2 = this.dataExpandingList.get(groupPosition).Header;
		final SearchParvazActivity.HeaderExpandingPlan item2 = this.dataExpandingList.get(groupPosition).Header;


		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_expanding, null);//list Group header//row_select_parvaz_two_header
		}


        TextView btnExpand = (TextView) convertView.findViewById(R.id.btnExpand);

		 final TextView txtPin= (TextView) convertView.findViewById(R.id.txtPin);

		TextView txtArrivelFalseLast = (TextView) convertView.findViewById(R.id.txtArrivelFalseLast);
		TextView txtDepurtureFalseOne = (TextView) convertView.findViewById(R.id.txtDepurtureFalseOne);

		TextView txtArrivelTrueLast = (TextView) convertView.findViewById(R.id.txtArrivelTrueLast);
		TextView txtDepurtureTrueOne = (TextView) convertView.findViewById(R.id.txtDepurtureTrueOne);


		TextView num_flight_r = (TextView) convertView.findViewById(R.id.num_flight_r);
		TextView num_flight_b = (TextView) convertView.findViewById(R.id.num_flight_b);

		TextView lblArrivalCityNameFaR = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaR);
		TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);



		TextView lblArrivalCityNameFaB = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaB);
		TextView lblFlightArrivalTimeB = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeB);

		TextView lblAdlCost = (TextView) convertView.findViewById(R.id.lblAdlCost);
		//TextView lblAirline = (TextView) convertView.findViewById(R.id.lblAirline);

		ImageView lblProductrow= (ImageView) convertView.findViewById(R.id.lblProductrow);

		TextView txt_economi = (TextView) convertView.findViewById(R.id.txt_economi);
		//TextView textCharter = (TextView) convertView.findViewById(R.id.textCharter);
		TextView txttedad = (TextView) convertView.findViewById(R.id.txttedad);

		LinearLayout linearBargashtOne = (LinearLayout) convertView.findViewById(R.id.linearBargashtOne);
		RelativeLayout linearBargashtTwo = (RelativeLayout) convertView.findViewById(R.id.linearBargashtTwo);
		LinearLayout linearBargashtTree = (LinearLayout) convertView.findViewById(R.id.linearBargashtTree);
		//LinearLayout linear_all = (LinearLayout) convertView.findViewById(R.id.linear_all);
		//final int[] flag = {0};
		txtPin.setTag(item2.IsPin);
		txtPin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){

				Toast.makeText(v.getContext(), groupPosition+"اتخاب شده!!", Toast.LENGTH_SHORT).show();
			//	flag[0] =1;
				if(item2.IsPin){
					item2.setPin(false);
					txtPin.setTextColor(Color.parseColor("#aaaaaa"));
				}else{
					item2.setPin(true);
					txtPin.setTextColor(Color.parseColor("#9966ff"));
				}
				notifyDataSetChanged();

			}
		});



        if (isExpanded){
            btnExpand.setText(_context.getString(R.string.icon_exp_up));


        }else{
            btnExpand.setText(_context.getString(R.string.icon_exp_down));
        }

		///
		System.out.println("item2.SegmentFalseCount:"+item2.SegmentFalseCount);
		if(item2.SegmentFalseCount <1 || item2.SegmentFalseCount ==0){//yek tarafe
			linearBargashtOne.setVisibility(View.GONE);
			linearBargashtTwo.setVisibility(View.GONE);

			linearBargashtTree.setVisibility(View.GONE);

		}else{//2tarafe
			//////Ruze hafte
			//bargasht
			 txtArrivelFalseLast.setText(item2.DepartureCityNameFaB);
			 txtDepurtureFalseOne .setText(item2.ArrivalCityNameFaB);

			num_flight_b.setText(item2.AirlineCode+item2.FlightNumberB);
			///////////////
			//lblArrivalCityNameFaB.setText(" برگشت به "+item2.DepartureCityNameFaB+"");
			System.out.println("bargasgt:"+item2.FltDateDayOfWeekFalse);
			lblArrivalCityNameFaB.setText(""+GetDayWeek(item2.FltDateDayOfWeekFalse)+" , "+item2.FlightTimeB);
			lblFlightArrivalTimeB.setText(item2.SegmentFalseCount+" توقف ");//count bargasht
		}
		//raft
		txtArrivelTrueLast.setText(item2.DepartureCityNameFaR);
		txtDepurtureTrueOne.setText(item2.ArrivalCityNameFaR);


		num_flight_r.setText(item2.AirlineCode+item2.FlightNumberR);
		//
	//	lblArrivalCityNameFaR.setText(" رفت به "+item2.DepartureCityNameFaR+"");
		System.out.println("raft:"+item2.FltDateDayOfWeek);
		lblArrivalCityNameFaR.setText(""+GetDayWeek(item2.FltDateDayOfWeek)+" , "+item2.FlightArrivalTimeR);
		lblFlightArrivalTimeR.setText(item2.SegmentTrueCount+" توقف ");//count raft

		lblAdlCost.setText(item2.AdlCost+"");
		lblAdlCost.setText(String.valueOf(NumberFormat.getInstance().format(item2.AdlCost)));

		//lblAirline.setText(item2.AirlineNameFa);

		txt_economi.setText(item2.CabinClassNameFa);

		if(item2.RemainSeats ==0){
			txttedad.setText("نفر"+item2.RemainSeats+"فقط");
			txttedad.setVisibility(View.INVISIBLE);

		}else{
			txttedad.setText("فقط"+item2.RemainSeats+"نفر");
			txttedad.setVisibility(View.VISIBLE);
		}
	/*	if(item2.get(0).IsCharter)
		    textCharter.setText("چارتر"+"");
		else{
			textCharter.setText("سیستمی"+"");
			textCharter.setBackgroundResource(R.drawable.background_strock_green);
			textCharter.setTextColor(Color.parseColor("#23b574"));
			  		}*/

		//((Button)findViewById(R.id.btntwo)).setBackgroundResource(R.drawable.purple_button_larg);
		//lblProductrow.setBackgroundResource(R.drawable.ir);
		String s=item2.AirlineCode;
		if(s.toLowerCase().equals("ir"))
			lblProductrow.setBackgroundResource(R.drawable.ir);
		if(s.toLowerCase().equals("a3"))
			lblProductrow.setBackgroundResource(R.drawable.a);
		if(s.toLowerCase().equals("af"))
			lblProductrow.setBackgroundResource(R.drawable.af);
		if(s.toLowerCase().equals("az"))
			lblProductrow.setBackgroundResource(R.drawable.az);
		if(s.toLowerCase().equals("ek"))
			lblProductrow.setBackgroundResource(R.drawable.ek);
		if(s.toLowerCase().equals("ey"))
			lblProductrow.setBackgroundResource(R.drawable.ey);
		if(s.toLowerCase().equals("fz"))
			lblProductrow.setBackgroundResource(R.drawable.fz);
		if(s.toLowerCase().equals("g9"))
			lblProductrow.setBackgroundResource(R.drawable.g);
		if(s.toLowerCase().equals("j2"))
			lblProductrow.setBackgroundResource(R.drawable.j);
		if(s.toLowerCase().equals("ji"))
			lblProductrow.setBackgroundResource(R.drawable.ji);
		if(s.toLowerCase().equals("kk"))
			lblProductrow.setBackgroundResource(R.drawable.kk);
		if(s.toLowerCase().equals("kl"))
			lblProductrow.setBackgroundResource(R.drawable.kl);
		if(s.toLowerCase().equals("lh"))
			lblProductrow.setBackgroundResource(R.drawable.lh);
		if(s.toLowerCase().equals("pc"))
			lblProductrow.setBackgroundResource(R.drawable.pc);
		if(s.toLowerCase().equals("qr"))
			lblProductrow.setBackgroundResource(R.drawable.qr);
		if(s.toLowerCase().equals("su"))
			lblProductrow.setBackgroundResource(R.drawable.su);
		if(s.toLowerCase().equals("tg"))
			lblProductrow.setBackgroundResource(R.drawable.tg);
		if(s.toLowerCase().equals("tk"))
			lblProductrow.setBackgroundResource(R.drawable.tk);
		if(s.toLowerCase().equals("w5"))
			lblProductrow.setBackgroundResource(R.drawable.w);
		if(s.toLowerCase().equals("wy"))
			lblProductrow.setBackgroundResource(R.drawable.wy);


		return convertView;
	}

	private String GetDayWeek(String fltDateDayOfWeekFalse) {
		if(fltDateDayOfWeekFalse.contains("Saturday")){
			return "شنبه";
		}else if(fltDateDayOfWeekFalse.contains("Sunday")){
			return "یکشنبه";
		}else if(fltDateDayOfWeekFalse.contains("Monday")){
			return "دوشنبه";
		}
		else if(fltDateDayOfWeekFalse.contains("Tuesday")){
			return "سه شنبه";
		}
		else if(fltDateDayOfWeekFalse.contains("Wednesday")){
			return "چهارشنبه";
		}
		else if(fltDateDayOfWeekFalse.contains("Thursday")){
			return "پنجشنبه";
		}
		else if(fltDateDayOfWeekFalse.contains("Friday")){
			return "جمعه";
		}

return "";
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
