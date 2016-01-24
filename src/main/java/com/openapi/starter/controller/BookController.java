package com.openapi.starter.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.util.Constants;
import com.openapi.starter.util.Util;
import com.openapi.starter.vo.BookVO;

@RestController
public class BookController {

	@Autowired
	private BookMapper mapper;

	@RequestMapping(value = "/getBookList")
	
	public HashMap<String, Object> getBookList(HttpServletRequest request) throws Exception {

		String page = request.getParameter("page");
		String query = request.getParameter("query");
		if (page == null)
			page = "1";

		List<BookVO> bookTitleList = new ArrayList<>();
		int ListCount = 0;

		if (query == "") { // 검색어가 없는 경우.
			bookTitleList = mapper.readAll();
			ListCount = mapper.getBookListTotalCount();
		} else { // 검색어가 있는 경우.
			bookTitleList = mapper.readTitle(query);
			ListCount = mapper.getBookListSearchCount(query);
		}

		if (bookTitleList.size() == 0) { // DB 내에 찾는 데이터가 없는 경우 네이버 api를 이용하여
											// 조회하고 저장.
			XMLDataConnection(query);
			bookTitleList = mapper.readTitle(query);
			ListCount = mapper.getBookListSearchCount(query);
		}

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", bookTitleList);
		resultMap.put("totalCnt", ListCount);
		resultMap.put("page", page);
		return resultMap;

	}

	public void XMLDataConnection(String query) throws Exception {

		BookVO vo = new BookVO();
		String url = Constants.BOOK_REQUEST_URL + query + "&display=10&start=1&target=book";
		// 데이터를 받아올 주소.
		URL ocu = new URL(url);
		URLConnection con = ocu.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = null;
		StringBuilder sb = new StringBuilder();
		Util parser = new Util();
		List<HashMap<String, String>>list = parser.XMLDataParser(sb,inputLine,in);
		
		for (HashMap<String, String> hm : list) { // for 문의 다른 형태는 아직 좀더 공부가
													// 필요하다. 일단 앞 변화하는 값, 뒤가 최종
													// 도달하는 값의 크기 라고 짐작된다.
													// 리스트에 저장된 데이터의 끝에 도달할 때까지
													// vo 객체 안에 데이터를 입력한다.
			vo.setTitle(hm.get("title"));
			vo.setAuthor(hm.get("author"));
			vo.setImage(hm.get("image"));
			vo.setLink(hm.get("link"));
			vo.setPrice(Integer.parseInt(hm.get("price")));
			mapper.create(vo);
		}
	}

	
}
