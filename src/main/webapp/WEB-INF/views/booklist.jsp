<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
 .hc { width:600px; left:0; right:0; margin-left:auto; margin-right:auto; }
</style>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.0.min.js" charset="UTF-8"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Book List</h1>
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" 
			data-toggle="dropdown" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	
		<input type="text" name="query" id="query" accesskey="s" title="검색어"
			class="keyword" value=""> <input type="button" id="search"
			name="search" alt="검색" value="검색" />
	</div>
	<div id="ajaxList"></div>
	
	
</body>
<script type="text/javascript">
	$(document).ready(function() { // DOM 객체를 모두 등록하고 나서 JavaScript 를 진행한다.
		var page = 1;
		var query = null;
		callList(query, page); // 리스트를 불러온다.
	});

	function clickPageButton(btn) {
		var page = (btn.value);
		$('#list').remove();
		callList($('#query').val(), page);
	}

	function clickPrevPageButton(page) {
		page -= 1;
		if(page<=0){
			alert("맨 앞 페이지 입니다.");
			page = 1; 
		}
		$('#list').remove();
		callList($('#query').val(), page);
	}
	
	function clickPrevListButton(page) {
		page -= 10;
		if(page<=0){
			alert("맨 앞 페이지 입니다.");
			page = 1; 
		}
		$('#list').remove();
		callList($('#query').val(), page);
	}


	function clickNextPageButton(page, maximunPage) {
		page += 1;
		if(page > maximunPage ){
			alert("마지막 페이지 입니다.");
			page = maximunPage;
		}
		$('#list').remove();
		callList($('#query').val(), page);
	}
	
	function clickNextListButton(page, maximunPage) {
		page += 10;
		console.log(page);
		console.log(maximunPage);
		if(page > maximunPage ){
			alert("마지막 페이지 입니다.");
			page = maximunPage;
		}
		$('#list').remove();
		callList($('#query').val(), page);
	}

	$('#search').click(function() { // 검색 버튼이 눌린 경우의 화면 전환
		if ($('#query').val() == '') {// 버튼이 눌렸을 때 검색어가 없으면 경고문을 띄운다.
			alert('검색어를 입력해 주세요');
			$('#query').focus();
		} else { // 버튼이 눌렸을 때 검색어가 있으면 새로운 창을 띄운다.
			$('#list').remove(); // 기존의 화면 지우
			callList($('#query').val()); // 새로운 화면을 띄우고 검색어를 전송한다.
		}
	});

	function callList(query, page) {// 컨트롤러에 검색어와 페이지 데이터를 받는다.
		$
				.ajax({
					url : "/jsonBookTitleList",
					dataType : 'json',
					type : 'POST',
					data : {
						'query' : query,
						'page' : page
					},
					success : function(result) {// 여기에 필요한 데이터가 전부 들어있다.

						var list = result.list;
						var page = result.page;
						var totalCnt = result.totalCnt;
						var pageCount = 0;
						var listCount = 0;
						var content = "<div class='table-responsive'>"
						content += "<table  class='table' id = 'list' >";
						var maximunPage = parseInt(totalCnt/10+1);
						var startPage = parseInt((page-1)/10)*10;
						
						$.each(list, function(key, value) {

							var resp = list[key];

							if (parseInt(key / 10) == page - 1) {
								content += "<tr>";
								content += "<td>"
										+ "<img src =" + resp.image +"/>"
										+ "</td>";
								content += "<td>" + resp.id + "</td>";
								content += "<td>" + resp.title + "</td>";
								content += "<td>" + resp.author + "</td>";
								content += "<td>" + resp.price + "</td>";
								content += "</tr>";
								pageCount++;

								return (pageCount != 10);
							}
						});
						content += "<table>";
						content += "</div>";
						content += "<div class='hc'>"
						content += "<button type='button' id='prev' class='btn btn-default' onclick = 'clickPrevListButton("+ page + ")'><<</button>";
						content += "<button type='button' id='prev' class='btn btn-default' onclick = 'clickPrevPageButton("+ page + ")'><</button>";

						for (var i = startPage+1 ; i <= startPage+10 ;  i++) {
							
							if(i == page){
								content += "<button type='button' id='page"+ i + "' value = '"	+ i + "' class='btn rimary btn-lg active' onclick = 'clickPageButton(this)'>" + i + "</button>";
							}else{
								content += "<button type='button' id='page"+ i + "' value = '"	+ i + "' class='btn btn-default' onclick = 'clickPageButton(this)'>" + i + "</button>";
							}
							
						}

						content += "<button type='button' id='prev' class='btn btn-default' onclick = 'clickNextPageButton("+ page + ","+ maximunPage +")'>></button>";
						content += "<button type='button' id='prev' class='btn btn-default' onclick = 'clickNextListButton("+ page + ","+ maximunPage +")'>>></button>";
						content += "</div>"
						$("#ajaxList").html(content);

						/* 	$(this).click(function() { // 페이징 버튼이 눌린 경우의 화면 전환
								console.log("pageclick");
								page = $('#page1').val(); */

					}
				});
	}
</script>
</html>
