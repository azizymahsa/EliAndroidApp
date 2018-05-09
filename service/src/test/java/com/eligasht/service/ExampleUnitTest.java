package com.eligasht.service;

import com.eligasht.service.model.test.markdown.HeaderTestService;
import com.eligasht.service.model.test.markdown.MarkDownGenerator;
import com.eligasht.service.model.test.markdown.ServiceTestModel;


import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
//        ServiceTestModel serviceTestModel = new ServiceTestModel();
//        serviceTestModel.setId(1);
//        serviceTestModel.setName("Common/StaticDataService.svc/MobileAppStartupService");
//        serviceTestModel.setMessage("unable to connect to database");
//        serviceTestModel.setTryTime("900 ms");
//        serviceTestModel.setStatusCode(200);
//        serviceTestModel.setTotalCall(253);
//        serviceTestModel.setClose(false);
//        serviceTestModel.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
//
////        Gson gson = new Gson();
////        System.out.println(gson.toJson(serviceTestModel));
//        List<ServiceTestModel> serviceTestModelList = new ArrayList<>();
//        serviceTestModelList.add(serviceTestModel);
//        serviceTestModelList.add(serviceTestModel);
//        serviceTestModelList.add(serviceTestModel);
//        HeaderTestService headerTestService = new HeaderTestService("Startup", serviceTestModelList);
//        HeaderTestService headerTestService2 = new HeaderTestService("Flight", serviceTestModelList);
//        HeaderTestService headerTestService3 = new HeaderTestService("Hotel", serviceTestModelList);
//        HeaderTestService headerTestService4 = new HeaderTestService("Hotelf", serviceTestModelList);
//        HeaderTestService headerTestService5 = new HeaderTestService("Package", serviceTestModelList);
//        MarkDownGenerator markDownGenerator = new MarkDownGenerator(532, headerTestService, headerTestService2, headerTestService3, headerTestService4, headerTestService5);
//
//        System.out.println(markDownGenerator.generate());
        // System.out.println(new Gson().toJson(markDownGenerator));
        // generateData();
       // System.out.println(simpleFormatDate("08 May 2018"));
    }




    private void generateData() {
        ///////////////////start up
        HeaderTestService headerTestServiceStartUp = new HeaderTestService("Startup", getStartup());
        HeaderTestService headerTestServicePublic = new HeaderTestService("Public", getPublic());
        HeaderTestService headerTestServiceFlight = new HeaderTestService("Flight", getFlightList());
        HeaderTestService headerTestServiceHotel = new HeaderTestService("Hotel", getHotelList());
        HeaderTestService headerTestServiceHotelf = new HeaderTestService("HotelFlight", getHotelFList());
        HeaderTestService headerTestServicePackage = new HeaderTestService("Package", getPackage());
        HeaderTestService headerTestServiceInsurance = new HeaderTestService("Insurance", getInsurance());
        HeaderTestService headerTestServiceStaticPage = new HeaderTestService("StaticPage", getStaticPage());
        HeaderTestService headerTestServiceLogin = new HeaderTestService("Login", getLogin());
        List<HeaderTestService> headerTestServiceArrayList = new ArrayList<>();
        headerTestServiceArrayList.add(headerTestServiceStartUp);
        headerTestServiceArrayList.add(headerTestServicePublic);
        headerTestServiceArrayList.add(headerTestServiceFlight);
        headerTestServiceArrayList.add(headerTestServiceHotel);
        headerTestServiceArrayList.add(headerTestServiceHotelf);
        headerTestServiceArrayList.add(headerTestServicePackage);
        headerTestServiceArrayList.add(headerTestServiceInsurance);
        headerTestServiceArrayList.add(headerTestServiceStaticPage);
        headerTestServiceArrayList.add(headerTestServiceLogin);
        MarkDownGenerator markDownGenerator = new MarkDownGenerator(0, headerTestServiceArrayList);
        // System.out.println(new Gson().toJson(markDownGenerator));
        System.out.println(markDownGenerator.generate());
    }

    private List<ServiceTestModel> getStartup() {
        ServiceTestModel MobileAppStartupService = new ServiceTestModel();
        MobileAppStartupService.setId(1);
        MobileAppStartupService.setName("Common/StaticDataService.svc/MobileAppStartupService");
        MobileAppStartupService.setMessage("ok");
        MobileAppStartupService.setTryTime("900 ms");
        MobileAppStartupService.setStatusCode(200);
        MobileAppStartupService.setTotalCall(0);
        MobileAppStartupService.setClose(true);
        MobileAppStartupService.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(MobileAppStartupService);
        return list;
    }

    private List<ServiceTestModel> getFlightList() {
        //flight
        ServiceTestModel f4 = new ServiceTestModel();
        f4.setId(1);
        f4.setName("Common/StaticDataService.svc/GetAirportWithParentsWithCulture");
        f4.setMessage("ok");
        f4.setTryTime("900 ms");
        f4.setStatusCode(200);
        f4.setTotalCall(0);
        f4.setClose(true);
        f4.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f5 = new ServiceTestModel();
        f5.setId(1);
        f5.setName("Flight/FlightService.svc/SearchFlights");
        f5.setMessage("ok");
        f5.setTryTime("900 ms");
        f5.setStatusCode(200);
        f5.setTotalCall(0);
        f5.setClose(true);
        f5.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f6 = new ServiceTestModel();
        f6.setId(1);
        f6.setName("Common/StaticDataService.svc/GetIsDomestic");
        f6.setMessage("ok");
        f6.setTryTime("900 ms");
        f6.setStatusCode(200);
        f6.setTotalCall(0);
        f6.setClose(true);
        f6.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f7 = new ServiceTestModel();
        f7.setId(1);
        f7.setName("Flight/FlightService.svc/PurchaseFlight");
        f7.setMessage("ok");
        f7.setTryTime("900 ms");
        f7.setStatusCode(200);
        f7.setTotalCall(0);
        f7.setClose(true);
        f7.setIssueLink("https://gitlab.com/nemati/eli/issues/1");


        ServiceTestModel f8 = new ServiceTestModel();
        f8.setId(1);
        f8.setName("HotelFlight/HotelFlightService.svc/HotelPlusFlightChangeFlt");
        f8.setMessage("ok");
        f8.setTryTime("900 ms");
        f8.setStatusCode(200);
        f8.setTotalCall(0);
        f8.setClose(true);
        f8.setIssueLink("https://gitlab.com/nemati/eli/issues/1");


        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        return list;
    }

    private List<ServiceTestModel> getHotelList() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Hotel/HotelService.svc/HotelAvail");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f2 = new ServiceTestModel();
        f2.setId(1);
        f2.setName("Hotel/HotelService.svc/GetRoomsList");
        f2.setMessage("ok");
        f2.setTryTime("900 ms");
        f2.setStatusCode(200);
        f2.setTotalCall(0);
        f2.setClose(true);
        f2.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f3 = new ServiceTestModel();
        f3.setId(1);
        f3.setName("Hotel/HotelService.svc/GetHotelDetail");
        f3.setMessage("ok");
        f3.setTryTime("900 ms");
        f3.setStatusCode(200);
        f3.setTotalCall(0);
        f3.setClose(true);
        f3.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f4 = new ServiceTestModel();
        f4.setId(1);
        f4.setName("Hotel/HotelService.svc/AddHotelReview");
        f4.setMessage("ok");
        f4.setTryTime("900 ms");
        f4.setStatusCode(200);
        f4.setTotalCall(0);
        f4.setClose(true);
        f4.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f5 = new ServiceTestModel();
        f5.setId(1);
        f5.setName("Common/StaticDataService.svc/GetHotelList");
        f5.setMessage("ok");
        f5.setTryTime("900 ms");
        f5.setStatusCode(200);
        f5.setTotalCall(0);
        f5.setClose(true);
        f5.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f6 = new ServiceTestModel();
        f6.setId(1);
        f6.setName("Hotel/HotelService.svc/GetHotelReview");
        f6.setMessage("ok");
        f6.setTryTime("900 ms");
        f6.setStatusCode(200);
        f6.setTotalCall(0);
        f6.setClose(true);
        f6.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        return list;
    }

    private List<ServiceTestModel> getHotelFList() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("HotelFlight/HotelFlightService.svc/HotelFlightSearch");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f2 = new ServiceTestModel();
        f2.setId(1);
        f2.setName("HotelFlight/HotelFlightService.svc/LoadFlight");
        f2.setMessage("ok");
        f2.setTryTime("900 ms");
        f2.setStatusCode(200);
        f2.setTotalCall(0);
        f2.setClose(true);
        f2.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f3 = new ServiceTestModel();
        f3.setId(1);
        f3.setName("Hotel/HotelService.svc/GetHotelPolicy");
        f3.setMessage("ok");
        f3.setTryTime("900 ms");
        f3.setStatusCode(200);
        f3.setTotalCall(0);
        f3.setClose(true);
        f3.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f4 = new ServiceTestModel();
        f4.setId(1);
        f4.setName("Hotel/HotelService.svc/HoldSelectedRoom");
        f4.setMessage("ok");
        f4.setTryTime("900 ms");
        f4.setStatusCode(200);
        f4.setTotalCall(0);
        f4.setClose(true);
        f4.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f5 = new ServiceTestModel();
        f5.setId(1);
        f5.setName("HotelFlight/HotelFlightService.svc/AirportTransportServicePrice");
        f5.setMessage("ok");
        f5.setTryTime("900 ms");
        f5.setStatusCode(200);
        f5.setTotalCall(0);
        f5.setClose(true);
        f5.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        return list;
    }

    private List<ServiceTestModel> getPackage() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Package/PackageService.svc/SearchXPackage");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f2 = new ServiceTestModel();
        f2.setId(1);
        f2.setName("Package/PackageService.svc/PurchasePackage");
        f2.setMessage("ok");
        f2.setTryTime("900 ms");
        f2.setStatusCode(200);
        f2.setTotalCall(0);
        f2.setClose(true);
        f2.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        return list;
    }

    private List<ServiceTestModel> getInsurance() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Insurance/InsuranceService.svc/PurchaseInsurance");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f4 = new ServiceTestModel();
        f4.setId(1);
        f4.setName("Insurance/InsuranceService.svc/ShowInsurance");
        f4.setMessage("ok");
        f4.setTryTime("900 ms");
        f4.setStatusCode(200);
        f4.setTotalCall(0);
        f4.setClose(true);
        f4.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f4);
        return list;
    }

    private List<ServiceTestModel> getStaticPage() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Common/StaticDataService.svc/GetAboutUsWithCulture");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f2 = new ServiceTestModel();
        f2.setId(1);
        f2.setName("Common/StaticDataService.svc/GetContactUsWithCuture");
        f2.setMessage("ok");
        f2.setTryTime("900 ms");
        f2.setStatusCode(200);
        f2.setTotalCall(0);
        f2.setClose(true);
        f2.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        return list;
    }

    private List<ServiceTestModel> getLogin() {
        //flight
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Common/StaticDataService.svc/Login");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        return list;
    }

    private List<ServiceTestModel> getPublic() {
        ServiceTestModel f1 = new ServiceTestModel();
        f1.setId(1);
        f1.setName("Common/StaticDataService.svc/PurchaseService");
        f1.setMessage("ok");
        f1.setTryTime("900 ms");
        f1.setStatusCode(200);
        f1.setTotalCall(0);
        f1.setClose(true);
        f1.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f2 = new ServiceTestModel();
        f2.setId(1);
        f2.setName("Common/StaticDataService.svc/GetCountryAjaxWithCulture");
        f2.setMessage("ok");
        f2.setTryTime("900 ms");
        f2.setStatusCode(200);
        f2.setTotalCall(0);
        f2.setClose(true);
        f2.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        ServiceTestModel f3 = new ServiceTestModel();
        f3.setId(1);
        f3.setName("Common/StaticDataService.svc/GetPreFactorDetails");
        f3.setMessage("ok");
        f3.setTryTime("900 ms");
        f3.setStatusCode(200);
        f3.setTotalCall(0);
        f3.setClose(true);
        f3.setIssueLink("https://gitlab.com/nemati/eli/issues/1");
        List<ServiceTestModel> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        return list;
    }
}