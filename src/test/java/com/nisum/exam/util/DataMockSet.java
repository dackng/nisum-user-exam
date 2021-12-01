package com.nisum.exam.util;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.nisum.exam.api.request.PhoneInfoRequest;
import com.nisum.exam.api.request.UserInfoRequest;
import com.nisum.exam.api.resource.UserInfoResource;
import com.nisum.exam.entity.User;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class DataMockSet {

	 public static JSONObject getUserInfoJSONRequestMock() {
		 JSONObject body = new JSONObject();
		 body.put("name", "Dackng");
		 body.put("email","dcayo@dominio.cl");
		 body.put("password", "Test10");
		 
		 JSONArray phones = new JSONArray();
		 phones.add(getPhoneInfoRequestMock());
		 body.put("phones", phones);
		 
		 return body;
	 }
	 
	 public static JSONObject getPhoneInfoJSONRequestMock() {
		 JSONObject phone = new JSONObject();
		 phone.put("number", "2323232");
		 phone.put("citycode", "1");
		 phone.put("contrycode", "57");
		 return phone;
	 }
	 
	 public static UserInfoRequest getUserInfoRequestMock() {
		 Set<PhoneInfoRequest> phones = new HashSet<PhoneInfoRequest>();
		 phones.add(getPhoneInfoRequestMock()); 
	      
		 return UserInfoRequest
				.builder()
                .name("Diego")
                .email("dcayo@dominio.cl")
                .password("Test10")
                .phones(phones)
                .build();
	 }
	 
	 public static PhoneInfoRequest getPhoneInfoRequestMock() {
		 return PhoneInfoRequest
				 .builder()
				 .number("2323232")
	             .citycode("1")
	             .contrycode("57")
				 .build();
	 }
	 
	 public static User getUserEntityMock() {   
		 return User
				.builder()
				.id("dads-131231-23")
                .name("Diego")
                .email("dcayo@dominio.cl") 
                .build();
	 }
	 
	 public static UserInfoResource getUserInfoResourceMock(String id) {
		 return UserInfoResource
				.builder()
				.id(id)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now()) 
                .lastLogin(LocalDateTime.now())
                .token("adsfasfasdf")
                .isactive(true)
                .build();
	 }
}
