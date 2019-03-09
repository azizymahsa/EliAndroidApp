package com.eligasht.reservation.views.adapters.train;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.eligasht.R;

import com.eligasht.reservation.models.train.adapter.SelectTrainModel;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.views.activities.train.SearchTrainActivity;
import com.wang.avi.AVLoadingIndicatorView;


import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Reza Nejati on 19,June,2018
 */
public class TrainResultAdapter extends RecyclerView.Adapter<TrainResultAdapter.ViewHolder> {
    private ArrayList<SelectTrainModel> data = new ArrayList<>();
    private Activity activity;
    Context context;
    TextView DateTime;
    RecyclerView recyclerView;
    boolean isGrid;

    public TrainResultAdapter(final ArrayList<SelectTrainModel> data, Activity activity, TextView DateTime, boolean isGrid) {
        this.data = data;
        this.activity = activity;
        this.DateTime = DateTime;
        this.isGrid = isGrid;

        Log.e("selectTrainModelList: ",data.size()+"" );
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
       /* if(isGrid){

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.recycler_view_train, parent, false));
        }else{*/

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.select_train_item, parent, false));

       // }



    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       String imageUri=createImgURL(position);
        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.imgTrainlineNameEn);
        holder.avi.setVisibility(View.GONE);

        holder.lblTrainNumber.setText(data.get(position).getTrainNumber()+" شماره قطار ");
        holder.txtTotalFare.setText(NumberFormat.getInstance().format(data.get(position).getTotalFare())+"");
        holder.txtDestinationText.setText(data.get(position).getDestinationText());
        holder.txtSourceText.setText(data.get(position).getSourceText());
        holder.txtTrainArrivalTime.setText(data.get(position).getTrainArrivalTime());
        holder.txtTrainTime.setText(data.get(position).getTrainTime());
        String count=seatRemaining(data.get(position).getSeatAvailable(),holder);
        //holder.txtSeatsRemaining.setText(" ظرفیت "+count+" بلیط ");
        
        holder.txtCompartmentCapacity.setText("کوپه ی"+data.get(position).getCompartmentCapacity()+"نفره");
        holder.txtSaloonName.setText(data.get(position).getSaloonName()+"");
        holder.txtNameTrain.setText(" قطار "+data.get(position).getTrainlineNameFa()+"");
        //holder.txtHotel.setText(data.get(position).getSaloonName()+"");
        if(data.get(position).getHotelIsIncluded())
        holder.txtHotel.setVisibility(View.VISIBLE);
        else
        holder.txtHotel.setVisibility(View.INVISIBLE);

        if(data.get(position).getHasMedia())
        holder.lblHasMedia.setVisibility(View.VISIBLE);
        else
        holder.lblHasMedia.setVisibility(View.GONE);

        if(data.get(position).getHasAirConditioning())
            holder.lblAirConditioning.setVisibility(View.VISIBLE);
        else
            holder.lblAirConditioning.setVisibility(View.GONE);


        holder.txt_select.setText(data.get(position).getTextButton());
        holder.txt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getDepartureSegment()) {
                    SearchTrainActivity.FLAG_SELECT_TRAIN=true;
                    SearchTrainActivity.updateSegmentTop(data.get(position),context,activity);
                }else{
                    SearchTrainActivity.FLAG_SELECT_TRAIN=false;
                    SearchTrainActivity.updateSegmentTop(data.get(position),context,activity);

                }


            }
        });


        /*switch (data.get(position).getStar()) {

            case 1:


                break;

        }*/

    }

    private String createImgURL(int position) {
        String imageUri="";
        String LogoName=data.get(position).getTrainlineNameEn();
        if (data.get(position).getTrainlineNameEn().equals("Raja"))
        {
            // LogoName=data.get(position).getTrainlineNameEn();
        } else if (data.get(position).getTrainlineNameEn().equals("Fadak")&& data.get(position).getSaloonName().contains("هتل"))
        {
            LogoName = LogoName + "-purple";
        }else if (data.get(position).getTrainlineNameEn().equals("Fadak") && data.get(position).getSaloonName().contains("بيزينس"))
        {
            LogoName = LogoName + "-blue";
        }else if (data.get(position).getTrainlineNameEn().equals("Fadak") && data.get(position).getSaloonName().contains("اکونومی"))
        {
            LogoName = LogoName + "-silver";
        }else if (data.get(position).getTrainlineNameEn().equals("Fadak")){
            LogoName = LogoName + "-gold";
        }

        return   "https://cdn.elicdn.com" +"/Content/Images/Train/TrainLine/"+LogoName +".png";//imgTrainlineNameEn
        //Log.d("imageUri:", "imageUri: "+imageUri);
    }

    private String seatRemaining(Integer count, ViewHolder holder) {
        String attributeStr ="";
        if (count > 20 ){
             attributeStr = "+20";
            holder.txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
            holder.txtSeatsRemaining.setTextColor(Color.GREEN);
           /* let range = NSRange(location: 0, length: attributeStr.string.count)
            attributeStr.addAttribute(.foregroundColor, value:  colorLiteral(red: 0.3411764706, green: 0.7176470588, blue: 0.4980392157, alpha: 1), range: range)
            attributeStr.addAttribute(.font, value: EligashtCommonAppearance.getFontBold(size: 11), range: range)*/
            return attributeStr;

        } else if (count > 5) {
             attributeStr = count.toString();
            holder.txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
            holder.txtSeatsRemaining.setTextColor(Color.BLUE);
           /* let range = NSRange(location: 0, length: attributeStr.string.count)
            attributeStr.addAttribute(.foregroundColor, value:  colorLiteral(red: 0.1725490196, green: 0.3254901961, blue: 0.5607843137, alpha: 1), range: range)
            attributeStr.addAttribute(.font, value: EligashtCommonAppearance.getFontBold(size: 11), range: range)*/
            return attributeStr;

        } else if (count > 0 ){
            attributeStr = count.toString();
            holder.txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
            holder.txtSeatsRemaining.setTextColor(Color.YELLOW);
            /*let range = NSRange(location: 0, length: attributeStr.string.count)
            attributeStr.addAttribute(.foregroundColor, value:  colorLiteral(red: 0.9137254902, green: 0.5960784314, blue: 0.2431372549, alpha: 1), range: range)
            attributeStr.addAttribute(.font, value: EligashtCommonAppearance.getFontBold(size: 11), range: range)*/
            return attributeStr;

        } else {
            attributeStr = " تکمیل";
            holder.txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" ");
            holder.txtSeatsRemaining.setTextColor(Color.RED);
            /*let range = NSRange(location: 0, length: attributeStr.string.count)
            attributeStr.addAttribute(.foregroundColor, value:  colorLiteral(red: 0.9294117647, green: 0.462745098, blue: 0.4941176471, alpha: 1), range: range)
            attributeStr.addAttribute(.font, value: EligashtCommonAppearance.getFontBold(size: 11), range: range)*/
            return attributeStr;
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTrainlineNameEn;
        TextView lblTrainNumber;
        TextView txt_select;
        TextView txtTotalFare;
        TextView txtDestinationText;
        TextView txtSourceText;
        TextView txtTrainArrivalTime;
        AVLoadingIndicatorView avi;
        TextView txtTrainTime,lblHasMedia,lblAirConditioning,txtSeatsRemaining,txtCompartmentCapacity,txtSaloonName,txtHotel,txtNameTrain;
        public ViewHolder(View v) {

            super(v);
            imgTrainlineNameEn = v.findViewById(R.id.imgTrainlineNameEn);
           // ivRate = v.findViewById(R.id.ivRate);



            //Train
             lblTrainNumber = v.findViewById(R.id.lblTrainNumber);
            txt_select = v.findViewById(R.id.txt_select);
            txtTotalFare = v.findViewById(R.id.txtTotalFare);
            txtDestinationText = v.findViewById(R.id.txtDestinationText);
            txtSourceText = v.findViewById(R.id.txtSourceText);
            txtTrainArrivalTime = v.findViewById(R.id.txtTrainArrivalTime);
            txtTrainTime = v.findViewById(R.id.txtTrainTime);
            lblHasMedia = v.findViewById(R.id.lblHasMedia);
            lblAirConditioning = v.findViewById(R.id.lblAirConditioning);
            txtSeatsRemaining = v.findViewById(R.id.txtSeatsRemaining);
            txtCompartmentCapacity = v.findViewById(R.id.txtCompartmentCapacity);
            txtSaloonName = v.findViewById(R.id.txtSaloonName);
            txtHotel = v.findViewById(R.id.txtHotel);
            txtNameTrain = v.findViewById(R.id.txtNameTrain);
            avi = v.findViewById(R.id.avi);
        }
    }
}
