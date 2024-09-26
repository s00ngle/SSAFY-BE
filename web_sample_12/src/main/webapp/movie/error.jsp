<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>에러 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        
        .error-container {
        	display: flex;
            flex-direction: column;
            align-items: center; /* 중앙 정렬을 위해 추가 */
        }

        h1 {
            color: #ff0000; /* 에러 메시지 강조를 위한 빨간색 */
            text-align: center;
            margin-top: 50px;
        }

        h3 {
            color: #333;
            text-align: center;
        }

        .error-message {
            display: inline-block; /* 콘텐츠에 맞게 가로 길이 조정 */
            text-align: left;
            margin: 20px;
            padding: 10px;
            border: 1px solid #ff0000; /* 에러 강조를 위한 빨간색 테두리 */
            background-color: #fff3f3; /* 연한 빨간색 배경 */
            border-radius: 5px;
            white-space: pre-wrap; /* 줄바꿈 처리 */
            word-wrap: break-word; /* 긴 단어가 줄을 넘어갈 때 처리 */
            max-width: 80%; /* 가로 길이 제한 */
        }

        .error-message p {
            margin: 0; /* 기본 마진 제거 */
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

    <h1>오류가 발생했습니다!</h1>
    <h3>에러 메시지</h3>
    <div class="error-container">
	    <div class="error-message">
	        <p><%= request.getAttribute("errorMsg") %></p>
	    </div>
	    <div class="content">
	        <a href="<%= request.getContextPath() %>/movie">영화 목록으로 돌아가기</a>
	    </div>
    </div>
</body>
</html>
