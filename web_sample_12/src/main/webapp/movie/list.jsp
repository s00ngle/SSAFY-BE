<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 목록</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
    }
    h1 {
        text-align: center;
        margin-top: 30px;
        color: #333;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    table, th, td {
        border: 1px solid #ddd;
    }

    th, td {
        padding: 12px;
        text-align: center;
    }

    th {
        background-color: #333;
        color: white;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    .btn {
        display: inline-block;
        padding: 10px 20px;
        margin: 20px 0;
        background-color: #333;
        color: white;
        text-decoration: none;
        border-radius: 5px;
    }

    .btn:hover {
        background-color: #575757;
    }
</style>
</head>
<body>
	<nav>
        <%@ include file="/nav.jsp" %>
    </nav>

    <div class="container">
        <h1>영화 목록</h1>
        <table>
            <thead>
                <tr>
                    <th>코드</th>
                    <th>제목</th>
                    <th>감독</th>
                    <th>시간</th>
                    <th>장르</th>
                    <th>상세보기</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="movie" items="${movieList}">
                    <tr>
                        <td>${movie.code}</td>
                        <td>${movie.title}</td>
                        <td>${movie.director}</td>
                        <td>${movie.time} 분</td>
                        <td>${movie.genre}</td>
                        <td><a href="<%= root %>/movie?act=detail&code=${movie.code}">상세보기</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="<%= root %>/movie?act=create-form" class="btn">새 영화 등록</a>
    </div>
</body>
</html>
