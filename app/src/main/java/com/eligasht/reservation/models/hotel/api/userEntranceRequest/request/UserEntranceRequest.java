package com.eligasht.reservation.models.hotel.api.userEntranceRequest.request;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class UserEntranceRequest {
    public final String IMEI;
    public final String ID;
    public final String IMSI;
    public final String SDKVersion;
    public final String DeviceName;
    public final String DeviceProduct;
    public final String AppVersion;
    public final int DeviceOSType ;
    public final String OperatorName;
    public final String Brand;
    public final String Culture;
    public final Identity identity;

    public UserEntranceRequest(String IMEI, String ID, String IMSI, String SDKVersion, String deviceName, String deviceProduct, String appVersion, int deviceOSType, String operatorName, String brand, String culture, Identity identity) {
        this.IMEI = IMEI;
        this.ID = ID;
        this.IMSI = IMSI;
        this.SDKVersion = SDKVersion;
        DeviceName = deviceName;
        DeviceProduct = deviceProduct;
        AppVersion = appVersion;
        DeviceOSType = deviceOSType;
        OperatorName = operatorName;
        Brand = brand;
        Culture = culture;
        this.identity = identity;
    }
}
