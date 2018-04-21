package com.eligasht.reservation.models.hotel.adapter;
import com.eligasht.service.model.hotelflight.search.response.FltList;
import com.eligasht.service.model.hotelflight.search.response.Location;
import com.eligasht.service.model.hotelflight.search.response.Facility_;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Reza.nejati on 1/4/2018.
 */
public class SelectFlightHotelModel {
    private String name;
    private String city;
    private String title;
    private String board;
    private String price;
    private String imageUrl;
    private String location;
    private List<Boolean> booleans = new ArrayList<>();
    private int oldPrice;
    private int star;
    private int eHotelId;
    private String ResultUniqID;
    private boolean bestSell;
    private boolean isOff;
    private String off;
    private String TypeText;
    private List<Facility_> Facilities;
    private int diff;
    private List<FltList> Flights;
    private String ArrRout;
    private String DepRout;
    private String Amount;
    private List<Location> Locations;
    private String FlightId;

    public SelectFlightHotelModel(String name, String city, String title, String board, String price, String imageUrl, String location, int oldPrice, int star, int eHotelId, String resultUniqID, boolean bestSell, boolean isOff, String off, String typeText, List<Facility_> facilities, int diff, List<FltList> flights, String arrRout, String depRout, String amount, List<Location> locations, String flightId) {
        this.name = name;
        this.city = city;
        this.title = title;
        this.board = board;
        this.price = price;
        this.imageUrl = imageUrl;
        this.location = location;
        this.oldPrice = oldPrice;
        this.star = star;
        this.eHotelId = eHotelId;
        ResultUniqID = resultUniqID;
        this.bestSell = bestSell;
        this.isOff = isOff;
        this.off = off;
        TypeText = typeText;
        Facilities = facilities;
        this.diff = diff;
        Flights = flights;
        ArrRout = arrRout;
        DepRout = depRout;
        Amount = amount;
        Locations = locations;
        FlightId = flightId;
        booleans.add(false);
        booleans.add(false);
    }

    public List<Boolean> getBooleans() {
        return booleans;
    }

    public void setBooleans(List<Boolean> booleans) {
        this.booleans = booleans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int geteHotelId() {
        return eHotelId;
    }

    public void seteHotelId(int eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getResultUniqID() {
        return ResultUniqID;
    }

    public void setResultUniqID(String resultUniqID) {
        ResultUniqID = resultUniqID;
    }

    public boolean isBestSell() {
        return bestSell;
    }

    public void setBestSell(boolean bestSell) {
        this.bestSell = bestSell;
    }

    public boolean isOff() {
        return isOff;
    }

    public void setOff(boolean off) {
        isOff = off;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getTypeText() {
        return TypeText;
    }

    public void setTypeText(String typeText) {
        TypeText = typeText;
    }

    public List<Facility_> getFacilities() {
        return Facilities;
    }

    public void setFacilities(List<Facility_> facilities) {
        Facilities = facilities;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public List<FltList> getFlights() {
        return Flights;
    }

    public void setFlights(ArrayList<FltList> flights) {
        Flights = flights;
    }

    public String getArrRout() {
        return ArrRout;
    }

    public void setArrRout(String arrRout) {
        ArrRout = arrRout;
    }

    public String getDepRout() {
        return DepRout;
    }

    public void setDepRout(String depRout) {
        DepRout = depRout;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public List<Location> getLocations() {
        return Locations;
    }

    public void setLocations(List<Location> locations) {
        Locations = locations;
    }

    public String getFlightId() {
        return FlightId;
    }

    public void setFlightId(String flightId) {
        FlightId = flightId;
    }
}