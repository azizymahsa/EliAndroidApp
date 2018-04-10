package com.eligasht.reservation.models;
import com.eligasht.reservation.models.model.ModelRowCountRoom;

import java.util.ArrayList;
/**
 * Created by Reza.nejati on 4/10/2018.
 */
public class SingletonRoom {
    private ArrayList<ModelRowCountRoom> room;
    private static final SingletonRoom ourInstance = new SingletonRoom();

    public static SingletonRoom getInstance() {
        return ourInstance;
    }

    private SingletonRoom() {
    }

    public ArrayList<ModelRowCountRoom> getRoom() {
        if (room == null)
            room = new ArrayList<>();
        return room;
    }

    public void setRoom(ArrayList<ModelRowCountRoom> room) {
        this.room = room;
    }
}
