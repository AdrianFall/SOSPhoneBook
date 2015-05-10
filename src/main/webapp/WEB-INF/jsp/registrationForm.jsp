<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 10/05/2015
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
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

<form:form action="/account" method="POST" modelAttribute="registrationForm">
  <label for="usernameInput">Name: </label>
  <form:input path="username" id="usernameInput" />
  <form:errors path="username" cssClass="error" />
  <br/>

  <label for="emailInput">Email: </label>
  <form:input path="email" id="emailInput" />
  <form:errors path="email" cssClass="error" />
  <br/>

  <label for="passwordInput">Password: </label>
  <form:input path="password" id="passwordInput" />
  <form:errors path="password" cssClass="error" />
  <br/>


  <input type="submit" value="Submit" />
</form:form>
</body>
</html>
