<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 17/10/2015
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="../../resources/sounds/game_sound.mp3" var="gameSound"/>
<html>
<head>
    <title></title>
</head>
<body>
<audio controls autoplay><source src="${gameSound}" type="audio/wav"/></audio>
Sound
</body>
</html>
