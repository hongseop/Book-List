package com.openapi.starter.service;

import com.openapi.starter.dto.LoginDTO;
import com.openapi.starter.vo.UserVO;

public interface UserService {

	public UserVO login(LoginDTO dto) throws Exception; 
	
}
