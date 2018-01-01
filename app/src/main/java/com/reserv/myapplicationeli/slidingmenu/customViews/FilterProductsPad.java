package com.reserv.myapplicationeli.slidingmenu.customViews;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.db.local.Products_Table;
import com.reserv.myapplicationeli.slidingmenu.db.main.CursorManager;
import com.reserv.myapplicationeli.slidingmenu.tools.Utility;

public class FilterProductsPad extends LinearLayout implements OnCheckedChangeListener {
	private View visiblePane;
	private LinearLayout list;
	private CheckBox credit, award, noStock, limiteds, producers;
	private RadioButton creditL1, creditL2, creditL3;
	private ArrayList<String> filters = new ArrayList<String>();
	private FiltersChangedListener listener;
	private CheckBox[] checks;

	public FilterProductsPad(Context context, AttributeSet attrs,int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public FilterProductsPad(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FilterProductsPad(Context context) {
		super(context);
		init();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			if (!Utility.isPointInsideView(event.getX(), event.getY(),visiblePane)) {
				dismiss();
			}
		return true;
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.panel_filter_products, this);
		try {
			Utility.applyFonts(findViewById(R.id.alert));
			list = (LinearLayout) findViewById(R.id.producersList);
			visiblePane = findViewById(R.id.visiblePane);
			credit = (CheckBox) findViewById(R.id.credit);
			award = (CheckBox) findViewById(R.id.award);
			noStock = (CheckBox) findViewById(R.id.noStock);
			limiteds = (CheckBox) findViewById(R.id.limiteds);
			producers = (CheckBox) findViewById(R.id.producers);
			creditL1 = (RadioButton) findViewById(R.id.creditL1);
			creditL2 = (RadioButton) findViewById(R.id.creditL2);
			creditL3 = (RadioButton) findViewById(R.id.creditL3);

			credit.setOnCheckedChangeListener(this);
			award.setOnCheckedChangeListener(this);
			noStock.setOnCheckedChangeListener(this);
			limiteds.setOnCheckedChangeListener(this);
			producers.setOnCheckedChangeListener(this);
			creditL1.setOnCheckedChangeListener(this);
			creditL2.setOnCheckedChangeListener(this);
			creditL3.setOnCheckedChangeListener(this);
			Products_Table db = new Products_Table();
			CursorManager c = db.getProducers();
			checks = new CheckBox[c.getCount()];
			/*for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				View v = GlobalApplication.getActivity().getLayoutInflater().inflate(R.layout.check_box, null);
				checks[i] = (CheckBox) v;
				checks[i].setText(String.format("%s (%s قلم کالا)",
						c.getString(Products_Table.Columns.GName.value()),
						c.getString("COUNT")));
				list.addView(v);
				checks[i].setOnCheckedChangeListener(this);
			}*/
		} catch (Exception e) {
		}
	}

	public void dismiss() {
		setVisibility(View.GONE);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView instanceof CheckBox
				|| (buttonView instanceof RadioButton && isChecked))
			regenerateFilters(buttonView);
		Log.e("", buttonView.getId() + " " + isChecked);
		if (filters != null)
			listener.filtersChanged();
	}

	private void regenerateFilters(CompoundButton buttonView) {
		filters.clear();
		if (credit.isChecked()) {
			
		}
		
		if (noStock.isChecked())
			filters.add(String.format("P.%s > 0",Products_Table.Columns.LogicalAmount));
		if (limiteds.isChecked())
			filters.add(String.format("(P.%s > 0 OR P.%s = 0)",Products_Table.Columns.LimitCount,Products_Table.Columns.LimitCount));
		if (producers.isChecked()) {
			String condition = "";
			for (int i = 0; i < checks.length; i++) {
				if (checks[i].isChecked())
					condition += String.format(
							"P.%s='%s' OR ",
							Products_Table.Columns.GName.value(),
							checks[i]
									.getText()
									.toString()
									.substring(
											0,
											checks[i].getText().toString().indexOf('(') - 1));
			}
			if (condition.length() > 0) {
				condition = "( "
						+ condition.substring(0, condition.length() - 3) + " )";
				filters.add(condition);
			}
		}
	}

	public ArrayList<String> getFilters() {
		return filters;
	}

	public interface FiltersChangedListener {
		public void filtersChanged();
	}

	public void setOnFilterChangedListener(FiltersChangedListener listener) {
		this.listener = listener;
	}
}
