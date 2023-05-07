package com.hms.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.entities.HmsRoomBookings;
import com.hms.entities.HmsUsers;
import com.hms.payloads.HmsUsersReqBody;
import com.hms.repositories.HmsRoomBookingsRepo;
import com.hms.repositories.HmsUsersRepo;
import com.hms.utils.commonQueryAPIUtils;

@Service
public class RootServices {
	
	@Autowired
	HmsUsersRepo usersRepo;
	
	@Autowired
	HmsRoomBookingsRepo roomBookingsRepo;
	
	public ResponseEntity<?> postUsers(HmsUsersReqBody reqBody)
	{
		try {
			System.out.println(reqBody.getUser_email()+" "+reqBody.getUser_name()+" "+reqBody.getUser_password());
			List<Object> values= Arrays.asList(reqBody.getUser_email(),reqBody.getUser_name(),reqBody.getUser_password());
			List<String> names= Arrays.asList("Email Id","Name","Password");
			
			String msg = commonQueryAPIUtils.validationService(values, names);
			
			if (msg == "") {
				Integer emailCount = usersRepo.checkUser(reqBody.getUser_email());
				
				if (emailCount>0) {
					
					return commonQueryAPIUtils.failureResponse("Email Id Is Already Registered");
					
				} else {
					
					HmsUsers s = new HmsUsers();
					
					s.setUser_email(reqBody.getUser_email());
					s.setUser_name(reqBody.getUser_name());
					s.setUser_password(reqBody.getUser_password());
					
					usersRepo.save(s);
					
					return commonQueryAPIUtils.successResponse();

				}
			} else {
				return commonQueryAPIUtils.failureResponse(msg);
			}
			
		} catch (Exception e) {
			return commonQueryAPIUtils.failureResponse();
		}
	}
	
	public ResponseEntity<?> authUser(HmsUsersReqBody reqBody)
	{
		try {
			
			Integer isStudent = usersRepo.checkUser(reqBody.getUser_email());
			System.out.println(isStudent);
			
			if (isStudent>0) {
				System.out.println("student");
				
				Integer userCount = usersRepo.getUserCount(reqBody.getUser_email(), reqBody.getUser_password());
				
				if (userCount>0) {
					return commonQueryAPIUtils.manualResponse("01", "Authentication Success");
				} else {
					return commonQueryAPIUtils.manualResponse("02", "Invalid Login Credentials");
				}
				
			} else {
				 String email = "admin@hms.com";
				 String password = "admin@123";
				 
				 if (email.equals(reqBody.getUser_email()) && password.equals(reqBody.getUser_password())) {
						return commonQueryAPIUtils.manualResponse("01", "Authentication Success");
					} else {
						return commonQueryAPIUtils.manualResponse("02", "Invalid Login Credentials");
					}
				
			}
			
		} catch (Exception e) {
			return commonQueryAPIUtils.manualResponse("02", "Internal Server Issue !");
		}
	}
	
	public ResponseEntity<?> postRoomBookings(HmsRoomBookings reqBody)
	{
		try {
			
			List<Object> values= Arrays.asList(reqBody.getRoomId(),reqBody.getRoomId(),reqBody.getContactNumber(),reqBody.getPersonsCount(),reqBody.getOptedFood(),reqBody.getCountry(),reqBody.getNativeAddress(),reqBody.getStayFrom(),reqBody.getStayUpto());
			List<String> names= Arrays.asList("Room Type","Email","Contact Number","No of Persons Staying","Food Status","Country","Address","Stay From","Stay Upto");
			
			String msg = commonQueryAPIUtils.validationService(values, names);
			
			if (msg != "") {
				return commonQueryAPIUtils.failureResponse(msg);
			} else {
				
				reqBody.setBookingId(usersRepo.getBookingId());
				roomBookingsRepo.save(reqBody);
				
				return commonQueryAPIUtils.successResponse();
			}
		} catch (Exception e) {
			return commonQueryAPIUtils.failureResponse();
		}
	}

}
