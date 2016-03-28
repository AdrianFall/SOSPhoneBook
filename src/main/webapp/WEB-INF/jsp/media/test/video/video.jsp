<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 17/10/2015
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="../../resources/videos/twitch_dog.flv" var="video"/>
<html>
<head>
    <title>Video Test</title>
  <link href="http://vjs.zencdn.net/c/video-js.css" rel="stylesheet">
  <script src="http://vjs.zencdn.net/c/video.js"></script>
</head>
<body>
<video id="video1" class="video-js vjs-default-skin" width="640" height="480"
       data-setup='{"controls" : true, "autoplay" : true, "preload" : "auto"}'>
  <source src="${video}" type="video/x-flv">
</video>

<%--<video controls autoplay><source src="${video}" type="video/x-flv"/></video>Video--%>
</body>
</html>
