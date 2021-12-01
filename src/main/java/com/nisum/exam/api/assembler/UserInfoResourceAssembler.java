package com.nisum.exam.api.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.nisum.exam.api.request.PhoneInfoRequest;
import com.nisum.exam.api.request.UserInfoRequest;
import com.nisum.exam.api.resource.UserInfoResource;
import com.nisum.exam.api.resource.UsersInfoResource;
import com.nisum.exam.entity.Phone;
import com.nisum.exam.entity.User;


public final class UserInfoResourceAssembler {

	
	public static UserInfoResource toResource(User user) {	
		return UserInfoResource
                .builder()
                .id(user.getId())
                .created(user.getCreatedAt())
                .modified(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(user.getIsActive())
                .build();
	}
	
	public static UsersInfoResource toResource(List<User> users) {	
		return UsersInfoResource
                .builder()
                .users(users.stream()
                		.map(UserInfoResourceAssembler::toResource)
                        .collect(Collectors.toList()))
                .build();
	}
	
	public static User toEntity(UserInfoRequest request, String uuid, String token, String encryptedPassword) {
     
        User user = User
                .builder()
                .id(uuid)
                .name(request.getName())
                .email(request.getEmail())
                .password(encryptedPassword)
                .token(token)
                .phones(new ArrayList<>())
                .build();

        request.getPhones().stream().forEach(phoneRequest -> user.getPhones()
        		.add(toEntity(user, phoneRequest, UUID.randomUUID().toString())));

        return user;
    }
	
	public static Phone toEntity(User user, PhoneInfoRequest request, String uuid) {
        return Phone
                .builder()
                .id(uuid)
                .user(user)
                .number(request.getNumber())
                .cityCode(request.getCitycode())
                .countryCode(request.getContrycode())
                .build();
    }
}
