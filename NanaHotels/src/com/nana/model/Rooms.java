package com.nana.model;

public class Rooms {
	
	private int roomNo;//Room Number from Db
	private String roomType;//field from DB
	private int bedNo;//field from DB
	private double roomRate;//field from DB
	private String roomLocation;//field from DB
	private String status;//field from DB
	
	public Rooms() {
		super();
	
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getBedNo() {
		return bedNo;
	}
	public void setBedNo(int bedNo) {
		this.bedNo = bedNo;
	}
	public double getRoomRate() {
		return roomRate;
	}
	public void setRoomRate(double roomRate) {
		this.roomRate = roomRate;
	}
	public String getRoomLocation() {
		return roomLocation;
	}
	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomType=" + roomType + ", bedNo=" + bedNo + ", roomRate=" + roomRate
				+ ", roomLocation=" + roomLocation + ", status=" + status + "]";
	}
	
		
	
	

}
