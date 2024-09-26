<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 50px;
        }

        .content {
            text-align: center;
            margin-top: 20px;
        }

        .content a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .content a:hover {
            background-color: #575757;
        }

        nav {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <nav>
        <%@ include file="/nav.jsp" %>
    </nav>

    <h1>메인 페이지 입니다.</h1>

    <div class="content">
        <a href="<%= root %>/movie?act=list">영화 목록 페이지</a>
    </div>
</body>
</html>
