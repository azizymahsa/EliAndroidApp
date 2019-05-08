package com.eligasht.service.helper;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class Const {

    public final static Boolean MOCK = false;

    public static Boolean TEST=false;

      // public final static String BASEURL = "http://mobilews.eligasht.com/LightServices/Rest/";
      public static String BASEURL = "http://192.168.1.82:120";
      public  static String TOKEN = "";

      public final static String BaseGitLab="https://gitlab.com/api/v4/projects/6147852/";



    //*************start up new*************
    public final static String StartUp = "api/ApplicationStartUp/Post";
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
    public final static String CheckValidSurveyAvil = "Survey/SurveyService.svc/CheckValidResultDetail";
    //*************NEW******************************************************************
    //*************Flight*****************
    public final static String GetNewAirports = "api/LoadAndFillAPI/Airports";
    public final static String GetNewFlightSearch = "api/FlightServiceAPI/Search";
    public final static String GetNewConfirmFlightPrice = "api/FlightServiceAPI/ConfirmFlightPrice";
    public final static String GetNewPurchaseFlight = "api/FlightServiceAPI/PurchaseFlight";
    public final static String GetNewServices = "api/ServicesServiceAPI/GetServices";
    public final static String GetNewPurchaseServices = "api/ServicesServiceAPI/PurchaseServices";
    public final static String GetNewPreFactServices = "api/Prefactory/GetPreFactor";
    public final static String GetNewPromotionCode = "api/Prefactory/DiscountCodeControlller";

    //*************Hotel*****************
    public final static String GetNewHotelCities = "api/LoadAndFillAPI/HotelCities";
    public final static String GetNewHotelPreSearch = "api/HotelServiceAPI/PreSearch";
    public final static String GetNewHotelSearch = "api/HotelServiceAPI/Search";
    public final static String GetNewHotelDetail = "api/HotelServiceAPI/GetHotelDetail";
    public final static String GetNewHotelRoom = "api/HotelServiceAPI/GetRoomsList";
    public final static String GetNewHotelPolicy = "api/HotelServiceAPI/GetHotelPolicy";
    public final static String GetNewHoldSelectRoom = "api/HotelServiceAPI/HoldSelectedRoom";
    public final static String GetNewHotelReview = "api/HotelServiceAPI/AddHotelReview";
    public final static String GetNewHotelBasket = "api/HotelServiceAPI/GetSelectedHotelFlightBasket";

    public final static String GetNewHotelFlightSearch = "api/FlightHotelServiceAPI/PreSearchFlightHotel";
    public final static String GetNewHotelFlightReserve = "api/FlightHotelServiceApi/ReserveFlightHotel";
    public final static String GetNewHotelPurchaseReserve = "api/HotelServiceApi/Purchase";

    public final static String GetNewChangeFlight = "api/FlightHotelServiceApi/ChangeFlight";
    public final static String GetNewLoadFlight = "api/FlightHotelServiceApi/LoadFlight";

    //*************Train*****************
    public final static String GetNewTrainStat = "api/LoadAndFillAPI/TrainStations";
    public final static String GetNewTrainSearch = "api/DomesticTrainAPI/Search";
    public final static String GetNewTrainGetPrice = "api/DomesticTrainAPI/DomesticTrainGetPrice";
    public final static String GetNewTrainPurchase = "api/DomesticTrainAPI/Purchase";
    //*************Insurance*****************
    public final static String GetNewInsuranceCountries = "api/LoadAndFillAPI/InsuranceCountries";
    public final static String GetNewInsuranceSearchResult = "api/InsuranceAPI/InsuranceSearchResultViewModel";
    public final static String GetNewInsurancePurchase = "api/InsuranceAPI/Purchase";
   //*************Package*****************
   public final static String GetNewPackageCities = "api/LoadAndFillAPI/GetPackageCities";
   public final static String GetNewPackageBasket = "api/PackageServiceAPI/GetPackageBasket";
   public final static String GetNewPurchasePackage = "api/PackageServiceAPI/PurchasePackage";


   public final static String GetNewHotelNames = "api/LoadAndFillAPI/HotelNames";


   //*************Auth*****************
   public final static String GetToken = "token";

   //*************Login Contact*****************
   public final static String GetRegisterUser = "api/UserAutentication/RegisterUser";
   public final static String GetRegisterActivation = "api/UserAutentication/RegisterActivation";
   public final static String GetReSendActivation = "api/UserAutentication/ReSendActivation";
}
