package tp.model;

import java.io.Serializable;

public class Address implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 291060865051886117L;
	 String homeNumberString;
	 String flatNumber;
	 String city;
	 String country;
	 String zip;
	 String street;
	
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Address() {
		super();
	}
	
	public Address(String homeNumberString, String flatNumber, String city, String country, String zip) {
		super();
		this.homeNumberString = homeNumberString;
		this.flatNumber = flatNumber;
		this.city = city;
		this.country = country;
		this.zip = zip;
	}

	public String getHomeNumberString() {
		return homeNumberString;
	}
	public void setHomeNumberString(String homeNumberString) {
		this.homeNumberString = homeNumberString;
	}
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	@Override
    public String toString() {
        return "Dupa";
    }
	
	
	
}
