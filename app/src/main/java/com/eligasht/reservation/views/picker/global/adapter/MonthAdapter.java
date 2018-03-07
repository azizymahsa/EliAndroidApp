package com.eligasht.reservation.views.picker.global.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.views.picker.utils.NumberUtil;
import com.eligasht.reservation.views.picker.utils.UiUtils;

import java.util.List;


public class MonthAdapter extends Adapter<MonthAdapter.MonthViewHolder> {
    public boolean isReverseTravel;
    private DateSelected dateSelectListener;
    private int f9217c;
    private int f9218d;
    private List<Boolean> days360;
    private int year;
    private int f9221g;
    private int f9222h = -1;
    private int f9223i = -1;
    private boolean isGeorgian;
    private boolean f9225k = false;
    private String fullDate;
    private Context context;
    private String usageCalendarText;
    private PopupWindow popupWindow;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private Activity activity;


    public MonthAdapter(Activity activity, Context context, int i, int i2, int year, int i4, List<Boolean> days360, boolean isReverseTravel, boolean isGeorgian, String fullDate, String typeUsageOfCalendar) {
        this.context = context;
        this.f9217c = i;
        this.f9218d = i2;
        this.days360 = days360;
        this.year = year;
        this.f9221g = i4;
        this.isReverseTravel = isReverseTravel;
        this.isGeorgian = isGeorgian;
        this.fullDate = fullDate;
        this.usageCalendarText = typeUsageOfCalendar;
        this.activity = activity;

    }

