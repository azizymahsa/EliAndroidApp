
package com.eligasht.service.model.flight.response.contactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseContactUs {

    @SerializedName("GetContactUsWithCutureResult")
    @Expose
    public GetContactUsWithCutureResult getContactUsWithCutureResult;

    public GetContactUsWithCutureResult getGetContactUsWithCutureResult() {
        return getContactUsWithCutureResult;
    }

    public void setGetContactUsWithCutureResult(GetContactUsWithCutureResult getContactUsWithCutureResult) {
        this.getContactUsWithCutureResult = getContactUsWithCutureResult;
    }

}
