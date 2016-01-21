package com.openapi.starter.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.vo.BookVO;

@Controller
public class BookListController {

	@Autowired
	private BookMapper mapper;


	@RequestMapping(value = "/main")
	public String main(Map<String, Object> model) throws Exception {

		return "main";
	}

	@RequestMapping(value = "/register")
	public String register(Map<String, Object> model) throws Exception {
		
		return "register";
	}

	@RequestMapping(value = "/test2")
	public String test2(Map<String, Object> model) throws Exception {

		return "test2";
	}

	@RequestMapping(value = "/booklist")
	public String booklist(Map<String, Object> model) throws Exception {

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
