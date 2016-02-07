package com.openapi.starter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.vo.UserVO;

@Controller
// @SessionAttributes({"uid"})
// uid 키로 저장된 attribute는 세션객체에 저장됨
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BookMapper mapper;

	@RequestMapping(value = "/register")
	public String register(Map<String, Object> model) throws Exception {
		return "register";
	}

	@RequestMapping(value = "/joinprocess", method = RequestMethod.POST)
	public String joinProcess(String uid, String pass, String name, String phone, String email, String birthday,
			String homepage, String sex) throws Exception {

		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setPassword(pass);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setBirthday(birthday);
		vo.setHomepage(homepage);
		vo.setSex(sex);
		mapper.creatUser(vo);
		return "redirect:main";

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

		httpSession.removeAttribute("uid");
		return "redirect:main";
	}

	@RequestMapping(value = "/loginprocess")
	public String loginProcess(HttpServletRequest request, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {

		String inputId = request.getParameter("uid");
		String inputPassword = request.getParameter("pass");
		String storedPassword = null;

		if (inputPassword == null || inputPassword == "") {
			redirectAttributes.addFlashAttribute("msg", "비밀번호 입력은 필수입니다.");
			return "redirect:login";
		}

		try {
			storedPassword = mapper.getUserPassword(inputId);
			// 본래는 이런 방식이 아니라 DAO 에 저장해서 DB랑 소통해야 한다.
			// 다음 리팩토링 때는 DAO 방식으로 mapper에 접근하도록 수정한다.

		} catch (Exception e) {
			logger.error("/loginprocess sql error !!", e);
		}

		if (storedPassword == null) {
			redirectAttributes.addFlashAttribute("msg", "없는 아이디 입니다.");
			return "redirect:login";
		}

		if (!inputPassword.equals(storedPassword)) {
			redirectAttributes.addFlashAttribute("msg", "비밀번호가 잘못되었습니다.");
			return "redirect:login";
		} else {
			httpSession.setAttribute("uid", inputId);
			// 로그인에 성공하면 세션에 아이디를 저장한다. 리다이렉트를 인터셉터에 처리하게 바꾼다.
			return "redirect:main";
		}

	}
}
