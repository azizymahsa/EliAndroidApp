
package com.eligasht.service.model.newModel.train.domesticSearch.response;

import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.Cost;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengerService {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Price")
    @Expose
    private Cost price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cost getPrice() {
        return price;
    }

    public void setPrice(Cost price) {
        this.price = price;
    }

}
