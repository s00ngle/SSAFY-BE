<%@page import="com.ssafy.ws.step2.dto.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
String root = request.getContextPath();
List<Movie> list = (List<Movie>) request.getAttribute("movies");
int cnt = (int) request.getAttribute("cnt");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>영화 목록</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: 'Arial', sans-serif;
}

body {
	background-color: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 80%;
	max-height: 90vh; /* 최대 높이를 제한 */
	background-color: #fff;
	padding: 40px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	text-align: center;
	overflow: hidden; /* 스크롤이 table에만 적용되도록 */
}

h1 {
	font-size: 2em;
	color: #333;
	margin-bottom: 20px;
}

h3 {
	margin-bottom: 20px;
}

.table-container {
	max-height: 60vh; /* 테이블에 최대 높이를 제한 */
	overflow-y: auto; /* 높이를 넘으면 세로 스크롤바 표시 */
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #717171;
	color: white;
	text-align: center;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #e9ecef;
}

a {
	display: inline-block;
	text-decoration: none;
	padding: 10px 20px;
	background-color: #007BFF;
	color: #fff;
	border-radius: 5px;
	font-size: 1.1em;
	transition: background-color 0.3s ease;
}

a:hover {
	background-color: #0056b3;
}

footer {
	margin-top: 30px;
	font-size: 0.9em;
	color: #666;
}
</style>
</head>
<body>
	<div class="container">
		<h1>영화 목록</h1>
		<h3>등록된 영화 수 : <%= cnt %></h3>
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>제목</th>
						<th>감독</th>
						<th>장르</th>
						<th>러닝 타임</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (list != null && !list.isEmpty()) {
					%>
					<%
					for (Movie movie : list) {
					%>
					<tr>
						<td><%=movie.getTitle()%></td>
						<td><%=movie.getDirector()%></td>
						<td><%=movie.getGenre()%></td>
						<td><%=movie.getRunningTime()%> 분</td>
					</tr>
					<%
					}
					%>
					<%
					} else {
					%>
					<tr>
						<td colspan="5" style="text-align: center;">등록된 영화가 없습니다.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<a href="<%=root%>/main?act=mvregist">영화 등록하기</a>
		<footer> &copy; SSAFY </footer>
	</div>
</body>
</html>
