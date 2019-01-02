package com.nana.model;

import java.util.Date;

public class Customer {
	
	
	private int custID;
	private String title;
	private String fname;
	private String lname;
	private String gender;
	private String address;
	private String state;
	private String country;
	private String phone;
	private Date checkin;
  	private Date checkout;
  	private int roomNo;
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(Date date) {
		this.checkin = date;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	@Override
	public String toString() {
		return "Customer [custID=" + custID + ", title=" + title + ", fname=" + fname + ", lname=" + lname + ", gender="
				+ gender + ", address=" + address + ", state=" + state + ", country=" + country + ", phone=" + phone
				+ ", checkin=" + checkin + ", checkout=" + checkout + ", roomNo=" + roomNo + "]";
	}
  	
  	

}
