package com.hms.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.entities.HmsRoomBookings;
import com.hms.payloads.HmsUsersReqBody;
import com.hms.repositories.HmsUsersRepo;
import com.hms.services.RootServices;
import com.hms.utils.commonQueryAPIUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "HMS APIs", description = "Root Controller")
public class RootController {
	
	@Autowired
	RootServices rootServices;
	
	@Autowired
	HmsUsersRepo usersRepo;
	
	
	@PostMapping("/v1/users")
	 ResponseEntity<?> postUsers(@RequestBody HmsUsersReqBody reqBody)
	{
		return rootServices.postUsers(reqBody);
	}
	
	@PostMapping("/v1/auth")
	ResponseEntity<?> authUser(@RequestBody HmsUsersReqBody reqBody)
	{
		return rootServices.authUser(reqBody);
	}
	
	@GetMapping("/v1/services")
	Map<String, Object> getServices(@RequestParam String email)
	{
		Integer isUser = usersRepo.checkUser(email);
		String serviceFor = "";
		if(isUser>0) {serviceFor="user";}else if(email.equals("admin@hms.com")) {serviceFor="admin";}
		
		return commonQueryAPIUtils.apiService("services", usersRepo.getServices(serviceFor));
	}
	
	@GetMapping("/v1/rooms")
	Map<String, Object> getRooms()
	{
		return commonQueryAPIUtils.apiService("rooms", usersRepo.getRooms());
	}
	
	@PostMapping("/v1/book-room")
	ResponseEntity<?> postRoomBookings(@RequestBody HmsRoomBookings reqBody)
	{
		return rootServices.postRoomBookings(reqBody);
	}
	
	@GetMapping("/v1/bookings")
	Map<String, Object> getBookings(@RequestParam String email)
	{
		return commonQueryAPIUtils.apiService("bookings", usersRepo.getBookings(email));
	}
	
	@DeleteMapping("/v1/del-booking")
	Map<String, Object> deleteBooking(@RequestParam Integer booking_id)
	{
		return commonQueryAPIUtils.apiServiceDelete("", usersRepo.deleteBooking(booking_id));
	}

	@GetMapping("/v1/all-bookings")
	Map<String, Object> getAllBookings()
	{
		return commonQueryAPIUtils.apiService("bookings", usersRepo.getAllBookings());
	}
	
	@PutMapping("/v1/update-booking")
	Map<String, Object> updateBooking(@RequestParam Boolean is_approoved,@RequestParam Integer booking_id)
	{
		return commonQueryAPIUtils.apiServiceUpdate("", usersRepo.updateBooking(is_approoved, booking_id));
	}
	
}
