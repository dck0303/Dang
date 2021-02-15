<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../../resources/css/main.css" />
<link rel="stylesheet" href="../../../../resources/css/member.css" />
<link rel="stylesheet" href="/resources/css/myPage.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
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
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/schoolpage.do">마이페이지</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/userpage.do">마이페이지</a></li></c:when>
								</c:choose>
								<li><a href="/map/map.do">유치원 찾기</a></li>
								<li><a href="/reservation/calendar.do">캘린더</a></li>
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/logout.do">로그아웃</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/logout.do">로그아웃</a></li></c:when>
								</c:choose>
								
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>








		<!-- Main -->
		<section class="user_board">
			<div id="class_board">
				<div id= "class_title">
					<h2>학급관리</h2>
				</div>
				<div id="class_form">
					<table>
						<tr>
							<td>번호</td>
							<td>아이디</td>
							<td>이름</td>
							<td>이메일</td>
							<td>핸드폰번호</td>
							<td>닉네임</td>
							<td><a>회원삭제</a></td>
						</tr>
						<c:forEach var="userMember" items="${classMemberList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${userMember.userId}</td>
								<td>${userMember.userName}</td>
								<td>${userMember.email}</td>
								<td>${userMember.phoneNumber}</td>
								<td>${userMember.nickname}</td>
								<td><a>삭제</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<input type=text id ="userId" name="userId" size="20">
					<button onclick= "userIdCheck()">추가할 회원 검색</button>
				</div>
				<div>
					<button onclick= "#">수정 완료</button>
				</div>
			</div>
	
	
		</section>
		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">

			</ul>
			<ul class="copyright">
				<li>&copy;댕댕아놀면뭐하니?</li>
			</ul>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="../../../../resources/js/jquery.min.js"></script>
	<script src="../../../../resources/js/jquery.scrollex.min.js"></script>
	<script src="../../../../resources/js/jquery.scrolly.min.js"></script>
	<script src="../../../../resources/js/browser.min.js"></script>
	<script src="../../../../resources/js/breakpoints.min.js"></script>
	<script src="../../../../resources/js/util.js"></script>
	<script src="../../../../resources/js/main.js"></script>
	<script src="../../../../resources/js/member.js"></script>
	<script type="text/javascript">
		let userIdCheck = () => {
				
			let classMemberObj = new Object();
			classMemberObj.userId = userId.value;
		
			
			let headerObj = new Headers();
			headerObj.append("content-type", "application/x-www-form-urlencoded");
			
			let url = "/school/kinderclassimpl.do"
			
			//비동기 처리해서 화면이 새로고침 되지않고 element에서만 바뀌도록 설정
			fetch(url, { //해당 url로 객체정보 포함하여 통신요청
				method: "post",
				headers: headerObj,
				body:"userinfo=" + JSON.stringify(classMemberObj)
				
			}).then(response => {
				
				return response.text();
				
			}).then((text) => {
				
				if(text == 'fail'){
					alert('찾는 아이디의 회원이 없습니다.');
				}else{
					
					let result = confirm("검색된 아이디는 <a>" + text + "</a> 입니다. 등록하시겠습니까 ?");
					
					if(result){
						let regUserObj = new Object();
						console.dir(userId.value);
						regUserObj.userId = userId.value;
						let url ="/school/withdraw.do";
						
						let headerObj = new Headers();
						headerObj.append("content-type", "application/x-www-form-urlencoded");
						
						fetch(url, {
							method:"post",
							headers:headerObj,
							body:"deleteUser="+JSON.stringify(deleteUserObj)
							
						}).then(response => {
							console.dir(response);
							if(response.ok){
								return response.text();
							}
							throw new AsyncPageError(response.text());
						}).then((text) => {
							if(text == 'success'){
								alert('회원탈퇴가 완료되었습니다.');
								location.href = "/main.do";
							
							}else{
								alert('회원탈퇴 중 오류가 발생하였습니다. 다시 시도해주세요.');
							}
						})

					}
					
				}
			});
		};	
		
			
	
	
	</script>
	

</body>
</html>