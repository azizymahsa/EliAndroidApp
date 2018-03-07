package com.eligasht.reservation.views.picker.utils;

import android.content.Context;


public class NumberUtil {
    private final char[] farsiNum = "۰۱۲۳۴۵۶۷۸۹".toCharArray();
    private final char[] englishNum = "0123456789".toCharArray();

    public NumberUtil(Context context) {
    }

    public String toFarsiString(String str) {
        return str.replace(".0000", "").replace('0', this.farsiNum[0]).replace('1', this.farsiNum[1]).replace('2', this.farsiNum[2]).replace('3', this.farsiNum[3]).replace('4', this.farsiNum[4]).replace('5', this.farsiNum[5]).replace('6', this.farsiNum[6]).replace('7', this.farsiNum[7]).replace('8', this.farsiNum[8]).replace('9', this.farsiNum[9]);
    }

    public String toEnglishString(String str) {
        return str.replace(".۰۰۰۰", "").replace('۰', this.englishNum[0]).replace('۱', this.englishNum[1]).replace('۲', this.englishNum[2]).replace('۳', this.englishNum[3]).replace('۴', this.englishNum[4]).replace('۵', this.englishNum[5]).replace('۶', this.englishNum[6]).replace('۷', this.englishNum[7]).replace('۸', this.englishNum[8]).replace('۹', this.englishNum[9]);
    }


}
