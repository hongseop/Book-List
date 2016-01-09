<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var str = "시험용 문자열";
		var msg = "<c:out value=${msg}/>";
		var strArray = msg.split('BookVO');
		var i=0;
		var j=0;
		var fieldArray;
		
		for (i = 0; i<strArray.length; i++)
		{
			/* document.write(strArray[i] + "<br /><br />"); */ //0 만 출력된다. 이유가 뭐지? 헉... for 문 밖에서 변수를 선언하고 사용하니 0~4까지의 값이 출력되었다. 내가 아는 자바의 for 문과는 조금 다른것 같다.
			fieldArray = strArray[i].split(",");
			for(j = 0; j<fieldArray.length; j++){
				document.write(fieldArray[j] + "<br />");
			}
		}
	</script>
</body>
</html>