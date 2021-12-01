package com.nisum.exam.api.request;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.nisum.exam.validation.UserValidator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoRequest {

	@NotBlank(message = "Nombre no debe ser vacío")
	private String name;
	
	@NotBlank(message = "Email no debe ser vacío")
	@Email(regexp = UserValidator.EMAIL_REGEX, message = "Correo debe tener el siguiente formato aaaaaaa@dominio.cl")
	private String email;
	
	@NotBlank(message = "Contraseña no debe ser vacío")
	@Pattern(regexp = UserValidator.PASSWORD_REGEX, message = "Contraseña debe tener una Mayuscula, letras minúsculas y dos numeros")
	private String password;
	
	@NotEmpty(message = "Telefonos vacíos")
	@Valid
	private Set<PhoneInfoRequest> phones;
}
