package com.eligasht.reservation.views.picker.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


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
        String obj = "fonts/shabnam.ttf";

        Typeface font = Typeface.createFromAsset(
                context.getAssets(),
                obj);
        return font;
    }

    private void m18579a() {
        setTypeface(a(getContext(), "shabnam"), 0);
    }

}
