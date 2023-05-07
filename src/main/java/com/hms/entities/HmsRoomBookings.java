package com.hms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HmsRoomBookings entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hms_room_bookings", schema = "public")

public class HmsRoomBookings{

	// Fields

	private Integer bookingId;
	private Integer roomId;
	private String userEmail;
	private String contactNumber;
	private String personsCount;
	private Boolean optedFood;
	private String country;
	private String nativeAddress;
	private Date stayFrom;
	private Date stayUpto;
	


	// Property accessors
	@Id

	@Column(name = "booking_id", unique = true, nullable = false)

	public Integer getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	@Column(name = "room_id")

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	@Column(name = "user_email")

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "contact_number")

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "persons_count")

	public String getPersonsCount() {
		return this.personsCount;
	}

	public void setPersonsCount(String personsCount) {
		this.personsCount = personsCount;
	}

	@Column(name = "opted_food")

	public Boolean getOptedFood() {
		return this.optedFood;
	}

	public void setOptedFood(Boolean optedFood) {
		this.optedFood = optedFood;
	}

	@Column(name = "country")

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "native_address")

	public String getNativeAddress() {
		return this.nativeAddress;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "stay_from", length = 13)

	public Date getStayFrom() {
		return this.stayFrom;
	}

	public void setStayFrom(Date stayFrom) {
		this.stayFrom = stayFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "stay_upto", length = 13)

	public Date getStayUpto() {
		return this.stayUpto;
	}

	public void setStayUpto(Date stayUpto) {
		this.stayUpto = stayUpto;
	}



}