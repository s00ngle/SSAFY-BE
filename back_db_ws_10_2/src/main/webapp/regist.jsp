<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>영화 정보 입력</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        fieldset {
            width: 400px;
            background-color: #fff;
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        legend {
            font-size: 1.5em;
            font-weight: bold;
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }
        .input-group {
            margin-bottom: 15px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease;
        }
        .input-group input:focus {
            border-color: #007BFF;
            outline: none;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .button-group button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }
        .button-group button[type="submit"] {
            background-color: #007BFF;
            color: #fff;
        }
        .button-group button[type="submit"]:hover {
            background-color: #0056b3;
        }
        .button-group button[type="reset"] {
            background-color: #6c757d;
            color: #fff;
        }
        .button-group button[type="reset"]:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <form action="/main?act=regist" method="post">
        <fieldset>
            <legend>영화 정보 입력</legend>

            <div class="input-group">
                <label for="title">제목</label>
                <input type="text" name="title" required />
            </div>

            <div class="input-group">
                <label for="director">감독</label>
                <input type="text" name="director" required />
            </div>

            <div class="input-group">
                <label for="genre">장르</label>
                <input type="text" name="genre" required />
            </div>

            <div class="input-group">
                <label for="running-time">상영 시간</label>
                <input type="number" name="running-time" required />
            </div>

            <!-- 
             <input type="hidden" name="act" value="regist"/>            
             -->
            
            <div class="button-group">
                <button type="submit">등록</button>
                <button type="reset">초기화</button>
            </div>
        </fieldset>
    </form>
</body>
</html>
