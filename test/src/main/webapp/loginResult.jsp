<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//セッションスコープにからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記</title>
</head>
<body>
<h1>日記にログイン</h1>
<%if(loginUser !=null) { %>
   <p>ログインに成功しました</p>
   <p>ようこそ<%= loginUser.getName() %>さん</p>
   <a href="/test/Main">日記投稿・閲覧へ</a>
<% } else { %>
    <p>ログインに失敗しました</p>
    <a href="/test/">TOPへ</a>
<% } %>
</body>
</html>