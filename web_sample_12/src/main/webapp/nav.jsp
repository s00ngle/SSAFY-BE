<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    String root = request.getContextPath();
%>

<nav class="navbar">
    <div class="nav-left">
        <a href="<%= root %>/index.jsp">홈</a>
        <a href="<%= root %>/movie?act=list">영화 목록</a>
        <a href="<%= root %>/movie?act=create-form">영화 등록</a>
    </div>
    <div class="nav-right">
        <a href="#">로그인</a>
    </div>
</nav>

<style>
    .navbar {
        display: flex;
        justify-content: space-between;
        background-color: #333;
        padding: 10px 20px;
    }

    .nav-left a, .nav-right a {
        color: white;
        padding: 10px;
        text-decoration: none;
        font-size: 16px;
    }

    .nav-left a:hover, .nav-right a:hover {
        background-color: #575757;
        border-radius: 5px;
    }

    .nav-left, .nav-right {
        display: flex;
        align-items: center;
    }
</style>
