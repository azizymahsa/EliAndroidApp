package com.eligasht.reservation.views.activities.pack.component.passenger.passtwo;

import android.animation.ObjectAnimator;
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
import com.eligasht.reservation.views.activities.pack.component.passenger.PassEntity;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

public class RcyclPassTwoAdapter extends RecyclerView.Adapter<RcyclPassTwoAdapter.ViewHolder> {

    private List<PassEntity> mDataset;
    private String tvTitle;

    private SparseBooleanArray expandState = new SparseBooleanArray();

    RetItemPassTwo _RetItemPassOne=new RetItemPassTwo();


    public RcyclPassTwoAdapter(List<PassEntity> myDataset) {
        mDataset = myDataset;
        for (int i = 0; i < myDataset.size(); i++) {
            expandState.append(i, false);
        }
    }
    public RcyclPassTwoAdapter(String tvTitle) {
        this.tvTitle = tvTitle;

    }

    @Override
    public RcyclPassTwoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        Log.v("test-recyclerview", "onCreateViewHolder");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rcycl_pass, parent, false);

        ViewHolder vh = new ViewHolder(v);
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
                //after the change calling the method and passing the search input
               // filter(editable.toString());
                //_RetItemPassOne.get(position)._itemName = editable.toString();
                _RetItemPassOne.RqPassenger_FirstNameEn = editable.toString();
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
        public ExpandableLinearLayout expandableLayout;
        public TextView tvArrow;
        public TextView tvTitleRcycl;
        RelativeLayout buttonLayout;

        public ViewHolder(View v) {
            super(v);
            txtnamem = (EditText) v.findViewById(R.id.txtnamem);
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
    public RetItemPassTwo get_RetItemPassOne(){
        return _RetItemPassOne;
    }
}
