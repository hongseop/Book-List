package com.openapi.starter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openapi.starter.mapper.BookDataMapper;
import com.openapi.starter.vo.BookVO;

import java_cup.reduce_action;

@RestController
public class HomeController {

	@Autowired
	private BookDataMapper mapper;

	// 오리지날

	// @RequestMapping(value = "/test")
	// public String testJSP(Map<String, Object> model) throws Exception{
	//
	//
	// //TODO jsp에서 bookdataVO 에 아이디 값은 넣어준 뒤 컨트롤서 콜
	// jsp 아이디, 패스워드 던지는 방식으로 데이터 전송
	// CRUD UI랑 연동시켜서 만들기
	// Test 코드를 사용해서 만들어 놓을것
	//
	// BookDataVO result = new BookDataVO();
	//
	// result.setBid(5);
	// result = mapper.read(result.getBid());
	// model.put("msg", result);
	// return "test";
	// }

	

	@RequestMapping(value = "/jsonBooklist")
	public List<BookVO> jsonBooklist() throws Exception {
		List<BookVO> bookList = mapper.readAll();
		return bookList;
	}
	
	@RequestMapping(value = "/jsonBookTitleList")
	public List<BookVO> jsonBookTitleList(HttpServletRequest request) throws Exception {
//		System.out.println("값전달 테스트 : " + query); // 쿼리 부분이 전달 안되서 메서드가 구동하지를 않는다.
		String query=request.getParameter("query");
		List<BookVO> bookTitleList = new ArrayList<>();
		bookTitleList = mapper.readTitle(query);
		return bookTitleList;
	}
	
//	@RequestMapping(value = "/jsonBooklistselect")
//	public List<BookVO> jsonBooklistselect() throws Exception {
//		List<BookVO> bookList = mapper.readTitle(title);
//		return bookList;
//	}

	// @RequestMapping(value = "/booklist")
	// public String booklist(Model model) throws Exception {
	//
	// List<BookVO> bookList = null;
	// bookList = mapper.readAll();
	//
	// model.addAttribute("msg", bookList);
	//
	// return "booklist";
	// }

	

}
