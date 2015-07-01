<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 25/06/2015
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/logout" var="logoutUrl" />
<html>
<head>
  <title>Main</title>
</head>
<body>
Main Page.
<br>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
