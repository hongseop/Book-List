package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openapi.starter.BookListApplication;
import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookListApplication.class)
@WebAppConfiguration
public class TestPaging {
	
	@Autowired
	private BookMapper mapper;

	@Test 
	public void testPaging() throws Exception{
		


	}
	
}
