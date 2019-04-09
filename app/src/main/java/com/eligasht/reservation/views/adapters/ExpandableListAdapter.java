package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.tools.ExpandableListViewE;

import com.eligasht.R;

import com.eligasht.reservation.models.model.PinModelDetail;
import com.eligasht.reservation.models.model.PinModelHeader;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.FlightSeatActivity;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.SearchFlightActivity;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity _context;
    Activity activity;
    SearchParvazPinAdapter searchParvazPinAdapter;
    List<SearchFlightActivity.ParentItemExpandingPlan> dataExpandingList;
    List<PinModelDetail> pinModelDetails;
    List<PinModelHeader> pinModelHeaders;
    public static Boolean shouldShowAnimation = false;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    boolean isChangeFlight;
    String searchKey;
    String searchKeyConfirm;
    String FlightId;
    ExpandableListViewE expListViewExpanding;
    int childPosition;

    public ExpandableListAdapter(Activity context, List<SearchFlightActivity.ParentItemExpandingPlan> dataList,
                                 SearchParvazPinAdapter searchParvazPinAdapter,
                                 boolean isChangeFlight, String searchKey, String FlightId, ExpandableListViewE expListViewExpanding,String searchKeyConfirm) {
        this._context = context;
        this.expListViewExpanding = expListViewExpanding;

        this.dataExpandingList = dataList;
        this.searchParvazPinAdapter = searchParvazPinAdapter;
        this.isChangeFlight = isChangeFlight;
        this.FlightId = FlightId;
        this.searchKey = searchKey;
        this.searchKeyConfirm = searchKeyConfirm;

    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        childPosition = childPosititon;
        return this.dataExpandingList.get(groupPosition).Items.get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
      //  System.out.println("groupPosition:" + groupPosition + "childPosition:" + childPosition);
        final SearchFlightActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_select_parvaz_two_detail, parent, false);
        }
        LinearLayout llCounter = convertView.findViewById(R.id.llCounter);
        llCounter.setOnClickListener(v -> {
            if (isChangeFlight) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("searchKey", searchKey);
                returnIntent.putExtra("FlightId", item.flGUID);
                _context.setResult(Activity.RESULT_OK, returnIntent);
                _context.finish();
            } else {
                Intent i4 = new Intent(_context, PassengerActivity.class);

                i4.putExtra("Flight_GUID", item.flGUID + "");//current.getCityName()
                i4.putExtra("Search_Key_Confirm", searchKeyConfirm + "");//current.getCityName()

                // _context.startActivity(i4);
                SwipeBackActivityHelper.activityBuilder(_context)
                        .intent(i4)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
               // System.out.println("item.flGUID:" + item.flGUID);

            }
        });
        TextView lblFlightTimeR = convertView.findViewById(R.id.lblFlightTimeR);
        TextView lblFlightArrivalTimeR = convertView.findViewById(R.id.lblFlightArrivalTimeR);
        TextView lblDepurtureAirportR = convertView.findViewById(R.id.lblDepurtureAirportR);
        TextView lblArrivalAirportR = convertView.findViewById(R.id.lblArrivalAirportR);
        TextView lblFlightNumberR = convertView.findViewById(R.id.lblFlightNumberR);
        TextView lblFlightNumberRPersian = convertView.findViewById(R.id.lblFlightNumberPersian);

        LinearLayout linearTableNerkh = convertView.findViewById(R.id.linearTableNerkh);//
        RelativeLayout linearButton = convertView.findViewById(R.id.linearButton);
        //nerkh
        TextView txtAdlCostP = convertView.findViewById(R.id.txtAdlCostP);
        TextView txtTaxes = convertView.findViewById(R.id.txtTaxes);
        TextView txtTotalFareCost = convertView.findViewById(R.id.txtTotalFareCost);

        TextView txtAdlCostPR = convertView.findViewById(R.id.txtAdlCostPR);
        TextView txtTaxesR = convertView.findViewById(R.id.txtTaxesR);
        TextView txtTotalFareCostR = convertView.findViewById(R.id.txtTotalFareCostR);

        TextView iconWeight = convertView.findViewById(R.id.iconWeight);
        TextView iconPieces = convertView.findViewById(R.id.iconPieces);

        Button btnSelect = convertView.findViewById(R.id.btnSelect);
        Button btnFlight = convertView.findViewById(R.id.btnFlight);
        btnFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _context.startActivity(new Intent(_context, FlightSeatActivity.class));
            }
        });

        LinearLayout weightLinear = convertView.findViewById(R.id.weightLinear);
        TextView txtPieces = convertView.findViewById(R.id.txtPieces);
        TextView txtWeight = convertView.findViewById(R.id.txtWeight);
        weightLinear.setVisibility(View.GONE);
        try {
            if (item.weight.length() > 0 && item.Pieces.length()> 0){
                if (Integer.parseInt(item.weight) > 0 && Integer.parseInt(item.Pieces)> 0) {
                    iconPieces.setVisibility(View.VISIBLE);
                    iconWeight.setVisibility(View.INVISIBLE);
                    weightLinear.setVisibility(View.VISIBLE);
                    txtWeight.setText(item.weight + "");
                    if (Locale.getDefault().getLanguage().contains("en") || Locale.getDefault().getLanguage().contains("tr")) {
                        // String text2 = "<font color=#0e874e>" + item.AirlineNameFaR + "" + "</font> ";
                        txtPieces.setText(item.Pieces + "×");
                    } else {
                        txtPieces.setText("×"+item.Pieces  );

                    }

                }else{
                    iconPieces.setVisibility(View.GONE);
                    iconWeight.setVisibility(View.GONE);
                    if (Integer.parseInt(item.weight) == 0 && Integer.parseInt(item.Pieces) == 0) {
                        txtPieces.setText("NoBaggage");
                    } else {
                        txtPieces.setText("NotAnnounced");
                    }

                }
            }else{
                iconPieces.setVisibility(View.GONE);
                iconWeight.setVisibility(View.GONE);
                txtPieces.setText("NotAnnounced");

            }
        } catch (Exception e) {
           // Log.e("getChildView: ",e.getMessage() );
        }
        //nerkh
        if (item.AdlBaseFare > 0) {
            txtAdlCostP.setText(String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
            txtAdlCostPR.setVisibility(View.VISIBLE);
            txtAdlCostPR.setText(Prefs.getString("GlobalCurrencyCode", ""));

        } else {
            txtAdlCostP.setText("IT");//
            txtAdlCostPR.setVisibility(View.GONE);
        }
        //  txtAdlCostP.setText(item.AdlBaseFare > 0 ? String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)) : "IT");//    String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
        if (item.Taxes > 0) {
            txtTaxes.setText(String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
            txtTaxesR.setVisibility(View.VISIBLE);
            txtTaxesR.setText(Prefs.getString("GlobalCurrencyCode", ""));

        } else {
            txtTaxes.setText("IT");
            txtTaxesR.setVisibility(View.GONE);
        }
        if (item.TotalFare > 0) {
            txtTotalFareCost.setText(String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));
            txtTotalFareCostR.setVisibility(View.VISIBLE);
            txtTotalFareCostR.setText(Prefs.getString("GlobalCurrencyCode", ""));
        } else {
            txtTotalFareCost.setText("IT");
            txtTotalFareCostR.setVisibility(View.GONE);
        }
        //String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
        //txtTotalFareCost.setText(item.TotalFare > 0 ? String.valueOf(NumberFormat.getInstance().format(item.TotalFare)) : "IT");//String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));

        lblFlightTimeR.setText(item.FlightTimeR + "");
        lblFlightArrivalTimeR.setText(item.FlightArrivalTimeR + "");
        lblDepurtureAirportR.setText(item.DepartureCityNameFa + " , " + item.DepartureAirportNameFaR);
        lblArrivalAirportR.setText(item.ArrivalCityNameFa + " , " + item.ArrivalAirportNameFaR);
        if (item.OperatingAirlineNameEn == null || item.OperatingAirlineNameEn.equals("null")) {
            String test = item.AirlineCode + item.FlightNumberR;
            lblFlightNumberR.setText(test.replace(" ", ""));
            Log.e("Exp:", test.replace(" ", ""));
            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
               // String text2 = "<font color=#0e874e>" + item.AirlineNameFaR + "" + "</font> ";
                lblFlightNumberRPersian.setText(" , " + item.AirlineNameFaR);
            } else {
                lblFlightNumberRPersian.setText(item.AirlineNameFaR + " , ");
            }

        } else {
            String text = "<font color=#aaaaaa>" + "By: " + item.OperatingAirlineNameEn + "</font> " +
                    "<font color=#0e874e>" + item.AirlineCode.replace(" ", "") + item.FlightNumberR.replace(" ", "") + "</font>";
            lblFlightNumberR.setText(Html.fromHtml(text));
            Log.e("Exp:", Html.fromHtml(text) + "");
            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
              //  String text2 = "<font color=#0e874e>" + item.AirlineNameFaR + "" + "</font> ";
                lblFlightNumberRPersian.setText(" , " + item.AirlineNameFaR);
            } else {
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

                if (isChangeFlight) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("searchKey", searchKey);
                    returnIntent.putExtra("FlightId", item.flGUID);
                    _context.setResult(Activity.RESULT_OK, returnIntent);
                    _context.finish();
                } else {
                    //SearchFlightActivity.getConfirmFlightPrice();
                    Intent i4 = new Intent(_context, PassengerActivity.class);

                    i4.putExtra("Flight_GUID", item.flGUID + "");//current.getCityName()
                    i4.putExtra("Search_Key_Confirm", searchKeyConfirm + "");//current.getCityName()


                    // _context.startActivity(i4);
                    SwipeBackActivityHelper.activityBuilder(_context)
                            .intent(i4)
                            .needParallax(true)
                            .needBackgroundShadow(true)
                            .startActivity();
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

        //ArrayList<SearchFlightActivity.HeaderExpandingPlan> item2 = this.dataExpandingList.get(groupPosition).Header;
        final SearchFlightActivity.HeaderExpandingPlan item2 = this.dataExpandingList.get(groupPosition).Header;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_expanding, parent, false);//list Group header//row_select_parvaz_two_header
        }

        TextView btnExpand = convertView.findViewById(R.id.btnExpand);

        final TextView txtPin = convertView.findViewById(R.id.txtPin);

        TextView txtArrivelFalseLast = convertView.findViewById(R.id.txtArrivelFalseLast);
        TextView txtDepurtureFalseOne = convertView.findViewById(R.id.txtDepurtureFalseOne);

        TextView txtArrivelTrueLast = convertView.findViewById(R.id.txtArrivelTrueLast);
        TextView txtDepurtureTrueOne = convertView.findViewById(R.id.txtDepurtureTrueOne);

        TextView num_flight_r = convertView.findViewById(R.id.num_flight_r);
        TextView num_flight_b = convertView.findViewById(R.id.num_flight_b);
        final AVLoadingIndicatorView avi = convertView.findViewById(R.id.avi);

        TextView lblArrivalCityNameFaRTime = convertView.findViewById(R.id.lblArrivalCityNameFaRTime);
        TextView lblArrivalCityNameFaR = convertView.findViewById(R.id.lblArrivalCityNameFaR);

        TextView lblFlightArrivalTimeR = convertView.findViewById(R.id.lblFlightArrivalTimeR);

        TextView lblArrivalCityNameFaB = convertView.findViewById(R.id.lblArrivalCityNameFaB);
        TextView lblArrivalCityNameFaBTime = convertView.findViewById(R.id.lblArrivalCityNameFaBTime);
        TextView lblFlightArrivalTimeB = convertView.findViewById(R.id.lblFlightArrivalTimeB);

        TextView lblAdlCost = convertView.findViewById(R.id.lblAdlCost);

        TextView txtCurrencyCode = convertView.findViewById(R.id.txtCurrencyCode);

        txtCurrencyCode.setText(Prefs.getString("GlobalCurrencyCode", ""));
        ImageView lblProductrow = convertView.findViewById(R.id.lblProductrow);

        TextView txt_economi = convertView.findViewById(R.id.txt_economi);

        TextView txttedad = convertView.findViewById(R.id.txttedad);

        TextView lblFlightArrivalTimeLongB = convertView.findViewById(R.id.lblFlightArrivalTimeLongB);
        TextView lblFlightArrivalTimeLongR = convertView.findViewById(R.id.lblFlightArrivalTimeLongR);

        TextView tvPlaneIcon2 = convertView.findViewById(R.id.tvPlaneIcon2);
        TextView tvPlaneIcon = convertView.findViewById(R.id.tvPlaneIcon);
        View viewLine2 = convertView.findViewById(R.id.viewLine2);
        View viewLine = convertView.findViewById(R.id.viewLine);
        LinearLayout llRaft = convertView.findViewById(R.id.llRaft);

        LinearLayout linearBargashtOne = convertView.findViewById(R.id.linearBargashtOne);
        LinearLayout linearBargashtTwo = convertView.findViewById(R.id.linearBargashtTwo);
        LinearLayout linearBargashtTree = convertView.findViewById(R.id.linearBargashtTree);
        LinearLayout linearKol = convertView.findViewById(R.id.linearKol);

        int hTrue = 0;
        int mTrue = 0;
       // for (int i = 0; i < dataExpandingList.get(groupPosition).Header.SegmentTrueCount; i++) {
          Log.e("SegmentTrueCount:", dataExpandingList.get(groupPosition).Header.SegmentTrueCount + "");
            try {
                hTrue = hTrue + Integer.parseInt(item2.segmentListtrueAkhari.get(item2.segmentListtrueAkhari.size()-1).getFltDurationH());
                mTrue = mTrue + Integer.parseInt(item2.segmentListtrueAkhari.get(item2.segmentListtrueAkhari.size()-1).getFltDurationM());
            } catch (Exception e) {
                lblFlightArrivalTimeLongR.setVisibility(View.INVISIBLE);
                lblFlightArrivalTimeLongB.setVisibility(View.INVISIBLE);
            }

      //  }
        int hour = mTrue / 60;
        int min = mTrue % 60;
        int sumTrueH = hTrue + hour;
        int sumTrueM = min;
        int hFalse = 0;
        int mFalse = 0;
       // for (int i = 0; i < dataExpandingList.get(groupPosition).Header.SegmentFalseCount; i++) {
            Log.e("SegmentFalseCount:", dataExpandingList.get(groupPosition).Header.SegmentFalseCount + "");
            try {
                hFalse = hFalse + Integer.parseInt(item2.segmentListfalseAkhari.get(item2.segmentListfalseAkhari.size()-1).getFltDurationH());
                mFalse = mFalse + Integer.parseInt(item2.segmentListfalseAkhari.get(item2.segmentListfalseAkhari.size()-1).getFltDurationM());
            } catch (Exception e) {
                lblFlightArrivalTimeLongR.setVisibility(View.INVISIBLE);
                lblFlightArrivalTimeLongB.setVisibility(View.INVISIBLE);

            }

       // }
        int hourF = mFalse / 60;
        int minF = mFalse % 60;
        int sumFalseH = hFalse + hourF;
        int sumFalseM = minF;

        lblFlightArrivalTimeLongR.setText(sumTrueH + " h " + sumTrueM + " m");
        lblFlightArrivalTimeLongB.setText(sumFalseH + " h " + sumFalseM + " m");
        if (shouldShowAnimation) {
            YoYo.with(Techniques.FadeIn)
                    .duration(300)
                    .playOn(linearKol);
            Log.d("TAG", "getGroupView: inside adapter true");
        } else {
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
                SearchFlightActivity.ItemExpandingPlan item = this.dataExpandingList.get(groupPosition).Items.get(i);

                PinModelDetail pinModelDetail = new PinModelDetail(item.AdlBaseFare, item.Taxes, item.TotalFare, item.FlightTimeR, item.FlightArrivalTimeR, item.DepartureCityNameFa, item.DepartureAirportNameFaR, item.ArrivalCityNameFa, item.ArrivalAirportNameFaR, item.AirlineCode, item.FlightNumberR, item.AirlineNameFaR, this.dataExpandingList.get(groupPosition).Items.size());
                pinModelDetails.add(pinModelDetail);
            }

            //SearchFlightActivity.updateAdapterPin(pinModelDetails,pinModelHeaders,_context);

        }


        if (isExpanded) {
            btnExpand.setText(_context.getString(R.string.icon_exp_up));
            expListViewExpanding.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
            //moving tvPlaneIcon2 from right to left
           /* float right = llRaft.getRight();
            float left = llRaft.getLeft();
            tvPlaneIcon.setTranslationX(right);

            float right2 = linearBargashtTwo.getRight();
            float left2 = linearBargashtTwo.getLeft();
            tvPlaneIcon2.setTranslationX(right);

            ObjectAnimator anim2 = ObjectAnimator.ofFloat(tvPlaneIcon, "translationX", right, left-_context.getResources().getInteger(R.integer._85));
            anim2.setDuration(1500);
            anim2.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim2.start();
            ObjectAnimator anim3 = ObjectAnimator.ofFloat(viewLine, "translationX", right, left-_context.getResources().getInteger(R.integer._85));
            anim3.setDuration(1500);
            anim3.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim3.start();

            ObjectAnimator anim4 = ObjectAnimator.ofFloat(tvPlaneIcon2, "translationX", right2, left2-_context.getResources().getInteger(R.integer._85) );
            anim4.setDuration(1500);
            anim4.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim4.start();
            ObjectAnimator anim5 = ObjectAnimator.ofFloat(viewLine2, "translationX", right2, left2-_context.getResources().getInteger(R.integer._85));
            anim5.setDuration(1500);
            anim5.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim5.start(); */
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

           // num_flight_b.setText(item2.AirlineCode + item2.FlightNumberB);
            num_flight_b.setText(item2.segmentListfalseAkhari.get(item2.segmentListfalseAkhari.size()-1).getAirlineCode() + item2.segmentListfalseAkhari.get(item2.segmentListfalseAkhari.size()-1).getFlightNumber());

            ///////////////
            //lblArrivalCityNameFaB.setText(" برگشت به "+item2.DepartureCityNameFaB+"");
            System.out.println("bargasgt:" + item2.FltDateDayOfWeekFalse);
            lblArrivalCityNameFaB.setText("" + GetDayWeek(item2.FltDateDayOfWeekFalse));
            lblArrivalCityNameFaBTime.setText(item2.FlightTimeB);//+" , ");
            try {
                int tavaghofB = item2.SegmentFalseCount - 1;
                System.out.println("tavaghofR:" + tavaghofB + "RR" + item2.SegmentFalseCount);
                lblFlightArrivalTimeB.setText((tavaghofB == 0) ? _context.getString(R.string.none_stop) : tavaghofB + _context.getString(R.string.stop) + "");//count bargasht
            } catch (Exception e) {
                e.getMessage();
            }

        }
        //raft
        txtArrivelTrueLast.setText(item2.DepartureCityNameFaR);
        txtDepurtureTrueOne.setText(item2.ArrivalCityNameFaR);


       // num_flight_r.setText(item2.AirlineCode + item2.FlightNumberR);
        num_flight_r.setText(item2.segmentListtrueAkhari.get(item2.segmentListtrueAkhari.size()-1).getAirlineCode() + item2.segmentListtrueAkhari.get(item2.segmentListtrueAkhari.size()-1).getFlightNumber());
        //
        //	lblArrivalCityNameFaR.setText(" رفت به "+item2.DepartureCityNameFaR+"");
        System.out.println("raft:" + item2.FltDateDayOfWeek);
        lblArrivalCityNameFaR.setText("" + GetDayWeek(item2.FltDateDayOfWeek));
        lblArrivalCityNameFaRTime.setText(item2.FlightArrivalTimeR);//+" , ");
        try {
            int tavaghofR = item2.SegmentTrueCount - 1;
            System.out.println("tavaghofR:" + tavaghofR + "BB" + item2.SegmentTrueCount);
            lblFlightArrivalTimeR.setText((tavaghofR == 0) ? _context.getString(R.string.none_stop) : tavaghofR + _context.getString(R.string.stop) + "");//count raft
        } catch (Exception e) {
            e.getMessage();
        }
        txtCurrencyCode.setText(Prefs.getString("GlobalCurrencyCode", ""));

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

        String imageUri = "https://cdn.elicdn.com/Content/AirLine/MblSize/" + s + ".png";

        System.out.println("https://cdn.elicdn.com/Content/AirLine/MblSize/" + s + ".png");
        GlideApp
                .with(_context)
                .load(imageUri)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        avi.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(lblProductrow);

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
