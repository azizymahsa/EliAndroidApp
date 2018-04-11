package com.eligasht.service.helper;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Const {
    public final static String BASEURL = "http://mobilews.eligasht.com/LightServices/Rest/";

    //*************start up*************
    public final static String StartUp = "Common/StaticDataService.svc/MobileAppStartupService";

    //*************hotel*************
    public final static String HotelAvail = "Hotel/HotelService.svc/HotelAvail";

    //*************hotel flight*************
    public final static String HotelFlightSearch = "HotelFlight/HotelFlightService.svc/HotelFlightSearch";
    public final static String LoadFlight = "HotelFlight/HotelFlightService.svc/LoadFlight";
    public final static String HotelPolicy = "Hotel/HotelService.svc/GetHotelPolicy";
    public final static String HoldSelectedRoom = "Hotel/HotelService.svc/HoldSelectedRoom";

    //*************flight*************
    public final static String AirportAvail = "Common/StaticDataService.svc/GetAirportWithParentsWithCulture";
    public final static String FlightSearchAvail = "Flight/FlightService.svc/SearchFlights";
    public final static String CheckFlightAvail = "Common/StaticDataService.svc/GetIsDomestic";
    public final static String PurchaseServiceFlightAvil="Common/StaticDataService.svc/PurchaseService";
    public final static String PurchaseFlightAvil="Flight/FlightService.svc/PurchaseFlight";
    public final static String ChangeFlightAvil="HotelFlight/HotelFlightService.svc/HotelPlusFlightChangeFlt";
    public final static String PreFactorDetailsAvil="Common/StaticDataService.svc/GetPreFactorDetails";
    //*************insurnace*************
    public final static String PurchaseInsuranceAvil="Insurance/InsuranceService.svc/PurchaseInsurance";
    public final static String PreFactorDetailsInsuranceAvil="Common/StaticDataService.svc/GetPreFactorDetails";
    public final static String GetCountryInsuranceAvil="Common/StaticDataService.svc/GetCountryAjaxWithCulture";
    //*************static page*************
    public final static String AboutAvil="Common/StaticDataService.svc/GetAboutUsWithCulture";
    public final static String ContactUsAvil="Common/StaticDataService.svc/GetContactUsWithCuture";


}
