<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="resources/css/login/loginForm.css" var="loginFormCss"/>
<p>
<head>
  <title>Sample Form</title>
  <link rel="stylesheet" href="${loginFormCss}"/>

</head>
<body>
  <%@include file="fragments/navigation.jspf" %>
  <%-- Errors --%>

  <c:if  test="${not empty param.error}">
    <div class="error">${param.error}</div>
  </c:if>

  <c:if test="${not empty param.msg}">
    <div class="msg">${param.msg}</div>
  </c:if>

  <form:form class="loginForm" action="/login" method="POST" modelAttribute="loginForm">
    <label for="emailInput"><spring:message code="login.email"/>:</label>
    <form:input path="email" id="emailInput" />
    <br/>
    <form:errors path="email" cssClass="error" />
    <br/>


    <label for="passwordInput"><spring:message code="login.password"/>:</label>
    <form:input type="password" path="password" id="passwordInput" />
    <br/>
    <form:errors path="password" cssClass="error" />
    <br/>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />

    <input class="submit" type="submit" value="<spring:message code="login.submit"/>" />
  </form:form>
</body>
</html>
