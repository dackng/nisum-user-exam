package com.nisum.exam.validation;

import java.util.Optional;

import com.nisum.exam.entity.User;
import com.nisum.exam.exception.InvalidEmailException;

public final class UserValidator {

	public static final String EMAIL_REGEX = "^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[cl/CL]{2,})$";
	public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})\\S{4,}$";
	
	public static void  validateEmail(Optional<User> optional) {
		optional.ifPresent(user -> {
	            throw new InvalidEmailException("El correo ya registrado");
	    });
	}
}
