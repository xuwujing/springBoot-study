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
    <title>用户列表</title>
    <h1>用户列表</h1>
  </head>
  
  <body>
	<table border="1">
		<tbody>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>密码</th>
				<th>年龄</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
			<c:if test="${!empty users}">
				<c:forEach items="${ users }" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.password}</td>
						<td>${user.age}</td>
						<td><a href="<%=basePath%>user/toEdit?id=${user.id}">编辑</a></td>
						<td><a href="<%=basePath%>user/toDelete?id=${user.id}">删除</a></td>
					</tr>				
				</c:forEach>
			</c:if>
		</tbody>
		 <h6><a href="<%=basePath%>user/toAdd">添加用户</a></h6>
	</table>
  </body>
</html>
