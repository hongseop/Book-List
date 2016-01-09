package com.openapi.starter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.openapi.starter.vo.BookVO;



public interface BookDataMapper {

	@Insert("INSERT INTO book_list ( id, title, author, image, link, price ) " + "VALUES (#{id}, #{title}, #{author}, #{image}, #{link}, #{price}) ")
	public void create(BookVO vo) throws Exception;
	
	@Select("SELECT * FROM book_list WHERE id = #{id}")
	public BookVO read(int id) throws Exception;
	
	@Select("SELECT * FROM book_list")
	public List<BookVO> readAll() throws Exception;
	
	@Update("UPDATE book_list SET " + "title = #{title}, autor=#{author}, bimage=#{image}, link=#{link}, price=#{price} " + "WHERE id =#{id}")
	public void update(BookVO vo) throws Exception;
	
	@Delete("DELETE FROM book_list WHERE id = #{id}")
	public void delete(int bid)throws Exception;
}
