<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑用户</title>
    
  </head>
  
  <body>
    <h1>编辑用户</h1>
	<form action="/edit" name="userForm" method="post">
		<input type="hidden" name="id" value="${user.id }"/>
		姓名：<input type="text" name="userName" value="${user.userName }"/>
		年龄：<input type="text" name="age" value="${user.age }"/>
		<input type="submit" value="提交" />
		 &nbsp; &nbsp; &nbsp;
        <input type="reset" value="重置"  />
	</form>
  </body>
  
</html>
