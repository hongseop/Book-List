package com.openapi.starter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookListController {

	public static String url = "http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=%EC%82%BC%EA%B5%AD%EC%A7%80&display=10&start=1&target=book";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() throws IOException {

		URL ocu = new URL(url);
		URLConnection con = ocu.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();

		// Connection 객체를 InputStreamReader로 읽고 utf-8로 인코딩.
		InputStreamReader isr = new InputStreamReader(ocu.openConnection().getInputStream(), "UTF-8");

		while ((inputLine = in.readLine()) != null) {
			// TODO StringBuilder 를 샤용해서 String 형태로 저장할
			// System.out.println(inputLine);
			sb.append(in.readLine());
			System.out.println(sb);

		}
		in.close();

		// TODO xml 을 파싱하여 디비에 저장할 것 // <title>, <author>, <link>, <image>,
		// <price>
		// TODO db table 생성 ->table name : book / column : id, title, author,
		// link, image, create_date
		// TODO CRUD 구성 MyBatis , Spring boot MyBatis 정리 찾기
		// TODO CRUD 관련 UNIT TEST 까지 작성

		System.out.println("Hello World");
		return "hello";

	}
	
}
