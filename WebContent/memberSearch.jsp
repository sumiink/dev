<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberSerach.jsp</title>
</head>
<body>
	<h3>회원정보검색</h3>
	<form action="memberSearch.do" method="post">
		ID:<input type="text" name="id"><br>
		<input type="hidden" name="job" value="search">
		<input type="submit" value="조회">
	</form>
</body>
</html>