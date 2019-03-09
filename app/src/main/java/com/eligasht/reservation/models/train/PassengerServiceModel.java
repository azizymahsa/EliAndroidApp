package com.eligasht.reservation.models.train;

public class PassengerServiceModel {

    private String Code;
    private String  Description;
    private String Name;
    private String  Price;

    public PassengerServiceModel(String code, String description, String name, String price) {
        Code = code;
        Description = description;
        Name = name;
        Price = price;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
