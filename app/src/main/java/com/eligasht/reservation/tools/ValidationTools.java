package com.eligasht.reservation.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationTools {

    public static boolean isEmptyOrNull(String input){
        return input == null || input.isEmpty() || input.trim().equals("") || input.trim().toLowerCase().equals("null");
    }
    public static boolean isEmptyOrNull(List arrayList){
        return arrayList == null || arrayList.size() == 0;
    }
    public static boolean isEmptyOrNull(ArrayList arrayList){
        return arrayList == null || arrayList.size() == 0;
    }
    public static boolean isEmailValid(String email) {
        if (ValidationTools.isEmptyOrNull(email)) {
            return false;
        }
        boolean isValid = true;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches()) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isMobileValid(String mobile, String countryCode) {
        String x;
        if (ValidationTools.isEmptyOrNull(mobile)) {
            return false;
        }
        if (countryCode.trim().equals("98")) {
            x = String.valueOf(mobile.charAt(0));
            return (x.trim().equals("" + 9) && mobile.length() == 10) || (x.trim().equals("" + 0) && mobile.length() == 11);
        } else {
            return !(mobile.length() > 15 || mobile.length() < 5);
        }
    }

    public static boolean isEnglish(String value) {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(value);
        return ms.matches();
    }

}
