package tp.model;

import java.util.UUID;


public class Person {

	private String id;
	private String name;
	private String surname;
	private String phone;
	
	private String idDocument;
	private String taxId;
	private Address addresses1;
	private Address addresses2;

	public String getId() {
		return id;
	}

	public Person(String name, String surname, String phone, String idDocument, String taxId, Address addresses1,
			Address addresses2) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.idDocument = idDocument;
		this.taxId = taxId;
		this.addresses1 = addresses1;
		this.addresses2 = addresses2;
		this.id = UUID.randomUUID().toString();
	}
	
	public Person() {
		this.addresses1 = new Address();
		this.addresses2 = new Address();
	}
	
	public Address getAddresses1() {
		return addresses1;
	}
	public void setAddresses1(Address addresses1) {
		this.addresses1 = addresses1;
	}
	public Address getAddresses2() {
		return addresses2;
	}
	public void setAddresses2(Address addresses2) {
		this.addresses2 = addresses2;
	}
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
