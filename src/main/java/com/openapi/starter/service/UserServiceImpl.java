package com.openapi.starter.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.openapi.starter.dao.UserDAO;
import com.openapi.starter.dto.LoginDTO;
import com.openapi.starter.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {

		return dao.login(dto);
	}
}
