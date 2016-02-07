<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.0.min.js" charset="UTF-8"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="./js/register.js"></script>

<title>[무라딘] "공부하는 자들이 만드는 서점 무라딘"</title>
</head>
<body>
	<div id="page-wrap">
		<form method="post" action="joinprocess" name="form">
			<fieldset>
				<legend>회원정보입력</legend>
					<label for="name">아이디</label> 
					<input type="text" name="uid" id="text1" /><button>중복검사</button>
					<br /> 
					<label for="password1">패스워드</label> 
					<input type="password" name="pass" />
					<br /> 
					<label for="password1">패스워드 확인</label>
					<input type="password" name="pass2" />
					<br /> 
					<label for="name">이름</label>
					<input type="text" name="name" />
					<br /> 
					<label for="name">휴대폰번호</label>
					<input type="tel" name="phone" />
					<br /> 
					<label for="email">이메일</label>
					<input type="email" name="email" />
					<br /> 
					<label for="dob">생일</label>
					<input type="date" name="birthday" />
					<br /> 
					<label for="url">홈페이지</label>
					<input type="url" name="homepage" />
					<br /> 
					<input type="radio" name="sex" value="Male">남성
					<br /> 
					<input type="radio" name="sex" value="Female">여성
					<br />
			</fieldset>
					<input type="submit" name="submit" value="제출" /> 
					<input type="reset" name="reset" value="초기화" />
					<a href="javascript:sendIt()" class="btn btn-info" role="button">회원가입</a>
		</form>
	</div>
</body>
</html>