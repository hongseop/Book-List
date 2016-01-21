package com.openapi.starter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.openapi.starter.vo.BookVO;



public interface BookMapper {

	@Insert("INSERT INTO book_list ( id, title, author, image, link, price ) " + "VALUES (#{id}, #{title}, #{author}, #{image}, #{link}, #{price}) ")
	public void create(BookVO vo) throws Exception;
	
	@Select("SELECT * FROM book_list WHERE id = #{id}")
	public BookVO read(int id) throws Exception;
	
	@Select("SELECT * FROM book_list")
	public List<BookVO> readAll() throws Exception;

	@Select("SELECT * FROM book_list WHERE title LIKE '%${title}%'")
	public List<BookVO> readTitle(@Param("title") String title) throws Exception;
	
//	@Select("SELECT * FROM book_list WHERE title LIKE '%${title}%'")
//	public List<BookVO> readTitle(BookVO title) throws Exception;
	
//	@Select("SELECT id, title, author, image, link, price FROM book_list WHERE id>0 ORDER BY id DESC , regdate DESC LIMIT #{page},10 ")
//	public List<BookVO> readTest(@Param("page") int page) throws Exception;
	
	@Select("SELECT * FROM book_list WHERE id>0 ORDER BY id DESC , regdate DESC LIMIT #{startRow},#{endRow} ")
	public List<BookVO> readPagingList(@Param ("startRow")int startRow , @Param ("endRow") int endRow ) throws Exception;
	
	@Update("UPDATE book_list SET " + "title = #{title}, autor=#{author}, bimage=#{image}, link=#{link}, price=#{price} " + "WHERE id =#{id}")
	public void update(BookVO vo) throws Exception;
	
	@Select("SELECT COUNT(*) FROM book_list")
	public int getBookListTotalCount() throws Exception;
	
	
	@Delete("DELETE FROM book_list WHERE id = #{id}")
	public void delete(int bid)throws Exception;
	
}
