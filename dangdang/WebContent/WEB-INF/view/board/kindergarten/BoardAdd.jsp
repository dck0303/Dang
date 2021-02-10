<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<%@ page import="com.dang.board.model.service.BoardService" %>
<%@ page import="com.dang.member.school.model.vo.SchoolMember" %>
<jsp:useBean id="board" class="com.dang.board.model.vo.Board" scope="page" />
<jsp:setProperty name="board" property="title" />
<jsp:setProperty name="board" property="content" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 게시판 글쓰기 페이지 (업주)</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${context}/resources/css/main.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${context}/resources/css/board.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<h1>
				<div id="dangmark"></div>
				<a href="/main.do" id="headermain" class="mainfont">댕댕아 놀면 뭐하니?</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="/main.do">Home</a></li>
								<li><a href="generic.html">마이페이지</a></li>
								<li><a href="/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>
		





		<%
			// BoardService 호출
			BoardService boardService = new BoardService();
		%>


		<!-- Main -->
		<section class="board">
			<div class="content">
				<h2 id="tit" class="mainfont">알림장</h2>
				<br>
				<form action="listboard1.do" method="post" enctype="multipart/form-data">
					<div class="addBoard-wrap">
						<table class="addBoard">
							<thead class="addBoard-head">
								<tr>
									<th colspan="2" class="addBoard-top">알림장 작성</th>
								</tr>
							</thead>
							<tbody class="addBoard-body">
								<tr>
									<td><input type="text" class="addBoard-title"
										placeholder="제목을 적어주세요." name="boardTitle" maxlength="50" required="required"></td>
								</tr>
								<tr>
									<td><textarea class="addBoard-content"
											placeholder="내용을 적어주세요." name="boardContent" maxlength="2000" required="required"></textarea>
								</tr>
							</tbody>
						</table>
					</div>
					<input type="submit" class="btn-addBoard" value="작성 완료">
					<%		
							int result = boardService.addBoard(board.getTitle(), board.getKgName(), board.getContent());
							if(result == -1){
					%>
								<script>
									alert('글쓰기에 실패했습니다.')
									history.back();
								</script>
						<%
							}else{
						%>
								<script>
									location.href = BoardLsit1.jsp
								</script>
						<%
							}
						%>
					
				</form> 
			</div>

		</section>




		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">

			</ul>
			<ul class="copyright">
				<li>&copy; Untitled</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/browser.min.js"></script>
	<script src="resources/js/breakpoints.min.js"></script>
	<script src="resources/js/util.js"></script>
	<script src="resources/js/main.js"></script>

</body>
</html>