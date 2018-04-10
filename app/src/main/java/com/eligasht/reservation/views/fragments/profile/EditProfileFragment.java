package com.eligasht.reservation.views.fragments.profile;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.reservation.tools.Utility;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.eligasht.R;
import com.eligasht.reservation.models.model.login.call.RegisterListReq;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.components.smoothcheckbox.SmoothCheckBox;
import com.eligasht.reservation.views.dialogs.NumberPickerDialog;

import static android.content.Context.INPUT_METHOD_SERVICE;
/**
 * Created by elham.bonyani on 1/25/2018.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener, NumberPickerDialog.NumberPickerListener,
        TimePickerDialog.OnTimeSetListener,
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    //   private TextView txt_arrow1;
    //  private TextView txt_arrow2;
    //  private ViewGroup btn_order;
//    private ViewGroup btn_user_info;
//    private ExpandableRelativeLayout expand_other;
    //   private ExpandableRelativeLayout expand_user_info;
    private EditText edt_name_fa;
    private EditText edt_last_name_fa;
    private EditText edt_email_user_name;
    private EditText edt_name_En;
    private EditText edt_last_name_En;
    private EditText edt_code_meli;
    private EditText edt_home_phone;
    private EditText edt_mobile;
    private EditText edt_address;
    private LinearLayout rlBirthday;
    public SmoothCheckBox chk_gender_man;
    public SmoothCheckBox chk_gender_woman;
    transient private boolean isChecked;
    private LinearLayout birthday_date;
    private EditText txt_birthday;
    private String birthdayDate;
    DatePickerDialog datePickerDialogDepart;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    ImageView imageView;

    public static EditProfileFragment instance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile_new, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        edt_address = view.findViewById(R.id.edt_address);
        edt_mobile = view.findViewById(R.id.edt_mobile);
        edt_home_phone = view.findViewById(R.id.edt_home_phone);
        edt_name_En = view.findViewById(R.id.edt_name_En);
        edt_name_fa = view.findViewById(R.id.edt_name_fa);
        edt_last_name_En = view.findViewById(R.id.edt_last_name_En);
        edt_last_name_fa = view.findViewById(R.id.edt_last_name_fa);
        edt_code_meli = view.findViewById(R.id.edt_code_meli);
        edt_email_user_name = view.findViewById(R.id.edt_email_user_name);
        chk_gender_man = view.findViewById(R.id.chB_man);
        chk_gender_woman = view.findViewById(R.id.chB_woman);
        birthday_date = view.findViewById(R.id.edt_birthday);
        txt_birthday = view.findViewById(R.id.txt_birthday);
        rlBirthday = view.findViewById(R.id.rlBirthday);
        imageView = getActivity().findViewById(R.id.ivImage);
        rlBirthday.setOnClickListener(this);




        //set information of user
        birthdayDate = WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserBirthDayMiladi();
        edt_name_fa.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF());
        edt_name_En.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameE());
        edt_last_name_fa.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
        edt_last_name_En.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameE());
        edt_mobile.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMobile());
        edt_home_phone.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserTel());
        edt_code_meli.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserNationalCode());
        edt_address.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserAddress());
        edt_email_user_name.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
        String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "dd/MM/yyyy");
        int currentDay = DateUtil.getDayOfMonth(currentDateTime, "dd/MM/yyyy", true);
        int currentYear = DateUtil.getYear(currentDateTime, "dd/MM/yyyy", true);
        int currentMonth = DateUtil.getMonth(currentDateTime, "dd/MM/yyyy", true) - 1;
        if (ValidationTools.isEmptyOrNull(birthdayDate)) {
            txt_birthday.setText(getString(R.string.please_select_one));
            datePickerDialogDepart = DatePickerDialog.newInstance(
                    this,
                    currentYear - 66,
                    0,
                    1
            );
        } else {
            try {
                txt_birthday.setText(DateUtil.getLongStringDateInsurance(birthdayDate, "dd/MM/yyyy", true));
                int day = DateUtil.getDayOfMonth(birthdayDate, "dd/MM/yyyy", true);
                int year = DateUtil.getYear(birthdayDate, "dd/MM/yyyy", true);
                int month = DateUtil.getMonth(birthdayDate, "dd/MM/yyyy", true) - 1;
                datePickerDialogDepart = DatePickerDialog.newInstance(
                        this,
                        year,
                        month,
                        day
                );
                datePickerDialogDepart.setYearRange(1330, currentYear);
                datePickerDialogDepart.setTitle(getActivity().getString(R.string.Brithday));
            } catch (Exception e) {
            }
        }
        chk_gender_man.setOnCheckedChangeListener(null);
        chk_gender_woman.setOnCheckedChangeListener(null);
        birthday_date.setOnClickListener(this);
        try {
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserGender()) {
                chk_gender_man.setChecked(true);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.man));
            } else {
                chk_gender_woman.setChecked(true);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.woman));

            }
        } catch (Exception e) {
        }
        chk_gender_man.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {

                if (isChecked) {
                    chk_gender_woman.setChecked(false);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.man));

                }
            }
        });
        chk_gender_woman.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {

                if (isChecked) {
                    chk_gender_man.setChecked(false);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.woman));

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        datePickerDialogDepart.show(getActivity().getSupportFragmentManager(), "BirthDay");
    }

    public boolean isValidForm() {
        // check validation and if all thigs are ok return true else return false;
        if (birthdayDate == null) {
            Toast.makeText(getActivity(), R.string.enter_your_birth_date, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_name_fa.length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_name_in_persian, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_name_En.length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_name_in_english, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_last_name_fa.length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_family_name_in_persian, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_last_name_En.length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_family_name_in_english, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edt_mobile.length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_mobile_number, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //request for edit profile
    public RegisterListReq getRegisterListReq() {
        RegisterListReq registerListReq = new RegisterListReq();
        registerListReq.setWebUserFnameF(edt_name_fa.getText().toString());
        registerListReq.setWebUserFnameE(edt_name_En.getText().toString());
        registerListReq.setWebUserLnameF(edt_last_name_fa.getText().toString());
        registerListReq.setWebUserLnameE(edt_last_name_En.getText().toString());
        registerListReq.setWebUserMail(edt_email_user_name.getText().toString());
        registerListReq.setWebUserTel(edt_home_phone.getText().toString());
        registerListReq.setWebUserMobile(edt_mobile.getText().toString());
        registerListReq.setWebUserAddress(edt_address.getText().toString());
        registerListReq.setWebUserNationalCode(edt_code_meli.getText().toString());
        if (chk_gender_man.isChecked()) {
            registerListReq.setWebUserGender(true);
        }
        if (chk_gender_woman.isChecked()) {
            registerListReq.setWebUserGender(false);
        }
        registerListReq.setWebUserBirthDayMiladi(Utility.convertNumbersToEnglish(birthdayDate));
        registerListReq.setWebUserID(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID());
        // set other parameter here!
        return registerListReq;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public void onReturnValue(String type, int duration) {
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
    }

    //shamsi
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        long milis = DateUtil.getMiliSecondPersianDateTime(year, monthOfYear, dayOfMonth);
        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");
        if (view.getTag().equals("BirthDay")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            txt_birthday.setText(DateUtil.getLongStringDateInsurance(currentDateTime, "yyyy-MM-dd", true));
            birthdayDate = currentDateTime;
            PersianCalendar persianCalendarDatePicker = new PersianCalendar();
            persianCalendarDatePicker.setPersianDate(year_Min, monthMin, dayMin);
            datePickerDialogDepart.initialize(this, year_, month, day);
        }
    }
}
