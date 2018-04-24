package com.eligasht.service.model.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmad.nemati on 4/23/2018.
 */
public class MarkDownGenerator {
    @SerializedName("totalRun")
    @Expose
    private Integer totalRun;
    @SerializedName("headerTestServices")
    @Expose
    private List<HeaderTestService> headerTestServices = null;


    public MarkDownGenerator(int totalRun, List<HeaderTestService> headerTestServices) {
        this.totalRun = totalRun;
        this.headerTestServices = headerTestServices;
    }

    public Integer getTotalRun() {
        return totalRun;
    }

    public void setTotalRun(Integer totalRun) {
        this.totalRun = totalRun;
    }

    public List<HeaderTestService> getHeaderTestServices() {
        return headerTestServices;
    }

    public void setHeaderTestServices(List<HeaderTestService> headerTestServices) {
        this.headerTestServices = headerTestServices;
    }

    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateTotalRuns())
                .append("\n")
                .append(generateIntroHeader())
                .append("\n")
                .append(generateService());
        return stringBuilder.toString();


    }

    private String generateTotalRuns() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# Total Runs ====>")
                .append(totalRun);
        return stringBuilder.toString();
    }

    private String generateIntroHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## Services").append("\n");
        for (int i = 0; i < headerTestServices.size(); i++) {
            stringBuilder.append(" - [")
                    .append(headerTestServices.get(i).getHeaderName())
                    .append("](#")
                    .append(headerTestServices.get(i).getHeaderName())
                    .append(")")
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private String generateService() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HeaderTestService headerTestService : headerTestServices) {
            stringBuilder
                    .append("\n")
                    .append(generateRootService(headerTestService))
                    .append("\n");

            for (int j = 0; j < headerTestService.getServiceTestModel().size(); j++) {
                stringBuilder
                        .append(generateItemService(headerTestService.getServiceTestModel().get(j)))
                        .append("\n");
            }
        }

        return stringBuilder.toString();

    }

    private String generateRootService(HeaderTestService headerTestService) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<h3><a name=\"")
                .append(headerTestService.getHeaderName())
                .append("\"></a>")
                .append(headerTestService.getHeaderName())
                .append("</h3>").append("").append("\n")
                .append("\n")
                .append(" Name | Message | Try Time  |  Status Code  | Total Calls | Status | Issues")
                .append("\n")
                .append("--- | --- | --- | --- | --- | --- | ---");
        return stringBuilder.toString();

    }

    private String generateItemService(ServiceTestModel serviceTestModel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(serviceTestModel.getName())
                .append(" ")
                .append("|")
                .append(" ")
                .append(serviceTestModel.getMessage())
                .append(" ")
                .append("|")
                .append(" ")
                .append(serviceTestModel.getTryTime())
                .append(" ")
                .append("|")
                .append(" ")
                .append(serviceTestModel.getStatusCode())
                .append(" ")
                .append("|")
                .append(" ")
                .append(serviceTestModel.getTotalCall())
                .append(" ")
                .append("|")
                .append(" ")
                .append(serviceTestModel.getClose() ? ":white_check_mark:" : ":x:")
                .append(" ")
                .append("|")
                .append(" ")
                .append("[:arrow_upper_right:]")
                .append("(")
                .append(serviceTestModel.getIssueLink())
                .append(")");
        return stringBuilder.toString();

    }


}
