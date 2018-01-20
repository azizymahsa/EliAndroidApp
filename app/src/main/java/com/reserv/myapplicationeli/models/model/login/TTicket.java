package com.reserv.myapplicationeli.models.model.login;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/20/2018.
 */

public class TTicket {

    private ArrayList<Ticket> Tickets;
    public final ArrayList<Ticket> getTickets()
    {
        return Tickets;
    }
    public final void setTickets(ArrayList<Ticket> value)
    {
        Tickets = value;
    }

}
