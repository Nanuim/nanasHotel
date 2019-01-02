package com.nana.model;

import java.util.Date;

public class User {
	
	private int employeeId;
	private String fname;
	private String lname;
	private String gender;
	private String department;
	private String position;
	private double  salary;
	private Date bdate;
	private Date joinedDate;
	private String username;
	private String emppass;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmppass() {
		return emppass;
	}
	public void setEmppass(String emppass) {
		this.emppass = emppass;
	}
	@Override
	public String toString() {
		return "User [employee_id=" + employeeId + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender
				+ ", department=" + department + ", position=" + position + ", salary=" + salary + ", bdate=" + bdate
				+ ", joinedDate=" + joinedDate + ", username=" + username + ", emppass=" + emppass + "]";
	}
	

	
	
}
