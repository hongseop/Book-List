<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[무라딘] "공부하는 자들이 만드는 서점 무라딘"</title>
</head>
<body>
<h1>로그인 페이지</h1>
<div>
<form method ="post" action="loginprocess" name="form" >
<fieldset>
<legend>아이디, 패스워드 입력</legend>
<label for="name">아이디</label>
<input type ="text" name ="uid" id="text1"><br /> 
<label for="pass">패스워드</label>
<input type="password" name="pass"><br /> 
<input type="submit" name="submit" value="로그인" />${msg }
</fieldset>
</form>
</div>
</body>
</html>