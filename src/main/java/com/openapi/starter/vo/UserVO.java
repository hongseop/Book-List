package com.openapi.starter.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {

	private int id;
	private String uid;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String birthday;
	private String homepage;
	private String sex;
	private Date regdate;

}
