package com.kh.spring06.dto;
//등록 없이 필요할 때마다 만들어서 사용
public class CountryDto {
private int countryNo;
private String countryRegion;
private String countryName;
private String countryCapital;
private long countryPopulation;
public CountryDto() {
	super();
}
@Override
public String toString() {
	return "CountryDto [countryNo=" + countryNo + ", countryRegion=" + countryRegion + ", countryName=" + countryName
			+ ", countryCapital=" + countryCapital + ", countryPopulation=" + countryPopulation + "]";
}
public int getCountryNo() {
	return countryNo;
}
public void setCountryNo(int countryNo) {
	this.countryNo = countryNo;
}
public String getCountryRegion() {
	return countryRegion;
}
public void setCountryRegion(String countryRegion) {
	this.countryRegion = countryRegion;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public String getCountryCapital() {
	return countryCapital;
}
public void setCountryCapital(String countryCapital) {
	this.countryCapital = countryCapital;
}
public long getCountryPopulation() {
	return countryPopulation;
}
public void setCountryPopulation(long countryPopulation) {
	this.countryPopulation = countryPopulation;
}



}
