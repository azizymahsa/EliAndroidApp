package com.eligasht.reservation.views.adapters.pack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.model.pack.LstProwPrice;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.components.smoothcheckbox.SmoothCheckBox;
import com.eligasht.reservation.views.components.stickyheaders.Section;
import com.eligasht.reservation.views.components.stickyheaders.SectioningAdapter;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 * for list of price in end of ever pack
 */

public class LstProwPriceAdapter extends SectioningAdapter {

    private Context context;
    private ArrayList<Section> feedItemList;
    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        private TextView adaultPrice;
        private TextView wChildPrice;
        private TextView nChildPrice;
        private TextView infantPrice;
        private TextView totalPrice;
        public TextView total_price;
        private TextView txt_hr_room_list;
        private TextView txt_hr_room_list2;
        private SmoothCheckBox chk_prow_price;

        private ItemViewHolder(View itemView) {
            super(itemView);
            adaultPrice = itemView.findViewById(R.id.price_adault);
            wChildPrice = itemView.findViewById(R.id.price_w_child);
            nChildPrice = itemView.findViewById(R.id.price_n_child);
            infantPrice = itemView.findViewById(R.id.price_infant);
            totalPrice = itemView.findViewById(R.id.total_price);
            //    total_price = itemView.findViewById(R.id.total_price);
            txt_hr_room_list = itemView.findViewById(R.id.txt_hr_room_list);
            txt_hr_room_list2 = itemView.findViewById(R.id.txt_hr_room_list2);
            chk_prow_price = itemView.findViewById(R.id.chk_prow_price);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        TextView txt_title_header;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.txt_title_header = itemView.findViewById(R.id.txt_title_header);
        }
    }

    public LstProwPriceAdapter(Context context, ArrayList<Section> feedItemList) {
        this.context = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_lst_prow_price, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.header_lst_prow_price, parent, false);
        return new HeaderViewHolder(v);
    }


    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, final int sectionIndex, final int itemIndex, int itemUserType) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        final LstProwPrice item = (LstProwPrice) feedItemList.get(sectionIndex).getList().get(itemIndex);

        holder.chk_prow_price.setOnCheckedChangeListener(null);
        holder.chk_prow_price.setChecked(item.isChecked());
        holder.adaultPrice.setText(Utility.priceFormat(String.valueOf(item.getAdl())));

        if (item.getChdWBCount() == 0) {
            holder.wChildPrice.setText(R.string.Nothing);
        } else if (item.getChWb() == 0) {
            holder.wChildPrice.setText(R.string.Nothing);
        } else {
            holder.wChildPrice.setText(Utility.priceFormat(String.valueOf(item.getChWb())));
        }

        if (item.getChdNBCount()==0 ) {
            holder.nChildPrice.setText(R.string.Nothing);
        } else if (item.getChNb() == 0 ) {
            holder.nChildPrice.setText(R.string.Nothing);
        } else {
            holder.nChildPrice.setText(Utility.priceFormat(String.valueOf( item.getChNb() ) ));
        }

        if (item.getInfCount()  == 0) {
            holder.infantPrice.setText(R.string.Nothing);
        } else if (item.getInf()  == 0) {
            holder.infantPrice.setText(R.string.Nothing);
        } else {
            holder.infantPrice.setText(Utility.priceFormat(String.valueOf( item.getInf())));
        }

        holder.totalPrice.setText(Utility.priceFormat(String.valueOf(item.getSumPrice())));
        holder.txt_hr_room_list.setText(ValidationTools.isEmptyOrNull(item.getHRroomListF())?item.getHRroomList():item.getHRroomListF());
        holder.txt_hr_room_list2.setText(ValidationTools.isEmptyOrNull(item.getHRroomListF())?item.getHRroomList(): item.getHServiceF() + "(" + item.getHService() +")");

        holder.chk_prow_price.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                item.setChecked(isChecked);
                unchedOtherItem(feedItemList.get(sectionIndex).getList(),itemIndex);
                notifySectionDataSetChanged(sectionIndex);
            }
        });
    }

    private void unchedOtherItem(ArrayList<LstProwPrice> list, int itemIndex) {
        if(ValidationTools.isEmptyOrNull(list)){
            return;
        }

        for(int index = 0;index < list.size();index++){
            if(index != itemIndex){
                list.get(index).setChecked(false);
            }
        }
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        if(isCheckedAnyOne(feedItemList.get(sectionIndex).getList())){
            ((LstProwPrice) feedItemList.get(sectionIndex).getList().get(0)).setChecked(true);
        }

        try{
            int adlCount = ((LstProwPrice) feedItemList.get(sectionIndex).getList().get(0)).getAdlCount();
            int chCount = ((LstProwPrice) feedItemList.get(sectionIndex).getList().get(0)).getChdWBCount()
                    + ((LstProwPrice) feedItemList.get(sectionIndex).getList().get(0)).getChdNBCount();
            int infCount = ((LstProwPrice) feedItemList.get(sectionIndex).getList().get(0)).getInfCount();
            String countPassenger = "";
            if(adlCount != 0){
                countPassenger = adlCount  +" "+ context.getString(R.string.Adult) +" ";
            }
            if(chCount != 0){
                countPassenger = countPassenger + " + " + chCount  +" "+ context.getString(R.string.ChildPackage)+" ";
            }

            if(infCount != 0){
                countPassenger = countPassenger + " + " + infCount +" "+ context.getString(R.string.BabyPackage)+" ";
            }
            String title;
            if (Prefs.getString("lang","fa").equals("fa")){
                 title = context.getString(R.string.room) +" "+getStringPosition( Integer.parseInt(feedItemList.get(sectionIndex).getTitle())) + " : " + context.getString(R.string.OfferTo)  +" "+ countPassenger ;

            }else{
                title = getStringPosition( Integer.parseInt(feedItemList.get(sectionIndex).getTitle())) +" "+context.getString(R.string.room) + " : " + context.getString(R.string.OfferTo)  +" "+ countPassenger ;

            }

            holder.txt_title_header.setText(title);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getNumberOfSections() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return (null != feedItemList ? feedItemList.get(sectionIndex).getList().size() : 0);
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    private String getStringPosition(int position) {
        switch (position) {
            case 1:
                return context.getString(R.string.First);
            case 2:
                return context.getString(R.string.Second);
            case 3:
                return context.getString(R.string.Third);
            case 4:
                return context.getString(R.string.Fourth);
            case 5:
                return context.getString(R.string.Fifth);
            case 6:
                return context.getString(R.string.Sixth);
            case 7:
                return context.getString(R.string.Seventh);
            case 8:
                return context.getString(R.string.Eighth);
            case 9:
                return context.getString(R.string.ninth);

            default:
                return "";
        }
    }

    private boolean isCheckedAnyOne(ArrayList<LstProwPrice> lstProwPrices){
        if(ValidationTools.isEmptyOrNull(lstProwPrices)){
            return false;
        }

        for(LstProwPrice lstProwPrice : lstProwPrices){
            if(lstProwPrice.equals("true")){
                return false;
            }
        }
        return true;
    }
}
