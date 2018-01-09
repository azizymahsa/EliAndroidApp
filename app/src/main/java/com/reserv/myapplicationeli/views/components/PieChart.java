package com.reserv.myapplicationeli.views.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.GlobalApplication;

public class PieChart extends View {
	public int width = 100, height = 100;
	Paint p1, p2, p3, p4;
	RectF rectF;
	Bitmap bm;
	private float confirmPercent = 0;
	private float deliveredPercent = 0;
	private float factorPercent = 0;
	private int reqCount = 0;

	public PieChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PieChart(Context context) {
		super(context);
		init();
	}

	public void init() {
		setBackgroundColor(Color.TRANSPARENT);
		p1 = new Paint();
		p1.setColor(Color.parseColor("#0bb302"));
		p1.setTextSize(25);
		p1.setTypeface(GlobalApplication.globalTypeFace);
		p1.setTextAlign(Align.RIGHT);
		p1.setAntiAlias(true);
		p2 = new Paint();
		p2.setTextSize(25);
		p2.setTypeface(GlobalApplication.globalTypeFace);
		p2.setTextAlign(Align.RIGHT);
		p2.setAntiAlias(true);
		p2.setColor(Color.parseColor("#0179be"));
		p3 = new Paint();
		p3.setTextSize(25);
		p3.setTypeface(GlobalApplication.globalTypeFace);
		p3.setTextAlign(Align.RIGHT);
		p3.setAntiAlias(true);
		p3.setColor(Color.parseColor("#ffb400"));
		p4 = new Paint();
		p4.setTextSize(25);
		p4.setTypeface(GlobalApplication.globalTypeFace);
		p4.setTextAlign(Align.RIGHT);
		p4.setAntiAlias(true);
		p4.setColor(Color.parseColor("#ff00e4"));
		rectF = new RectF(0, 0, 200, 200);
		bm = BitmapFactory.decodeResource(getContext().getResources(),
				R.drawable.circle_green_bg);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		width = canvas.getWidth();
		height = canvas.getHeight();

		rectF.set(0, 0, height, height);
		canvas.drawBitmap(bm, null, rectF, p1);

		rectF.set(25, 25, height - 25, height - 25);
		canvas.drawArc(rectF, 0, -360 * confirmPercent, true, p2);

		rectF.set(40, 40, height - 40, height - 40);
		canvas.drawArc(rectF, 0, -360 * factorPercent, true, p3);

		rectF.set(55, 55, height - 55, height - 55);
		canvas.drawArc(rectF, 0, -360 * deliveredPercent, true, p4);

		canvas.drawText(
				String.format("درخواست های ثبت شده ( %d درخواست )", reqCount),
				width - 60, 50, p1);
		canvas.drawText(String.format("درخواست های تایید شده ( %%%.2f )",
				confirmPercent * 100), width - 60, 90, p2);
		canvas.drawText(String.format("فاکتور های صادر شده ( %%%.2f%% )",
				factorPercent * 100), width - 60, 130, p3);
		canvas.drawText(String.format("درخواست های تحویل شده ( %%%.2f%% )",
				deliveredPercent * 100), width - 60, 170, p4);

		canvas.drawRect(width - 50, 30, width - 25, 55, p1);
		canvas.drawRect(width - 50, 70, width - 25, 95, p2);
		canvas.drawRect(width - 50, 110, width - 25, 135, p3);
		canvas.drawRect(width - 50, 150, width - 25, 175, p4);
	}

	public void setconfirmPercent(float precent) {
		confirmPercent = precent;
	}

	public void setFactorPercent(float precent) {
		factorPercent = precent;
	}

	public void setDeliveredPercent(float precent) {
		deliveredPercent = precent;
	}

	public void setRequestsCount(int reqCount) {
		this.reqCount = reqCount;
	}
}
