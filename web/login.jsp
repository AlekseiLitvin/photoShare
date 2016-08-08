<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsu.epamlab.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
    <form action="login" method="post">
        <div class="block">
            <label>Login</label>
            <input type="text" name="<%=Constants.LOGIN%>" required ><br>
        </div>
        <div class="block">
            <label>Password</label>
            <input type="password" name="<%=Constants.PASSWORD%>" required ><br>
            <input style="margin-left: 114px" type="submit" value="Submit">
            <a href="<c:url value="/registration.jsp"/> ">Registration</a>
            <a href="<c:url value="/mainpage"/> ">Main page</a><br>
        </div>
    </form>

    <%@include file="templates/error.jsp"%>

    <%@include file="templates/footer.jsp"%>
</body>
</html>
