<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Hello World!</h2>
 	<c:forEach items="${list}" var="user">
 		<c:out value="${user.id}"/>
 		<c:out value="${user.userName}"/>
 		<c:out value="${user.password}"/>
 		<c:out value="${user.age}"/>
 	</c:forEach>
</body>
</html>
