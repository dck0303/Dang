<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dang.board.model.service.BoardService"%>
<%@ page import="com.dang.board.model.vo.Board" %>
<%@ page import="com.dang.member.school.model.vo.SchoolMember" %>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
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
	} 
	
	int bdIdx = 0;
	if(request.getParameter("bdIdx") != null){
		bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
	}
	if(bdIdx == 0){
%>
		<script>
			alert('등록되지 않은 글입니다.')
			location.href = "BoardList1.jsp"
		</script>
<%
	}
	Board board = new BoardService().viewBoard(bdIdx);
	if(!kgId.equals(board.getKgName())){
%>
		<script>
			alert('글을 수정할 권한이 없습니다.')
			location.href = "BoardList1.jsp"
		</script>
<% 
	} else {
		if (request.getParameter("boardTitle") == null || request.getParameter("boardContent") == null
				|| request.getParameter("boardTitle").equals("") || request.getParameter("boardContent").equals("")) {
	%>
			<script>
				alert('입력이 안된 사항이 있습니다.')
				history.back()
			</script>	
	<%
		} else {
			BoardService boardService = new BoardService();
			int result = boardService.modifyBoard(bdIdx, request.getParameter("boardTitle"), request.getParameter("boardContent"));
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