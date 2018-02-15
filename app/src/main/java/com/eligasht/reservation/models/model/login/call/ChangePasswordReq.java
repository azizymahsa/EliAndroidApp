package com.eligasht.reservation.models.model.login.call;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.WebUser;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class ChangePasswordReq {

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
    private com.eligasht.reservation.models.model.login.WebUser WebUser;
    public final WebUser getWebUser()
    {
        return WebUser;
    }
    public final void setWebUser(WebUser value)
    {
        WebUser = value;
    }
    private String OldPassword;
    public final String getOldPassword()
    {
        return OldPassword;
    }
    public final void setOldPassword(String value)
    {
        OldPassword = value;
    }
    private String NewPassword;
    public final String getNewPassword()
    {
        return NewPassword;
    }
    public final void setNewPassword(String value)
    {
        NewPassword = value;
    }

}
