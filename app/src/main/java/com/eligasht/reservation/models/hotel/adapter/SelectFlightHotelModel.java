package com.eligasht.reservation.models.hotel.adapter;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Facility;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Flights;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Location;
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
    private Double price;
    private String imageUrl;
    private String location;
    private List<Boolean> booleans = new ArrayList<>();
    private Double oldPrice;
    private int star;
    private int eHotelId;
    private String ResultUniqID;
    private boolean bestSell;
    private boolean isOff;
    private String off;
    private String TypeText;
    private List<Facility> Facilities;
    private int diff;
    private List<com.eligasht.service.model.newModel.hotelFlight.preSearch.response.FltList> Flights;
    private com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Flights FlightList;



    private String ArrRout;
    private String DepRout;
    private String Amount;
    private List<Location> Locations;
    private String FlightId;
    private String FlightOfferId;
    private String FlightGUID;
    private String CurrencyCode;



    public SelectFlightHotelModel(String name, String city, String title, String board, Double price, String imageUrl, String location, Double oldPrice, int star, int eHotelId, String resultUniqID, boolean bestSell, boolean isOff, String off, String typeText, List<Facility> facilities, int diff, List<com.eligasht.service.model.newModel.hotelFlight.preSearch.response.FltList> flights, String arrRout, String depRout, String amount, List<Location> locations, String flightId, String FlightOfferId, Flights FlightList, String FlightGUID, String CurrencyCode) {
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

        this.FlightOfferId = FlightOfferId;
        this.FlightList = FlightList;
        this.FlightGUID = FlightGUID;
        this.CurrencyCode = CurrencyCode;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getFlightGUID() {
        return FlightGUID;
    }

    public void setFlightGUID(String flightGUID) {
        FlightGUID = flightGUID;
    }
    public Flights getFlightList() {
        return FlightList;
    }

    public void setFlightList(Flights flightList) {
        FlightList = flightList;
    }

    public String getFlightOfferId() {
        return FlightOfferId;
    }

    public void setFlightOfferId(String flightOfferId) {
        FlightOfferId = flightOfferId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
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

    public List<Facility> getFacilities() {
        return Facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        Facilities = facilities;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public List<com.eligasht.service.model.newModel.hotelFlight.preSearch.response.FltList> getFlights() {
        return Flights;
    }

    public void setFlights(ArrayList<com.eligasht.service.model.newModel.hotelFlight.preSearch.response.FltList> flights) {
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