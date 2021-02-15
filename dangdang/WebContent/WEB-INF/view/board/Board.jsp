<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dang.board.model.service.BoardService"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="board" class="com.dang.board.model.vo.Board"
	scope="page" />
<jsp:setProperty name="board" property="title" />
<jsp:setProperty name="board" property="content" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>댕스쿨</title>
</head>
<body>
	<%
		String kgId = null;
	if (session.getAttribute("kgId") != null) {
		kgId = (String) session.getAttribute("kgId");
	}
	if (kgId == null) {
	%>
		<script>
			alert('로그인을 먼저 해주세요.')
			location.href = "userlogin.jsp"
		</script>
	<%
	} else {
		if (board.getTitle() == null || board.getContent() == null) {
	%>
			<script>
				alert('입력이 안된 사항이 있습니다.')
				history.back()
			</script>	
	<%
		} else {
			BoardService boardService = new BoardService();
			int result = boardService.addBoard(board.getTitle(), board.getKgName(), board.getContent());
			if (result == -1) {
	%>
				<script>
					alert('글쓰기에 실패했습니다.')
					history.back()
				</script>
	<%
			} else {
	%>
				<script>
					location.href = "BoardLsit1.jsp"
				</script>
	<%
			}
		}

	}
	%>


</body>
</html>