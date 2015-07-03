<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="resources/css/login/loginForm.css" var="loginFormCss"/>
<spring:url value="resources/css/normalize/normalize.css" var="normalizeCss"/>
<head>
  <title><spring:message code="login.title"/></title>
  <%--<link rel="stylesheet" href="${normalizeCss}"/>--%>
  <link rel="stylesheet" href="${loginFormCss}"/>

</head>
<body>
  <%@include file="fragments/navigation.jspf" %>

  <div class="content-container">

    <br/>

    <c:if  test="${not empty param.error}">
      <div class="error">${param.error}</div>
    </c:if>

    <c:if test="${not empty param.msg}">
      <div class="msg">${param.msg}</div>
    </c:if>


    <div class="content-header">
      <h1 class="header-title"><spring:message code="login.title"/></h1></div>
    <div class="content-body">
      <form:form class="loginForm" action="/login" method="POST" modelAttribute="loginForm">
        <label for="emailInput"><spring:message code="login.email"/>:</label>
        <form:input path="email" id="emailInput" />
        <br/>
        <form:errors path="email" cssClass="error" />
        <c:if test="${not empty requestScope.requestResendEmail}">
          <br/>
          <a href="resendEmail" class="resendEmail"><spring:message code="login.link.resendemail"/></a>
        </c:if>
        <br/>


        <label for="passwordInput"><spring:message code="login.password"/>:</label>
        <form:input type="password" path="password" id="passwordInput" />
        <br/>
        <form:errors path="password" cssClass="error" />

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

        <spring:message code="login.rememberme"/> <input type="checkbox" name="remember-me" />
        <input class="submit" type="submit" value="<spring:message code="login.submit"/>" />

      </form:form>

      <div class="social-links">
        <div scope="user_likes,email" class="col-lg-4">
          <!-- Add Facebook sign in button -->
        <%--  <form action="${pageContext.request.contextPath}/auth/facebook" method="POST">
            <input type="hidden" name="scope" value="publish_stream,offline_access" />
            <button type="submit" class="btn btn-facebook"><i class="icon-facebook"></i>Sign in with fb</button>
          </form>--%>
          <a href="${pageContext.request.contextPath}/auth/facebook?scope=email"><button class="btn btn-facebook"><i class="icon-facebook"></i>Sign in with fb</button></a>

        </div>
      </div>

      <div class="quick-links">
        <ul>
          <li><a href="requestResetPassword"><spring:message code="login.link.resetpassword"/></a></li>
          <li><a href="resendEmail"><spring:message code="login.link.resendemail"/></a></li>
          <li><a href="register"><spring:message code="login.link.register"/></a></li>
        </ul>
      </div>
    </div>
  </div>
  <%-- Include footer --%>
  <%@include file="fragments/footer.jspf" %>
</body>
</html>
