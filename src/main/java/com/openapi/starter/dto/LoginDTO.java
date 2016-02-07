package com.openapi.starter.dto;

import lombok.Data;

@Data
public class LoginDTO {

	private String uid;
	private String password;
	private boolean useCookie;
	private String destination;
}
