package com.reserv.myapplicationeli.models.hotel.api.userEntranceRequest.request;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class UserEntranceRequest {
    public final String IMEI;
    public final String IMSI;
    public final String SDKVersion;
    public final String DeviceName;
    public final String DeviceProduct;
    public final int AppVersion;
    public final int DeviceOSType ;
    public final String OperatorName;
    public final String Brand;

    public UserEntranceRequest(String IMEI, String IMSI, String SDKVersion, String deviceName, String deviceProduct, int appVersion, int deviceOSType, String operatorName, String brand) {
        this.IMEI = IMEI;
        this.IMSI = IMSI;
        this.SDKVersion = SDKVersion;
        DeviceName = deviceName;
        DeviceProduct = deviceProduct;
        AppVersion = appVersion;
        DeviceOSType = deviceOSType;
        OperatorName = operatorName;
        Brand = brand;
    }
}
