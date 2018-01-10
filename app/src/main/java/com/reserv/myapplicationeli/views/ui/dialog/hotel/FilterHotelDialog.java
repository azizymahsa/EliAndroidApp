package com.reserv.myapplicationeli.views.ui.dialog.hotel;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class FilterHotelDialog implements View.OnClickListener, SmoothCheckBox.OnCheckedChangeListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    FilterHotelDialogListenerArray filterHotelDialogListenerArray;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;
    ArrayList<FilterModel> filter;
    boolean star1_;
    boolean star2_;
    boolean star3_;
    boolean star4_;
    boolean star5_;
    boolean bestSeler_;
    boolean bestOff_;
    boolean resort_;
    boolean boutique_;
    boolean apartment_;
    boolean hotel_;
    boolean remove_;

    public FilterHotelDialog(final Context activity, ArrayList<FilterModel> filter, FilterHotelDialogListenerArray filterHotelDialogListenerArray) {
        this.activity = activity;
        this.filter = filter;
        this.filterHotelDialogListenerArray = filterHotelDialogListenerArray;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_dialog, null);
        // filter.add(new FilterModel(false, false, false, false, false, false, false, false, false, false, false));

        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        bestSeler = (SmoothCheckBox) dialogView.findViewById(R.id.bestSeler);
        bestOff = (SmoothCheckBox) dialogView.findViewById(R.id.bestOff);
        Remove = (SmoothCheckBox) dialogView.findViewById(R.id.Remove);
        star1 = (SmoothCheckBox) dialogView.findViewById(R.id.star1);
        star2 = (SmoothCheckBox) dialogView.findViewById(R.id.star2);
        star3 = (SmoothCheckBox) dialogView.findViewById(R.id.star3);
        star4 = (SmoothCheckBox) dialogView.findViewById(R.id.star4);
        star5 = (SmoothCheckBox) dialogView.findViewById(R.id.star5);

        hotel = (SmoothCheckBox) dialogView.findViewById(R.id.hotel);
        boutique = (SmoothCheckBox) dialogView.findViewById(R.id.boutique);
        apartment = (SmoothCheckBox) dialogView.findViewById(R.id.apartment);
        resort = (SmoothCheckBox) dialogView.findViewById(R.id.resort);

        btnOk.setCustomTextFont("irsans.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);

        for (FilterModel filterModel : filter) {
            if (filterModel.isStar1()) {
                star1.setChecked(true);
                star1_ = true;

            } else {
                star1_ = false;
                star1.setChecked(false);

            }
            //========


            if (filterModel.isStar2()) {
                star2.setChecked(true);
                star2_ = true;

            } else {
                star2_ = false;
                star2.setChecked(false);

            }

            //========

            if (filterModel.isStar3()) {
                star3.setChecked(true);
                star3_ = true;


            } else {
                star3_ = false;

                star3.setChecked(false);

            }
            //========


            if (filterModel.isStar4()) {
                star4.setChecked(true);
                star4_ = true;


            } else {
                star4_ = false;
                star4.setChecked(false);

            }
            //========

            if (filterModel.isStar5()) {
                star5.setChecked(true);
                star5_ = true;


            } else {
                star5_ = false;
                star5.setChecked(false);

            }
            //========

            if (filterModel.isBestOff()) {
                bestOff.setChecked(true);
                bestOff_ = true;


            } else {
                bestOff_ = false;
                bestOff.setChecked(false);

            }
            //========

            if (filterModel.isBestSeler()) {
                bestSeler.setChecked(true);
                bestSeler_ = true;


            } else {
                bestSeler_ = false;
                bestSeler.setChecked(false);

            }

            //========

            if (filterModel.isResort()) {
                resort.setChecked(true);
                resort_ = true;


            } else {
                resort_ = false;
                resort.setChecked(false);

            }
            //========

            if (filterModel.isHotel()) {
                hotel.setChecked(true);
                hotel_ = true;

            } else {
                hotel.setChecked(false);

                hotel_ = false;
            }
            //========

            if (filterModel.isBoutique()) {
                boutique.setChecked(true);
                boutique_ = true;


            } else {
                boutique.setChecked(false);

                boutique_ = false;
            }
            //========


            if (filterModel.isApartment()) {
                apartment.setChecked(true);
                apartment_ = true;


            } else {
                apartment_ = false;
                apartment.setChecked(false);

            }
            //========
            if (filterModel.isRemove()) {
                Remove.setChecked(true);
                remove_ = true;


            } else {
                Remove.setChecked(false);
                remove_ = false;

            }
        }
        Remove.setOnCheckedChangeListener(this);


        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));


                if (star1.isChecked()) {
                    star1_ = true;
                } else {
                    star1_ = false;
                }

                //========
                if (star2.isChecked()) {
                    star2_ = true;

                } else {
                    star2_ = false;
                }


                //========

                if (star3.isChecked()) {
                    star3_ = true;


                } else {
                    star3_ = false;
                }


                //========


                if (star4.isChecked()) {
                    star4_ = true;
                } else {
                    star4_ = false;
                }

                //========

                if (star5.isChecked()) {
                    star5_ = true;
                } else {
                    star5_ = false;
                }


                //========

                if (bestSeler.isChecked()) {
                    bestSeler_ = true;
                } else {
                    bestSeler_ = false;
                }
                //========

                if (bestOff.isChecked()) {
                    bestOff_ = true;
                } else {
                    bestOff_ = false;
                }


                //========

                if (apartment.isChecked()) {
                    apartment_ = true;
                } else {
                    apartment_ = false;
                }

                //========

                if (resort.isChecked()) {
                    resort_ = true;
                } else {
                    resort_ = false;
                }

                //========

                if (hotel.isChecked()) {
                    hotel_ = true;
                } else {
                    hotel_ = false;
                }
                //========

                if (boutique.isChecked()) {
                    boutique_ = true;
                } else {
                    boutique_ = false;
                }

                //========
                if (Remove.isChecked()) {
                    if (filter.isEmpty()) {
                        filter.add(new FilterModel(false, false, false, false, false, false, bestOff_, false, false, false, hotel_, remove_));

                    } else {
                        filter.set(0, new FilterModel(false, false, false, star4_, false, bestSeler_, false, false, boutique_, false, hotel_, true));

                    }
                } else {
                    if (filter.isEmpty()) {
                        filter.add(new FilterModel(star1_, star2_, star3_, star4_, star5_, bestSeler_, bestOff_, resort_, boutique_, apartment_, hotel_, false));

                    } else {
                        filter.set(0, new FilterModel(star1_, star2_, star3_, star4_, star5_, bestSeler_, bestOff_, resort_, boutique_, apartment_, hotel_, false));

                    }
                }




                filterHotelDialogListenerArray.onReturnValue(filter);


                dialog.cancel();


                break;

        }
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {

        switch (checkBox.getId()) {
            case R.id.Remove:
                if (isChecked) {
                    apartment.setChecked(false);
                    boutique.setChecked(false);
                    hotel.setChecked(false);
                    resort.setChecked(false);
                    bestSeler.setChecked(false);
                    bestOff.setChecked(false);
                    star5.setChecked(false);
                    star4.setChecked(false);
                    star3.setChecked(false);
                    star2.setChecked(false);
                    star1.setChecked(false);


                }
                break;


            case R.id.bestSeler:
                if (isChecked) {
                    apartment.setChecked(false);
                }
                break;
            case R.id.bestOff:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.star2:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.star3:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.star4:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.star5:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.hotel:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;
            case R.id.boutique:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;

            case R.id.apartment:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;

            case R.id.resort:
                if (isChecked) {
                    apartment.setChecked(false);

                }
                break;


        }

    }


    public interface FilterHotelDialogListenerArray {
        public void onReturnValue(ArrayList<FilterModel> type);
    }

}

