package com.reserv.myapplicationeli.models.model.login;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.tools.ValidationTools;

/**
 * Created by elham.bonyani on 1/20/2018.
 */

public class WebUser {

    private Identity identity;
    public final Identity getidentity()
    {
        return identity;
    }
    public final void setidentity(Identity value)
    {
        identity = value;
    }
    private String Culture;
    public final String getCulture()
    {
        return Culture;
    }
    public final void setCulture(String value)
    {
        Culture = value;
    }
    private String ActivationCode;
    public final String getActivationCode()
    {
        return ActivationCode;
    }
    public final void setActivationCode(String value)
    {
        ActivationCode = value;
    }


    private String BankCardNo;
    public final String getBankCardNo()
    {
        return BankCardNo;
    }
    public final void setBankCardNo(String value)
    {
        BankCardNo = value;
    }


    private String Bull;
    public final String getBull()
    {
        return Bull;
    }
    public final void setBull(String value)
    {
        Bull = value;
    }


    private String DemosticCityFa;
    public final String getDemosticCityFa()
    {
        return DemosticCityFa;
    }
    public final void setDemosticCityFa(String value)
    {
        DemosticCityFa = value;
    }


    private int DemosticCityID;
    public final int getDemosticCityID()
    {
        return DemosticCityID;
    }
    public final void setDemosticCityID(int value)
    {
        DemosticCityID = value;
    }


    private String DemosticPrivinceFa;
    public final String getDemosticPrivinceFa()
    {
        return DemosticPrivinceFa;
    }
    public final void setDemosticPrivinceFa(String value)
    {
        DemosticPrivinceFa = value;
    }


    private int DemosticPrivinceID;
    public final int getDemosticPrivinceID()
    {
        return DemosticPrivinceID;
    }
    public final void setDemosticPrivinceID(int value)
    {
        DemosticPrivinceID = value;
    }


    private String EncryptWebUserID;
    public final String getEncryptWebUserID()
    {
        return EncryptWebUserID;
    }
    public final void setEncryptWebUserID(String value)
    {
        EncryptWebUserID = value;
    }


    private String ImgURL;
    public final String getImgURL()
    {
        return ImgURL;
    }
    public final void setImgURL(String value)
    {
        ImgURL = value;
    }


    private int MsgCount;
    public final int getMsgCount()
    {
        return MsgCount;
    }
    public final void setMsgCount(int value)
    {
        MsgCount = value;
    }


    private String Password;
    public final String getPassword()
    {
        return Password;
    }
    public final void setPassword(String value)
    {
        Password = value;
    }


    private String Username;
    public final String getUsername()
    {
        return Username;
    }
    public final void setUsername(String value)
    {
        Username = value;
    }


    private String WebUserAddress;
    public final String getWebUserAddress()
    {
        return WebUserAddress;
    }
    public final void setWebUserAddress(String value)
    {
        WebUserAddress = value;
    }


    private String WebUserBirthDayMiladi;
    public final String getWebUserBirthDayMiladi()
    {
        if(!ValidationTools.isEmptyOrNull(WebUserBirthDayMiladi)){
            WebUserBirthDayMiladi = WebUserBirthDayMiladi.split(" ")[0];
        }
        return WebUserBirthDayMiladi;
    }
    public final void setWebUserBirthDayMiladi(String value)
    {
        WebUserBirthDayMiladi = value;
    }


    private String WebUserBirthDayShamsi;
    public final String getWebUserBirthDayShamsi()
    {
        return WebUserBirthDayShamsi;
    }
    public final void setWebUserBirthDayShamsi(String value)
    {
        WebUserBirthDayShamsi = value;
    }


    private String WebUserFatherName;
    public final String getWebUserFatherName()
    {
        return WebUserFatherName;
    }
    public final void setWebUserFatherName(String value)
    {
        WebUserFatherName = value;
    }


    private String WebUserFnameE;
    public final String getWebUserFnameE()
    {
        return WebUserFnameE;
    }
    public final void setWebUserFnameE(String value)
    {
        WebUserFnameE = value;
    }


    private String WebUserFnameF;
    public final String getWebUserFnameF()
    {
        return WebUserFnameF;
    }
    public final void setWebUserFnameF(String value)
    {
        WebUserFnameF = value;
    }


    private boolean WebUserGender;
    public final boolean getWebUserGender()
    {
        return WebUserGender;
    }
    public final void setWebUserGender(boolean value)
    {
        WebUserGender = value;
    }


    private int WebUserID = -1;
    public final int getWebUserID()
    {
        return WebUserID;
    }
    public final void setWebUserID(int value)
    {
        WebUserID = value;
    }


    private String WebUserIDcardNo;
    public final String getWebUserIDcardNo()
    {
        return WebUserIDcardNo;
    }
    public final void setWebUserIDcardNo(String value)
    {
        WebUserIDcardNo = value;
    }


    private String WebUserLnameE;
    public final String getWebUserLnameE()
    {
        return WebUserLnameE;
    }
    public final void setWebUserLnameE(String value)
    {
        WebUserLnameE = value;
    }


    private String WebUserLnameF;
    public final String getWebUserLnameF()
    {
        return WebUserLnameF;
    }
    public final void setWebUserLnameF(String value)
    {
        WebUserLnameF = value;
    }


    private String WebUserMail;
    public final String getWebUserMail()
    {
        return WebUserMail;
    }
    public final void setWebUserMail(String value)
    {
        WebUserMail = value;
    }


    private String WebUserMobile;
    public final String getWebUserMobile()
    {
        return WebUserMobile;
    }
    public final void setWebUserMobile(String value)
    {
        WebUserMobile = value;
    }


    private String WebUserNameE;
    public final String getWebUserNameE()
    {
        return WebUserNameE;
    }
    public final void setWebUserNameE(String value)
    {
        WebUserNameE = value;
    }


    private String WebUserNameF;
    public final String getWebUserNameF()
    {
        return WebUserNameF;
    }
    public final void setWebUserNameF(String value)
    {
        WebUserNameF = value;
    }


    private String WebUserNationalCode;
    public final String getWebUserNationalCode()
    {
        return WebUserNationalCode;
    }
    public final void setWebUserNationalCode(String value)
    {
        WebUserNationalCode = value;
    }


    private String WebUserOccupation;
    public final String getWebUserOccupation()
    {
        return WebUserOccupation;
    }
    public final void setWebUserOccupation(String value)
    {
        WebUserOccupation = value;
    }


    private String WebUserTel;
    public final String getWebUserTel()
    {
        return WebUserTel;
    }
    public final void setWebUserTel(String value)
    {
        WebUserTel = value;
    }


}
