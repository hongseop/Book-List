package com.openapi.starter.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class Util {

	public  List<HashMap<String, String>> XMLDataParser(StringBuilder sb, String inputLine, BufferedReader in ) throws Exception {
		
		
		
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
																// 데이터 하나의 단위는
																// HashMap을 통해
																// 받아들이고 받아들인
																// 데이터의 관리는
																// ArrayList 로
																// 한다.

		// 파싱
		while ((result = ap.evalXPath()) != -1) { // 전송되는 데이터가 없을때까지 HashMap 과
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
		
		return list;
	}
}
