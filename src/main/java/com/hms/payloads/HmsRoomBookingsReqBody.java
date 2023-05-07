package com.hms.payloads;

import java.util.Date;

public class HmsRoomBookingsReqBody {

	private Integer roomId;
	private String userEmail;
	private String contactNumber;
	private String personsCount;
	private Boolean optedFood;
	private String country;
	private String nativeAddress;
	private Date stayFrom;
	private Date stayUpto;
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPersonsCount() {
		return personsCount;
	}
	public void setPersonsCount(String personsCount) {
		this.personsCount = personsCount;
	}
	public Boolean getOptedFood() {
		return optedFood;
	}
	public void setOptedFood(Boolean optedFood) {
		this.optedFood = optedFood;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNativeAddress() {
		return nativeAddress;
	}
	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}
	public Date getStayFrom() {
		return stayFrom;
	}
	public void setStayFrom(Date stayFrom) {
		this.stayFrom = stayFrom;
	}
	public Date getStayUpto() {
		return stayUpto;
	}
	public void setStayUpto(Date stayUpto) {
		this.stayUpto = stayUpto;
	}
	
	
}
