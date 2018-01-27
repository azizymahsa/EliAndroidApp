package com.reserv.myapplicationeli.models.model.login.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class EmailContractReq {

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
    private String EncryptedContractID;
    public final String getEncryptedContractID()
    {
        return EncryptedContractID;
    }
    public final void setEncryptedContractID(String value)
    {
        EncryptedContractID = value;
    }
    private String Body;
    public final String getBody()
    {
        return Body;
    }
    public final void setBody(String value)
    {
        Body = value;
    }
    private String RecieverEmail;
    public final String getRecieverEmail()
    {
        return RecieverEmail;
    }
    public final void setRecieverEmail(String value)
    {
        RecieverEmail = value;
    }
    private String Subject;
    public final String getSubject()
    {
        return Subject;
    }
    public final void setSubject(String value)
    {
        Subject = value;
    }


}
