package com.eligasht.reservation.views.activities.pack.component.passenger.passOne;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.activities.pack.PassengerPackageActivity;
import com.eligasht.reservation.views.activities.pack.component.passenger.PassEntity;
import com.eligasht.reservation.views.ui.CountrycodeActivity;
import com.eligasht.reservation.views.ui.NationalitycodeActivity;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.util.List;
import java.util.Locale;

public class RcyclPassOneAdapter extends RecyclerView.Adapter<RcyclPassOneAdapter.ViewHolder> {

    private  String txttavalodm;
    private FragmentManager fragmentManager = null;
    private List<PassEntity> mDataset;
    private String tvTitle;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    RetItemPassOne _RetItemPassOne=new RetItemPassOne();
    DatePickerDialog datePickerDialogGregorian1;
    DatePickerDialog datePickerDialogGregorian2;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;



    public RcyclPassOneAdapter(List<PassEntity> myDataset) {
        mDataset = myDataset;
        for (int i = 0; i < myDataset.size(); i++) {
            expandState.append(i, false);
        }
    }
    public RcyclPassOneAdapter(String txttavalodm,String tvTitle, Context context, DatePickerDialog datePickerDialogGregorian1, DatePickerDialog datePickerDialogGregorian2, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog, FragmentManager fragmentManager) {
        this.tvTitle = tvTitle;
        this.context=context;
        this.datePickerDialog=datePickerDialog;
        this.datePickerDialogGregorian1=datePickerDialogGregorian1;
        this.datePickerDialogGregorian2=datePickerDialogGregorian2;
        this.fragmentManager=fragmentManager;
        this.txttavalodm=txttavalodm;
        notifyDataSetChanged();

    }

    /*public updateItem(String txttavalodm) {
        this.txttavalodm=txttavalodm;
        notifyDataSetChanged();
    }*/

    @Override
    public RcyclPassOneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        Log.v("test-recyclerview", "onCreateViewHolder");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rcycl_pass, parent, false);

        ViewHolder vh = new ViewHolder(v);
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
       // holder.txtnamem.setText(mDataset.get(position).getTextToDisplay());
        holder.tvTitleRcycl.setText(tvTitle+"");
        holder.txtnamem.setText( ((_RetItemPassOne == null) ? "" : _RetItemPassOne.RqPassenger_FirstNameEn));
        holder.txtnamem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               _RetItemPassOne.RqPassenger_FirstNameEn = editable.toString();
            }
        });
        holder.txtfamilym.setText( ((_RetItemPassOne == null) ? "" : _RetItemPassOne.RqPassenger_LastNameEn));
        holder.txtfamilym.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                _RetItemPassOne.RqPassenger_LastNameEn = editable.toString();
            }
        });
        holder.txtnumber_passport.setText( ((_RetItemPassOne == null) ? "" : _RetItemPassOne.RqPassenger_PassNo));
        holder.txtnumber_passport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                _RetItemPassOne.RqPassenger_PassNo = editable.toString();
            }
        });
        holder.txt_NationalCode_m.setText( ((_RetItemPassOne == null) ? "" : _RetItemPassOne.RqPassenger_NationalCode));
        holder.txt_NationalCode_m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                _RetItemPassOne.RqPassenger_NationalCode = editable.toString();
            }
        });

        holder.txttavalodm.setText(Prefs.getString("itemTavalod", ""));
        holder.txttavalodm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PassengerPackageActivity.updatetxttavalodm(tvTitle+"",datePickerDialog,datePickerDialogGregorian1,context,fragmentManager);
            }
        });
        holder.txtexp_passport.setText(Prefs.getString("txtexp_passport", ""));
        holder.txtexp_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  PassengerPackageActivity.updatetxtexp_passport(datePickerDialogGregorian2,fragmentManager);

            }
        });
        holder.txtmeliyatm.setText(NationalitycodeActivity.RESULT_NATIONALITYCODE+"");
        holder.txtmeliyatm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent4 = new Intent(context, NationalitycodeActivity.class);
               // myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent4);
            }
        });
       // holder.txtmahale_eghamat.setText( data.getStringExtra(CountrycodeActivity.RESULT_CONTRYCODE));//RESULT_CONTRYNAME
        holder.txtmahale_eghamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.expandableLayout.setInRecyclerView(true);
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.tvArrow, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.tvArrow, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

        holder.tvArrow.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EditText txtnamem;
        public EditText txtfamilym;
        public EditText txtnumber_passport;
        public EditText txt_NationalCode_m;

        public TextView txttavalodm;
        public TextView txtexp_passport;
        public TextView txtmeliyatm;
        public TextView txtmahale_eghamat;

        public ExpandableLinearLayout expandableLayout;
        public TextView tvArrow;
        public TextView tvTitleRcycl;
        RelativeLayout buttonLayout;

        public ViewHolder(View v) {
            super(v);
            txtnamem = (EditText) v.findViewById(R.id.txtnamem);
            txtfamilym = (EditText) v.findViewById(R.id.txtfamilym);
            txtnumber_passport = (EditText) v.findViewById(R.id.txtnumber_passport);
            txt_NationalCode_m = (EditText) v.findViewById(R.id.txt_NationalCode_m);

            txttavalodm = (TextView) v.findViewById(R.id.txttavalodm);
            txtexp_passport = (TextView) v.findViewById(R.id.txtexp_passport);
            txtmeliyatm = (TextView) v.findViewById(R.id.txtmeliyatm);
            txtmahale_eghamat = (TextView) v.findViewById(R.id.txtmahale_eghamat);

            expandableLayout = v.findViewById(R.id.expandableLayout);
            buttonLayout = v.findViewById(R.id.buttonLayout);
            tvArrow = v.findViewById(R.id.tvArrow);
            tvTitleRcycl = v.findViewById(R.id.tvTitleRcycl);
        }
    }
    @Override
    public int getItemCount() {
        return 1;
    }
    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }
    public RetItemPassOne get_RetItemPassOne(){
        return _RetItemPassOne;
    }


}
