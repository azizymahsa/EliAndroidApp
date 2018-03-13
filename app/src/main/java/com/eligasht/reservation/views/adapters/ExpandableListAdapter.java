package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.tools.ExpandableListViewE;

import com.eligasht.R;

import com.eligasht.reservation.models.model.PinModelDetail;
import com.eligasht.reservation.models.model.PinModelHeader;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.SearchParvazActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity _context;
    Activity activity;
    SearchParvazPinAdapter searchParvazPinAdapter;
    List<SearchParvazActivity.ParentItemExpandingPlan> dataExpandingList;
    List<PinModelDetail> pinModelDetails;
    List<PinModelHeader> pinModelHeaders;
    public static Boolean shouldShowAnimation = false;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    boolean isChangeFlight;
    String searchKey;
    String FlightId;
    ExpandableListViewE expListViewExpanding;

    public ExpandableListAdapter(Activity context, List<SearchParvazActivity.ParentItemExpandingPlan> dataList,
                                 SearchParvazPinAdapter searchParvazPinAdapter,
                                 boolean isChangeFlight, String searchKey, String FlightId, ExpandableListViewE expListViewExpanding) {
        this._context = context;
        this.expListViewExpanding = expListViewExpanding;

        this.dataExpandingList = dataList;
        this.searchParvazPinAdapter = searchParvazPinAdapter;
        this.isChangeFlight = isChangeFlight;
        this.FlightId = FlightId;
        this.searchKey = searchKey;

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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        System.out.println("groupPosition:" + groupPosition + "childPosition:" + childPosition);
        final SearchParvazActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_select_parvaz_two_detail, parent,false);
        }
        LinearLayout llCounter = (LinearLayout) convertView.findViewById(R.id.llCounter);
        TextView lblFlightTimeR = (TextView) convertView.findViewById(R.id.lblFlightTimeR);
        TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);
        TextView lblDepurtureAirportR = (TextView) convertView.findViewById(R.id.lblDepurtureAirportR);
        TextView lblArrivalAirportR = (TextView) convertView.findViewById(R.id.lblArrivalAirportR);
        TextView lblFlightNumberR = (TextView) convertView.findViewById(R.id.lblFlightNumberR);
        TextView lblFlightNumberRPersian = (TextView) convertView.findViewById(R.id.lblFlightNumberPersian);

        LinearLayout linearTableNerkh = (LinearLayout) convertView.findViewById(R.id.linearTableNerkh);//
        LinearLayout linearButton = (LinearLayout) convertView.findViewById(R.id.linearButton);
        //nerkh
        TextView txtAdlCostP = (TextView) convertView.findViewById(R.id.txtAdlCostP);
        TextView txtTaxes = (TextView) convertView.findViewById(R.id.txtTaxes);
        TextView txtTotalFareCost = (TextView) convertView.findViewById(R.id.txtTotalFareCost);

        TextView txtAdlCostPR = (TextView) convertView.findViewById(R.id.txtAdlCostPR);
        TextView txtTaxesR = (TextView) convertView.findViewById(R.id.txtTaxesR);
        TextView txtTotalFareCostR = (TextView) convertView.findViewById(R.id.txtTotalFareCostR);

        Button btnSelect = (Button) convertView.findViewById(R.id.btnSelect);

        //nerkh
        if (item.AdlBaseFare > 0) {
            txtAdlCostP.setText(String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
            txtAdlCostPR.setVisibility(View.VISIBLE);
        } else {
            txtAdlCostP.setText("IT");//
            txtAdlCostPR.setVisibility(View.GONE);
        }
        //  txtAdlCostP.setText(item.AdlBaseFare > 0 ? String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)) : "IT");//    String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
        if (item.Taxes > 0) {
            txtTaxes.setText(String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
            txtTaxesR.setVisibility(View.VISIBLE);
        } else {
            txtTaxes.setText("IT");
            txtTaxesR.setVisibility(View.GONE);
        }
        if (item.TotalFare > 0) {
            txtTotalFareCost.setText(String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));
            txtTotalFareCostR.setVisibility(View.VISIBLE);
        } else {
            txtTotalFareCost.setText("IT");
            txtTotalFareCostR.setVisibility(View.GONE);
        }
        //String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
        //  txtTotalFareCost.setText(item.TotalFare > 0 ? String.valueOf(NumberFormat.getInstance().format(item.TotalFare)) : "IT");//String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));

        lblFlightTimeR.setText(item.FlightTimeR + "");
        lblFlightArrivalTimeR.setText(item.FlightArrivalTimeR + "");
        lblDepurtureAirportR.setText(item.DepartureCityNameFa + " , " + item.DepartureAirportNameFaR);
        lblArrivalAirportR.setText(item.ArrivalCityNameFa + " , " + item.ArrivalAirportNameFaR);
        if (item.OperatingAirlineNameEn.contains("null")) {
            lblFlightNumberR.setText(item.AirlineCode + item.FlightNumberR);
            if(Locale.getDefault().getLanguage().equals("en")||Locale.getDefault().getLanguage().equals("tr")){
                String text2 = "<font color=#0e874e>"  + item.AirlineNameFaR+ ""+"</font> " ;
                lblFlightNumberRPersian.setText( " , "+Html.fromHtml(text2));
            }else{
                lblFlightNumberRPersian.setText(item.AirlineNameFaR + " , ");
            }

        } else {
            String text = "<font color=#aaaaaa>" + "By: " + item.OperatingAirlineNameEn + "</font> " +
                    "<font color=#0e874e>" + item.AirlineCode + item.FlightNumberR + "</font>";
            lblFlightNumberR.setText(Html.fromHtml(text));
            if(Locale.getDefault().getLanguage().equals("en")||Locale.getDefault().getLanguage().equals("tr")){
                String text2 = "<font color=#0e874e>"  + item.AirlineNameFaR+ ""+"</font> " ;
                lblFlightNumberRPersian.setText( " , "+Html.fromHtml(text2));
            }else{
                lblFlightNumberRPersian.setText(item.AirlineNameFaR + " , ");
            }
            //lblFlightNumberR.setText("Operated By: " + item.OperatingAirlineNameEn+" , "+item.AirlineCode+item.FlightNumberR+" , "+ item.AirlineNameFaR );
        }
        System.out.println("item.OperatingAirlineNameEn:" + item.OperatingAirlineNameEn);
        int size = this.dataExpandingList.get(groupPosition).Items.size();
        int childSize = childPosition + 1;
        System.out.println(size + "ggg" + childPosition);
        if (childSize != size) {
            btnSelect.setTag(childPosition);
            btnSelect.setVisibility(View.GONE);
            linearTableNerkh.setVisibility(View.GONE);
            linearButton.setVisibility(View.GONE);
        } else {
            btnSelect.setTag(childPosition);
            btnSelect.setVisibility(View.VISIBLE);
            linearTableNerkh.setVisibility(View.VISIBLE);
            linearButton.setVisibility(View.VISIBLE);
        }

        btnSelect.setTag(childPosition);

        if (isChangeFlight) {
            btnSelect.setText(R.string.change_flight);
        }
        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /////////inja biyam Api call Konam

                //////////////////////////////
                if (isChangeFlight) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("searchKey", searchKey);
                    returnIntent.putExtra("FlightId", item.flGUID);
                    _context.setResult(_context.RESULT_OK, returnIntent);
                    _context.finish();
                } else {
                    Intent i4 = new Intent(_context, PassengerActivity.class);

                    i4.putExtra("Flight_GUID", item.flGUID + "");//current.getCityName()

                    _context.startActivity(i4);
                    System.out.println("item.flGUID:" + item.flGUID);

                }


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
        System.out.println("groupPositionRR" + groupPosition);
        System.out.println("groupPositionRR2" + this.dataExpandingList.get(groupPosition).Items.size());
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
            convertView = infalInflater.inflate(R.layout.list_group_expanding, parent,false);//list Group header//row_select_parvaz_two_header
        }


        TextView btnExpand = (TextView) convertView.findViewById(R.id.btnExpand);

        final TextView txtPin = (TextView) convertView.findViewById(R.id.txtPin);

        TextView txtArrivelFalseLast = (TextView) convertView.findViewById(R.id.txtArrivelFalseLast);
        TextView txtDepurtureFalseOne = (TextView) convertView.findViewById(R.id.txtDepurtureFalseOne);

        TextView txtArrivelTrueLast = (TextView) convertView.findViewById(R.id.txtArrivelTrueLast);
        TextView txtDepurtureTrueOne = (TextView) convertView.findViewById(R.id.txtDepurtureTrueOne);


        TextView num_flight_r = (TextView) convertView.findViewById(R.id.num_flight_r);
        TextView num_flight_b = (TextView) convertView.findViewById(R.id.num_flight_b);
        final AVLoadingIndicatorView avi = convertView.findViewById(R.id.avi);

        TextView lblArrivalCityNameFaR = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaR);
        TextView lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);

        TextView lblArrivalCityNameFaB = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaB);
        TextView lblFlightArrivalTimeB = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeB);

        TextView lblAdlCost = (TextView) convertView.findViewById(R.id.lblAdlCost);

        ImageView lblProductrow = (ImageView) convertView.findViewById(R.id.lblProductrow);

        TextView txt_economi = (TextView) convertView.findViewById(R.id.txt_economi);

        TextView txttedad = (TextView) convertView.findViewById(R.id.txttedad);

        LinearLayout linearBargashtOne = (LinearLayout) convertView.findViewById(R.id.linearBargashtOne);
        LinearLayout linearBargashtTwo = (LinearLayout) convertView.findViewById(R.id.linearBargashtTwo);
        LinearLayout linearBargashtTree = (LinearLayout) convertView.findViewById(R.id.linearBargashtTree);
        LinearLayout linearKol = (LinearLayout) convertView.findViewById(R.id.linearKol);
        //final int[] flag = {0};
