package com.openapi.starter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openapi.starter.mapper.BookDataMapper;
import com.openapi.starter.vo.BookVO;

@Controller
public class BookListController {
	
	@Autowired
	private BookDataMapper mapper;

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
	
	@RequestMapping(value = "/main")
	public String main(Map<String, Object> model) throws Exception {

		return "main";
	}

	@RequestMapping(value = "/booklist")
	public String booklist(Map<String, Object> model) throws Exception {

		List<BookVO> bookList = null;
		bookList = mapper.readAll();
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(bookList);	
		
		model.put("bookList", json);

		return "booklist";
	}
	
	@RequestMapping(value = "/update")
	public String insert(Map<String, Object> model) throws Exception {

		BookVO result = new BookVO();

		result = mapper.read(result.getId());
		model.put("msg", result);
		return "update";
	}

	@RequestMapping(value = "/delete")
	public String delete(Map<String, Object> model) throws Exception {

		return "delete";
	}

	@RequestMapping(value = "/deleteaction")
	public String deleteaction(Map<String, Object> model, @RequestParam(value = "bid") String bid) throws Exception {

		BookVO result = new BookVO();

		result.setId(Integer.parseInt(bid));
		mapper.delete(result.getId()); // 여기까지는 작동하는것 같은데. 실제로 DB에서 데이터가 삭제되었다.

		return "deleteaction";
	}

	@RequestMapping(value = "/select")
	public String select(Map<String, Object> model) throws Exception {

		return "select";
	}

	@RequestMapping(value = "/selectaction")
	public String selectaction(Map<String, Object> model, @RequestParam(value = "id") String id) throws Exception {

		BookVO result = new BookVO();

		result.setId(Integer.parseInt(id));
		result = mapper.read(result.getId());
		model.put("msg", result);

		return "selectaction";
	}

	@RequestMapping(value = "/insert")
	public String update(Map<String, Object> model) throws Exception {

		return "insert";
	}

	@RequestMapping(value = "/insertaction")
	public String updateaction(Map<String, Object> model, @RequestParam(value = "title") String title,
			@RequestParam(value = "author") String author, @RequestParam(value = "image") String image,
			@RequestParam(value = "link") String link, @RequestParam(value = "price") String price) throws Exception {

		BookVO result = new BookVO();

		result.setTitle(title);
		result.setAuthor(author);
		result.setImage(image);
		result.setLink(link);
		result.setPrice(Integer.parseInt(price));
		mapper.create(result);

		return "insertaction";
	}
	
}
