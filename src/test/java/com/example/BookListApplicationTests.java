package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.openapi.starter.BookListApplication;
import com.openapi.starter.mapper.BookDataMapper;
import com.openapi.starter.vo.BookVO;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookListApplication.class)
@WebAppConfiguration
public class BookListApplicationTests {

	@Autowired
	private BookDataMapper mapper;

	@Autowired
	private DataSource ds;

	@Autowired
	private SqlSessionFactory sqlSession;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testReadAll() throws Exception {

		List<BookVO> bookList = null;
		bookList = mapper.readAll();

		for (BookVO book : bookList) {
			System.out.println(book.toString());
		}
	}

	@Test
	public void testInsert() throws Exception {

		BookVO vo = new BookVO();

		vo.setId(1);
		vo.setTitle("test title2");
		vo.setAuthor("test autor2");
		vo.setImage("test image2");
		vo.setLink("test link2");
		vo.setPrice(20000);

		mapper.create(vo);

	}

	@Test
	public void testSelect() throws Exception {

		BookVO vo = new BookVO();
		BookVO result = new BookVO();

		vo.setId(5);

		System.out.println(vo.toString());

		result = mapper.read(vo.getId());

		org.junit.Assert.assertEquals(1000, result.getPrice());

	}

	@Test
	public void testdelete() throws Exception {

		BookVO vo = new BookVO();
		BookVO result = new BookVO();

		vo.setId(5);

		System.out.println(vo.toString());

		result = mapper.read(vo.getId());

		org.junit.Assert.assertEquals(1000, result.getPrice());

	}

	@Test
	public void testXML() throws Exception {
		String url = "http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=%EC%82%BC%EA%B5%AD%EC%A7%80&display=10&start=1&target=book";

		URL ocu = new URL(url);
		URLConnection con = ocu.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = null;
		StringBuilder sb = new StringBuilder();

		BookVO vo = new BookVO();

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);// 데이터를 스트링으로 연결받
		}
		in.close();

		String xml = sb.toString(); // 사용자 정의 객체를 출력하면 주소가 나타난다. toString() 은
									// 참조변수에 저장된 주소가 아닌 데이터의 모양세로 바꾸어주는 메서드이다?

		// 파싱준비
		VTDGen vg = new VTDGen();// 어디에 쓰이는지 잘 모르는 객체 선
		vg.setDoc(xml.getBytes());// 위에 선언된 객체의 문서관련 메서드를 이용하여 바이트단위 데이터로 객체에 저장
		vg.parse(true);// 저장된 데이터를 파싱할수 있는 것이라 선언?

		// 파싱 시작
		VTDNav vn = vg.getNav();
		AutoPilot ap = new AutoPilot(vn);
		ap.selectXPath("/rss/channel/item/*");
		int result = -1;

		HashMap<String, String> map = null;
		List<HashMap<String, String>> list = new ArrayList<>();

		// 파싱
		while ((result = ap.evalXPath()) != -1) {
			if ("title".equals(vn.toString(result))) {
				map = new HashMap<>();
			}

			vn.toString(result);
			int t = vn.getText();
			if (t != -1) {
				// System.out.println(vn.toString(result));
				// System.out.println(vn.toNormalizedString(t));
				map.put(vn.toString(result), vn.toNormalizedString(t));
				if ("description".equals(vn.toString(result))) {
					list.add(map);
				}

			}

		}
		// System.out.println("MAP : " + map.toString());
		System.out.println("ListSize : " + list.size());
		for (HashMap<String, String> hm : list) {
			// System.out.println(hm);
			vo.setTitle(hm.get("title"));
			vo.setAuthor(hm.get("author"));
			vo.setImage(hm.get("image"));
			vo.setLink(hm.get("link"));
			vo.setPrice(Integer.parseInt(hm.get("price")));
			// if ("title".equals(hm)){
			// vo.setBtitle(hm.get(list));
			// System.out.println("---------------------------------------------
			// ");
			// }
			//
			// if ("autor".equals(hm))
			// vo.setBautor(hm.get(list));
			//
			// if ("image".equals(hm))
			// vo.setBimage(hm.get(list));
			//
			// if ("link".equals(hm))
			// vo.setBlink(hm.get(list));
			//
			// if ("price".equals(hm))
			// vo.setBprice(Integer.parseInt(hm.get(list)));
			// // System.out.println();

			// mapper.create(vo);
		}

	}
	// TODO 파싱된 데이터를 DB에 저장하는 방법이 뭐지?
	// 1. 객체 담기
	// 2. DB 담기
	// 3. DB 담긴 데이터 조회
	// TODO List DB 에 저장하는법 / 저장완
	// TODO 이클립스 단축키

	@Test
	public void testSqlSession() throws Exception {

		System.out.println(sqlSession);
	}

	@Test
	public void testConnection() throws SQLException {

		System.out.println(ds);

		Connection con = ds.getConnection();

		System.out.println(con);

		con.close();
	}

}
