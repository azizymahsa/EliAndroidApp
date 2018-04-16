
package com.eligasht.service.model.XPackage.response.searchXPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearchXPackage {

    @SerializedName("SearchXPackageResult")
    @Expose
    private SearchXPackageResult searchXPackageResult;

    public SearchXPackageResult getSearchXPackageResult() {
        return searchXPackageResult;
    }

    public void setSearchXPackageResult(SearchXPackageResult searchXPackageResult) {
        this.searchXPackageResult = searchXPackageResult;
    }

}
