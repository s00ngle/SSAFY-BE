<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SecondScope</title>
</head>
<body>
	<p>페이지 속성 : <%= pageContext.getAttribute("name") %> </p>
	<p>요청 속성 : <%= request.getAttribute("name") %> </p>
	<p>세션 속성 : <%= session.getAttribute("name") %> </p>
	<p>웹 속성 : <%= application.getAttribute("name") %> </p>
	
	<a href="ThirdScope.jsp">ThirdScope</a>
</body>
</html>