package com.nisum.exam.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.exam.api.assembler.UserInfoResourceAssembler;
import com.nisum.exam.api.request.UserInfoRequest;
import com.nisum.exam.api.resource.UserInfoResource;
import com.nisum.exam.api.resource.UsersInfoResource;
import com.nisum.exam.dao.OperationsDao;
import com.nisum.exam.entity.User;
import com.nisum.exam.security.JwtUtils;
import com.nisum.exam.validation.UserValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final OperationsDao<User> operationsDao;
	private final JwtUtils jwtUtils;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = false)
	public UserInfoResource createUser(UserInfoRequest request) {
		UserValidator.validateEmail(operationsDao.findOne("email", request.getEmail()));
		
		String uuid = UUID.randomUUID().toString();
		String token = jwtUtils.generateToken(request.getEmail());
		String password = passwordEncoder.encode(request.getPassword());
		
		User user = UserInfoResourceAssembler.toEntity(request, uuid, token, password);
		
		return UserInfoResourceAssembler.toResource(operationsDao.save(user));
	}
	
	public UsersInfoResource findAllUsers() {
        return UserInfoResourceAssembler.toResource(operationsDao.findAll());
    }
}
