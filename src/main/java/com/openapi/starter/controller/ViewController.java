package com.openapi.starter.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openapi.starter.mapper.BookMapper;

@Controller
public class ViewController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BookMapper mapper;

	@RequestMapping(value = "/main")
	public String main(Map<String, Object> model) throws Exception {
		logger.info("/main");
		return "main";
	}

	@RequestMapping(value = "/booklist")
	public String booklist(Map<String, Object> model) throws Exception {
		return "booklist";
	}
	
	@RequestMapping(value = "/login")
	public String login(Map<String, Object> model) throws Exception {

		return "login";
	}

}
