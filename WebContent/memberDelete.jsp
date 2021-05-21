<%@page import="com.dev.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberDelte.jsp</title>
</head>
<body>
	<form action="memberSearch.do" method="post">
		ID: <input type="text" name="id"><br>
		<input type="hidden" name="job" value="delete">
		<input type="submit" value="조회">
	</form>
	
	<%
		MemberVO member = (MemberVO) request.getAttribute("member");
		if(member != null){
			//조회결과 반영
			%>
			<p>ID: <%=member.getId() %>/ Name: <%=member.getName()%>
				Mail:<%=member.getmail() %>
			</p>
			<form action="memberDelete.do" method="post">
				<input type="hidden" name="id" value="<%=member.getId() %>">
				<input type="submit" value="삭제"> 			
			</form>
			<% 
		}else{
			//조회결과 없음
			%>
			<p> 조회결과가 없습니다.</p>
			<%
		}
	
	%>
	
</body>
</html>