//        linearKol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("TAG", "onClick: ");
//            }
//        });
        if (shouldShowAnimation) {
            YoYo.with(Techniques.FadeIn)
                    .duration(300)
                    .playOn(linearKol);
            Log.d("TAG", "getGroupView: inside adapter true");
        } else{
            Log.d("TAG", "getGroupView: inside adapter false");
        }
        txtPin.setTag(item2.IsPin);

        txtPin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

			/*	Toast.makeText(v.getContext(), groupPosition+"اتخاب شده!!", Toast.LENGTH_SHORT).show();

				if(item2.IsPin){
					item2.setPin(false);
					txtPin.setTextColor(Color.parseColor("#aaaaaa"));
				}else{
					item2.setPin(true);
					txtPin.setTextColor(Color.parseColor("#9966ff"));


				}
				notifyDataSetChanged();*/

            }
        });
        if (item2.IsPin) {
            pinModelHeaders = new ArrayList<>();

            PinModelHeader pinModelHeader = new PinModelHeader(item2.isPin() + "", item2.DepartureCityNameFaB, item2.ArrivalCityNameFaB, item2.DepartureCityNameFaR, item2.ArrivalCityNameFaR, item2.AirlineCode + item2.FlightNumberR + "", item2.AirlineCode + item2.FlightNumberB + ""
                    , "" + GetDayWeek(item2.FltDateDayOfWeek) + " , " + item2.FlightArrivalTimeR, item2.SegmentTrueCount + _context.getString(R.string.stop),
                    GetDayWeek(item2.FltDateDayOfWeekFalse) + " , " + item2.FlightTimeB, item2.SegmentFalseCount + _context.getString(R.string.stop), item2.AdlCost + "", item2.AirlineCode, item2.CabinClassNameFa, _context.getString(R.string.people_number) + item2.RemainSeats + _context.getString(R.string.just), item2.SegmentFalseCount, item2.RemainSeats);
            pinModelHeaders.add(pinModelHeader);
            //
            pinModelDetails = new ArrayList<>();
            int count = item2.SegmentFalseCount + item2.SegmentTrueCount;//count segment
            for (int i = 0; i < count; i++) {
                SearchParvazActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(i);
                ;

                PinModelDetail pinModelDetail = new PinModelDetail(item.AdlBaseFare, item.Taxes, item.TotalFare, item.FlightTimeR, item.FlightArrivalTimeR, item.DepartureCityNameFa, item.DepartureAirportNameFaR, item.ArrivalCityNameFa, item.ArrivalAirportNameFaR, item.AirlineCode, item.FlightNumberR, item.AirlineNameFaR, this.dataExpandingList.get(groupPosition).Items.size());
                pinModelDetails.add(pinModelDetail);
            }
            //felan barmidaram
            //SearchParvazActivity.updateAdapterPin(pinModelDetails,pinModelHeaders,_context);

        }


        if (isExpanded) {
            btnExpand.setText(_context.getString(R.string.icon_exp_up));
            expListViewExpanding.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);


            //linearKol.clearAnimation();

        } else {
            btnExpand.setText(_context.getString(R.string.icon_exp_down));

            //linearKol.clearAnimation();
        /*	Animation scaleUpp = AnimationUtils.loadAnimation(_context, R.anim.anim_list);
            linearKol.startAnimation(scaleUpp);*/
        }

        ///
        System.out.println("item2.SegmentFalseCount:" + item2.SegmentFalseCount);
        if (item2.SegmentFalseCount < 1 || item2.SegmentFalseCount == 0) {//yek tarafe
            linearBargashtOne.setVisibility(View.GONE);
            linearBargashtTwo.setVisibility(View.GONE);

            linearBargashtTree.setVisibility(View.GONE);

        } else {//2tarafe
            //////Ruze hafte
            //bargasht
            txtArrivelFalseLast.setText(item2.DepartureCityNameFaB);
            txtDepurtureFalseOne.setText(item2.ArrivalCityNameFaB);

            num_flight_b.setText(item2.AirlineCode + item2.FlightNumberB);
            ///////////////
            //lblArrivalCityNameFaB.setText(" برگشت به "+item2.DepartureCityNameFaB+"");
            System.out.println("bargasgt:" + item2.FltDateDayOfWeekFalse);
            lblArrivalCityNameFaB.setText("" + GetDayWeek(item2.FltDateDayOfWeekFalse) + " , " + item2.FlightTimeB);
            int tavaghofB = item2.SegmentFalseCount - 1;
            lblFlightArrivalTimeB.setText((tavaghofB == 0) ? _context.getString(R.string.none_stop) : tavaghofB + _context.getString(R.string.stop));//count bargasht
        }
        //raft
        txtArrivelTrueLast.setText(item2.DepartureCityNameFaR);
        txtDepurtureTrueOne.setText(item2.ArrivalCityNameFaR);


        num_flight_r.setText(item2.AirlineCode + item2.FlightNumberR);
        //
        //	lblArrivalCityNameFaR.setText(" رفت به "+item2.DepartureCityNameFaR+"");
        System.out.println("raft:" + item2.FltDateDayOfWeek);
        lblArrivalCityNameFaR.setText("" + GetDayWeek(item2.FltDateDayOfWeek) + " , " + item2.FlightArrivalTimeR);
        int tavaghofR = item2.SegmentTrueCount - 1;
        lblFlightArrivalTimeR.setText((tavaghofR == 0) ? _context.getString(R.string.none_stop) : tavaghofR + _context.getString(R.string.stop));//count raft

        lblAdlCost.setText(item2.AdlCost + "");
        lblAdlCost.setText(String.valueOf(NumberFormat.getInstance().format(item2.AdlCost)));

        //lblAirline.setText(item2.AirlineNameFa);

        txt_economi.setText(item2.CabinClassNameFa);

        if (item2.RemainSeats == 0) {
            txttedad.setText(_context.getString(R.string.people) + item2.RemainSeats + _context.getString(R.string.just));
            txttedad.setVisibility(View.INVISIBLE);

        } else {
            txttedad.setText(_context.getString(R.string.just) + item2.RemainSeats + _context.getString(R.string.people));
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
        String s = item2.AirlineCode;
        /*if(s.toLowerCase().equals("ir"))
            lblProductrow.setBackgroundResource(R.drawable.ir);
		else if(s.toLowerCase().equals("a3"))
			lblProductrow.setBackgroundResource(R.drawable.a);
		else if(s.toLowerCase().equals("af"))
			lblProductrow.setBackgroundResource(R.drawable.af);
		else if(s.toLowerCase().equals("az"))
			lblProductrow.setBackgroundResource(R.drawable.az);
		else if(s.toLowerCase().equals("ek"))
			lblProductrow.setBackgroundResource(R.drawable.ek);
		else if(s.toLowerCase().equals("ey"))
			lblProductrow.setBackgroundResource(R.drawable.ey);
		else if(s.toLowerCase().equals("fz"))
			lblProductrow.setBackgroundResource(R.drawable.fz);
		else if(s.toLowerCase().equals("g9"))
			lblProductrow.setBackgroundResource(R.drawable.g);
		else if(s.toLowerCase().equals("j2"))
			lblProductrow.setBackgroundResource(R.drawable.j);
		else if(s.toLowerCase().equals("ji"))
			lblProductrow.setBackgroundResource(R.drawable.ji);
		else if(s.toLowerCase().equals("kk"))
			lblProductrow.setBackgroundResource(R.drawable.kk);
		else if(s.toLowerCase().equals("kl"))
			lblProductrow.setBackgroundResource(R.drawable.kl);
		else if(s.toLowerCase().equals("lh"))
			lblProductrow.setBackgroundResource(R.drawable.lh);
		else if(s.toLowerCase().equals("pc"))
			lblProductrow.setBackgroundResource(R.drawable.pc);
		else if(s.toLowerCase().equals("qr"))
			lblProductrow.setBackgroundResource(R.drawable.qr);
		else if(s.toLowerCase().equals("su"))
			lblProductrow.setBackgroundResource(R.drawable.su);
		else if(s.toLowerCase().equals("tg"))
			lblProductrow.setBackgroundResource(R.drawable.tg);
		else if(s.toLowerCase().equals("tk"))
			lblProductrow.setBackgroundResource(R.drawable.tk);
		else if(s.toLowerCase().equals("w5"))
			lblProductrow.setBackgroundResource(R.drawable.w);
		else if(s.toLowerCase().equals("wy"))
			lblProductrow.setBackgroundResource(R.drawable.wy);
		else {*/
        System.out.println(s);
				/*	try {
						InputStream is = (InputStream) new URL("https://cdn.elicdn.com/Content/AirLine/"+s+".png").getContent();
						System.out.println("https://cdn.elicdn.com/Content/AirLine/"+s+".png");
						Bitmap d = BitmapFactory.decodeStream(is);
						is.close();
						lblProductrow.setImageBitmap(d);
						//return d;
					} catch (Exception e) {
						System.out.println(e);
						return null;
					}*/

        String imageUri = "https://cdn.elicdn.com/Content/AirLine/MblSize/" + s + ".png";

        System.out.println("https://cdn.elicdn.com/Content/AirLine/MblSize/" + s + ".png");
        Glide
                .with(_context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                avi.setVisibility(View.GONE);
                return false;
            }
        })
                .into(lblProductrow);




		/*lblProductrow.setImageResource();
		/////////////store image/////////////////////
		File filename;
		Bitmap bitMapImg = null;
		try {
			bitMapImg = ((BitmapDrawable) lblProductrow.getDrawable()).getBitmap();
			//System.out.println("bitmap"+bitMapImg);
			String path = Environment.getExternalStorageDirectory().toString();

			new File(path + "/folder/subfolder").mkdirs();
			filename = new File(path + "/folder/subfolder/"+s+".png");

			FileOutputStream out = new FileOutputStream(filename);

			bitMapImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

			//MediaStore.Images.Media.insertImage(_context, filename.getAbsolutePath(), filename.getName(), filename.getName());

			Toast.makeText(_context, "File is Saved in  " + filename, 1000).show();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
        ////////////////////////////////////////////
        //}

        return convertView;
    }

    private String GetDayWeek(String fltDateDayOfWeekFalse) {
        if (fltDateDayOfWeekFalse.contains("Saturday")) {
            return _context.getString(R.string.saturday);
        } else if (fltDateDayOfWeekFalse.contains("Sunday")) {
            return _context.getString(R.string.sunday);
        } else if (fltDateDayOfWeekFalse.contains("Monday")) {
            return _context.getString(R.string.monday);
        } else if (fltDateDayOfWeekFalse.contains("Tuesday")) {
            return _context.getString(R.string.tuesday);
        } else if (fltDateDayOfWeekFalse.contains("Wednesday")) {
            return _context.getString(R.string.wednesday);
        } else if (fltDateDayOfWeekFalse.contains("Thursday")) {
            return _context.getString(R.string.thursday);
        } else if (fltDateDayOfWeekFalse.contains("Friday")) {
            return _context.getString(R.string.friday);
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
//end asynTask



}
