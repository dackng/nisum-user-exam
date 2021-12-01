package com.nisum.exam.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix = "nisum")
@Getter
@Setter
public class YamlConfig {
	
	private String jwtSecret;
	private Long jwtExpiration;
}
