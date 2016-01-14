<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.0.min.js" charset="UTF-8"></script>


</head>
<body>
	<div>
		<input type="text" name="query" id="query" accesskey="s" title="검색어"
			class="keyword" value=""> <input type="button" id="search"
			name="search" alt="검색" value="검색" />
	</div>
	<div id="ajaxList"></div>
</body>
<script type="text/javascript">

$(document).ready(function() {
	$.ajax({
		type : "POST",
		dataType : "json",
		data : "",
		url : '/jsonBooklist',
		success : function(result) {
			
			var content = "<table border = 1 class = 'oldlist' >";
		
			$.each(result, function(key){
				
				var list = result[key];
				
				content += "<tr>";
				content += "<td>" + list.id + "</td>";
				content += "<td>" + list.title + "</td>";
				content += "<td>" + list.author + "</td>";
				content += "<td>" + list.price + "</td>";
				content += "</tr>";
				
			});
			
			content += "<table>";
			
			$("#ajaxList").html(content);
		 }
		});
	
});
	

	$('#search').click(function() {
		if ($('#query').val() == '') {
			alert('검색어를 입력해 주세요');
			$('#query').focus();
		} else {
			$('.oldlist').remove();
			callNewList($('#query').val());
			$('#name').val("");

		}
	});

	  function callNewList(query) {
	$.ajax({
		url : "/jsonBookTitleList",
		dataType : 'json',
		type : 'POST',
		data : {
			'query' : query
		},
		success : function(result) {
			
			var content = "<table border = 1 class = 'newlist' >";

			$.each(result, function(key) {



				var list = result[key];

				console.log(JSON.stringify(list));

				content += "<tr>";
				content += "<td>" + list.id + "</td>";
				content += "<td>" + list.title + "</td>";
				content += "<td>" + list.author + "</td>";
				content += "<td>" + list.price + "</td>";
				content += "</tr></table>";

			});

			content += "</table>";

			$("#ajaxList").html(content);
			

			}
		});
	} 
	
</script>


</html>