    private void initPopupWindow(View view, String text) {
        if (this.popupWindow != null) {
            this.popupWindow.dismiss();
            this.popupWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this.context, 17170445)));
        }
        View inflate = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popup_calendar, null);
        ((TextView) inflate.findViewById(R.id.txt)).setText(text);
        this.popupWindow.setFocusable(false);
        this.popupWindow.setWidth(-2);
        this.popupWindow.setHeight(-2);
        this.popupWindow.setContentView(inflate);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setTouchable(true);
        this.popupWindow.showAsDropDown(view, -((int) (((double) (44.0f * this.displayMetrics.density)) + 0.5d)), -((int) (((double) (74.0f * this.displayMetrics.density)) + 0.5d)));
        if (VERSION.SDK_INT >= 21) {
            this.popupWindow.setElevation(6.0f);
        }
    }

    @Override
    public MonthViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MonthViewHolder(LayoutInflater.from(this.context).inflate(R.layout.adapter_day_of_month_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MonthViewHolder monthViewHolder, int i) {
        if (i > this.f9217c - 1) {
            if (i % 7 == 6) {
                monthViewHolder.day.setTextColor(-65536);
            } else {
                monthViewHolder.day.setTextColor(-16777216);
            }
            if (i < (this.f9218d + this.f9217c) - 1) {
                monthViewHolder.day.setAlpha(0.24f);
            } else {
                monthViewHolder.day.setAlpha(0.87f);
                if (this.days360.contains(Boolean.TRUE)) {
                    if (this.days360.get(i)) {
                        monthViewHolder.day.setBackgroundResource(R.drawable.circle_shape_blue);
                        monthViewHolder.day.setTextColor(-1);
                    } else if (i < this.f9223i && i > this.f9222h && this.f9222h != -1 && this.f9223i != -1) {
                        monthViewHolder.day.setBackgroundResource(R.drawable.rec_calendar);
                        monthViewHolder.day.setTextColor(-16777216);
                    } else if (i == (this.f9218d + this.f9217c) - 1) {
                        monthViewHolder.day.setTextColor(Color.parseColor("#9d80ff"));
                        monthViewHolder.day.setAlpha(1.0f);
                    } else {
                        monthViewHolder.day.setAlpha(0.87f);
                        monthViewHolder.day.setBackgroundResource(0);
                    }
                } else if (i == (this.f9218d + this.f9217c) - 1) {
                    monthViewHolder.day.setTextColor(Color.parseColor("#9d80ff"));
                    monthViewHolder.day.setAlpha(1.0f);
                }
            }
            if (this.isGeorgian) {
                monthViewHolder.day.setText(String.valueOf(UiUtils.m18434a((i - this.f9217c) + this.f9221g, this.year, this.isGeorgian)));
                return;
            } else {
                monthViewHolder.day.setText(monthViewHolder.numberUtil.toFarsiString(String.valueOf(UiUtils.m18434a((i - this.f9217c) + this.f9221g, this.year, this.isGeorgian))));
                return;
            }
        }
        monthViewHolder.day.setText("");
        monthViewHolder.day.setBackgroundResource(0);
        monthViewHolder.day.setTextColor(-16777216);
        monthViewHolder.day.setAlpha(0.87f);
    }

    @Override
    public int getItemCount() {
        return 365;
    }

    public void setOnDateSelect(DateSelected dateSelected) {
        this.dateSelectListener = dateSelected;
    }

    public interface DateSelected {
        void onDateSelected(View view, int index);
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private TextView day;
        private NumberUtil numberUtil;

        MonthViewHolder(View view) {
            super(view);
            int i = 0;
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.numberUtil = new NumberUtil(view.getContext());
            this.day = view.findViewById(R.id.day);
            view.setOnClickListener(this);
            popupWindow = new PopupWindow(context);
            if (!f9225k) {
                if (isReverseTravel) {
                    if (fullDate != null && fullDate.contains("-")) {
                        while (i < days360.size()) {
                            if (days360.get(i) && f9222h == -1) {
                                f9222h = i;
                            } else if (days360.get(i) && f9223i == -1) {
                                f9223i = i;
                                f9225k = true;
                                return;
                            }
                            i++;
                        }
                    }
                } else if (fullDate != null) {
                    while (i < days360.size()) {
                        if (days360.get(i)) {
                            f9222h = i;
                            f9225k = true;
                            return;
                        }
                        i++;
                    }
                }
            }
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() > (f9217c + f9218d) - 2) {
                this.itemView.setClickable(false);
                f9225k = true;
                if (dateSelectListener != null && getAdapterPosition() >= (f9218d + f9217c) - 1) {
                    view.setBackgroundResource(R.drawable.circle_shape_blue);
                    days360.set(getAdapterPosition(), Boolean.TRUE);
                    notifyItemChanged(getAdapterPosition());
                    dateSelectListener.onDateSelected(view, getAdapterPosition());
                }
                if (isReverseTravel) {
                    if (!(f9222h == -1 || f9223i == -1)) {
                        days360.set(f9222h, Boolean.FALSE);
                        notifyItemChanged(f9222h);
                        days360.set(f9223i, Boolean.FALSE);
                        notifyItemChanged(f9223i);
                        notifyItemRangeChanged(f9222h + 1, f9223i - 1);
                        f9222h = -1;
                        f9223i = -1;
                    }
                    if (f9222h == -1) {
                        f9222h = getAdapterPosition();
                        days360.set(f9222h, Boolean.TRUE);
                        notifyItemChanged(f9222h);
                        if (usageCalendarText.equals("Hotel")) {
                            initPopupWindow(view, "تاریخ خروج را انتخاب نمایید");
                        } else if (usageCalendarText.equals("AutoAlert")) {
                            initPopupWindow(view, "تاریخ پایان را انتخاب نمایید");
                        } else if (usageCalendarText.equals("InternationalFlight") || usageCalendarText.equals("NationalFlight")) {
                            initPopupWindow(view, "تاریخ برگشت را انتخاب نمایید");
                        }
                    } else if (getAdapterPosition() < f9222h) {
                        days360.set(f9222h, Boolean.FALSE);
                        notifyItemChanged(f9222h);
                        f9222h = getAdapterPosition();
                        days360.set(f9222h, Boolean.TRUE);
                        notifyItemChanged(f9222h);
                        if (usageCalendarText.equals("Hotel")) {
                            initPopupWindow(view, "تاریخ خروج را انتخاب نمایید");
                        } else if (usageCalendarText.equals("AutoAlert")) {
                            initPopupWindow(view, "تاریخ پایان را انتخاب نمایید");
                        } else if (usageCalendarText.equals("InternationalFlight") || usageCalendarText.equals("NationalFlight")) {
                            initPopupWindow(view, "تاریخ برگشت را انتخاب نمایید");
                        }
                    } else if (usageCalendarText.equals("Hotel") && f9222h == getAdapterPosition()) {
                        Toast.makeText(context, "تاریخ ورود و خروج یکی نمی توانند باشند", 1).show();
                    } else {
                        f9223i = getAdapterPosition();
                        days360.set(f9223i, Boolean.TRUE);
                        notifyItemChanged(f9223i);
                        if (usageCalendarText.equals("Hotel")) {
                            initPopupWindow(view, this.numberUtil.toFarsiString(String.valueOf(f9223i - f9222h)) + " شب");
                        }
                        notifyItemRangeChanged(f9222h + 1, f9223i - 1);
                    }
                } else if (f9222h != -1) {
                    days360.set(f9222h, Boolean.FALSE);
                    notifyItemChanged(f9222h);
                    f9222h = getAdapterPosition();
                } else {
                    f9222h = getAdapterPosition();
                }
                this.itemView.setClickable(true);
            }
        }
    }
}
