<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Diary,java.util.List" %>
<%

//セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");

//アプリケーションスコープに保存されたリストを取得
List<Diary>diaryList =
  (List<Diary>) request.getAttribute("diaryList");

//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記</title>
</head>
<body>
<h1>日記メイン</h1>
<p>
<%=loginUser.getName() %>さんログイン中
<a href="/test/Logout">ログアウト</a>
</p>

<p><a href="/test/Main">更新</a></p>
<form action="/test/Main"method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
<% for(Diary diary : diaryList) { %>
<p><%= diary.getUserName() %>:<%= diary.getText() %></p>
<% } %>
</body>
</html>