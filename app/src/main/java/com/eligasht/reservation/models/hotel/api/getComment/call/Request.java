package com.eligasht.reservation.models.hotel.api.getComment.call;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class Request {
    private String Culture;

    private String EHotelId;

    public String getCulture ()
    {
        return Culture;
    }

    public void setCulture (String Culture)
    {
        this.Culture = Culture;
    }

    public String getEHotelId ()
    {
        return EHotelId;
    }

    public void setEHotelId (String EHotelId)
    {
        this.EHotelId = EHotelId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Culture = "+Culture+", EHotelId = "+EHotelId+"]";
    }
}
