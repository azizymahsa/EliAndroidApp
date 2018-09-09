
package com.eligasht.service.model.survey.response.addServeyResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSurveyResultResult {

    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("ResultKey")
    @Expose
    private String resultKey;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("ResultText")
    @Expose
    private String resultText;
    @SerializedName("SuccessResult")
    @Expose
    private Integer successResult;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public Integer getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(Integer successResult) {
        this.successResult = successResult;
    }

}
