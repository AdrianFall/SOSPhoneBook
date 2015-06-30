<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 09/05/2015
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="resources/css/index/index.css" var="indexCss"/>
<!DOCTYPE html>
<head>
  <title>Template</title>
  <link rel="stylesheet" href="${indexCss}"/>
</head>
<body>

  <%-- Include navigation fragment --%>
  <%@include file="fragments/navigation.jspf" %>
  <div class="content-container">
    <div class="content-body">
      <h1 class="template-header">Template</h1>
    </div>
  </div>

  <%-- Include footer --%>
  <%@include file="fragments/footer.jspf" %>
</body>
</html>
