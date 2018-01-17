package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.LstProwPrice;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.tools.StreamList;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.components.stickyheaders.Section;
import com.reserv.myapplicationeli.views.viewholders.PRowXferRowHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PRowXferAdapter extends RecyclerView.Adapter<PRowXferRowHolder> {

    private Context context;
    private ArrayList<PRowXfer> feedItemList;
    private ListenerSearchPackAdapter listenerPackAdapter;


    public PRowXferAdapter(Context context, ArrayList<PRowXfer> NameItem) {

        this.context = context;
        this.feedItemList = NameItem;
    }

    public interface ListenerSearchPackAdapter {
        void onClickPackageBookingItem(PRowXfer pack);
    }

    public PRowXferAdapter setListener(ListenerSearchPackAdapter listener) {
        this.listenerPackAdapter = listener;
        return this;
    }

    @Override
    public PRowXferRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_pack_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PRowXferRowHolder(view);
    }

    @Override
    public void onBindViewHolder(PRowXferRowHolder holder, final int position) {
        final PRowXfer item = feedItemList.get(position);


        if(item.getLstProwPriceAdapter() == null){
            List<String> list = StreamList.convertAll(item.getLstProwPrices(), new StreamList.Function<LstProwPrice, String>() {
                @Override
                public String apply(LstProwPrice lstProwPrice) {
                    return String.valueOf(lstProwPrice.getRoomNo());
                }
            });
            ArrayList<Section> sections = new ArrayList<>();
            for (String header : list) {
                Section section = new Section();
                section.setTitle(header);

                for (LstProwPrice lstProwPrice : item.getLstProwPrices()) {
                    if (header.equals(String.valueOf(lstProwPrice.getRoomNo()))) {
                        section.setList(lstProwPrice);
                    }
                }
                if (!ValidationTools.isEmptyOrNull(section.getList())) {
                    sections.add(section);
                }
            }
            item.setLstProwPriceAdapter(new LstProwPriceAdapter(context, sections));
        }

        if(item.getLstProwHotelAdapter() == null){
            item.setLstProwHotelAdapter(new LstProwHotelAdapter(context, feedItemList.get(position).getLstProwHotels()));
        }


        holder.rcl_price.showList(item.getLstProwPriceAdapter());

        holder.rcl_hotels.showList(item.getLstProwHotelAdapter());

        holder.txt_depart_time.setText(item.getXferList().getXFlightsList().get(0).getFltLocalTime());
        holder.txt_return_time.setText(item.getXferList().getXFlightsList().get(1).getFltLocalTime());
        holder.txt_airline.setText(item.getXferList().getXFlightsList().get(0).getAirlineEnglishName());
        holder.txt_economi.setText(item.getXferList().getXFlightsList().get(0).getSeatClassNameFa());
        if (item.getXferList().getXFlightsList().get(0).getAvailable().equals("Available") || item.getXferList().getXFlightsList().get(0).getAvailable().equals("")) {

        } else {
            holder.txt_count.setText(item.getXferList().getXFlightsList().get(0).getAvailable());
        }
        holder.txt_depart_air.setText(" رفت به " + (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getArrivalCityName()) ? item.getXferList().getXFlightsList().get(0).getArrivalCityName() : item.getXferList().getXFlightsList().get(0).getArrivalCityName()));
        holder.txt_return_air.setText(" برگشت به " + (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(1).getArrivalCityName()) ? item.getXferList().getXFlightsList().get(1).getArrivalCityName() : item.getXferList().getXFlightsList().get(1).getArrivalCityName()));
        //holder.date_arrive.setText(ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getShamsi())? item.getXferList().getXFlightsList().get(0).getShamsi() : item.getXferList().getXFlightsList().get(0).getShamsi());
        //holder.date_arrive.setText(ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(1).getShamsi())? item.getXferList().getXFlightsList().get(1).getShamsi() : item.getXferList().getXFlightsList().get(1).getShamsi());

        Glide.with(context)
                .load("http://www.eligasht.com/Content/AirLine/" + item.getXferList().getXFlightsList().get(0).getAirlineCode() + ".png")
                .into(holder.img_airLine);

        holder.btn_package_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerPackAdapter != null) {
                    listenerPackAdapter.onClickPackageBookingItem(feedItemList.get(position));
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }
}



