package tp.model;

import java.util.ArrayList;


public class Person {

	private String name;
	private String surname;
	private String phone;
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
	private String idDocument;
	private String taxId;
	private ArrayList<Address> addresses;
	
	public String getIdDocument() {
		return idDocument;
	}
	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phoneString) {
		this.phone = phoneString;
	}
	public String getName() {
		return name;
	}
	public void setName(String nameString) {
		this.name = nameString;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surnameString) {
		this.surname = surnameString;
	}
}
