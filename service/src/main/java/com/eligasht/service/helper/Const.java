package com.eligasht.service.helper;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Const {

    public final static Boolean MOCK = false;

    public static Boolean TEST=false;

    public final static String BASEURL = "http://mobilews.eligasht.com/LightServices/Rest/";

    public final static String BaseGitLab="https://gitlab.com/api/v4/projects/6147852/";

    //*************start up*************
    public final static String StartUp = "Common/StaticDataService.svc/MobileAppStartupService";
    //*************profile & login*************
    public final static String Login = "Common/StaticDataService.svc/Login";
    //*************hotel*************
    public final static String HotelAvail = "Hotel/HotelService.svc/HotelAvail";
    public final static String GetRoomsList = "Hotel/HotelService.svc/GetRoomsList";
    public final static String GetHotelDetail = "Hotel/HotelService.svc/GetHotelDetail";
    public final static String AddHotelReview = "Hotel/HotelService.svc/AddHotelReview";
    public final static String GetHotelList = "Common/StaticDataService.svc/GetHotelList";
    public final static String GetHotelReview = "Hotel/HotelService.svc/GetHotelReview";
    //*************hotel flight*************
    public final static String HotelFlightSearch = "HotelFlight/HotelFlightService.svc/HotelFlightSearch";
    public final static String LoadFlight = "HotelFlight/HotelFlightService.svc/LoadFlight";
    public final static String HotelPolicy = "Hotel/HotelService.svc/GetHotelPolicy";
    public final static String HoldSelectedRoom = "Hotel/HotelService.svc/HoldSelectedRoom";
    public final static String AirportTransportServicePrice = "HotelFlight/HotelFlightService.svc/AirportTransportServicePrice";
    public final static String HotelFlightPurchaseServic = "Common/StaticDataService.svc/PurchaseService";
    public final static String PurchaseFlightHotel = "HotelFlight/HotelFlightService.svc/PurchaseFlightHotel";

    //*************flight*************
    public final static String AirportAvail = "Common/StaticDataService.svc/GetAirportWithParentsWithCulture";
    public final static String FlightSearchAvail = "Flight/FlightService.svc/SearchFlights";
    public final static String CheckFlightAvail = "Common/StaticDataService.svc/GetIsDomestic";
    public final static String PurchaseServiceFlightAvil = "Common/StaticDataService.svc/PurchaseService";
    public final static String PurchaseFlightAvil = "Flight/FlightService.svc/PurchaseFlight";
    public final static String ChangeFlightAvil = "HotelFlight/HotelFlightService.svc/HotelPlusFlightChangeFlt";
    public final static String PreFactorDetailsAvil = "Common/StaticDataService.svc/GetPreFactorDetails";
    //*************insurnace*************
    public final static String PurchaseInsuranceAvil = "Insurance/InsuranceService.svc/PurchaseInsurance";
    public final static String PreFactorDetailsInsuranceAvil = "Common/StaticDataService.svc/GetPreFactorDetails";
    public final static String GetCountryInsuranceAvil = "Common/StaticDataService.svc/GetCountryAjaxWithCulture";
    public final static String SearchInsuranceAvil = "Insurance/InsuranceService.svc/ShowInsurance";
    //*************static page*************
    public final static String AboutAvil = "Common/StaticDataService.svc/GetAboutUsWithCulture";
    public final static String ContactUsAvil = "Common/StaticDataService.svc/GetContactUsWithCuture";
    //*************pachage*****************
    public final static String SearchXPackageAvil = "Package/PackageService.svc/SearchXPackage";
    public final static String PurchasePackageAvil = "Package/PackageService.svc/PurchasePackage";
    public final static String GetPreFactorDetailsPackageAvil = "Common/StaticDataService.svc/GetPreFactorDetails";
    //*************survey*****************
    public final static String GetSurveyAvil = "Survey/SurveyService.svc/GetSurvey";
    public final static String GetSurveyDetailsAvil = "Survey/SurveyService.svc/GetSurveyDetails";
    public final static String AddSurveyResultAvil = "Survey/SurveyService.svc/AddSurveyResult";
}
