<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
  <title>Login Page</title>
  <style>
    .error {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #a94442;
      background-color: #f2dede;
      border-color: #ebccd1;
    }

    .msg {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #31708f;
      background-color: #d9edf7;
      border-color: #bce8f1;
    }

    #login-box {
      width: 300px;
      padding: 20px;
      margin: 100px auto;
      background: #fff;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border: 1px solid #000;
    }
  </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1>Spring Security Login Form (Database Authentication)</h1>

<div id="login-box">

  <h2>Login with Username and Password</h2>

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>

  <c:url value="/login" var="loginUrl"/>
  <form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
      <p>
        Invalid username and password.
      </p>
    </c:if>
    <c:if test="${param.logout != null}">
      <p>
        You have been logged out.
      </p>
    </c:if>
    <p>
      <label for="username">Username</label>
      <input type="text" id="username" name="username"/>
    </p>
    <p>
      <label for="password">Password</label>
      <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn">Log in</button>
  </form>

  &lt;%&ndash;<form name='loginForm'
        action="/login" method='POST'>

    <table>
      <tr>
        <td>User:</td>
        <td><input type='email' name='email' id='email'></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type='password' name='password' id='password'/></td>
      </tr>
      <tr>
        <td colspan='2'><input name="submit" type="submit"
                               value="submit" /></td>
      </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />

  </form>&ndash;%&gt;
</div>

</body>
</html>--%>




<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sample Form</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
      body { background-color: #eee; font: helvetica; }
      #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; }
      .green { font-weight: bold; color: green; }
      .message { margin-bottom: 10px; }
      label {width:70px; display:inline-block;}
      input { display:inline-block; margin-right: 10px; }
      form {line-height: 160%; }
      .hide { display: none; }
      .error { color: red; font-size: 0.9em; font-weight: bold; }
    </style>
</head>
<body>

<form:form action="/login" method="POST" modelAttribute="loginForm">
  <label for="emailInput">Email: </label>
  <form:input path="email" id="emailInput" />
  <form:errors path="email" cssClass="error" />
  <br/>


  <label for="passwordInput">Password: </label>
  <form:input type="password" path="password" id="passwordInput" />
  <form:errors path="password" cssClass="error" />
  <br/>

  <input type="hidden" name="${_csrf.parameterName}"
         value="${_csrf.token}" />

  <input type="submit" value="Submit" />
</form:form>
</body>
</html>
