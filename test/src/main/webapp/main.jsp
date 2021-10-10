<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき日記</title>
</head>
<body>
<h1>つぶやき日記メイン</h1>
<p>
<%=loginUser.getName() %>さんログイン中
</p>

</body>
</html>