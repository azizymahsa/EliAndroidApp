package com.eligasht.reservation.models.model;

import com.eligasht.reservation.views.activities.transfer.ExcursionDta;

public class PurchaseFlightResult {
	private String CityEn;
	private String CityFa;
	private String Currency_ID;
	
	private String HasFlight;
	private String HasHotel;
	private String LoadDB;
	
	private String ServiceAdlPrice;
	private String ServiceChdPrice;
	private String ServiceDescEn;
	
	private String ServiceDescFa;
	private String ServiceID;
	private String ServiceImgURL;
	
	private String ServiceInfPrice;
	private String ServiceNameEn;
	private String ServiceNameFa;
	
	private String ServiceTypeEn;
	private String ServiceTypeFa;
	private String ServiceTypeID;
	
	private String ServiceTypeImgURL;
	private ExcursionDta excursionData;


	private String BookingCode;


	private long ServiceTotalPrice;

	public String getBookingCode() {
		return BookingCode;
	}

	public void setBookingCode(String bookingCode) {
		BookingCode = bookingCode;
	}

	boolean flag;
	private String SelectID; 
	public String getCityEn() {
		return CityEn;
	}
	public void setCityEn(String cityEn) {
		CityEn = cityEn;
	}
	public String getCityFa() {
		return CityFa;
	}
	public void setCityFa(String cityFa) {
		CityFa = cityFa;
	}
	public String getCurrency_ID() {
		return Currency_ID;
	}
	public void setCurrency_ID(String currency_ID) {
		Currency_ID = currency_ID;
	}
	public String getHasFlight() {
		return HasFlight;
	}
	public void setHasFlight(String hasFlight) {
		HasFlight = hasFlight;
	}
	public String getHasHotel() {
		return HasHotel;
	}
	public void setHasHotel(String hasHotel) {
		HasHotel = hasHotel;
	}
	public String getLoadDB() {
		return LoadDB;
	}
	public void setLoadDB(String loadDB) {
		LoadDB = loadDB;
	}
	public String getServiceAdlPrice() {
		return ServiceAdlPrice;
	}
	public void setServiceAdlPrice(String serviceAdlPrice) {
		ServiceAdlPrice = serviceAdlPrice;
	}
	public String getServiceChdPrice() {
		return ServiceChdPrice;
	}
	public void setServiceChdPrice(String serviceChdPrice) {
		ServiceChdPrice = serviceChdPrice;
	}
	public String getServiceDescEn() {
		return ServiceDescEn;
	}
	public void setServiceDescEn(String serviceDescEn) {
		ServiceDescEn = serviceDescEn;
	}
	public String getServiceDescFa() {
		return ServiceDescFa;
	}
	public void setServiceDescFa(String serviceDescFa) {
		ServiceDescFa = serviceDescFa;
	}
	public String getServiceID() {
		return ServiceID;
	}
	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}
	public String getServiceImgURL() {
		return ServiceImgURL;
	}
	public void setServiceImgURL(String serviceImgURL) {
		ServiceImgURL = serviceImgURL;
	}
	public String getServiceInfPrice() {
		return ServiceInfPrice;
	}
	public void setServiceInfPrice(String serviceInfPrice) {
		ServiceInfPrice = serviceInfPrice;
	}
	public String getServiceNameEn() {
		if (ServiceNameEn == null) {
			ServiceNameEn="";
		}
		return ServiceNameEn;
	}
	public void setServiceNameEn(String serviceNameEn) {
		ServiceNameEn = serviceNameEn;
	}
	public String getServiceNameFa() {
		return ServiceNameFa;
	}
	public void setServiceNameFa(String serviceNameFa) {
		ServiceNameFa = serviceNameFa;
	}
	public String getServiceTypeEn() {
		return ServiceTypeEn;
	}
	public void setServiceTypeEn(String serviceTypeEn) {
		ServiceTypeEn = serviceTypeEn;
	}
	public String getServiceTypeFa() {
		return ServiceTypeFa;
	}
	public void setServiceTypeFa(String serviceTypeFa) {
		ServiceTypeFa = serviceTypeFa;
	}
	public String getServiceTypeID() {
		return ServiceTypeID;
	}
	public void setServiceTypeID(String serviceTypeID) {
		ServiceTypeID = serviceTypeID;
	}
	public String getServiceTypeImgURL() {
		return ServiceTypeImgURL;
	}
	public void setServiceTypeImgURL(String serviceTypeImgURL) {
		ServiceTypeImgURL = serviceTypeImgURL;
	}
	public long getServiceTotalPrice() {
		return ServiceTotalPrice;
	}
	public void setServiceTotalPrice(long serviceTotalPrice) {
		ServiceTotalPrice = serviceTotalPrice;
	}
	public String getSelectID() {
		return SelectID;
	}
	public void setSelectID(String selectID) {
		SelectID = selectID;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ExcursionDta getExcursionData() {
		return excursionData;
	}

	public void setExcursionData(ExcursionDta excursionData) {
		this.excursionData = excursionData;
	}
}
