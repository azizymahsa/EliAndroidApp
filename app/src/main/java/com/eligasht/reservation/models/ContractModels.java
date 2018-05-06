package com.eligasht.reservation.models;
/**
 * Created by Reza.nejati on 2/27/2018.
 */
public class ContractModels {
    public String num_contract;
    public String date;
    public String path;
    public String depart_date;
    public String login_date;
    public String sum_price;
    public String remained_price;
    public String follower;
    public String email;
    public String visaConfirm;
    public String visaIssue;
    public String ticketConfirm;
    public String ticketIssue;
    public String hotelConfirm;
    public String hotelIssue;
    public String documents;
    public String id;

    public ContractModels(String num_contract, String date, String path, String depart_date, String login_date, String sum_price, String remained_price, String follower, String email, String visaConfirm,
                          String visaIssue, String ticketConfirm, String ticketIssue, String hotelConfirm, String hotelIssue, String documents, String id) {
        this.num_contract = num_contract;
        this.date = date;
        this.path = path;
        this.depart_date = depart_date;
        this.login_date = login_date;
        this.sum_price = sum_price;
        this.remained_price = remained_price;
        this.follower = follower;
        this.email = email;
        this.visaConfirm = visaConfirm;
        this.visaIssue = visaIssue;
        this.ticketConfirm = ticketConfirm;
        this.ticketIssue = ticketIssue;
        this.hotelConfirm = hotelConfirm;
        this.hotelIssue = hotelIssue;
        this.documents = documents;
        this.id = id;
    }

    public String getNum_contract() {
        return num_contract;
    }

    public void setNum_contract(String num_contract) {
        this.num_contract = num_contract;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public String getSum_price() {
        return sum_price;
    }

    public void setSum_price(String sum_price) {
        this.sum_price = sum_price;
    }

    public String getRemained_price() {
        return remained_price;
    }

    public void setRemained_price(String remained_price) {
        this.remained_price = remained_price;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower (String follower) {
        this.follower = follower;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVisaConfirm() {
        return visaConfirm;
    }

    public void setVisaConfirm(String visaConfirm) {
        this.visaConfirm = visaConfirm;
    }

    public String getVisaIssue() {
        return visaIssue;
    }

    public void setVisaIssue(String visaIssue) {
        this.visaIssue = visaIssue;
    }

    public String getTicketConfirm() {
        return ticketConfirm;
    }

    public void setTicketConfirm(String ticketConfirm) {
        this.ticketConfirm = ticketConfirm;
    }

    public String getTicketIssue() {
        return ticketIssue;
    }

    public void setTicketIssue(String ticketIssue) {
        this.ticketIssue = ticketIssue;
    }

    public String getHotelConfirm() {
        return hotelConfirm;
    }

    public void setHotelConfirm(String hotelConfirm) {
        this.hotelConfirm = hotelConfirm;
    }

    public String getHotelIssue() {
        return hotelIssue;
    }

    public void setHotelIssue(String hotelIssue) {
        this.hotelIssue = hotelIssue;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
