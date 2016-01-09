package com.openapi.starter.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openapi.starter.mapper.BookDataMapper;
import com.openapi.starter.vo.BookVO;

@Controller
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

	@RequestMapping(value = "/main")
	public String main(Map<String, Object> model) throws Exception {

		return "main";
	}

	@RequestMapping(value = "/booklist")
	public String booklist(Map<String, Object> model) throws Exception {

		List<BookVO> bookList = null;
		bookList = mapper.readAll();

		for (BookVO book : bookList) {
			
		}
		model.put("msg", bookList);
		System.out.println(model.toString());

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
			@RequestParam(value = "link") String link, @RequestParam(value = "price") String price)
					throws Exception {

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
