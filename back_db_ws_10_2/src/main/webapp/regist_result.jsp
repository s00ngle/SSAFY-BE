<%@page import="com.ssafy.ws.step2.dto.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String root = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>영화 등록 결과</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
            color: #333;
			background-color: #f4f4f9;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
        }

        h1 {
            text-align: center;
            color: #3d89ff;
            margin-bottom: 20px;
        }

        h3 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
        }

        .container {
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #717171;
            color: white;
            width: 30%;
        }

        td {
            background-color: #f9f9f9;
        }
        
        a {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 20px;
            text-decoration: none;
            background-color: #3d89ff;
            color: white;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        
        a:hover {
            background-color: #0056b3;
        }

        .button-container {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>영화 등록 결과</h1>
        <h3>등록된 영화 정보</h3>
        <table>
        	<% Movie movie = (Movie) request.getAttribute("movie"); %>
            <tr>
                <th>제목</th>
                <td><%= movie.getTitle() %></td>
            </tr>
            <tr>
                <th>감독</th>
                <td><%= movie.getDirector() %></td>
            </tr>
            <tr>
                <th>장르</th>
                <td><%= movie.getGenre() %></td>
            </tr>
            <tr>
                <th>상영 시간</th>
                <td><%= movie.getRunningTime() %> 분</td>
            </tr>
        </table>
        <div class="button-container">
            <a href="<%= root %>/main">홈으로 이동</a>
            <a href="<%= root %>/main?act=mvregist">추가 등록</a>
            <a href="<%= root %>/main?act=list">영화 목록</a>
        </div>
    </div>
</body>
</html>
