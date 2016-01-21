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
import com.openapi.starter.mapper.BookMapper;
import com.openapi.starter.vo.BookVO;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookListApplication.class)
@WebAppConfiguration
public class BookListApplicationTests {

	@Autowired
	private BookMapper mapper;

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
		}
	}

	@Test
	public void testReadTitle() throws Exception {

		List<BookVO> bookList = null;
		List<BookVO> result = new ArrayList<>();
		result = mapper.readTitle("old");
		System.out.println(result.toString());

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
		String url = "http://openapi.naver.com/search?key=d54e5b142aa91ba7c325eebde2e20d44&query=%EC%82%BC%EA%B5%AD%EC%A7%80&display=10&start=1&target=book"; 
		//데이터를 받아올 주소.
		
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
		in.close();//BufferedReader 종료시킴.

		String xml = sb.toString(); // toString() 은 참조변수에 저장된 주소가 아닌 데이터의 모양세로 바꾸어주는 메서드이다.
									// 실제 데이터를 변수 xml 에 저장한다. (데이터도 xml 형식인가?)

		// 파싱준비
		VTDGen vg = new VTDGen();// 파싱에 사용되는 객체 선언.
		vg.setDoc(xml.getBytes());// 선언된 객체를 통해 데이터가 저장된 변수인 xml 을 바이트단위 데이터로 객체에 저장.
		vg.parse(true);// VTD 토큰과 캐시 로케이션 정보를 출력하기로 설정한다?

		// 파싱 시작
		VTDNav vn = vg.getNav();
		AutoPilot ap = new AutoPilot(vn); // 오토 파일럿 객체 선언 
		ap.selectXPath("/rss/channel/item/*"); // 해당 경로 아래에 있는 요소들을 검색한다.
		int result = -1; // -1값을 갖는 변수를 선언 ( 잘 모르겠다. 아마 배열의 크기와 관계가 있는 듯하다.)

		HashMap<String, String> map = null; // 해시맵 객체 생성.
		List<HashMap<String, String>> list = new ArrayList<>(); // 해시맵을 포함하는 리스트 객체 생성. 리스트 인터페이스의 구현체인 어레이 리스트로 만들었다.
		// 데이터 하나의 단위는 HashMap을 통해 받아들이고 받아들인 데이터의 관리는 ArrayList 로 한다.

		// 파싱
		while ((result = ap.evalXPath()) != -1) {// 전송되는 데이터가 없을때까지 HashMap 과 ArrayList에 데이터를 입력하는 작업을 반복한다.
			if ("title".equals(vn.toString(result))) {
				map = new HashMap<>(); // 반복문 내부에서 데이터가 있을 때마다 HashMap 객체를 새롭게 생성하여 데이터를 저장한다.
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
		for (HashMap<String, String> hm : list) { // for 문의 다른 형태는 아직 좀더 공부가 필요하다. 일단 앞 변화하는 값, 뒤가 최종 도달하는 값의 크기 라고 짐작된다.
												  // 리스트에 저장된 데이터의 끝에 도달할 때까지 vo 객체 안에 데이터를 입력한다.

			vo.setTitle(hm.get("title"));
			vo.setAuthor(hm.get("author"));
			vo.setImage(hm.get("image"));
			vo.setLink(hm.get("link"));
			vo.setPrice(Integer.parseInt(hm.get("price")));
			System.out.println(hm.toString());
			mapper.create(vo);
		}
		
	}

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
	
	@Test
	public void testListPage( ) throws Exception {
		
		int page = 1;
		
	
	}

}
