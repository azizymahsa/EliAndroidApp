package com.reserv.myapplicationeli.models.model;

public class Country {//AirportCode //AirportName//CityName ":
private String AirportCode;
private String AirportName;
private String CityName;


private String ParentId ;

private String AirportID ;

public String getCityName() {
	return CityName;
}
public void setCityName(String cityName) {
	CityName = cityName;
}
public String getAirportName() {
	return AirportName;
}
public void setAirportName(String airportName) {
	AirportName = airportName;
}
public String getAirportCode() {
	return AirportCode;
}
public void setAirportCode(String airportCode) {
	AirportCode = airportCode;
}
public String getParentId() {
	return ParentId;
}
public void setParentId(String parentId) {
	ParentId = parentId;
}
public String getAirportID() {
	return AirportID;
}
public void setAirportID(String airportID) {
	AirportID = airportID;
}
}