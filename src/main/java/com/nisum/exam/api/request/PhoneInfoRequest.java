package com.nisum.exam.api.request;

import javax.validation.constraints.NotBlank;

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
public class PhoneInfoRequest {
	
	@NotBlank(message = "Número no debe ser vacío")
	private String number;
	
	@NotBlank(message = "Código de ciudad no debe ser vacío")
	private String citycode;
	
	@NotBlank(message = "Código de país no debe ser vacío")
	private String contrycode;
}
