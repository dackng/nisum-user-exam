package com.nisum.exam.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.exam.api.resource.UsersInfoResource;
import com.nisum.exam.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST})
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<UsersInfoResource> findAll(){
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}
}
