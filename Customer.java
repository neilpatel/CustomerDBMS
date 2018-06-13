/*
*  Author: Neil Patel
*  GitHub: neilpatel
*  Project: Customer DBMS
*/

public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String county;
	private String company;
	private String phone;
	private String email;
	private String fax;
	private String web;
	
	public Customer(String firstName, String lastName, String company, String address, String city, String county, String state, String zip, String phone, String fax, String email, String web) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.county = county;
		this.company = company;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
		this.web = web;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZip() {
		return zip;
	}
	public String getCounty() {
		return county;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFax() {
		return fax;
	}
	
	public String getWeb() {
		return web;
	}
}