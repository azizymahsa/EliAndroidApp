package com.eligasht.reservation.views.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.eligasht.R;

import com.eligasht.reservation.models.model.PinModelDetail;
import com.eligasht.reservation.models.model.PinModelHeader;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class SearchParvazPinAdapter extends RecyclerView.Adapter<SearchParvazPinAdapter.ViewHolder> {

    private final List<PinModelDetail> data;
    private final List<PinModelHeader> dataHeader;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public SearchParvazPinAdapter(final List<PinModelDetail> data,final List<PinModelHeader> dataHeader) {
        this.data = data;
        this.dataHeader=dataHeader;
        if(data != null)
        for (int i = 0; i < dataHeader.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_list_row_pin, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final PinModelDetail item = data.get(position);
        PinModelHeader item2 = dataHeader.get(position-1);
        holder.setIsRecyclable(false);
//Detail//////////////////////////////////////////////////////////
        holder.txtAdlCostP.setText( item.getAdlBaseFare() > 0 ? String.valueOf(NumberFormat.getInstance().format(item.getAdlBaseFare())) : "It");//    String.valueOf(NumberFormat.getInstance().format(item.AdlBaseFare)));
        holder.txtTaxes.setText(item.getTaxes() > 0 ? String.valueOf(NumberFormat.getInstance().format(item.getTaxes())) : "It");//String.valueOf(NumberFormat.getInstance().format(item.Taxes)));
        holder.txtTotalFareCost.setText(item.getTotalFare() > 0 ? String.valueOf(NumberFormat.getInstance().format(item.getTotalFare())) : "It");//String.valueOf(NumberFormat.getInstance().format(item.TotalFare)));
        holder.lblFlightTimeR.setText(item.getFlightTimeR()+"");
        holder.lblFlightArrivalTimeR.setText(item.getFlightArrivalTimeR()+"");
        holder.lblDepurtureAirportR.setText(item.getDepartureCityNameFa()+" , "+item.getDepartureAirportNameFaR());
        holder.lblArrivalAirportR.setText(item.getArrivalCityNameFa()+" , "+item.getArrivalAirportNameFaR());
        holder.lblFlightNumberR.setText(item.getAirlineCode()+item.getFlightNumberR()+" , "+ item.getAirlineNameFaR());
  //Header/////////////////////////////////////////////////////
        if(item2.getSegmentFalseCount() <1 || item2.getSegmentFalseCount() ==0){//yek tarafe
            holder.linearBargashtOne.setVisibility(View.GONE);
            holder.linearBargashtTwo.setVisibility(View.GONE);

            holder.linearBargashtTree.setVisibility(View.GONE);

        }else{//2tarafe
            //////Ruze hafte
            //bargasht
            holder.txtArrivelFalseLast.setText(item2.getTxtArrivelFalseLast());
            holder.txtDepurtureFalseOne .setText(item2.getTxtDepurtureFalseOne());

            holder.num_flight_b.setText(item2.getNum_flight_b());
            ///////////////

            holder.lblArrivalCityNameFaB.setText(item2.getLblArrivalCityNameFaB());
            holder.lblFlightArrivalTimeB.setText(item2.getLblFlightArrivalTimeB());//count bargasht
        }
            //raft
            holder.txtArrivelTrueLast.setText(item2.getTxtArrivelTrueLast());
            holder.txtDepurtureTrueOne.setText(item2.getTxtDepurtureTrueOne());


            holder.num_flight_r.setText(item2.getNum_flight_r());

            holder.lblArrivalCityNameFaR.setText(item2.getLblArrivalCityNameFaR());
            holder.lblFlightArrivalTimeR.setText(item2.getLblFlightArrivalTimeR());//count raft

            holder. lblAdlCost.setText(item2.getLblAdlCost());

            holder.txt_economi.setText(item2.getTxt_economi());

            if(item2.getRemainSeats() ==0){
                holder. txttedad.setText("نفر"+item2.getRemainSeats()+"فقط");
                holder.txttedad.setVisibility(View.INVISIBLE);

            }else{
                holder.txttedad.setText("فقط"+item2.getRemainSeats()+"نفر");
                holder. txttedad.setVisibility(View.VISIBLE);
            }
/////////////////////////////////////////////////////////////
        holder.expandableLayout.setInRecyclerView(true);
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
               // createRotateAnimator(holder.tvArrow, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
              //  createRotateAnimator(holder.tvArrow, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

       // holder.tvArrow.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lblFlightTimeR,lblFlightArrivalTimeR,lblDepurtureAirportR,lblArrivalAirportR,lblFlightNumberR,txtAdlCostP,txtTaxes,txtTotalFareCost;
        public Button btnSelect;
        public LinearLayout linearTableNerkh,linearButton;
        //  public RelativeLayout tvArrow;
        public RelativeLayout buttonLayout;
        //header
        public TextView txttedad,txt_economi,lblAdlCost,lblFlightArrivalTimeB,lblArrivalCityNameFaB,btnExpand,txtPin,txtArrivelFalseLast,txtDepurtureFalseOne,txtArrivelTrueLast,txtDepurtureTrueOne,num_flight_r,num_flight_b,lblArrivalCityNameFaR,lblFlightArrivalTimeRHeader;
        public ImageView lblProductrow;
        public LinearLayout linearBargashtOne,linearBargashtTree;
        public RelativeLayout linearBargashtTwo;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            expandableLayout= (ExpandableLinearLayout) convertView.findViewById(R.id.expandableLayout);
            //Detail
             lblFlightTimeR = (TextView) convertView.findViewById(R.id.lblFlightTimeR);
             lblFlightArrivalTimeR = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);
             lblDepurtureAirportR = (TextView) convertView.findViewById(R.id.lblDepurtureAirportR);
             lblArrivalAirportR=(TextView)convertView.findViewById(R.id.lblArrivalAirportR);
             lblFlightNumberR = (TextView) convertView.findViewById(R.id.lblFlightNumberR);

             linearTableNerkh = (LinearLayout) convertView.findViewById(R.id.linearTableNerkh);//
             linearButton = (LinearLayout) convertView.findViewById(R.id.linearButton);

             txtAdlCostP = (TextView) convertView.findViewById(R.id.txtAdlCostP);
             txtTaxes=(TextView)convertView.findViewById(R.id.txtTaxes);
             txtTotalFareCost = (TextView) convertView.findViewById(R.id.txtTotalFareCost);
             btnSelect = (Button) convertView.findViewById(R.id.btnSelect);

            buttonLayout= (RelativeLayout) convertView.findViewById(R.id.buttonLayout);
             //header
             btnExpand = (TextView) convertView.findViewById(R.id.btnExpand);

              txtPin= (TextView) convertView.findViewById(R.id.txtPin);

             txtArrivelFalseLast = (TextView) convertView.findViewById(R.id.txtArrivelFalseLast);
             txtDepurtureFalseOne = (TextView) convertView.findViewById(R.id.txtDepurtureFalseOne);

             txtArrivelTrueLast = (TextView) convertView.findViewById(R.id.txtArrivelTrueLast);
             txtDepurtureTrueOne = (TextView) convertView.findViewById(R.id.txtDepurtureTrueOne);


             num_flight_r = (TextView) convertView.findViewById(R.id.num_flight_r);
             num_flight_b = (TextView) convertView.findViewById(R.id.num_flight_b);

             lblArrivalCityNameFaR = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaR);
             lblFlightArrivalTimeRHeader = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeR);

             lblArrivalCityNameFaB = (TextView) convertView.findViewById(R.id.lblArrivalCityNameFaB);
             lblFlightArrivalTimeB = (TextView) convertView.findViewById(R.id.lblFlightArrivalTimeB);

             lblAdlCost = (TextView) convertView.findViewById(R.id.lblAdlCost);

             lblProductrow= (ImageView) convertView.findViewById(R.id.lblProductrow);

             txt_economi = (TextView) convertView.findViewById(R.id.txt_economi);

             txttedad = (TextView) convertView.findViewById(R.id.txttedad);

             linearBargashtOne = (LinearLayout) convertView.findViewById(R.id.linearBargashtOne);
             linearBargashtTwo = (RelativeLayout) convertView.findViewById(R.id.linearBargashtTwo);
             linearBargashtTree = (LinearLayout) convertView.findViewById(R.id.linearBargashtTree);
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

}
