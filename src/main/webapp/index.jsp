<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    session.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h3>用户登陆</h3>
<form action="${basePath }dologin.d" method="post">
    <input type="text" name="Id">
    <input type="text" name="Password">
    <button type="submit">登陆</button>
</form>
</body>
</html>