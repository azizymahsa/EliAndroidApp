
package com.eligasht.service.model.newModel.train.domesticTrainGetPrice.request;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDomesticTrainGetPrice {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("TrainID")
    @Expose
    private String trainID;
    @SerializedName("TrainSegmentIds")
    @Expose
    private List<String> trainSegmentIds = null;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public List<String> getTrainSegmentIds() {
        return trainSegmentIds;
    }

    public void setTrainSegmentIds(List<String> trainSegmentIds) {
        this.trainSegmentIds = trainSegmentIds;
    }

    public void setTrainSegmentIds(String segmengt_id_false, String segmengt_id_true) {
        trainSegmentIds=new ArrayList<>();
        trainSegmentIds.add(segmengt_id_false);
        trainSegmentIds.add(segmengt_id_true);
    }
}
