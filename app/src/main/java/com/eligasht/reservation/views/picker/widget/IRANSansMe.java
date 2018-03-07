package com.eligasht.reservation.views.picker.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class IRANSansMe extends TextView {
    public IRANSansMe(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public IRANSansMe(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public IRANSansMe(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public static Typeface a(Context context) {
        String obj = "fonts/shabna_bold.ttf";

        Typeface font = Typeface.createFromAsset(
                context.getAssets(),
                obj);
        return font;
    }

    private void init(Context context) {
        setTypeface(a(context), 0);
    }
}
