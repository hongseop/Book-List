package com.openapi.starter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.vo.BookVO;

@Controller
public class ViewController {

	@Autowired
	private BookMapper mapper;

	@RequestMapping(value = "/main")
	public String main(Map<String, Object> model) throws Exception {
		return "main";
	}

	@RequestMapping(value = "/booklist")
	public String booklist(Map<String, Object> model) throws Exception {
		return "booklist";
	}


}
