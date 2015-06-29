<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 29/06/2015
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false"%>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
<%-- Include navigation fragment --%>
<%@include file="fragments/navigation.jspf" %>
<h1>Change Password</h1>
  <form:form class="regForm" action="/register" method="POST" modelAttribute="resetPasswordConfirmForm">

    <label for="passwordInput"><spring:message code="registration.password"/>:</label>
    <form:input type="password" path="password" id="passwordInput" />
    <form:errors path="password" cssClass="error" />
    <br/>

    <label for="confirmPasswordInput"><spring:message code="registration.confirmPassword"/>:</label>
    <form:input type="password" path="confirmPassword" id="confirmPasswordInput" />
    <form:errors path="confirmPassword" cssClass="error" />
    <br/>
    <input class="submit" type="submit" value="<spring:message code="registration.submit"/>" />
  </form:form>
</body>
</html>
