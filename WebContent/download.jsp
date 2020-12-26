<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>从数据库下载文件</title>
</head>
<body>
	<form action="DBFileDownload" method="get">
		<input type="text" name="id">
		<input type="submit" value="getfile">
		</form>
</body>
</html>