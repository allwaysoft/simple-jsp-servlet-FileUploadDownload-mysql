<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>文件上传到数据库</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form method="POST" action="FileUploadToFile" enctype="multipart/form-data" >
			<table>
        		<tr><td>Id</td>
        			<td><input type="text" name="id" /></td>
        		<tr>
        			<td>PDF</td>
           			<td><input type="file" name="file" id="file" /> </td>
        		</tr>
        		<tr>
        	<td colspan="2">
            	<input type="submit" value="Upload" name="upload" id="upload" /> </td>
        	</tr>
   	</table>
    </form>
   </body>
</html>