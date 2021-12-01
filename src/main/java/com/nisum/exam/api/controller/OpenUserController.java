package com.nisum.exam.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.exam.api.request.UserInfoRequest;
import com.nisum.exam.api.resource.UserInfoResource;
import com.nisum.exam.service.UserService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/open-api/v1/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST})
@RequiredArgsConstructor
public class OpenUserController {

	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserInfoResource> create(@RequestBody @Valid UserInfoRequest request){
		return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
	}
}
