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
import com.openapi.starter.vo.BookVO;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

@RestController
public class HomeController {

	@Autowired
	private BookMapper mapper;

	@RequestMapping(value = "/jsonBooklist")
	public List<BookVO> jsonBooklist(HttpServletRequest request) throws Exception {

		// List<BookVO> bookList = mapper.readAll();

		int pageSize = 10; // 한번에 자료를 10개씩 보여준다.

		String pageNum = request.getParameter("page"); // 뷰에서 페이지 넘버 3을 입력받았다고
														// 가정한다.
		if (pageNum == null)
			pageNum = "1";

		// int currentPage = Integer.parseInt(pageNum); // 현재 페이지 넘버
		// int startRow = (currentPage - 1) * pageSize + 1; //

		int startRow = Integer.parseInt(pageNum) * 10 - 9;

		// int totalCount = mapper.getBookListTotalCount();

		List<BookVO> bookList = mapper.readPagingList(startRow, pageSize);

		// pqgeNum, totalCount 데이터를 보내서 페이징을 해야한다.

		return bookList;
	}

	@RequestMapping(value = "/jsonBookTitleList")
	public HashMap<String, Object> jsonBookTitleList(HttpServletRequest request) throws Exception {

		String page = request.getParameter("page");
		String query = request.getParameter("query");
		int pageSize = 10;
		if (page == null)
			page = "1";

		int startRow = Integer.parseInt(page) * 10 - 9;
		List<BookVO> bookTitleList = new ArrayList();

		// 데이터는 Json 으로 보내져 있다.

		if (query == "") {// 검색어가 없는 경우.
			bookTitleList = mapper.readAll();

		} else {// 검색어가 있는 경우.
			bookTitleList = mapper.readTitle(query);
		}

		if (bookTitleList.size() == 0) { // DB 내에 찾는 데이터가 없는 경우 네이버 api를 이용하여
											// 조회하고 저장.
			XMLDataConnection(query);
			bookTitleList = mapper.readTitle(query);

		}

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", bookTitleList);
		resultMap.put("totalCnt", mapper.getBookListTotalCount());
		resultMap.put("page", page);

		return resultMap;

	}

	// Controller 백업.
	// @RequestMapping(value = "/jsonBookTitleList")
	// public List<BookVO> jsonBookTitleList(HttpServletRequest request) throws
	// Exception {
	//
	// String page = request.getParameter("page");
	// String query = request.getParameter("query");
	// int pageSize = 10;
	// int startRow = Integer.parseInt(page) * 10 - 9;
	//
	// // 검색어가 없고 페이지 버튼도 클릭하지 않은 경우.
	//
	// List<BookVO> bookTitleList = new ArrayList();
	//
	// bookTitleList = mapper.readTitle(query);// DB 에서 데이터를 받아옴
	// if (bookTitleList.size() == 0) { // 이건 성립하지 않는다.
	// XMLDataConnection(query);
	// }
	// // api 조회
	// bookTitleList = mapper.readTitle(query);
	// return bookTitleList;
	//
	// }

	public void XMLDataConnection(String query) throws Exception {

		String url = Constants.BOOK_REQUEST_URL + query + "&display=10&start=1&target=book";
		// 데이터를 받아올 주소.
		System.out.println(url);
		URL ocu = new URL(url);
		URLConnection con = ocu.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = null;
		StringBuilder sb = new StringBuilder();

		BookVO vo = new BookVO();
		// 받아온 데이터를 저장할 객체.

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);// 데이터를 문자열을 이용해 연결해서 받음.
		}
		in.close();// BufferedReader 종료시킴.

		String xml = sb.toString(); // toString() 은 참조변수에 저장된 주소가 아닌 데이터의 모양세로
									// 바꾸어주는 메서드이다.
									// 실제 데이터를 변수 xml 에 저장한다. (데이터도 xml 형식인가?)

		// 파싱준비
		VTDGen vg = new VTDGen();// 파싱에 사용되는 객체 선언.
		vg.setDoc(xml.getBytes());// 선언된 객체를 통해 데이터가 저장된 변수인 xml 을 바이트단위 데이터로
									// 객체에 저장.
		vg.parse(true);// VTD 토큰과 캐시 로케이션 정보를 출력하기로 설정한다?

		// 파싱 시작
		VTDNav vn = vg.getNav();
		AutoPilot ap = new AutoPilot(vn); // 오토 파일럿 객체 선언
		ap.selectXPath("/rss/channel/item/*"); // 해당 경로 아래에 있는 요소들을 검색한다.
		int result = -1; // -1값을 갖는 변수를 선언 ( 잘 모르겠다. 아마 배열의 크기와 관계가 있는 듯하다.)

		HashMap<String, String> map = null; // 해시맵 객체 생성.
		List<HashMap<String, String>> list = new ArrayList<>(); // 해시맵을 포함하는 리스트
																// 객체 생성. 리스트
																// 인터페이스의 구현체인
																// 어레이 리스트로
																// 만들었다.
		// 데이터 하나의 단위는 HashMap을 통해 받아들이고 받아들인 데이터의 관리는 ArrayList 로 한다.

		// 파싱
		while ((result = ap.evalXPath()) != -1) {// 전송되는 데이터가 없을때까지 HashMap 과
													// ArrayList에 데이터를 입력하는 작업을
													// 반복한다.
			if ("title".equals(vn.toString(result))) {
				map = new HashMap<>(); // 반복문 내부에서 데이터가 있을 때마다 HashMap 객체를 새롭게
										// 생성하여 데이터를 저장한다.
			}

			vn.toString(result);
			int t = vn.getText();

			if (t != -1) {

				map.put(vn.toString(result), vn.toNormalizedString(t));
				if ("description".equals(vn.toString(result))) {
					list.add(map); // Map형식의 데이터를 ArrayList 에 저장한다.
				}

			}

		}

		System.out.println("ListSize : " + list.size());
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
