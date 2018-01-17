package com.reserv.myapplicationeli.models.model;

public class Country {


	private String CountryCode;
	private int CountryID;
	private boolean CountryIsDefault;
	private String CountryNameEn;
	private String CountryNameFa;
	private boolean HasInsurance;

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public int getCountryID() {
		return CountryID;
	}

	public void setCountryID(int countryID) {
		CountryID = countryID;
	}

	public boolean isCountryIsDefault() {
		return CountryIsDefault;
	}

	public void setCountryIsDefault(boolean countryIsDefault) {
		CountryIsDefault = countryIsDefault;
	}

	public String getCountryNameEn() {
		return CountryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		CountryNameEn = countryNameEn;
	}

	public String getCountryNameFa() {
		return CountryNameFa;
	}

	public void setCountryNameFa(String countryNameFa) {
		CountryNameFa = countryNameFa;
	}

	public boolean isHasInsurance() {
		return HasInsurance;
	}

	public void setHasInsurance(boolean hasInsurance) {
		HasInsurance = hasInsurance;
	}
}