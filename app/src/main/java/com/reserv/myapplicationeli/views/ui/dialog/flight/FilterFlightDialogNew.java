package com.reserv.myapplicationeli.views.ui.dialog.flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelCheckBox;
import com.reserv.myapplicationeli.views.adapters.hotel.FilterAdapter;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class FilterFlightDialogNew implements View.OnClickListener , SmoothCheckBox.OnCheckedChangeListener{
    android.app.AlertDialog dialog;

    public ArrayList<ModelCheckBox> arrayTrue=new ArrayList<>();
    private ArrayList<ModelCheckBox> modelCheckBoxes = new ArrayList<>();

    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel,btnDeletFilter;
    SmoothCheckBox noStop, oneStop,twoStopMore,economiF, businessF, ferstF;
    TextView txtTavaghof;
    // FilterFlightDialogListenerNew filterFlightDialogListenerNew;
    FilterFlightDialogListenerArray filterFlightDialogListenerArray;
    SmoothCheckBox remove, hotel, boutique, apartment, resort;
    ArrayList<FilterModelّFlight> filter;
    boolean noStop_;
    boolean twoStopMore_;
    boolean oneStop_;

    boolean economiF_;
    boolean businessF_;
    boolean ferstF_;

    boolean remove_;

    ListView lv;
     FilterAdapter adapter;
    public ArrayList<ModelCheckBox> modelItems=new ArrayList<>();


    public FilterFlightDialogNew(final Context activity, ArrayList<FilterModelّFlight> filter, FilterFlightDialogListenerArray filterFlightDialogListenerArray, ArrayList<ModelCheckBox> filterAirlines) {
        this.activity = activity;
        this.filter = filter;
        this.modelCheckBoxes = filterAirlines;
        // this.filterFlightDialogListenerNew = filterFlightDialogListener;
        this.filterFlightDialogListenerArray = filterFlightDialogListenerArray;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_flight_dialog, null);
        //  filter.add(new FilterModelّFlight(false, false, false, false, false, false, false));

        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        btnDeletFilter = (FancyButton) dialogView.findViewById(R.id.btnDeletFilter);
        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnDeletFilter.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        btnDeletFilter.setOnClickListener(this);

        noStop = (SmoothCheckBox) dialogView.findViewById(R.id.noStop);
        oneStop = (SmoothCheckBox) dialogView.findViewById(R.id.oneStop);
        twoStopMore = (SmoothCheckBox) dialogView.findViewById(R.id.twoStopMore);

        remove = (SmoothCheckBox) dialogView.findViewById(R.id.Remove);

        economiF = (SmoothCheckBox) dialogView.findViewById(R.id.economiF);
        businessF = (SmoothCheckBox) dialogView.findViewById(R.id.businessF);
        ferstF = (SmoothCheckBox) dialogView.findViewById(R.id.ferstF);


        boutique = (SmoothCheckBox) dialogView.findViewById(R.id.boutique);
        resort = (SmoothCheckBox) dialogView.findViewById(R.id.resort);
        txtTavaghof = (TextView) dialogView.findViewById(R.id.txtTavaghof);


        lv = (ListView)  dialogView.findViewById(R.id.listView1);
       /* modelItems = new ModelCheckBox[5];
        modelItems[0] = new ModelCheckBox("pizza", 0);
        modelItems[1] = new ModelCheckBox("burger", 1);
        modelItems[2] = new ModelCheckBox("olives", 1);
        modelItems[3] = new ModelCheckBox("orange", 0);
        modelItems[4] = new ModelCheckBox("tomato", 1);*/
        //modelItems = new ModelCheckBox[filterAirlines.size()];

        noStop.setOnCheckedChangeListener(this);
        oneStop.setOnCheckedChangeListener(this);
        twoStopMore.setOnCheckedChangeListener(this);
        economiF.setOnCheckedChangeListener(this);
        businessF.setOnCheckedChangeListener(this);
        ferstF.setOnCheckedChangeListener(this);
        remove.setOnCheckedChangeListener(this);






          adapter = new FilterAdapter(activity,modelCheckBoxes);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (modelCheckBoxes.get(position).isCheck()){


                    modelCheckBoxes.set(position,new ModelCheckBox(modelCheckBoxes.get(position).getName(),false));

                }else{
                    modelCheckBoxes.set(position,new ModelCheckBox(modelCheckBoxes.get(position).getName(),true));

                }
                adapter.notifyDataSetChanged();

            }
        });


        dialog = builder.create();
        dialog.setCancelable(true);

        ////
      /*  CitySpinnerAdapter citySpinnerAdapter = new CitySpinnerAdapter(this, android.R.layout.simple_spinner_item, response.body().getGetHotelListResult().getCities());
        spn_cities.setAdapter(citySpinnerAdapter);*/
        ////////
        for (FilterModelّFlight filterModel : filter) {
            if (filterModel.isNoStop()) {
                noStop.setChecked(true);
                noStop_ = true;

            } else {
                noStop_ = false;
                noStop.setChecked(false);
            }
            if (filterModel.isOneStop()) {
                oneStop.setChecked(true);
                oneStop_ = true;

            } else {
                oneStop_ = false;
                oneStop.setChecked(false);
            }
            if (filterModel.isTwoStopMore()) {
                twoStopMore.setChecked(true);
                twoStopMore_ = true;

            } else {
                twoStopMore_ = false;
                twoStopMore.setChecked(false);
            }

            if (filterModel.isEconomiF()) {
                economiF.setChecked(true);
                economiF_ = true;

            } else {
                economiF_ = false;
                economiF.setChecked(false);
            }//
            if (filterModel.isBusinessF()) {
                businessF.setChecked(true);
                businessF_ = true;

            } else {
                businessF_ = false;
                businessF.setChecked(false);

            }
            if (filterModel.isFerstF()) {
                ferstF.setChecked(true);
                ferstF_ = true;

            } else {
                ferstF_ = false;
                ferstF.setChecked(false);
            }
            //========
            if (filterModel.isRemove()) {
               remove.setChecked(true);
                remove_ = true;


            } else {
                remove.setChecked(false);
                remove_ = false;

            }
        }//end for
        dialog.show();
    }


    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));
                // arrayTrue

                if (noStop.isChecked()) {
                    noStop_ = true;


                } else{
                    noStop_ = false;


                }
                if (oneStop.isChecked()) {
                    oneStop_ = true;

                }else{
                    oneStop_=false;
                }
                if (twoStopMore.isChecked()) {
                    twoStopMore_ = true;


                }else{
                    twoStopMore_=false;
                }

                if (economiF.isChecked()) {
                    economiF_ = true;
                }else{
                    economiF_=false;
                }

                if (businessF.isChecked()) {
                    businessF_ = true;


                }else{
                    businessF_=false;
                }
                if (ferstF.isChecked()) {
                    ferstF_ = true;
                }else{
                    ferstF_=false;
                }
                //========
                if (remove.isChecked()) {
                    if (filter.isEmpty()) {
                        filter.add(new FilterModelّFlight(false,false,false,remove_,false,false,false));
                    } else {
                        filter.set(0, new FilterModelّFlight(false,false,false,true,false,false,false));
                    }
                } else {

                    if (filter.isEmpty()) {
                        filter.add(new FilterModelّFlight(noStop_,oneStop_,twoStopMore_,false,economiF_,businessF_,ferstF_));

                    } else {
                        filter.set(0, new FilterModelّFlight(noStop_,oneStop_,twoStopMore_,false,economiF_,businessF_,ferstF_));

                    }
                }



                //  }


                filterFlightDialogListenerArray.onReturnValueFlightNew(filter,modelCheckBoxes);


                dialog.cancel();

                break;
            case R.id.btnDeletFilter:
                if (filter.isEmpty()) {
                    filter.add(new FilterModelّFlight(false,false,false,true,false,false,false));
                } else {
                    noStop.setChecked(false);
                    oneStop.setChecked(false);
                    twoStopMore.setChecked(false);
                    ferstF.setChecked(false);
                    businessF.setChecked(false);
                    economiF.setChecked(false);
                    //star1.setChecked(false);
                    for (int i =0;i<modelCheckBoxes.size();i++){

                        modelCheckBoxes.set(i,new ModelCheckBox(modelCheckBoxes.get(i).getName(),false));

                    }
                    adapter.notifyDataSetChanged();

                    filter.set(0, new FilterModelّFlight(false,false,false,true,false,false,false));
                }
                filterFlightDialogListenerArray.onReturnValueFlightNew(filter,modelCheckBoxes);


                dialog.cancel();
                break;
        }
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        switch (checkBox.getId()) {



            case R.id.Remove:
                if (isChecked) {

                    noStop.setChecked(false);
                    oneStop.setChecked(false);
                    twoStopMore.setChecked(false);
                    ferstF.setChecked(false);
                    businessF.setChecked(false);
                    economiF.setChecked(false);
                    //star1.setChecked(false);
                    for (int i =0;i<modelCheckBoxes.size();i++){

                        modelCheckBoxes.set(i,new ModelCheckBox(modelCheckBoxes.get(i).getName(),false));

                    }
                    adapter.notifyDataSetChanged();


                }
                break;
                case R.id.noStop:
                if (isChecked) {
                    remove.setChecked(false);



                }
                break;
                case R.id.oneStop:
                if (isChecked) {

                    remove.setChecked(false);


                }
                break;
                case R.id.twoStopMore:
                if (isChecked) {
                    remove.setChecked(false);



                }
                break;
            case R.id.economiF:
                if (isChecked) {

                    remove.setChecked(false);


                }
                break;
            case R.id.businessF:
                if (isChecked) {
                    remove.setChecked(false);



                }
                break;
            case R.id.ferstF:
                if (isChecked) {

                    remove.setChecked(false);


                }
                break;
        }
    }


    public interface FilterFlightDialogListenerArray {
        public void onReturnValueFlightNew(ArrayList<FilterModelّFlight> type, ArrayList<ModelCheckBox> arrayTrue);
    }

}

