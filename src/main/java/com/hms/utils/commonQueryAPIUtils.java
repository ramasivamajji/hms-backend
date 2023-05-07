package com.hms.utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class commonQueryAPIUtils {
	

	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	public static Map<String, Object> apiService(String apiServiceName, List<Map<String, Object>> repomethod)
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		if(repomethod.size()>0)
		{
			final_data.put("status", true);
			final_data.put(apiServiceName, repomethod);
		}
		
		else {
			final_data.put("status", false);
			final_data.put(apiServiceName, "No data found");
		}
		return final_data;
	}
	
	
	public static Map<String, Object> apiServiceMulti(List<String> apiServiceNameList, List<List<Map<String, Object>>> repomethodList)
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		String apiServiceName = "";
		
		for (int i = 0; i < repomethodList.size(); i++) 
		{
			apiServiceName = apiServiceNameList.get(i);
		
			if(repomethodList.get(i).size()>0)
			{
				final_data.put(apiServiceName+"Status", true);
				final_data.put(apiServiceName, repomethodList.get(i));
			}
			
			else {
				final_data.put(apiServiceName+"Status", false);
				final_data.put(apiServiceName, "No data found");
			}
		}
		
		return final_data;
		
		//************************************ Sample Controller *****************************
		/*
		 * @GetMapping("/t") 
		 * Map<String, Object> getMethod(@RequestParam Integer reqParam) 
		 * {
		 * List<List<Map<String, Object>>> mulDataList = List.of(repo.get1(reqParam),repo.get2(reqParam)); 
		 * List<String> mulNamesList = (List.of("get1Name","get2Name")); 
		 * return commonQueryAPIUtils.apiServiceMulti(mulNamesList, mulDataList); 
		 * }
		 */
	}
	
	public static Map<String, Object> apiServiceDelete(String errorMessage,Integer deleteQueryfromRepo)
	
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		try {
			
			if (errorMessage.equals("") && deleteQueryfromRepo>0) {
				
					final_data.put("scode", "01");
					final_data.put("sdesc", "Successfully Deleted");
					
				}
			
			else {
					
					final_data.put("scode", "03");
					final_data.put("sdesc", "Deletion Failed Due To: " + (errorMessage.equals("") ? "Internal Server Issue" : errorMessage  ));

				}
					
		} catch (Exception e) {
			
			e.printStackTrace();
			
			final_data.put("scode", "02");
			final_data.put("sdesc", "Deletion Failed Due To: Internal Server Issue");
		}
		
		return final_data;
	}
	
	public static Map<String, Object> apiServiceUpdate(String errorMessage,Integer updateQueryfromRepo)
	
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		try {
			
			if (errorMessage.equals("") && updateQueryfromRepo>0) {
				
					final_data.put("scode", "01");
					final_data.put("sdesc", "Successfully Updated");
					
				}
			
			else {
					
					final_data.put("scode", "03");
					final_data.put("sdesc", "Updation Failed Due To: " + (errorMessage.equals("") ? "Internal Server Issue" : errorMessage  ));

				}
					
		} catch (Exception e) {
			
			e.printStackTrace();
			
			final_data.put("scode", "02");
			final_data.put("sdesc", "Updation Failed Due To: Internal Server Issue");
		}
		
		return final_data;
	}
	
	public static ResponseEntity<?> successResponse()
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		final_data.put("scode", "01");
		final_data.put("sdesc", "Successfully Submitted");
		
		return ResponseEntity.ok().body(final_data);
	}
	
	public static ResponseEntity<?> failureResponse()
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		final_data.put("scode", "02");
		final_data.put("sdesc", "Submission Failed Due To: Internal Server Issue");
		
		return ResponseEntity.ok().body(final_data);
	}
	
	public static ResponseEntity<?> manualResponse(String code,String message)
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		final_data.put("scode", code);
		final_data.put("sdesc", message);
		
		return ResponseEntity.ok().body(final_data);
	}
	
	public static ResponseEntity<?> failureResponse(String errorMessage)
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		if(errorMessage == "02")
		{
			final_data.put("scode", "02");
			final_data.put("sdesc", "Submission Failed Due To: Internal Server Issue");
		}
		else {
			final_data.put("scode", "03");
			final_data.put("sdesc", "Submission Failed Due To: " + (errorMessage.equals("") ? "Internal Server Issue !" : errorMessage));
		}
		
		return ResponseEntity.ok().body(final_data);
	}
	
	public static ResponseEntity<?> testResponse(Object res)
	{
		Map<String, Object> final_data = new LinkedHashMap<String, Object>();
		
		final_data.put("data", res);
		
		return ResponseEntity.ok().body(final_data);
	}

	
	public static String validationService(List<Object> values, List<String> names) {
	    String message = "";
	    try {
	        for (int i = 0; i < values.size(); i++) {
	            if (Objects.isNull(values.get(i)) || values.get(i).equals("")) {
	                message += names.get(i) + " is Required. ";
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("::::::::::::: validation service failure :::::::::::::");
	        message = "Internal Server Issue";
	    }
	    return message;
	}
	
	public static String validationServiceWithIndex(List<Object> values, List<String> names, Integer index) {
	    String message = "";
	    try {
	        for (int i = 0; i < values.size(); i++) {
	            if (Objects.isNull(values.get(i)) || values.get(i).equals("")) {
	                message += names.get(i) + " is Required At Index " + index + ". ";
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("::::::::::::: validation service failure :::::::::::::");
	        message = "Internal Server Issue";
	    }
	    return message;
	}
	
}
