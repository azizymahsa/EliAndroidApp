package com.eligasht.reservation.views.components;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eligasht.reservation.R;
import com.eligasht.reservation.tools.Utility;

public class Header extends LinearLayout implements OnClickListener,
		TextWatcher {
	private EditText searchText;
	private onSearchTextChangedListener listener;
	private View menu;
	private View filterView;
	private ImageView filterIcon;

	public Header(Context context) {
		super(context);
		initView();
	}

	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.header, this);
		try {
			menu = findViewById(R.id.menu);
			if (menu != null)
				menu.setOnClickListener(this);
			searchText = (EditText) findViewById(R.id.searchText);
			searchText.addTextChangedListener(this);
			Utility.hideKeyboard(getContext(), searchText);
			filterIcon = (ImageView) findViewById(R.id.filter);
			filterIcon.setOnClickListener(this);
			findViewById(R.id.clear).setOnClickListener(this);
		} catch (Exception e) {
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
		//	MainMenuDialog dialog = new MainMenuDialog(context);
			//dialog.show();
			break;
		case R.id.filter:
			if (filterView != null)
				filterView.setVisibility(View.VISIBLE);
			break;
		case R.id.clear:

			/*
			 * String d = searchText.getText().toString(); adapter.setData(d);
			 * adapter.refreshData(false);
			 */
			searchText.setText("");
			break;
		}
	}

	public void setSearchEnable(boolean enable) {
		if (enable) {
			findViewById(R.id.searchPanel).setVisibility(View.VISIBLE);
			findViewById(R.id.searchText).setVisibility(View.GONE);
			findViewById(R.id.clear).setVisibility(View.GONE);
		} else {
			findViewById(R.id.searchPanel).setVisibility(View.GONE);
			Utility.hideKeyboard(getContext(), searchText);
			searchText.setText("");
		}
	}

	public void setSearchEnable_customer(boolean enable) {
		if (enable) {
			findViewById(R.id.searchPanel).setVisibility(View.VISIBLE);
		} else {
			findViewById(R.id.searchPanel).setVisibility(View.GONE);
			Utility.hideKeyboard(getContext(), searchText);
			searchText.setText("");
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (listener != null)
			listener.searchTextChanged(s.toString());
	}

	public interface onSearchTextChangedListener {
		public void searchTextChanged(String searchText);
	}

	public void setOnSearchTextChangedListener(
			onSearchTextChangedListener listener) {
		this.listener = listener;
	}

	public void setMenuVisiblity(boolean b) {
		if (b)
			menu.setVisibility(View.VISIBLE);
		else
			menu.setVisibility(View.GONE);
	}

	public void setFilterView(View filterView) {
		this.filterView = filterView;
		filterIcon.setVisibility(View.VISIBLE);
		filterIcon.setImageResource(R.drawable.ic_filter_empty);
	}

	public void setFilterState(boolean isEmpty) {
		if (isEmpty)
			filterIcon.setImageResource(R.drawable.ic_filter_empty);
		else
			filterIcon.setImageResource(R.drawable.arm);
	}

}
