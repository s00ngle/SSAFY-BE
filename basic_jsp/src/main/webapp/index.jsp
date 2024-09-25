<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/basic_jsp/assets/css/app.css" rel="stylesheet" />
    <title>SSAFY</title>
  </head>
  <body>
  	<%@ include file="/template/header.jsp" %>
    <div class="container">
      <h2>SSAFY JSP</h2>
      <a href="/basic_jsp/hello.jsp">Hello JSP</a><br />
      <a href="/basic_jsp/lifecycle">JSP`s LifeCycle</a><br />
      <a href="/basic_jsp/parameter">Parameter Test</a><br />
    </div>
    <%@ include file="/template/footer.jsp" %>
  </body>
</html>
