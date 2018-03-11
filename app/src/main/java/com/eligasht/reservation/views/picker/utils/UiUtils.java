package com.eligasht.reservation.views.picker.utils;


import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.eligasht.reservation.views.ui.SingletonContext;

import java.util.Calendar;
import java.util.Locale;


/* compiled from: UiUtils */
public class UiUtils {

    public static CalendarTool f14019i = new CalendarTool();
    public static Calendar f14020j;


    public static String getSolarMonthName(int i) {
        switch (i) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                return "";
        }
    }

    public static String getGregorianMonthName(int i) {
        switch (i) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }


    public static String m18489p(String str) {
        if (str.isEmpty()) {
            return " ";
        }
        f14019i = new CalendarTool();
        if (!str.contains("T")) {
            str = str + "T00:00:00";
        }
        if (str.contains("/")) {
            str = str.replace("/", "-");
        }
        f14019i.m18351b(Integer.valueOf(str.split("T")[0].split("-")[0]).intValue(), Integer.valueOf(str.split("T")[0].split("-")[1]).intValue(), Integer.valueOf(str.split("T")[0].split("-")[2]).intValue());
        return f14019i.m18356g().split("/")[0] + "/" + UiUtils.m18494u(String.valueOf(f14019i.m18350b())) + "/" + UiUtils.m18494u(String.valueOf(f14019i.m18352c()));
    }


    public static String m18492s(String str) {
        String str2;
        String str3;
        if (str.contains("/")) {
            str = str.replace("/", "-");
        }
        f14019i.m18349a(Integer.valueOf(str.split("-")[0]).intValue(), Integer.valueOf(str.split("-")[1]).intValue(), Integer.valueOf(str.split("-")[2]).intValue());
        if (f14019i.m18357h().split("/")[1].length() == 1) {
            str2 = "0" + f14019i.m18357h().split("/")[1];
        } else {
            str2 = f14019i.m18357h().split("/")[1];
        }
        if (f14019i.m18357h().split("/")[2].length() == 1) {
            str3 = "0" + f14019i.m18357h().split("/")[2];
        } else {
            str3 = f14019i.m18357h().split("/")[2];
        }
        return f14019i.m18357h().split("/")[0] + "-" + str2 + "-" + str3;
    }


    public static int m18476i(int i) {
        if (i == 5) {
            return 1;
        }
        if (i == 6) {
            return 2;
        }
        if (i == 0) {
            return 3;
        }
        return i + 3;
    }

    public static int m18478j(int i) {
        if (i == 6) {
            return 1;
        }
        return i + 2;
    }

    public static String m18494u(String str) {
        if (str.length() == 1) {
            return "0" + str;
        }
        return str;
    }


    public static int m18432a(int i, int i2) {
        f14020j = Calendar.getInstance();
        f14020j.set(1, i2);
        f14020j.set(6, i);
        return f14020j.get(2) + 1;
    }

    public static int m18455b(int i, int i2) {
        if (UiUtils.m18481k(i2) && i > 366) {
            i -= 366;
        } else if (!UiUtils.m18481k(i2) && i > 365) {
            i -= 365;
        }
        if (i <= 31) {
            return 1;
        }
        if (i <= 62 && i >= 32) {
            return 2;
        }
        if (i <= 93 && i >= 63) {
            return 3;
        }
        if (i <= 124 && i >= 94) {
            return 4;
        }
        if (i <= 155 && i >= 125) {
            return 5;
        }
        if (i <= 186 && i >= 156) {
            return 6;
        }
        if (i <= 216 && i >= 187) {
            return 7;
        }
        if (i <= 246 && i >= 217) {
            return 8;
        }
        if (i <= 276 && i >= 247) {
            return 9;
        }
        if (i <= 306 && i >= 277) {
            return 10;
        }
        if (i <= 336 && i >= 307) {
            return 11;
        }
        if (i >= 337) {
            return 12;
        }
        return 1;
    }

    public static int m18434a(int i, int i2, boolean z) {
        if (z) {
            f14020j = Calendar.getInstance();
            if (UiUtils.m18483l(i2)) {
                f14020j.set(6, i);
                f14020j.set(1, i2);
            } else {
                f14020j.set(6, i);
                f14020j.set(1, i2);
            }
            return f14020j.get(5);
        }
        if (UiUtils.m18481k(i2)) {
            if (i > 366) {
                i -= 366;
                i2++;
            }
        } else if (i > 365) {
            i -= 365;
            i2++;
        }
        if (i < 1 || i > 186) {
            if (i <= 186 || i > 336) {
                if (i > 336) {
                    if (i2 % 4 == 3) {
                        if (i < 366) {
                            return i - 336;
                        }
                        if (i == 366) {
                            return 30;
                        }
                    } else if (i < 365) {
                        return i - 336;
                    } else {
                        if (i == 365) {
                            return 29;
                        }
                    }
                }
                return 0;
            } else if (((i - 186) + 30) % 30 != 0) {
                return ((i - 186) + 30) % 30;
            } else {
                return 30;
            }
        } else if ((i + 31) % 31 == 0) {
            return 31;
        } else {
            return (i + 31) % 31;
        }
    }

    public static Typeface getFont() {
        String obj;
        SharedPreferences sharedPrefrences = SingletonContext.getInstance().getContext().getSharedPreferences("eligasht.com", 0);
        if (sharedPrefrences.getBoolean("isGregorian", false))
            obj = "fonts/times.ttf";
        else if (Locale.getDefault().getLanguage().equals("fa"))
            obj = "fonts/iran_sans_bold.ttf";
        else
            obj = "fonts/times.ttf";

        Typeface font = Typeface.createFromAsset(
                SingletonContext.getInstance().getContext().getAssets(),
                obj);
        return font;
    }

    public static int m18433a(int i, int i2, int i3) {
        if (i2 >= 1 && i2 <= 6) {
            return ((i2 - 1) * 31) + i;
        }
        if (i2 >= 7 && i2 <= 11) {
            return (((i2 - 7) * 30) + 186) + i;
        }
        if (i2 == 12) {
            if (UiUtils.m18481k(i3)) {
                if (i <= 366) {
                    return i + 336;
                }
            } else if (i <= 365) {
                return i + 336;
            }
        }
        return 0;
    }

    public static int m18456b(int i, int i2, int i3) {
        f14020j = Calendar.getInstance();
        f14020j.set(i3, i2 - 1, i);
        return f14020j.get(6);
    }

    public static boolean m18481k(int i) {
        return i % 4 == 3;
    }

    public static boolean m18483l(int i) {
        return i % 4 == 0;
    }

    public static int m18437a(boolean z, int i) {
        if (z) {
            if (UiUtils.m18483l(i)) {
                return 366;
            }
            return 365;
        } else if (UiUtils.m18481k(i)) {
            return 366;
        } else {
            return 365;
        }
    }


    public static String m18430A(String str) {
        char[] cArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '٠' && charAt <= '٩') {
                charAt = (char) (charAt - 1584);
            } else if (charAt >= '۰' && charAt <= '۹') {
                charAt = (char) (charAt - 1728);
            }
            cArr[i] = charAt;
        }
        return new String(cArr);
    }


}
