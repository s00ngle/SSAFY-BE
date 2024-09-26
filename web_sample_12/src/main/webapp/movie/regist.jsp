<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록 페이지</title>
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
        max-width: 600px;
        margin: 30px auto;  /* 상하 여백을 30px로 설정 */
        background-color: white;
        padding: 30px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
    }

    fieldset {
        border: none;
        padding: 0;
    }

    label {
        display: block;
        margin-bottom: 15px;
        font-size: 16px;
        color: #333;
    }

    input[type="text"], input[type="number"], select {
        width: 100%;
        padding: 10px;
        margin-top: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    input[type="submit"] {
        display: block;
        width: 100%;
        padding: 12px;
        background-color: #333;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 18px;
        cursor: pointer;
        margin-top: 20px;
    }

    input[type="submit"]:hover {
        background-color: #575757;
    }

    .back-link {
        display: block;
        text-align: center;
        margin-top: 20px;
        color: #333;
        text-decoration: none;
        font-size: 16px;
    }

    .back-link:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <nav>
        <%@ include file="/nav.jsp" %>
    </nav>

    <div class="container">
        <h1>영화 등록 페이지</h1>
        <form action="<%= root %>/movie" method="POST">
            <fieldset>
                <input type="hidden" name="act" value="create"/>
                <label> 영화 코드
                    <input type="text" name="code" required>
                </label> 
                <label> 영화 제목
                    <input type="text" name="title" required>
                </label> 
                <label> 영화 시간 (분)
                    <input type="number" name="time" required>
                </label> 
                <label> 영화 감독
                    <input type="text" name="director" required>
                </label> 
                <label> 영화 장르
                    <select name="genre" required>
                        <option value="판타지">판타지</option>
                        <option value="액션">액션</option>
                        <option value="로맨스">로맨스</option>
                    </select>
                </label>
                <input type="submit" value="등록">
                <a href="<%= root %>/movie?act=list" class="back-link">목록으로</a>
            </fieldset>
        </form>
    </div>
</body>
</html>
