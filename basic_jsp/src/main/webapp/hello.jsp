<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = "김용순";
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        color: #333;
        text-align: center;
    }
    table {
        margin: 20px auto;
        border-collapse: collapse;
        width: 80%;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
        transition: background-color 0.3s;
    }
    th {
        background-color: #349fff;
        color: white;
    }
    td:hover {
        background-color: #f1f1f1;
    }
    h1, h3, h5 {
        color: #349fff;
    }
</style>
</head>
<body>
	<h1>Hello SSAFY</h1>
	<h3>안녕 싸피!!!</h3>
	<h5><%= name %>님 안녕하세요.</h5>
	<a href="/basic_jsp/index.jsp">홈으로</a>
	
	<table>
		<%
			for (int j = 1; j < 10; j++) { // 행: 1부터 9까지
				out.println("<tr>");
				for (int i = 2; i < 10; i++) { // 열: 2부터 9까지
					out.println("<td>" + i + " * " + j + " = " + (i * j) + "</td>");
				}
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
