package com.eligasht.service.model;
import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by Reza Nejati on 21,April,2018
 */
public class BaseModel  {
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
