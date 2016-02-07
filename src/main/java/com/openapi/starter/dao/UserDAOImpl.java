package com.openapi.starter.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.openapi.starter.dto.LoginDTO;
import com.openapi.starter.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	
	private SqlSession session;
	
	private static String namespace = "org.zerock.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return session.selectOne(namespace + ".login", dto);
		
	}
}
