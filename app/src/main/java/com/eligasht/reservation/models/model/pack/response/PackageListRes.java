package com.eligasht.reservation.models.model.pack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.pack.SearchXPackageResult;

/**
 * Created by elham.bonyani on 1/7/2018.
 */

public class PackageListRes {
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
