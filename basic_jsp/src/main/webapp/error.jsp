<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오류 발생</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #e74c3c;
        }
        p {
            font-size: 18px;
            margin: 20px 0;
        }
        a {
            text-decoration: none;
            color: #349fff;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        .error-container {
            background-color: white;
            border: 1px solid #e74c3c;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>오류가 발생했습니다!</h1>
        <p>죄송합니다. 요청을 처리하는 중에 오류가 발생했습니다.</p>
        <p>오류 메시지: <%= exception.getMessage() %></p>
        <p>다시 시도하시거나 <a href="/basic_jsp/index.jsp">홈으로 돌아가기</a> 해주세요.</p>
    </div>
</body>
</html>
