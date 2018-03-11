package com.eligasht.reservation.views.picker.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.eligasht.reservation.views.ui.SingletonContext;

import java.util.Locale;


public class IRANSansTextView extends TextView {
    public IRANSansTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            m18579a();
        }
    }

    public IRANSansTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            m18579a();
        }
    }

    public IRANSansTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            m18579a();
        }
    }

    public static Typeface a(Context context, String str) {
        String obj;
        SharedPreferences sharedPrefrences = SingletonContext.getInstance().getContext().getSharedPreferences("eligasht.com", 0);
        if (sharedPrefrences.getBoolean("isGregorian", false))
            obj = "fonts/times.ttf";
        else if (Locale.getDefault().getLanguage().equals("fa"))
            obj = "fonts/iran_sans_normal.ttf";
        else
            obj = "fonts/times.ttf";
        Typeface font = Typeface.createFromAsset(
                context.getAssets(),
                obj);
        return font;
    }

    private void m18579a() {
        setTypeface(a(getContext(), "iran_sans_normal"), 0);
    }

}
