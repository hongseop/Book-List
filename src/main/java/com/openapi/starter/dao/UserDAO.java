package com.openapi.starter.dao;

import com.openapi.starter.dto.LoginDTO;
import com.openapi.starter.vo.UserVO;

public interface UserDAO {

	public UserVO login(LoginDTO dto) throws Exception;
}
