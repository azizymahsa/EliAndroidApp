package com.reserv.myapplicationeli.slidingmenu.customViews.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.tools.Utility;



public class IDM_Dialog extends Dialog implements
		View.OnClickListener {
	Button accept, cancel, extra;
	private OnAcceptInterface acceptListener;
	private OnCancelInterface cancelListener;
	private OnExtraInterface extraListener;

	public IDM_Dialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alert_idm);
		accept = (Button) findViewById(R.id.accept);
		cancel = (Button) findViewById(R.id.cancel);
		extra = (Button) findViewById(R.id.extraButton);
		accept.setOnClickListener(this);
		cancel.setOnClickListener(this);
		extra.setOnClickListener(this);
		Utility.applyFonts(findViewById(R.id.alert));
	}

	public Dialog setMessage(String message) {
		((TextView) findViewById(R.id.message)).setText(message);
		return this;
	}

	public void hideIcon() {
		findViewById(R.id.icon).setVisibility(View.GONE);
	}

	public Dialog setAcceptButton(OnAcceptInterface listener) {
		this.acceptListener = listener;
		accept.setVisibility(View.VISIBLE);
		return this;
	}

	public Dialog setAcceptButton(String text, OnAcceptInterface listener) {
		this.acceptListener = listener;
		accept.setVisibility(View.VISIBLE);
		accept.setText(text);
		return this;
	}

	public Dialog setCancelButton() {
		cancel.setVisibility(View.VISIBLE);
		return this;
	}

	public Dialog setCancelButton(String text, OnCancelInterface listener) {
		this.cancelListener = listener;
		this.cancel.setText(text);
		cancel.setVisibility(View.VISIBLE);
		return this;
	}

	public Dialog setExtraButton(String text, OnExtraInterface listener) {
		this.extraListener = listener;
		this.extra.setText(text);
		extra.setVisibility(View.VISIBLE);
		return this;
	}

	public interface OnAcceptInterface {
		public void accept();
	}

	public interface OnCancelInterface {
		public void cancel();
	}

	public interface OnExtraInterface {
		public void onExtraPressed();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.accept:
			if (acceptListener != null)
				acceptListener.accept();
			dismiss();
			break;
		case R.id.cancel:
			if (cancelListener != null)
				cancelListener.cancel();
			dismiss();
			break;
		case R.id.extraButton:
			if (extraListener != null)
				extraListener.onExtraPressed();
			dismiss();
			break;
		}
	}
}
