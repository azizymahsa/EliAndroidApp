package com.eligasht.reservation.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;


import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.views.picker.utils.utils.PersianCalendar;
import com.onesignal.OneSignal;
import com.eligasht.R;
import com.eligasht.reservation.base.GlobalApplication;
import com.scalified.fab.ActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Utility {

    public static void applyFonts(final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    applyFonts(child);
                }
            } else if (v instanceof TextView) {
                if (GlobalApplication.globalTypeFace == null)
                    GlobalApplication.setGlobalTypeFace(v.getContext());
                ((TextView) v).setTypeface(GlobalApplication.globalTypeFace, Typeface.BOLD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public static String MD5(String str) {

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(str.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void playSound(Context ctx, int resID) {
        MediaPlayer mp = MediaPlayer.create(ctx, resID);
        mp.setVolume(.2f, .2f);
        mp.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }

        });
        mp.start();
    }

    public static int getResourceIdByName(Context context, String string, String packageString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(string, packageString, packageName);
        return resId;
    }

    public static String getResourceNameById(Context context, int id) {
        return context.getResources().getResourceName(id);
    }

    public static int DtoN(String date) {
        try {
            int num, i, year, month, day;
            String yearStr = date.substring(0, 4);
            String monthStr = date.substring(5, 7);
            String dayStr = date.substring(8, 10);
            try {
                year = Integer.parseInt(yearStr);
                month = Integer.parseInt(monthStr);
                day = Integer.parseInt(dayStr);
            } catch (Exception e) {
                return -1;
            }
            int BaseYear = 1300;
            if (year == 0 || month == 0 || day == 0)
                return -999999;
            if (year < BaseYear || month < 1 || month > 12 || day < 1
                    || (month <= 6 && day > 31) || (month > 6 && day > 30))
                return -1;
            int year_dist;
            year_dist = year - BaseYear;
            num = year_dist * 365;
            i = 1;
            while (i < month) {
                int x;
                if (i <= 6)
                    x = 31;
                else if (i <= 11)
                    x = 30;
                else
                    x = 29;
                num = num + x;
                i++;
            }
            num = num + day;
            i = BaseYear;
            while (i < year) {
                if (IsSolHejLeap(i))
                    num++;
                i++;
            }
            return num - 18262;
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean IsSolHejLeap(int year) {
        year = year + 38;
        year = year * 31;
        year = year % 128;
        return year <= 30;
    }

    public static void CallUSSD(String USSDMessage, Context mContext) {
        try {
            String message = USSDMessage.replace("#", Uri.encode("#"));
            Intent startIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + message));
            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(startIntent);
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static String getMyOperator(Context aContext) {
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager) aContext.getSystemService(Context.TELEPHONY_SERVICE);
        String mynumber = mTelephonyMgr.getNetworkOperator();

        String Operator = "";
        if (mynumber != null) {
            if (mynumber.equals("43211")) {
                Operator = "MCI";
            } else if (mynumber.equals("43235")) {
                Operator = "IRANCELL";
            } else if (mynumber.equals("43232")) {
                Operator = "TALIYA";
            } else if (mynumber.equals("43220")) {
                Operator = "RAITEL";
            }
        }
        return Operator;
    }

    public static String getIranianDate() {
        CalendarTool tool = new CalendarTool();
        Calendar cal = Calendar.getInstance();
        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return date;
    }

    public static String getIranianDateNormal() {
        CalendarTool tool = new CalendarTool();
        Calendar cal = Calendar.getInstance();
        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return date;
    }

    public static String getIranianDate(Calendar cal) {
        CalendarTool tool = new CalendarTool();
        tool.setGregorianDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        String date = String.format(Locale.US, "%d/%02d/%02d %02d:%02d:%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay(), cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return date;
    }

    public static String getIranianShortDate(Calendar cal) {
        CalendarTool tool = new CalendarTool();
        tool.setGregorianDate(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        String date = String.format(Locale.US, "%d/%02d/%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay());
        return date;
    }

    public static String getIranianShortDate() {
        CalendarTool tool = new CalendarTool();
        String date = String.format(Locale.US, "%d/%02d/%02d",
                tool.getIranianYear(), tool.getIranianMonth(),
                tool.getIranianDay());
        return date;
    }

    public static void showKeyboard(Context context) {
        /*((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_IMPLICIT_ONLY);*/
    }

    public static void hideKeyboard(Context context, EditText textBox) {
	/*	InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(textBox.getWindowToken(), 0);*/
    }

    public static String timeLongToString(long time) {
        time /= 1000;
        int hour, minute, second;
        second = (int) (time % 60);
        time /= 60;
        minute = (int) (time % 60);
        time /= 60;
        hour = (int) time;
        return String.format(Locale.US, "%02d:%02d:%02d", hour, minute, second);
    }


    public static boolean isConnectionReachable(Context context) {
        InetAddress in;
        in = null;

        try {
            return in.isReachable(5000);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.isAvailable() ;

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setMobileDataEnabled(Context context, boolean enabled) {
        try {
            final ConnectivityManager conman = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Class conmanClass;
            conmanClass = Class.forName(conman.getClass().getName());
            final Field connectivityManagerField = conmanClass
                    .getDeclaredField("mService");
            connectivityManagerField.setAccessible(true);
            final Object connectivityManager = connectivityManagerField
                    .get(conman);
            final Class connectivityManagerClass = Class
                    .forName(connectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = connectivityManagerClass
                    .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPointInsideView(float x, float y, View view) {
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        return (x > viewX && x < (viewX + view.getWidth()))
                && (y > viewY && y < (viewY + view.getHeight()));
    }

    public static String getVersionInfos() {
        String infos = getVersionName();
        try {
            ApplicationInfo ai = GlobalApplication
                    .getContext()
                    .getPackageManager()
                    .getApplicationInfo(
                            GlobalApplication.getContext().getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();
            infos += "-"
                    + converToEnglish(new SimpleDateFormat("yyyyMMdd")
                    .format(new java.util.Date(time)));
            zf.close();
        } catch (Exception e) {
        }
        return infos;
    }

    public static String getVersionCode() {
        // گر�?تن ورژن جاری نرما�?زار
        PackageInfo pInfo = null;
        try {
            pInfo = GlobalApplication
                    .getContext()
                    .getPackageManager()
                    .getPackageInfo(
                            GlobalApplication.getContext().getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = "1";
        if (pInfo != null) {
            version = pInfo.versionCode + "";
        }
        return version;
    }

    public static String getVersionName() {
        // گر�?تن ورژن جاری نرما�?زار
        PackageInfo pInfo = null;
        try {
            pInfo = GlobalApplication
                    .getActivity()
                    .getPackageManager()
                    .getPackageInfo(
                            GlobalApplication.getContext().getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = "1";
        if (pInfo != null) {
            version = pInfo.versionName + "";
        }
        return version;
    }

    public static String converToEnglish(String recieveDate) {
        for (int i = 0; i <= 9; i++) {
            recieveDate = recieveDate.replace((char) (1776 + i),
                    (char) (48 + i));
        }
        return recieveDate;
    }

    public static String priceFormat(String price) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Double.valueOf(price));
    }


    public static void disableEnableControls(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String dateShow(String time) {

        String[] splite = time.split(" ");
        String[] dateSplite = splite[0].split("/");

	/*	String dayM=dateSplite[2];
		String monthM=dateSplite[1];
		String yearM=dateSplite[0];*/

		/*	Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(yearM));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayM));
			cal.set(Calendar.MONTH, Integer.parseInt(monthM));
			String format = new SimpleDateFormat(" MMM dd").format(cal.getTime());

			return format;
		}catch (Exception e) {
			System.out.println("Exception ::"+e);
			//return "";
		}

*/

        //String outputPattern = "dd-MMM-yyyy h:mm a";


        String inputPattern = "yyyy/MM/dd HH:mm";
        String outputPattern = "dd MMM";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;


    }
    public static String dateShowPolicy(String time) {

        String[] splite = time.split(" ");
        String[] dateSplite = splite[0].split("/");

        //String outputPattern = "dd-MMM-yyyy h:mm a";


        String inputPattern = "yyyy/MM/dd HH:mm";
        String outputPattern = "MMM dd HH:mm";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;


    }

    public static String dateShowView(String time) {

        String[] splite = time.split(" ");
        String[] dateSplite = splite[0].split("/");

	/*	String dayM=dateSplite[2];
		String monthM=dateSplite[1];
		String yearM=dateSplite[0];*/

		/*	Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(yearM));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayM));
			cal.set(Calendar.MONTH, Integer.parseInt(monthM));
			String format = new SimpleDateFormat(" MMM dd").format(cal.getTime());

			return format;
		}catch (Exception e) {
			System.out.println("Exception ::"+e);
			//return "";
		}

*/

        //String outputPattern = "dd-MMM-yyyy h:mm a";


        String inputPattern = "yyyy/MM/dd";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;


    }

    public static void startAnim(Context context, View view, int animId) {
        if (context == null || view == null) {
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(context, animId);
        view.startAnimation(animation);
    }

    public static void openUrlCustomTab(Activity context, String url) {
        try {
            Uri uri = Uri.parse(url);
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.toolbar_color));
            intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            CustomTabsIntent customTabsIntent = intentBuilder.build();
            customTabsIntent.launchUrl(context, uri);

        }catch (Exception e){
            Toast.makeText(context,context.getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
        }


// create an intent builder

// Begin customizing
// set toolbar colors

// set start and exit animations
//        intentBuilder.setStartAnimations(context, android.R.anim.slide_in_right, R.anim.slide_out_left);
//        intentBuilder.setExitAnimations(context, android.R.anim.slide_in_left,
//                android.R.anim.slide_out_right);

// build custom tabs intent


// launch the url

    }


    public static String dateShowViewFlight(String time) {

        String[] splite = time.split(" ");
        String[] dateSplite = splite[0].split("-");

   /* String dayM=dateSplite[2];
      String monthM=dateSplite[1];
      String yearM=dateSplite[0];*/

      /* Calendar cal = Calendar.getInstance();
         cal.set(Calendar.YEAR, Integer.parseInt(yearM));
         cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayM));
         cal.set(Calendar.MONTH, Integer.parseInt(monthM));
         String format = new SimpleDateFormat(" MMM dd").format(cal.getTime());

         return format;
      }catch (Exception e) {
         System.out.println("Exception ::"+e);
         //return "";
      }

*/

        //String outputPattern = "dd-MMM-yyyy h:mm a";


        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;


    }

    public static String getDeviceID(Context context) {
        //  postEvent(new ReadPhoneStatePermissionRequiredEvent());
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getSubscriberID(Context context) {
        //  postEvent(new ReadPhoneStatePermissionRequiredEvent());
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId();
    }

    public static String getOpratorID(Context context) {
        //  postEvent(new ReadPhoneStatePermissionRequiredEvent());
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getNetworkOperatorName();
    }

    public static String getSoftWareVersion(Context context) {
        //  postEvent(new ReadPhoneStatePermissionRequiredEvent());
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceSoftwareVersion();
    }

    public static void sendTag(String position, boolean online, boolean splash) {
        boolean login;

        if (splash) {


            int numberOfEntry = Prefs.getInt("numberOfEntry", 0);
            Prefs.putInt("numberOfEntry", numberOfEntry + 1);

        }


        try {
            login = WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1;
        } catch (Exception e) {
            login = false;

        }


        try {


            JSONObject tags = new JSONObject();
            tags.put("Position", position);
            tags.put("Online", online);
            tags.put("Login", login);
            tags.put("NumberOfEntry", Prefs.getInt("numberOfEntry", 0));
            OneSignal.sendTags(tags);
        } catch (Exception ee) {
        }

    }

    public static boolean campareDate(String Depart, String Return) {


        try {

            DateFormat formatter;
            Date date;
            Date date2;
            formatter = new SimpleDateFormat("yyyy/MM/dd");
            date = formatter.parse(Depart);
            date2 = formatter.parse(Return);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return date2.getTime() < date.getTime();

        } catch (Exception e) {
            return false;
        }

    }

    public static void setAnimLoading(Activity activity) {
        LottieAnimationView lottieAnimationView = activity.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/circle-l.json");
        lottieAnimationView.playAnimation();


    }

    public static void loadingText(final TextView typeWriter, final String json) {
        final Handler handler1 = new Handler();
        Runnable characterAdder = null;
        try {

            final JSONArray jsonObj = new JSONArray(json);
         //   Log.e("teeeeeeeeeeeeeest",jsonObj.toString());

            typeWriter.setText(jsonObj.getString(0));

            for ( int i = 1; i < jsonObj.length(); i++) {
              //  Log.e("piiccc12345","ok");

                final int finalI = i;
                final Runnable finalCharacterAdder = characterAdder;
                final int finalI1 = i;
                characterAdder = new Runnable() {

                    @Override
                    public void run() {
                     //   Log.d("onStart2", jsonObj.toString());

                        if (finalI1 <= jsonObj.length()) {
                            YoYo.with(Techniques.SlideOutDown)
                                    .duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    try {
                                        typeWriter.setText(jsonObj.getString(finalI));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    YoYo.with(Techniques.SlideInDown)
                                            .duration(500)
                                            .playOn(typeWriter);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                                    .playOn(typeWriter);
                            handler1.postDelayed(finalCharacterAdder, 4000);

                        }else{
                            handler1.removeCallbacks(finalCharacterAdder);

                        }

                    }
                };

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            handler1.postDelayed(characterAdder, 4000);

        }catch (Exception e){}




















    }


    public static void init_floating(final com.eligasht.reservation.tools.ListView list,Activity context){
        final boolean[] isShow = {true};
        final com.scalified.fab.ActionButton floatingActionButton = context.findViewById(R.id.action_button);
        floatingActionButton.setShadowRadius(7.0f);

        floatingActionButton.setShadowXOffset(1f);


        floatingActionButton.setShadowYOffset(1f);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.up));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.smoothScrollToPosition(0);
            }
        });
        floatingActionButton.setVisibility(View.GONE);
        floatingActionButton.setButtonColor(ContextCompat.getColor(context,R.color.floating_button_color));
        floatingActionButton.setButtonColorPressed(ContextCompat.getColor(context,R.color.focusColor));
        if (context.getResources().getInteger(R.integer._300)==300){
            floatingActionButton.setType(ActionButton.Type.DEFAULT);

        }else {
            floatingActionButton.setType(ActionButton.Type.MINI);

        }
        list.setOnDetectScrollListener(new OnDetectScrollListener() {
            @Override
            public void onUpScrolling() {

            }

            @Override
            public void onDownScrolling() {
                if (floatingActionButton.getVisibility()==View.GONE&& isShow[0]){

                    floatingActionButton.setVisibility(View.VISIBLE);
                 //   Log.e("onUpScrolling", "onUpScrolling: 3333333333333" );

                    YoYo.with(Techniques.SlideInUp).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                        @Override
                        public void onAnimationStart(android.animation.Animator animation) {


                        }

                        @Override
                        public void onAnimationEnd(android.animation.Animator animation) {

                           // floatingActionButton.show();
                            isShow[0] =false;


                        }

                        @Override
                        public void onAnimationCancel(android.animation.Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(android.animation.Animator animation) {

                        }

                    })
                            .playOn(floatingActionButton);
                }


            }

            @Override
            public void onFirstVisibleItem() {

                if (floatingActionButton.getVisibility()==View.VISIBLE&& !isShow[0]){
                //    Log.e("onUpScrolling", "onUpScrolling:44444444444" );

                    isShow[0]=true;
                    YoYo.with(Techniques.SlideOutDown).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                        @Override
                        public void onAnimationStart(android.animation.Animator animation) {


                        }

                        @Override
                        public void onAnimationEnd(android.animation.Animator animation) {



                            floatingActionButton.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(android.animation.Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(android.animation.Animator animation) {

                        }

                    })
                            .playOn(floatingActionButton);

                }
            }
        });


    }




    public static void init_floating_flight(final com.eligasht.reservation.tools.ExpandableListViewE list,Activity context) {
        final boolean[] isShow = {true};
        final com.scalified.fab.ActionButton floatingActionButton = context.findViewById(R.id.action_button);
        floatingActionButton.setShadowRadius(7.0f);

        floatingActionButton.setShadowXOffset(1f);


        floatingActionButton.setShadowYOffset(1f);
        floatingActionButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.up));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.smoothScrollToPosition(0);
            }
        });
        floatingActionButton.setVisibility(View.GONE);
        floatingActionButton.setButtonColor(ContextCompat.getColor(context, R.color.floating_button_color));
        floatingActionButton.setButtonColorPressed(ContextCompat.getColor(context, R.color.focusColor));
        if (context.getResources().getInteger(R.integer._300) == 300) {
            floatingActionButton.setType(ActionButton.Type.DEFAULT);

        } else {
            floatingActionButton.setType(ActionButton.Type.MINI);

        }
        list.setOnDetectScrollListener(new OnDetectScrollListener() {
            @Override
            public void onUpScrolling() {

            }

            @Override
            public void onDownScrolling() {
                if (floatingActionButton.getVisibility() == View.GONE && isShow[0]) {

                    floatingActionButton.setVisibility(View.VISIBLE);
                //    Log.e("onUpScrolling", "onUpScrolling: 3333333333333");

                    YoYo.with(Techniques.SlideInUp).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                        @Override
                        public void onAnimationStart(android.animation.Animator animation) {


                        }

                        @Override
                        public void onAnimationEnd(android.animation.Animator animation) {

                            // floatingActionButton.show();
                            isShow[0] = false;


                        }

                        @Override
                        public void onAnimationCancel(android.animation.Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(android.animation.Animator animation) {

                        }

                    })
                            .playOn(floatingActionButton);
                }


            }

            @Override
            public void onFirstVisibleItem() {

                if (floatingActionButton.getVisibility() == View.VISIBLE && !isShow[0]) {
                 //   Log.e("onUpScrolling", "onUpScrolling:44444444444");

                    isShow[0] = true;
                    YoYo.with(Techniques.SlideOutDown).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


                        @Override
                        public void onAnimationStart(android.animation.Animator animation) {


                        }

                        @Override
                        public void onAnimationEnd(android.animation.Animator animation) {


                            floatingActionButton.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(android.animation.Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(android.animation.Animator animation) {

                        }

                    })
                            .playOn(floatingActionButton);

                }
            }
        });
    }

    public static String convertNumbersToPersian(String str) {
        String answer = str;
        answer = answer.replace("1", "١");
        answer = answer.replace("2", "٢");
        answer = answer.replace("3", "٣");
        answer = answer.replace("4", "٤");
        answer = answer.replace("5", "٥");
        answer = answer.replace("6", "٦");
        answer = answer.replace("7", "٧");
        answer = answer.replace("8", "٨");
        answer = answer.replace("9", "٩");
        answer = answer.replace("0", "٠");
        return answer;
    }

    public static String convertNumbersToEnglish(String str) {
        String answer = str;
        answer = answer.replace("۱", "1");
        answer = answer.replace("۲", "2");
        answer = answer.replace("۳", "3");
        answer = answer.replace("۴", "4");
        answer = answer.replace("۵", "5");
        answer = answer.replace("۶", "6");
        answer = answer.replace("۷", "7");
        answer = answer.replace("۸", "8");
        answer = answer.replace("۹", "9");
        answer = answer.replace("۰", "0");
        answer = answer.replace("١", "1");
        answer = answer.replace("٢", "2");
        answer = answer.replace("٣", "3");
        answer = answer.replace("٤", "4");
        answer = answer.replace("٥", "5");
        answer = answer.replace("٦", "6");
        answer = answer.replace("٧", "7");
        answer = answer.replace("٨", "8");
        answer = answer.replace("٩", "9");
        answer = answer.replace("٠", "0");

        return answer;
    }
    public static String simpleFormatDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy",Locale.US);

        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.setTime(newDate);
        StringBuilder stringBuilder = new StringBuilder();
        String slash = "/";
        stringBuilder.
                append(persianCalendar.getPersianYear()).
                append(slash).
                append(persianCalendar.getPersianMonth()).
                append(slash).
                append(persianCalendar.getPersianDay());

        return stringBuilder.toString();

    }



}

/*   YoYo.with(Techniques.SlideOutLeft)
                                .duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                try {
                                    typeWriter.setText(jsonObj.getString(finalI));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                YoYo.with(Techniques.SlideInRight)
                                        .duration(500)
                                        .playOn(typeWriter);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                                .playOn(typeWriter);*/