<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>insert</h2>
	<form method="post" action="insertaction">
		<label for="insertTitle">제목</label> <input type="text" name="title" /> <br/>
		<label for="insertAuthor">작가</label> <input type="text" name="author" /> <br/>
		<label for="insertImage">이미지링크</label> <input type="text" name="image" /> <br/>
		<label for="insertLink">링크</label> <input type="text" name="link" /> <br/>
		<label for="insertPrice">가격</label> <input type="text" name="price" /> <br/>
		<input type="submit" value="DB 저장" />
	</form>
</body>
</html>