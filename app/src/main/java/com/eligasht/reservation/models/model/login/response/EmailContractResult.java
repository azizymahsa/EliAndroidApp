package com.eligasht.reservation.models.model.login.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.Errors;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class EmailContractResult {
    private String Comments;

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    @SerializedName("Errors")
    @Expose
    private ArrayList<Errors> error;

    public ArrayList<Errors> getError() {
        return error;
    }

    public void setError(ArrayList<Errors> error) {
        this.error = error;
    }

    private String ResultKey;

    public String getResultKey() {
        return ResultKey;
    }

    public void setResultKey(String resultKey) {
        ResultKey = resultKey;
    }

    private String Warningss;

    public String getWarningss() {
        return Warningss;
    }

    public void setWarningss(String warningss) {
        Warningss = warningss;
    }

    private String ResultText;
    public final String getResultText()
    {
        return ResultText;
    }
    public final void setResultText(String value)
    {
        ResultText = value;
    }

    private int SuccessResult;
    public final int getSuccessResult()
    {
        return SuccessResult;
    }
    public final void setSuccessResult(int value)
    {
        SuccessResult = value;
    }

}
