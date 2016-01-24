package com.openapi.starter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.openapi.starter.vo.BookVO;
import com.openapi.starter.vo.UserVO;

public interface BookMapper {

	@Insert("INSERT INTO book_list ( id, title, author, image, link, price ) VALUES (#{id}, #{title}, #{author}, #{image}, #{link}, #{price}) ")
	public void create(BookVO vo) throws Exception;

	@Insert("INSERT INTO book_user ( id ,uid, pass, name, phone, email, birthday, homepage, sex ) VALUES (#{id}, #{uid}, #{pass}, #{name}, #{phone}, #{email}, #{birthday}, #{homepage}, #{sex}) ")
	public void creatUser(UserVO vo) throws Exception;
	
	@Select("SELECT * FROM book_list")
	public List<BookVO> readAll() throws Exception;

	@Select("SELECT * FROM book_list WHERE title LIKE '%${title}%'")
	public List<BookVO> readTitle(@Param("title") String title) throws Exception;

	@Select("SELECT COUNT(*) FROM book_list")
	public int getBookListTotalCount() throws Exception;

	@Select("SELECT COUNT(*) FROM book_list WHERE title LIKE '%${title}%'")
	public int getBookListSearchCount(@Param("title") String title) throws Exception;

}
